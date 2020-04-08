package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 汇款账户验证信息
 *
 * @author lincl
 */
@Data
public class AccountValidationDTO {
    /**
     * 付款户名
     * 必填：否
     * 需商户使用该户名的账户进行汇款。
     * 示例值： rDdICA3ZYXshYqeOSslSjSMf+MhhC4oaujiISFzq3AE+as7mAEDJly+DgRuVs74msmKUH8pl+3oA==
     */
    private String accountName;
    /**
     * 付款卡号
     * 必填：否
     * 结算账户为对私时会返回，商户需使用该付款卡号进行汇款。
     * 示例值：9nZYDEvBT4rDdICA3ZYXshYqeOSslSjSauAE+as7mAEDJly+DgRuVs74msmKUH8pl+3oA==
     */
    private String accountNo;
    /**
     * 汇款金额
     * 必填：否
     * 需要汇款的金额(单位：分)。
     * 示例值：124
     */
    private Long payAmount;
    /**
     * 收款卡号
     * 必填：否
     * 收款账户的卡号
     * 示例值：7222223333322332
     */
    private String destinationAccountNumber;
    /**
     * 收款户名
     * 必填：否
     * 收款账户名
     * 示例值：财付通支付科技有限公司
     */
    private String destinationAccountName;
    /**
     * 开户银行
     * 必填：否
     * 收款账户的开户银行名称。
     * 示例值：招商银行威盛大厦支行
     */
    private String destinationAccountBank;
    /**
     * 省市信息
     * 必填：否
     * 收款账户的省市。
     * 示例值：深圳
     */
    private String city;
    /**
     * 备注信息
     * 必填：否
     * 商户汇款时，需要填写的备注信息。
     * 示例值：入驻账户验证
     */
    private String remark;
    /**
     * 汇款截止时间
     * 必填：否
     * 请在此时间前完成汇款。
     * 示例值：2018-12-1017:09:01
     */
    private String deadline;
}
