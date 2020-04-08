package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 退款金额信息
 *
 * @author Administrator
 */
@Data
public class NoticeRefundAmountDTO {
    /**
     * 订单金额
     * 必填：是
     * 订单总金额，单位为分，只能为整数，详见支付金额
     * 示例值：999
     */
    private Long total;
    /**
     * 退款金额
     * 必填：是
     * 退款金额，币种的最小单位，只能为整数，不能超过原订单支付金额，如果有使用券，后台会按比例退。
     * 示例值：999
     */
    private Long refund;
    /**
     * 用户支付金额
     * 必填：是
     * 用户实际支付金额，单位为分，只能为整数，详见支付金额
     * 示例值：999
     */
    private Long payerTotal;
    /**
     * 用户退款金额
     * 必填：是
     * 退款给用户的金额，不包含所有优惠券金额
     * 示例值：999
     */
    private Long payerRefund;
}
