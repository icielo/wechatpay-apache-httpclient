package com.wechat.pay.contrib.apache.httpclient.auth;

/**
 * 签名验证
 *
 * @author lincl
 */
public interface Verifier {

    /**
     * 验证
     *
     * @param serialNumber
     * @param message
     * @param signature
     * @return
     */
    boolean verify(String serialNumber, byte[] message, String signature);
}
