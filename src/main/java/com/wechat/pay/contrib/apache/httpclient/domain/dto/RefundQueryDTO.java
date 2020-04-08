package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 查询退款
 *
 * @author Administrator
 */
@Data
public class RefundQueryDTO {
    /**
     * 微信退款单号
     * 必填：是
     */
    private String refundId;
    /**
     * 二级商户号
     * 必填：是
     */
    private String subMchid;
}
