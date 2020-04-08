package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 支付者信息
 *
 * @author lincl
 */
@Data
public class CombinePayerInfoDTO {
    /**
     * 用户标识
     * 使用合单appid获取的对应用户openid。是用户在商户appid下的唯一标识。
     * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     */
    private String openid;
}
