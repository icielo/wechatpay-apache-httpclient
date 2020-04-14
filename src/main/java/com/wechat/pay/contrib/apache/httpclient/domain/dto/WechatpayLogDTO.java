package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 微信支付API日志
 *
 * @author lincl
 * @date 2020-04-13
 */
@Data
public class WechatpayLogDTO {

    /**
     * 微信支付API日志ID
     */
    private String wechatpayLogId;

    /**
     * API名称
     */
    private String name;

    /**
     * API地址
     */
    private String url;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 请求密文
     */
    private String requestCiphertext;

    /**
     * 请求报文
     */
    private String requestText;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 响应密文
     */
    private String responseCiphertext;

    /**
     * 响应报文
     */
    private String responseText;

    /**
     * 请求时间
     */
    private LocalDateTime requestTime;

    /**
     * 请求IP
     */
    private String requestIp;

    /**
     * 请求耗时
     */
    private Long costTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 异常详细
     */
    private String exceptionDetail;
}