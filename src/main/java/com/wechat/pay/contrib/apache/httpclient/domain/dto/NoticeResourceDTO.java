package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 通知资源数据
 *
 * @author Administrator
 */
@Data
public class NoticeResourceDTO {

    /**
     * 加密算法类型
     * 必填：是
     * 对开启结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM
     * 示例值：AEAD_AES_256_GCM
     */
    private String algorithm;
    /**
     * 加密前的对象类型
     * 必填：是
     * 加密前的对象类型，退款通知的类型为refund
     * 示例值：refund
     * 【支付通知：没有该字段】
     */
    private String originalType;
    /**
     * 数据密文
     * 必填：是
     * Base64编码后的开启/停用结果数据密文
     * 示例值：fdasfsadsadsalkja484w
     */
    private String ciphertext;
    /**
     * 附加数据
     * 必填：否
     * 附加数据
     * 示例值：fdasdsadsafcsflkja484w
     */
    private String associatedData;
    /**
     * 随机串
     * 必填：是
     * 加密使用的随机串
     * 示例值：fdasfjihihihlkja484w
     */
    private String nonce;
}
