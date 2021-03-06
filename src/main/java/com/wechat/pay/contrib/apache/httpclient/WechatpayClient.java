package com.wechat.pay.contrib.apache.httpclient;

import cn.hutool.core.io.FileUtil;
import com.wechat.pay.contrib.apache.httpclient.annotation.Log;
import com.wechat.pay.contrib.apache.httpclient.auth.*;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.ResponseDTO;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.UploadResultDTO;
import com.wechat.pay.contrib.apache.httpclient.exception.WechatpayException;
import com.wechat.pay.contrib.apache.httpclient.util.JsonUtil;
import com.wechat.pay.contrib.apache.httpclient.util.UrlUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

/**
 * 微信支付客户端
 *
 * @author lincl
 */
@Slf4j
@Data
@Component
public class WechatpayClient {

    /**
     * 请求客户端
     */
    private CloseableHttpClient httpClient;

    /**
     * 签名验证器
     */
    private Verifier verifier;

    /**
     * 构造方法
     *
     * @throws IOException
     */
    public WechatpayClient() throws IOException {
        //使用自动更新的签名验证器，不需要传入证书
        PrivateKeySigner privateKeySigner = new PrivateKeySigner(WechatpayConfig.MERCHANT_CERT_ID, WechatpayConfig.PRIVATE_KEY);
        WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(WechatpayConfig.MERCHANT_ID, privateKeySigner);
        verifier = new AutoUpdateCertificatesVerifier(wechatPay2Credentials, WechatpayConfig.API_V3_KEY.getBytes("utf-8"));
        // 请求客户端
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(WechatpayConfig.MERCHANT_ID, WechatpayConfig.MERCHANT_CERT_ID, WechatpayConfig.PRIVATE_KEY)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
    }

    /**
     * 发送get请求
     *
     * @param wechatpayAPI
     * @param params
     * @return
     */
    @Log
    public String doGet(WechatpayAPI wechatpayAPI, Map<String, Object> params) {
        try {
            if (!HttpMethod.GET.equals(wechatpayAPI.getHttpMethod())) {
                throw new WechatpayException(wechatpayAPI.getName() + "API请使用doPost方法请求");
            }
            // 创建请求
            String url = UrlUtil.getRealUrl(WechatpayConfig.API_BASE_URL + wechatpayAPI.getUrl(), params);
            URIBuilder uriBuilder = new URIBuilder(url);
            // 设置请求参数
            if (params != null) {
                Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> item = it.next();
                    if (item.getValue() != null) {
                        uriBuilder.setParameter(item.getKey(), item.getValue().toString());
                    }
                }
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Accept", "application/json");
            // 请求
            return this.execute(httpGet);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new WechatpayException(wechatpayAPI.getName() + "API请求报错");
        }
    }

    /**
     * 发送post请求
     *
     * @param wechatpayAPI
     * @param params，对象或map
     * @return
     */
    @Log
    public String doPost(WechatpayAPI wechatpayAPI, Object params) {
        // 创建请求
        String url = UrlUtil.getRealUrl(WechatpayConfig.API_BASE_URL + wechatpayAPI.getUrl(), params);
        String json = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置请求参数
        if (params != null) {
            if (params instanceof Map) {
                throw new WechatpayException("post请求参数不允许定义为Map");
            }
            json = JsonUtil.toSnakeJson(params);
            StringEntity reqEntity = new StringEntity(json, ContentType.create("application/json", "utf-8"));
            httpPost.setEntity(reqEntity);
        }
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Wechatpay-Serial", WechatpayConfig.CERTIFICATE_ID);
        // 请求
        return this.execute(httpPost);

    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Log
    public String doUpload(WechatpayAPI wechatpayAPI, File file) throws IOException {
        // 文件名
        String filename = file.getName();
        // 文件类型
        String suffix = FileUtil.getType(file);
        // SHA256
        FileInputStream fileInputStream = new FileInputStream(file);
        String sha256 = DigestUtils.sha256Hex(fileInputStream);
        fileInputStream.close();
        // 待签名body
        String meta = "{\"filename\":\"" + filename + "\",\"sha256\":\"" + sha256 + "\"}";
        // 创建请求
        String url = UrlUtil.getRealUrl(WechatpayConfig.API_BASE_URL + wechatpayAPI.getUrl(), null);
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-Type", "multipart/form-data;boundary=boundary");
        httpPost.addHeader("Sign-Body", meta);
        //创建MultipartEntityBuilder
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
        //设置boundary
        multipartEntityBuilder.setBoundary("boundary");
        multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
        //设置meta内容
        multipartEntityBuilder.addTextBody("meta", meta, ContentType.APPLICATION_JSON);
        //设置文件内容
        if (wechatpayAPI.equals(WechatpayAPI.MEDIA_IMAGE_UPLOAD)) {
            multipartEntityBuilder.addBinaryBody("file", file, ContentType.create("image/" + suffix), filename);
        } else if (wechatpayAPI.equals(WechatpayAPI.MEDIA_VIDEO_UPLOAD)) {
            multipartEntityBuilder.addBinaryBody("file", file, ContentType.create("video/" + suffix), filename);
        }
        //放入内容
        httpPost.setEntity(multipartEntityBuilder.build());
        // 请求
        return this.execute(httpPost);
    }

    /**
     * 执行请求
     *
     * @param httpUriRequest
     * @return
     */
    public String execute(HttpUriRequest httpUriRequest) {
        CloseableHttpResponse response = null;
        String content = null;
        ResponseDTO responseDTO = null;
        try {
            response = httpClient.execute(httpUriRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
            if (HttpStatus.SC_OK == statusCode) {
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity);
            } else {
                log.error("请求错误！返回结果：" + content);
                responseDTO = JsonUtil.fromSnakeJson(content, ResponseDTO.class);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new WechatpayException("请求微信支付失败！错误信息：" + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {

                }
            }
        }
        if (responseDTO != null) {
            throw new WechatpayException("请求微信支付失败！错误码：" + responseDTO.getCode() + "，错误信息：" + responseDTO.getMessage());
        }
        return content;
    }

    /**
     * 通知回调验证签名
     *
     * @param request
     * @return
     */
    public boolean verify(HttpServletRequest request) throws UnsupportedEncodingException {
        String wechatpayTimestamp = request.getHeader("Wechatpay-Timestamp");
        String wechatpayNonce = request.getHeader("Wechatpay-Nonce");
        String wechatpaySignature = request.getHeader("Wechatpay-Signature");
        String message = wechatpayTimestamp + "\n" + wechatpayNonce + "\n" + wechatpaySignature;
        return verifier.verify(WechatpayConfig.MERCHANT_CERT_ID, message.getBytes("utf-8"), wechatpaySignature);
    }

}
