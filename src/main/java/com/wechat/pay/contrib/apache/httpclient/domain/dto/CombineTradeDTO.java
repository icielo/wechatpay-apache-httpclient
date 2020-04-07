package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 合单下单
 *
 * @author lincl
 */
@Data
public class CombineTradeDTO {
    /**
     * 合单商户appid
     * 合单发起方的appid。
     * 示例值：wxd678efh567hg6787
     */
    private String combineAppid;
    /**
     * 合单商户号
     * 合单发起方商户号。
     * 示例值：1900000109
     */
    private String combineMchid;
    /**
     * 合单商户订单号
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一 。
     * 示例值：P20150806125346
     */
    private String combineOutTradeNo;
    /**
     * 支付场景信息描述
     */
    private SceneInfoDTO sceneInfo;
    /**
     * 子单信息
     * 最多支持子单条数：50
     */
    private List<SubOrdersDTO> subOrders;
    /**
     * 支付者信息
     */
    private CombinePayerInfoDTO combinePayerInfo;
    /**
     * 交易起始时间
     */
    private String timeStart;
    /**
     * 交易结束时间
     */
    private String timeExpire;
    /**
     * 通知地址
     */
    private String notifyUrl;
    /**
     * 指定支付方式
     */
    private String limitPay;
}
