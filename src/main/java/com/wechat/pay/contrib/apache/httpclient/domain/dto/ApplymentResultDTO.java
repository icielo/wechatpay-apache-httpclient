package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 返回参数：商户进件结果
 *
 * @author lincl
 */
@Data
public class ApplymentResultDTO {

    /**
     * 微信支付分配的申请单号
     * 必填：是
     * 示例值：2000002124775691
     */
    private String applymentId;

    /**
     * 业务申请编号
     * 必填：是
     * 服务商自定义的商户唯一编号。每个编号对应一个申请单，每个申请单审核通过后会生成一个微信支付商户号。
     * 示例值：APPLYMENT_00000000001
     */
    private String outRequestNo;
}
