package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 通知响应对象
 *
 * @author Administrator
 */
@Data
public class NoticeResponse {

    /**
     * 编码
     */
    private String code;

    /**
     * 消息
     */
    private String message;

    public NoticeResponse() {
    }

    public NoticeResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
