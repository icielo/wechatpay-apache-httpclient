package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 支付场景信息描述
 *
 * @author lincl
 */
@Data
public class SceneInfoDTO {
    /**
     * 商户端设备号
     * 必填：否
     * 终端设备号（门店号或收银设备ID）。
     * 特殊规则：长度最小7个字节
     * 示例值：POS1:1
     */
    private String deviceId;
    /**
     * 用户终端IP
     * 必填：是
     * 用户端实际ip
     * 格式: ip(ipv4+ipv6)
     * 示例值：14.17.22.32
     * 【通知时，无该字段】
     */
    private String payerClientIp;
}
