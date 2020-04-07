package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 子单信息
 *
 * @author Administrator
 */
@Data
public class SubOrdersDTO {
    /**
     * 子单商户号
     * 子单发起方商户号，必须与发起方appid有绑定关系。
     * 示例值：1900000109
     */
    private String mchid;
    /**
     * 附加信息
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     * 示例值：深圳分店
     */
    private String attach;
    /**
     * 订单金额
     * 订单金额信息
     */
    private AmountDTO amount;
    /**
     * 子单商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 特殊规则：最小字符长度为6
     * 示例值：20150806125346
     */
    private String outTradeNo;
    /**
     * 二级商户号
     * 二级商户商户号，由微信支付生成并下发。
     * 示例值：1900000109
     */
    private String subMchid;
    /**
     * 商品详情
     * 商品详细描述（商品列表）。
     */
    private String detail;
    /**
     * 是否指定分账
     * 是否指定分账，枚举值：
     * true：是
     * false：否
     * 示例值：true
     */
    private Boolean profitSharing;
    /**
     * 商品描述
     * 商品简单描述。需传入应用市场上的APP名字-实际商品名称，例如：天天爱消除-游戏充值。
     * 示例值：腾讯充值中心-QQ会员充值
     */
    private String description;
    /**
     * 结算信息
     */
    private SettleInfoDTO settleInfo;
}
