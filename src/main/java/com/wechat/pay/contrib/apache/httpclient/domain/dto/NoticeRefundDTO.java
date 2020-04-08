package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 退款成功通知参数
 *
 * @author Administrator
 */
@Data
public class NoticeRefundDTO {
    /**
     * 电商平台商户号
     * 必填：是
     * 微信支付分配给电商平台的商户号
     * 示例值：1900000100
     */
    private String spMchid;
    /**
     * 二级商户号
     * 必填：是
     * 微信支付分配给二级商户的商户号
     * 示例值：1900000109
     */
    private String subMchid;
    /**
     * 商户订单号
     * 必填：是
     * 返回的商户订单号
     * 示例值： 1217752501201407033233368018
     */
    private String outTradeNo;
    /**
     * 微信订单号
     * 必填：是
     * 微信支付订单号
     * 示例值： 1217752501201407033233368018
     */
    private String transactionId;
    /**
     * 商户退款单号
     * 必填：是
     * 商户退款单号
     * 示例值： 1217752501201407033233368018
     */
    private String outRefundNo;
    /**
     * 微信退款单号
     * 必填：是
     * 微信退款单号
     * 示例值： 1217752501201407033233368018
     */
    private String refundId;
    /**
     * 退款状态
     * 必填：是
     * 退款状态，枚举值：
     * SUCCESS：退款成功
     * CLOSE：退款关闭
     * ABNORMAL：退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往【服务商平台—>交易中心】，手动处理此笔退款
     * 示例值：SUCCESS
     */
    private String refundStatus;
    /**
     * 退款成功时间
     * 必填：否
     * YYYY-MM-DDTHH:mm:ss+TIMEZONE
     */
    private String successTime;
    /**
     * 退款入账账户
     * 必填：是
     * 取当前退款单的退款入账方。
     * 退回银行卡：{银行名称}{卡类型}{卡尾号}
     * 退回支付用户零钱: 支付用户零钱
     * 退还商户: 商户基本账户、商户结算银行账户
     * 退回支付用户零钱通：支付用户零钱通
     * 示例值：招商银行信用卡0403
     */
    private String userReceivedAccount;
    /**
     * -金额信息
     * 必填：是
     */
    private NoticeRefundAmountDTO amount;
}
