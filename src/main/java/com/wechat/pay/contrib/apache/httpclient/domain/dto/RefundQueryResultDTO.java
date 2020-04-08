package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 返回参数：查询退款
 *
 * @author Administrator
 */
@Data
public class RefundQueryResultDTO {
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
     * 微信订单号
     * 必填：是
     * 微信支付交易订单号。
     * 示例值： 1217752501201407033233368018
     */
    private String transactionId;
    /**
     * 商户订单号
     * 必填：是
     * 返回的原交易订单号。
     * 示例值： 1217752501201407033233368018
     */
    private String outTradeNo;
    /**
     * 退款渠道
     * 必填：否
     * ORIGINAL：原路退款
     * BALANCE：退回到余额
     * OTHER_BALANCE：原账户异常退到其他余额账户
     * OTHER_BANKCARD：原银行卡异常退到其他银行卡
     * 示例值： ORIGINAL
     */
    private String channel;
    /**
     * 退款入账账户
     * 必填：否
     * 取当前退款单的退款入账方。
     * 退回银行卡：{银行名称}{卡类型}{卡尾号}
     * 退回支付用户零钱：支付用户零钱
     * 退还商户：商户基本账户、商户结算银行账户
     * 退回支付用户零钱通：支付用户零钱通
     * 示例值： 招商银行信用卡0403
     */
    private String userReceivedAccount;
    /**
     * 退款成功时间
     * 必填：否
     * 格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
     */
    private String successTime;
    /**
     * 退款创建时间
     * 必填：是
     * 格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE
     */
    private String createTime;
    /**
     * 退款状态
     * 必填：是
     * 退款状态，枚举值：
     * SUCCESS：退款成功
     * REFUNDCLOSE：退款关闭
     * PROCESSING：退款处理中
     * ABNORMAL：退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往【服务商平台—>交易中心】，手动处理此笔退款
     * 示例值：SUCCESS
     */
    private String status;
    /**
     * +退款金额信息
     * 必填：是
     */
    private RefundAmountDTO amount;
    /**
     * +营销详情
     * 必填：否
     */
    private List<PromotionDetailDTO> promotionDetail;
}
