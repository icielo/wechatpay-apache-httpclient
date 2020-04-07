package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.wechat.pay.contrib.apache.httpclient.serializer.EncryptSerializer;
import lombok.Data;

/**
 * 结算账户信息
 *
 * @author lincl
 */
@Data
public class AccountInfoDTO {

    /**
     * 账户类型74-对公账户。75-对私账户。
     * 必填：是
     * 1、若主体为企业/党政、机关及事业单位/其他组织，可填写：74-对公账户。
     * 2、若主体为小微，可填写：75-对私账户。
     * 3、若主体为个体工商户，可填写：74-对公账户或75-对私账户。
     * 示例值：75
     */
    private String bankAccountType;

    /**
     * 开户银行
     * 必填：是
     * 详细参见开户银行对照表。
     * 示例值：工商银行
     */
    private String accountBank;

    /**
     * 开户名称
     * 必填：是
     * 1、选择经营者个人银行卡时，开户名称必须与身份证姓名一致。
     * 2、选择对公账户时，开户名称必须与营业执照上的“商户名称”一致。
     * 3、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * 示例值：AOZdYGISxo4yw96uY1Pk7Rq79Jtt7+I8juwEc4P4TG5xzchG/5IL9DBd+Z0zZXkw==
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String accountName;

    /**
     * 开户银行省市编码
     * 必填：是
     * 至少精确到市，详细参见省市区编号对照表。
     * 示例值：110000
     */
    private String bankAddressCode;

    /**
     * 开户银行联行号
     * 必填：条件选填
     * 1、17家直连银行无需填写，如为其他银行，开户银行全称（含支行）和开户银行联行号二选一。
     * 2、详细参见开户银行全称（含支行）对照表。
     * 示例值：402713354941
     */
    private String bankBranchId;

    /**
     * 开户银行全称 （含支行)
     * 必填：条件选填
     * 1、17家直连银行无需填写，如为其他银行，开户银行全称（含支行）和开户银行联行号二选一。
     * 2、需填写银行全称，如"深圳农村商业银行XXX支行" 。
     * 3、详细参见开户银行全称（含支行）对照表。
     * 示例值：施秉县农村信用合作联社城关信用社
     */
    private String bankName;

    /**
     * 银行帐号
     * 必填：是
     * 1、数字，长度遵循系统支持的对公/对私卡号长度要求表。
     * 2、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * 示例值： d+xT+MQCvrLHUVDWv/8MR/dB7TkXLVfSrUxMPZy6jWWYzpRrEEaYQE8ZRGYoeorwC+w==
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String accountNumber;
}