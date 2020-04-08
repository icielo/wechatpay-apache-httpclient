package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 合单关闭订单-子单信息
 *
 * @author lincl
 */
@Data
public class CombineTradeCloseSubOrders {
    /**
     * 子单商户号
     * 必填：是
     * 子单发起方商户号，必须与发起方appid有绑定关系。
     * 示例值：1900000109
     */
    private String mchid;
    /**
     * 子单商户订单号
     * 必填：是
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 特殊规则：最小字符长度为6
     * 示例值：20150806125346
     */
    private String outTradeNo;
    /**
     * 二级商户号
     * 必填：是
     * 二级商户商户号，由微信支付生成并下发。
     * 示例值：1900000109
     */
    private String subMchid;
}
