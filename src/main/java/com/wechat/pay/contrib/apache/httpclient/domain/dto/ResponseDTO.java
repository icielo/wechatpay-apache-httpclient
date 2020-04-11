package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 响应信息
 *
 * @author lincl
 */
@Data
public class ResponseDTO {

    /**
     * 状态码
     */
    private String code;

    /**
     * 信息
     */
    private String message;
}
