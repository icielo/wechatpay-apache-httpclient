package com.wechat.pay.contrib.apache.httpclient.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wechat.pay.contrib.apache.httpclient.Credentials;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.WechatpayConfig;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在原有CertificatesVerifier基础上，增加自动更新证书功能
 *
 * @author lincl
 */
@Slf4j
public class AutoUpdateCertificatesVerifier implements Verifier {

    /**
     * 证书下载地址
     */
    private static final String CertDownloadPath = "https://api.mch.weixin.qq.com/v3/certificates";

    /**
     * 上次更新时间
     */
    private volatile Instant instant;

    /**
     * 证书更新间隔时间，单位为分钟
     */
    private int minutesInterval;

    private CertificatesVerifier verifier;

    private Credentials credentials;

    private byte[] apiV3Key;

    private ReentrantLock lock = new ReentrantLock();

    /**
     * 时间间隔枚举，支持一小时、六小时以及十二小时
     */
    public enum TimeInterval {
        /**
         * 一小时
         */
        OneHour(60),
        /**
         * 六个小时
         */
        SixHours(60 * 6),
        /**
         * 十二个小时
         */
        TwelveHours(60 * 12);

        private int minutes;

        TimeInterval(int minutes) {
            this.minutes = minutes;
        }

        public int getMinutes() {
            return minutes;
        }
    }

    public AutoUpdateCertificatesVerifier(Credentials credentials, byte[] apiV3Key) {
        this(credentials, apiV3Key, TimeInterval.OneHour.getMinutes());
    }

    public AutoUpdateCertificatesVerifier(Credentials credentials, byte[] apiV3Key, int minutesInterval) {
        this.credentials = credentials;
        this.apiV3Key = apiV3Key;
        this.minutesInterval = minutesInterval;
        //构造时更新证书
        try {
            autoUpdateCert();
            instant = Instant.now();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verify(String serialNumber, byte[] message, String signature) {
        if (instant == null || Duration.between(instant, Instant.now()).toMinutes() >= minutesInterval) {
            if (lock.tryLock()) {
                try {
                    autoUpdateCert();
                    //更新时间
                    instant = Instant.now();
                } catch (GeneralSecurityException | IOException e) {
                    log.warn("Auto update cert failed, exception = " + e);
                } finally {
                    lock.unlock();
                }
            }
        }
        return verifier.verify(serialNumber, message, signature);
    }

    /**
     * 自动更新
     *
     * @throws IOException
     * @throws GeneralSecurityException
     */
    private void autoUpdateCert() throws IOException, GeneralSecurityException {
        CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                .withCredentials(credentials)
                .withValidator(verifier == null ? (response) -> true : new WechatPay2Validator(verifier))
                .build();

        HttpGet httpGet = new HttpGet(CertDownloadPath);
        httpGet.addHeader("Accept", "application/json");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        String body = EntityUtils.toString(response.getEntity());
        if (statusCode == HttpStatus.SC_OK) {
            List<X509Certificate> newCertList = deserializeToCerts(apiV3Key, body);
            if (newCertList.isEmpty()) {
                log.warn("Cert list is empty");
                return;
            }
            this.verifier = new CertificatesVerifier(newCertList);
        } else {
            log.warn("Auto update cert failed, statusCode = " + statusCode + ",body = " + body);
        }
    }


    /**
     * 反序列化证书并解密
     */
    private List<X509Certificate> deserializeToCerts(byte[] apiV3Key, String body)
            throws GeneralSecurityException, IOException {
        AesUtil decryptor = new AesUtil(apiV3Key);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataNode = mapper.readTree(body).get("data");
        List<X509Certificate> newCertList = new ArrayList<>();
        if (dataNode != null) {
            for (int i = 0, count = dataNode.size(); i < count; i++) {
                JsonNode encryptCertificateNode = dataNode.get(i).get("encrypt_certificate");
                //解密
                String cert = decryptor.decryptToString(
                        encryptCertificateNode.get("associated_data").toString().replaceAll("\"", "")
                                .getBytes("utf-8"),
                        encryptCertificateNode.get("nonce").toString().replaceAll("\"", "")
                                .getBytes("utf-8"),
                        encryptCertificateNode.get("ciphertext").toString().replaceAll("\"", ""));

                X509Certificate x509Cert = PemUtil
                        .loadCertificate(new ByteArrayInputStream(cert.getBytes("utf-8")));
                try {
                    x509Cert.checkValidity();
                } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                    continue;
                }
                newCertList.add(x509Cert);

                String serialNumber = dataNode.get(i).get("serial_no").textValue();
                // 检测到新证书，更新配置。业务程序定时检测，更新到配置文件或数据库存储
                if (WechatpayConfig.CERTIFICATE == null || serialNumber.equals(WechatpayConfig.CERTIFICATE_ID)
                        && serialNumber.equals(WechatpayConfig.CERTIFICATE_ID_OLD)) {
                    WechatpayConfig.CERTIFICATE_ID_OLD = WechatpayConfig.CERTIFICATE_ID;
                    WechatpayConfig.CERTIFICATE_ID = serialNumber;
                    WechatpayConfig.CERTIFICATE_PEM = cert;
                    WechatpayConfig.CERTIFICATE = x509Cert;
                }
            }
        }
        return newCertList;
    }
}
