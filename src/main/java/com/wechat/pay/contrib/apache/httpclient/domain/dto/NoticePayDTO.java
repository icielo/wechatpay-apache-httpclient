package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 支付成功通知参数
 *
 * @author lincl
 */
@Data
public class NoticePayDTO {
    /**
     * 合单商户appid
     * 必填：是
     * 合单发起方的appid。（即电商平台appid）
     * 示例值：wxd678efh567hg6787
     */
    private String combineAppid;
    /**
     * 合单商户号
     * 必填：是
     * 合单发起方商户号。（即电商平台mchid）
     * 示例值：1900000109
     */
    private String combineMchid;
    /**
     * 合单商户订单号
     * 必填：是
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * 示例值：P20150806125346
     */
    private String combineOutTradeNo;
    /**
     * -场景信息
     * 必填：否
     * 支付场景信息描述
     */
    private SceneInfoDTO sceneInfo;
    /**
     * -子单信息
     * 必填：是
     * 最多支持子单条数：50
     */
    private List<NoticePaySubOrdersDTO> subOrders;
    /**
     * -支付者
     * 必填：否
     * 示例值：见请求示例
     */
    private CombinePayerInfoDTO combinePayerInfo;
}
