package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 店铺信息
 *
 * @author lincl
 */
@Data
public class SalesSceneInfoDTO {

    /**
     * 店铺名称
     * 必填：是
     * 请填写店铺全称。
     * 示例值：爱烧烤
     */
    private String storeName;

    /**
     * 店铺链接
     * 必填：二选一
     * 1、店铺二维码or店铺链接二选一必填。
     * 2、请填写店铺主页链接，需符合网站规范。
     * 示例值：http://www.qq.com
     */
    private String storeUrl;

    /**
     * 店铺二维码
     * 必填：二选一
     * 1、店铺二维码 or 店铺链接二选一必填。
     * 2、若为电商小程序，可上传店铺页面的小程序二维码。
     * 3、请填写通过图片上传接口预先上传图片生成好的MediaID，仅能上传1张图片 。
     * 示例值：jTpGmxUX3FBWVQ5NJTZvlKX_gdU4cRz7z5NxpnFuAxhBTEO1D8daLC-ehEuo0BJqRTvDujqhThn4ReFxikqJ5YW6zFQ
     */
    private String storeQrCode;
}