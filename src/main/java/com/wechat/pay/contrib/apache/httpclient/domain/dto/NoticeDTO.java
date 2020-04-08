package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 支付通知对象
 *
 * @author lincl
 */
@Data
public class NoticeDTO {
    /**
     * 通知ID
     * 必填：是
     * 通知的唯一ID
     * 示例值：EV-2018022511223320873
     */
    private String id;
    /**
     * 通知创建时间
     * 必填：是
     * 通知创建的时间，格式为yyyyMMddHHmmss或YYYY-MM-DDTHH:mm:ss+TIMEZONE
     * 示例值：20180225112233或2018-06-08T10:34:56+08:00
     */
    private String createTime;
    /**
     * 通知类型
     * 必填：是
     * 通知的类型，支付成功通知的类型为TRANSACTION.SUCCESS
     * 示例值：TRANSACTION.SUCCESS
     */
    private String eventType;
    /**
     * 通知简要说明
     * 必填：是
     * 【支付通知：没有该字段】
     */
    private String summary;
    /**
     * 通知数据类型
     * 必填：是
     * 通知的资源数据类型，支付成功通知为encrypt-resource
     * 示例值：encrypt-resource
     */
    private String resourceType;
    /**
     * 通知数据
     * 必填：是
     * 通知资源数据
     * json格式，见示例
     */
    private NoticeResourceDTO resource;
}
