package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 合单关闭订单
 *
 * @author Administrator
 */
@Data
public class CombineTradeCloseDTO {
    /**
     * 合单商户appid
     * 必填：是
     * 合单发起方的appid。
     * 示例值：wxd678efh567hg6787
     */
    private String combineAppid;
    /**
     * 合单商户订单号
     * 必填：是
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 示例值：P20150806125346
     */
    private String combineOutTradeNo;
    /**
     * -子单信息
     * 必填：是
     */
    private List<CombineTradeCloseSubOrders> subOrders;
}
