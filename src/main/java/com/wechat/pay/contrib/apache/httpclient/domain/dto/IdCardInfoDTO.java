package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.wechat.pay.contrib.apache.httpclient.serializer.EncryptSerializer;
import lombok.Data;

/**
 * 经营者/法人身份证信息
 *
 * @author lincl
 */
@Data
public class IdCardInfoDTO {

    /**
     * 身份证人像面照片
     * 必填：是
     * 1、请上传经营者/法定代表人的身份证人像面照片。
     * 2、可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID。
     * 示例值：xpnFuAxhBTEO_PvWkfSCJ3zVIn001D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     */
    private String idCardCopy;

    /**
     * 身份证国徽面照片
     * 必填：是
     * 1、请上传经营者/法定代表人的身份证国徽面照片。
     * 2、可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
     * 示例值：vByf3Gjm7KE53JXvGy9tqZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     */
    private String idCardNational;

    /**
     * 身份证姓名
     * 必填：是
     * 1、请填写经营者/法定代表人对应身份证的姓名，2~30个中文字符、英文字符、符号。
     * 2、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * 示例值：pVd1HJ6v/69bDnuC4EL5Kz4jBHLiCa8MRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     * 字段加密:使用APIv3定义的方式加密
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String idCardName;

    /**
     * 身份证号码
     * 必填：是
     * 1、请填写经营者/法定代表人对应身份证的号码。
     * 2、15位数字或17位数字+1位数字|X ，该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * 示例值：zV+BEmytMNQCqQ8juwEc4P4TG5xzchG/5IL9DBd+Z0zZXkw==4
     * 特殊规则：长度最小15个字节
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String idCardNumber;

    /**
     * 身份证有效期限
     * 必填：是
     * 1、请填写身份证有效期的结束时间，注意参照示例中的格式。
     * 2、若证件有效期为长期，请填写：长期。
     * 3、证件有效期需大于60天。
     * 示例值：2026-06-06，长期
     */
    private String idCardValidTime;

}