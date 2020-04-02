package com.wechat.pay.contrib.apache.httpclient;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Properties;

/**
 * 微信支付配置
 *
 * @author lincl
 */
@Slf4j
@Data
public class WechatpayConfig {

    /**
     * 构造函数
     *
     * @param apiBaseUrl
     * @param apiV3Key
     * @param merchantId
     * @param merchantCertId
     * @param merchantCertIdOld
     * @param privateKeyPem
     * @param certificatePem
     */
    public static void init(String apiBaseUrl, String apiV3Key, String merchantId, String merchantCertId, String merchantCertIdOld, String privateKeyPem, String certificatePem) throws UnsupportedEncodingException {
        WechatpayConfig.API_BASE_URL = apiBaseUrl;
        WechatpayConfig.API_V3_KEY = apiV3Key;
        WechatpayConfig.MERCHANT_ID = merchantId;
        WechatpayConfig.MERCHANT_CERT_ID = merchantCertId;
        WechatpayConfig.MERCHANT_CERT_ID_OLD = merchantCertIdOld;
        WechatpayConfig.PRIVATE_KEY_PEM = privateKeyPem;
        WechatpayConfig.PRIVATE_KEY = PemUtil.loadPrivateKey(new ByteArrayInputStream(privateKeyPem.getBytes("utf-8")));
        if (certificatePem != null && !"".equals(certificatePem.trim())) {
            WechatpayConfig.CERTIFICATE_PEM = certificatePem;
            WechatpayConfig.CERTIFICATE = PemUtil.loadCertificate(new ByteArrayInputStream(certificatePem.getBytes("utf-8")));
        }
    }

    /**
     * 构造方法
     *
     * @param path：路径下应包含config.properties、商户私钥证书
     * @throws IOException
     */
    public static void init(String path) throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            Properties properties = new Properties();
            fileReader = new FileReader(path + File.separator + "config.properties");
            bufferedReader = new BufferedReader(fileReader);
            properties.load(bufferedReader);
            bufferedReader.close();
            WechatpayConfig.API_BASE_URL = properties.getProperty("api.base.url");
            WechatpayConfig.API_V3_KEY = properties.getProperty("api.v3.key");
            WechatpayConfig.MERCHANT_ID = properties.getProperty("merchant.id");
            WechatpayConfig.MERCHANT_CERT_ID = properties.getProperty("merchant.cert.id");
            WechatpayConfig.MERCHANT_CERT_ID_OLD = properties.getProperty("merchant.cert.id.old");
            WechatpayConfig.PRIVATE_KEY = PemUtil.loadPrivateKey(new FileInputStream(path + File.separator + "apiclient_key.pem"));
            WechatpayConfig.CERTIFICATE = PemUtil.loadCertificate(new FileInputStream(path + File.separator + "certificate.pem"));
        } catch (Exception e) {
            log.error("微信支付加载错误！", e);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }

    /**
     * API基础地址
     */
    public static String API_BASE_URL;

    /**
     * apiV3Key
     */
    public static String API_V3_KEY;

    /**
     * 商户号
     */
    public static String MERCHANT_ID;

    /**
     * 商户证书序列号
     */
    public static String MERCHANT_CERT_ID;

    /**
     * 旧的商户证书序列号
     */
    public static String MERCHANT_CERT_ID_OLD;

    /**
     * 商户私钥
     */
    public static String PRIVATE_KEY_PEM;

    /**
     * 商户私钥
     */
    public static PrivateKey PRIVATE_KEY;

    /**
     * 证书
     */
    public static String CERTIFICATE_PEM;

    /**
     * 证书
     */
    public static X509Certificate CERTIFICATE;

}
