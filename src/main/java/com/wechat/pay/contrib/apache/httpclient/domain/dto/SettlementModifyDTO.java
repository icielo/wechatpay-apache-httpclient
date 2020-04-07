package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.wechat.pay.contrib.apache.httpclient.serializer.EncryptSerializer;
import lombok.Data;

/**
 * 修改结算帐号
 *
 * @author lincl
 */
@Data
public class SettlementModifyDTO {

    /**
     * 特约商户号
     * 必填：是
     * 请填写本服务商负责进件的特约商户号。
     * 特殊规则：长度最小8个字节。
     * 示例值：1511101111
     */
    private String subMchid;
    /**
     * 账户类型
     * 必填：是
     * 根据特约商户号的主体类型，可选择的账户类型如下：
     * 1、小微主体：经营者个人银行卡
     * 2、个体工商户主体：经营者个人银行卡/ 对公银行账户
     * 3、企业主体：对公银行账户
     * 4、党政、机关及事业单位主体：对公银行账户
     * 5、其他组织主体：对公银行账户
     * 枚举值：
     * ACCOUNT_TYPE_BUSINESS：对公银行账户
     * ACCOUNT_TYPE_PRIVATE：经营者个人银行卡
     * 示例值：ACCOUNT_TYPE_BUSINESS
     */
    private String accountType;
    /**
     * 开户银行
     * 必填：是
     * 请填写开户银行名称，详细参见开户银行对照表。
     * 示例值：工商银行
     */
    private String accountBank;
    /**
     * 开户银行省市编码
     * 必填：是
     * 需至少精确到市，详细参见省市区编号对照表。
     * 示例值：110000
     */
    private String bankAddressCode;
    /**
     * 开户银行全称（含支行）
     * 必填：否
     * 若开户银行为“其他银行”，则需二选一填写“开户银行全称（含支行）”或“开户银行联行号”。
     * 填写银行全称，如"深圳农村商业银行XXX支行" ，详细参见开户银行全称（含支行）对照表。
     * 示例值：施秉县农村信用合作联社城关信用社
     */
    private String bankName;
    /**
     * 开户银行联行号
     * 必填：否
     * 若开户银行为“其他银行”，则需二选一填写“开户银行全称（含支行）”或“开户银行联行号”。
     * 填写银行联行号，详细参见开户银行全称（含支行）对照表。
     * 示例值：402713354941
     */
    private String bankBranchId;
    /**
     * 银行账号
     * 必填：是
     * 数字，长度遵循系统支持的对公/对私卡号长度要求
     * 该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * 附注：经营者个人银行卡，暂不支持以下前缀的银行卡
     * "623501；621468；620522；625191；622384；623078；940034；622150；622151；622181；622188；955100；621095；620062；621285；621798；621799；621797；622199；621096；62215049；62215050；62215051；62218849；62218850；62218851；621622；623219；621674；623218；621599；623698；623699；623686；621098；620529；622180；622182；622187；622189；621582；623676；623677；622812；622810；622811；628310；625919；625368；625367；518905；622835；625603；625605；518905"。
     * 示例值：d+xT+MQCvrLHUVDWv/8MR/dB7TkXM2YYZlokmXzFsWs35NXUot7C0NcxIrUF5FnxqCJHkNgKtxa6RxEYyba1+VBRLnqKG2fSy/Y5qDN08Ej9zHCwJjq52Wg1VG8MRugli9YMI1fI83KGBxhuXyemgS/hqFKsfYGiOkJqjTUpgY5VqjtL2N4l4z11T0ECB/aSyVXUysOFGLVfSrUxMPZy6jWWYGvT1+4P633f+R+ki1gT4WF/2KxZOYmli385ZgVhcR30mr4/G3HBcxi13zp7FnEeOsLlvBmI1PHN4C7Rsu3WL8sPndjXTd75kPkyjqnoMRrEEaYQE8ZRGYoeorwC+w==
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String accountNumber;
}