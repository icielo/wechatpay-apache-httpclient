package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 订单金额信息
 *
 * @author Administrator
 */
@Data
public class RefundApplyResultAmountDTO {
    /**
     * 退款金额
     * 必填：是
     * 退款金额，币种的最小单位，只能为整数，不能超过原订单支付金额。
     * 示例值：888
     */
    private Long refund;
    /**
     * 用户退款金额
     * 必填：是
     * 退款给用户的金额，不包含所有优惠券金额。
     * 示例值：888
     */
    private Long payerRefund;
    /**
     * 优惠退款金额
     * 必填：否
     * 优惠券的退款金额，原支付单的优惠按比例退款。
     * 示例值：888
     */
    private Long discountRefund;
    /**
     * 退款币种
     * 必填：否
     * 符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY 。
     * 示例值：CNY
     */
    private String currency;
}
