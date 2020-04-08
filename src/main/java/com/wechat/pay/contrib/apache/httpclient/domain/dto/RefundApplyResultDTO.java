package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 退款申请结果
 * @author lincl
 */
@Data
public class RefundApplyResultDTO {
    /**
     * 微信退款单号
     * 必填：是
     * 微信支付退款订单号。
     * 示例值：1217752501201407033233368018
     */
    private String refundId;
    /**
     * 商户退款单号
     * 必填：是
     * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔。
     * 示例值：1217752501201407033233368018
     */
    private String outRefundNo;
    /**
     * 退款创建时间
     * 必填：是
     * 格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
     */
    private String createTime;
    /**
     * -订单金额
     * 必填：是
     */
    private RefundApplyResultAmountDTO amount;
    /**
     * +优惠退款详情
     * 必填：否
     */
    private List<PromotionDetailDTO> promotionDetail;
}
