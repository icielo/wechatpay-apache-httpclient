package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 经营者/法人其他类型证件信息
 *
 * @author lincl
 */
@Data
public class IdDocInfoDTO {

    /**
     * 证件姓名
     * 必填：是
     * 请填写经营者/法人姓名。
     * 示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4LC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     */
    private String idDocName;

    /**
     * 证件号码
     * 必填：是
     * 7~11位 数字|字母|连字符 。
     * 示例值：jTpGmxUX3FBWVQ5NJTZvlKX_go0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     */
    private String idDocNumber;

    /**
     * 证件照片
     * 必填：是
     * 1、可上传1张图片，请填写通过图片上传接口预先上传图片生成好的MediaID。
     * 2、2M内的彩色图片，格式可为bmp、png、jpeg、jpg或gif 。
     * 示例值：xi-vByf3Gjm7KE53JXvGy9tqZm2XAUf-4KGprrKhpVBDIUv0OF4wFNIO4kqg05InE4d2I6_H7I4
     */
    private String idDocCopy;

    /**
     * 证件结束日期
     * 必填：是
     * 1、请按照示例值填写。
     * 2、若证件有效期为长期，请填写：长期。
     * 3、证件有效期需大于60天 。
     * 示例值：2020-01-02
     */
    private String docPeriodEnd;
}