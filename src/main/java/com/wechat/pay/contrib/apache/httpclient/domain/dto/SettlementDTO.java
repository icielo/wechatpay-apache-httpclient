package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 查询结算账户
 *
 * @author lincl
 */
@Data
public class SettlementDTO {

    /**
     * 账户类型
     * 必填：是
     * 返回特约商户的结算账户类型。
     * 枚举值：
     * ACCOUNT_TYPE_BUSINESS：对公银行账户
     * ACCOUNT_TYPE_PRIVATE：经营者个人银行卡
     * 示例值：ACCOUNT_TYPE_BUSINESS
     */
    private String accountType;
    /**
     * 开户银行
     * 必填：是
     * 返回特约商户的结算账户-开户银行全称。
     * 示例值：工商银行
     */
    private String accountBank;
    /**
     * 开户银行全称（含支行)
     * 必填：是
     * 返回特约商户的结算账户-开户银行全称（含支行）。
     * 示例值：施秉县农村信用合作联社城关信用社
     */
    private String bankName;
    /**
     * 开户银行联行号
     * 必填：是
     * 返回特约商户的结算账户-联行号。
     * 示例值：402713354941
     */
    private String bankBranchId;
    /**
     * 银行账号
     * 必填：是
     * 返回特约商户的结算账户-银行账号，掩码显示。
     * 示例值：62*************78
     */
    private String accountNumber;
    /**
     * 汇款验证结果
     * 必填：是
     * 返回特约商户的结算账户-汇款验证结果。
     * VERIFYING：系统汇款验证中，商户可发起提现尝试。
     * VERIFY_SUCCESS：系统成功汇款，该账户可正常发起提现。
     * VERIFY_FAIL：系统汇款失败，该账户无法发起提现，请检查修改。
     * 示例值：VERIFY_SUCCESS
     */
    private String verifyResult;
}
