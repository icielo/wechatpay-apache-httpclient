package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 返回参数：合单查询订单
 *
 * @author lincl
 */
@Data
public class CombineTradeStatusDTO {

    /**
     * 合单商户appid
     * 必填：是
     * 合单发起方的appid。
     * 示例值：wxd678efh567hg6787
     */
    private String combineAppid;
    /**
     * 合单商户号
     * 必填：是
     * 合单发起方商户号。
     * * 示例值：1900000109
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
     */
    private SceneInfoDTO sceneInfo;
    /**
     * -子单信息
     * 必填：是
     */
    private List<NoticePaySubOrdersDTO> subOrders;
    /**
     * +支付者
     * 必填：否
     */
    private CombinePayerInfoDTO combinePayerInfo;

}
