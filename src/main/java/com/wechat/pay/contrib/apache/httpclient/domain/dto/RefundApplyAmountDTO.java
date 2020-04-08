package com.wechat.pay.contrib.apache.httpclient.domain.dto;

/**
 * 退款申请金额
 *
 * @author lincl
 */
public class RefundApplyAmountDTO {
    /**
     * 退款金额
     * 必填：是
     */
    private Long refund;
    /**
     * 原订单金额
     * 必填：是
     */
    private Long total;
    /**
     * 退款币种
     * 必填：否
     */
    private String currency;
}
