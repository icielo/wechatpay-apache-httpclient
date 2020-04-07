package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 订单金额信息
 *
 * @author Administrator
 */
@Data
public class AmountDTO {
    /**
     * 标价金额
     * 子单金额，单位为分
     * 示例值：100
     */
    private Long totalAmount;
    /**
     * 标价币种
     * 符合ISO 4217标准的三位字母代码，人民币：CNY 。
     * 示例值：CNY
     */
    private String currency;
}
