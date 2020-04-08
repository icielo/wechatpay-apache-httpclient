package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 优惠退款详情
 *
 * @author Administrator
 */
@Data
public class PromotionDetailDTO {
    /**
     * 券ID
     * 必填：是
     */
    private String promotionId;
    /**
     * 优惠范围
     * 必填：是
     */
    private String scope;
    /**
     * 优惠类型
     * 必填：是
     */
    private String type;
    /**
     * 优惠券面额
     * 必填：是
     */
    private Long amount;
    /**
     * 优惠退款金额
     * 必填：是
     */
    private Long refundAmount;
}
