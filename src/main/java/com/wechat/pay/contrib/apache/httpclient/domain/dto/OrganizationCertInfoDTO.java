package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 组织机构代码证信息
 *
 * @author lincl
 */
@Data
public class OrganizationCertInfoDTO {

    /**
     * 组织机构代码证照片
     * 必填：是
     * 可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID。
     * 示例值：vByf3Gjm7KE53JXv\prrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     */
    private String organizationCopy;

    /**
     * 组织机构代码
     * 必填：是
     * 1、请填写组织机构代码证上的组织机构代码。
     * 2、可填写9或10位 数字|字母|连字符。
     * 示例值：12345679-A
     */
    private String organizationNumber;

    /**
     * 组织机构代码有效期限
     * 必填：是
     * 1、请填写组织机构代码证的有效期限，注意参照示例中的格式。
     * 2、若证件有效期为长期，请填写：长期。
     * 3、结束时间需大于开始时间。
     * 4、有效期必须大于60天，即结束时间距当前时间需超过60天。
     * 示例值：[\"2014-01-01\",\"长期\"]
     */
    private String organizationTime;
}