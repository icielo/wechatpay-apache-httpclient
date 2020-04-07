package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 返回参数：完结分账结果
 *
 * @author Administrator
 */
@Data
public class ProfitSharingFinishResultDTO {
    /**
     * 二级商户号
     * 必填：是
     * 分账出资的电商平台二级商户，填写微信支付分配的商户号。
     * 示例值：1900000109
     */
    private String subMchid;
    /**
     * 微信订单号
     * 必填：是
     * 微信支付订单号。
     * 示例值： 4208450740201411110007820472
     */
    private String transactionId;
    /**
     * 商户分帐单号
     * 必填：是
     * 商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。
     * 示例值：P20150806125346
     */
    private String outOrderNo;
    /**
     * 微信分帐单号
     * 必填：是
     * 微信分账单号，微信系统返回的唯一标识。
     * 示例值： 3008450740201411110007820472
     */
    private String orderId;
}
