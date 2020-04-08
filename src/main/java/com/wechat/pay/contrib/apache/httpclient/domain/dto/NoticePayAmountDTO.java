package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 订单金额信息
 *
 * @author lincl
 */
@Data
public class NoticePayAmountDTO {
    /**
     * 标价金额
     * 必填：是
     * 子单金额，单位为分。
     * 示例值：100
     */
    private String totalAmount;
    /**
     * 标价币种
     * 必填：是
     * 符合ISO 4217标准的三位字母代码，人民币：CNY。
     * 示例值：CNY
     */
    private String currency;
    /**
     * 现金支付金额
     * 必填：是
     * 订单现金支付金额。
     * 示例值：10
     */
    private String payerAmount;
    /**
     * 现金支付币种
     * 必填：是
     * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY。
     * 示例值： CNY
     */
    private String payerCurrency;
}
