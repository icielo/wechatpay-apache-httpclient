package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 结算信息
 *
 * @author lincl
 */
@Data
public class SettleInfoDTO {
    /**
     * 是否指定分账
     * 是否分账，与外层profit_sharing同时存在时，以本字段为准。
     * true：是
     * false：否
     * 示例值：true
     */
    private Boolean profitSharing;
    /**
     * 补差金额
     * SettleInfo.profit_sharing为true时，该金额才生效。
     * 示例值：10
     */
    private Long subsidyAmount;
}
