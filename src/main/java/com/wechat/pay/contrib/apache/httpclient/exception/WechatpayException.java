package com.wechat.pay.contrib.apache.httpclient.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 * 统一异常处理
 */
@Getter
public class WechatpayException extends RuntimeException {

    private Integer status = BAD_REQUEST.value();

    public WechatpayException(String msg) {
        super(msg);
    }

    public WechatpayException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }

    /**
     * 格式化消息提示，统一用中文“！”结尾
     *
     * @return
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public String getMessage() {
        String msg = super.getMessage();
        if (!msg.endsWith("！")) {
            if (msg.endsWith(("!"))) {
                msg = msg.substring(0, msg.lastIndexOf("!")) + "！";
            }
        }
        return msg;
    }

}
