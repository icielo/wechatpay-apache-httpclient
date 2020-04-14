package com.wechat.pay.contrib.apache.httpclient.service;

import com.wechat.pay.contrib.apache.httpclient.domain.dto.WechatpayLogDTO;

/**
 * API日志服务
 *
 * @author lincl
 */
public interface WechatpayLogService {

    /**
     * 保存日志
     *
     * @param wechatpayLogDTO
     */
    void save(WechatpayLogDTO wechatpayLogDTO);
}
