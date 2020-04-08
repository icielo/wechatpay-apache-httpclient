package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * @author lincl
 */
@Data
public class RefundApplyDTO {
    /**
     * 二级商户号
     * 必填：是
     * 微信支付分配二级商户的商户号。
     * 示例值： 1900000109
     */
    private String subMchid;
    /**
     * 电商平台APPID
     * 必填：是
     * 电商平台在微信公众平台申请服务号对应的APPID，申请商户功能的时候微信支付会配置绑定关系。
     * 示例值：wx8888888888888888
     */
    private String spAppid;
    /**
     * 二级商户APPID
     * 必填：否
     * 二级商户在微信申请公众号成功后分配的帐号ID，需要电商平台侧配置绑定关系才能传参。
     * 示例值：wx8888888888888888
     */
    private String subAppid;
    /**
     * 微信订单号
     * 必填：否
     * 原支付交易对应的微信订单号。
     * 示例值：1217752501201407033233368018
     */
    private String transactionId;
    /**
     * 商户订单号
     * 必填：否
     * 原支付交易对应的商户订单号。
     * 示例值：1217752501201407033233368018
     */
    private String outTradeNo;
    /**
     * 商户退款单号
     * 必填：是
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@，同一退款单号多次请求只退一笔。
     * 示例值：1217752501201407033233368018
     */
    private String outRefundNo;
    /**
     * 退款原因
     * 必填：否
     * 若商户传入，会在下发给用户的退款消息中体现退款原因。
     * 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
     * 示例值：商品已售完
     */
    private String reason;
    /**
     * -订单金额
     * 必填：是
     */
    private RefundApplyAmountDTO amount;
    /**
     * 退款结果回调url
     * 必填：否
     * 异步接收微信支付退款结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效，优先回调当前传的地址。
     * 示例值：https://weixin.qq.com
     */
    private String notifyUrl;
}
