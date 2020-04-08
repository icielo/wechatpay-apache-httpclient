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
     * 券或者立减优惠id 。
     * 示例值：109519
     */
    private String promotionId;
    /**
     * 优惠范围
     * 必填：是
     * 枚举值：
     * GLOBAL：全场代金券
     * SINGLE：单品优惠
     * 示例值：SINGLE
     */
    private String scope;
    /**
     * 优惠类型
     * 必填：是
     * 枚举值：
     * COUPON：充值型代金券，商户需要预先充值营销经费
     * DISCOUNT：免充值型优惠券，商户不需要预先充值营销经费
     * 示例值：DISCOUNT
     */
    private String type;
    /**
     * 优惠券面额
     * 必填：是
     * 用户享受优惠的金额（优惠券面额=微信出资金额+商家出资金额+其他出资方金额 ）。
     * 示例值：5
     */
    private Long amount;
    /**
     * 优惠退款金额
     * 必填：是
     * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见《代金券或立减优惠》。
     * 示例值：100
     */
    private Long refundAmount;
}
