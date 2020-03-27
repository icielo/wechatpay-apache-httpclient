package com.wechat.pay.contrib.apache.httpclient.auth;

import com.wechat.pay.contrib.apache.httpclient.Credentials;
import java.io.IOException;
import java.net.URI;
import java.security.SecureRandom;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WechatPay2Credentials implements Credentials {
  private static final Logger log = LoggerFactory.getLogger(WechatPay2Credentials.class);

  private static final String SYMBOLS =
      "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final SecureRandom RANDOM = new SecureRandom();
  protected String merchantId;
  protected Signer signer;

  public WechatPay2Credentials(String merchantId, Signer signer) {
    this.merchantId = merchantId;
    this.signer = signer;
  }

  public String getMerchantId() {
    return merchantId;
  }

  protected long generateTimestamp() {
    return System.currentTimeMillis() / 1000;
  }

  protected String generateNonceStr() {
    char[] nonceChars = new char[32];
    for (int index = 0; index < nonceChars.length; ++index) {
      nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
    }
    return new String(nonceChars);
  }

  @Override
  public final String getSchema() {
    return "WECHATPAY2-SHA256-RSA2048";
  }

  @Override
  public final String getToken(HttpUriRequest request) throws IOException {
    String nonceStr = generateNonceStr();
    long timestamp = generateTimestamp();

    String message = buildMessage(nonceStr, timestamp, request);
    log.debug("authorization message=[{}]", message);

    Signer.SignatureResult signature = signer.sign(message.getBytes("utf-8"));

    String token = "mchid=\"" + getMerchantId() + "\","
        + "nonce_str=\"" + nonceStr + "\","
        + "timestamp=\"" + timestamp + "\","
        + "serial_no=\"" + signature.certificateSerialNumber + "\","
        + "signature=\"" + signature.sign + "\"";
    log.debug("authorization token=[{}]", token);

    return token;
  }

  protected final String buildMessage(String nonce, long timestamp, HttpUriRequest request)
      throws IOException {
    URI uri = request.getURI();
    String canonicalUrl = uri.getRawPath();
    if (uri.getQuery() != null) {
      canonicalUrl += "?" + uri.getRawQuery();
    }

    String body = "";
    Header[] signBody = request.getHeaders("Sign-Body");
    if (signBody.length != 0) {
      // 在图片上传API、视频上传API中，签名的body不能简单取Entity，而是指定了签名的body，
      // 如“{"filename":" filea.jpg ","sha256":" hjkahkjsjkfsjk78687dhjahdajhk "}”
      // 这里加入自定义签名body，用于替换该签名body
      body = signBody[0].getValue();
      request.removeHeaders("Sign-Body");
    } else if (request instanceof HttpEntityEnclosingRequestBase) {
      // PATCH,POST,PUT
      body = EntityUtils.toString(((HttpEntityEnclosingRequestBase) request).getEntity());
    }

    return request.getRequestLine().getMethod() + "\n"
        + canonicalUrl + "\n"
        + timestamp + "\n"
        + nonce + "\n"
        + body + "\n";
  }

}
