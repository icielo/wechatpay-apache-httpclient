package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 上传结果
 *
 * @author Administrator
 */
@Data
public class UploadResultDTO {

    /**
     * 媒体文件标识 Id
     * 必填：是
     * 微信返回的媒体文件标识Id。
     * 示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
     */
    private String mediaId;
}
