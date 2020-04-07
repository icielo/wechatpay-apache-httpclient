package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 二级商户进件申请状态
 *
 * @author lincl
 */
@Data
public class ApplymentStatusDTO {
    /**
     * 申请状态
     * 必填：否
     * 枚举值：
     * CHECKING：资料校验中
     * ACCOUNT_NEED_VERIFY：待账户验证
     * AUDITING：审核中
     * REJECTED：已驳回
     * NEED_SIGN：待签约
     * FINISH：完成
     * FROZEN：已冻结
     */
    private String applymentState;
    /**
     * 申请状态描述
     * 必填：否
     * 示例值：“审核中”
     */
    private String applymentStateDesc;
    /**
     * 签约链接
     * 必填：否
     * 1、当申请状态为NEED_SIGN时才返回。
     * 2、建议将链接转为二维码展示，需让申请单-管理者用微信扫码打开，完成签约。
     * 示例值：https://pay.weixin.qq.com/public/apply4ec_sign/s?applymentId=2000002126198476&sign=b207b673049a32c858f3aabd7d27c7ec
     */
    private String signUrl;
    /**
     * 电商平台二级商户号
     * 必填：否
     * 当申请状态为NEED_SIGN或FINISH时才返回。
     * 示例值：1542488631
     */
    private String subMchid;
    /**
     * 汇款账户验证信息
     * 必填：否
     * 当申请状态为ACCOUNT_NEED_VERIFY 时有返回，可根据指引汇款，完成账户验证
     */
    private AccountValidationDTO accountValidation;
    /**
     * 驳回原因详情
     * 必填：否
     * 各项资料的审核情况。当申请状态为REJECTED或 FROZEN时才返回。
     */
    private AuditDetailDTO auditDetail;
    /**
     * 法人验证链接
     * 必填：否
     * 1、当申请状态为
     * ACCOUNT_NEED_VERIFY，且通过系统校验的申请单，将返回链接。
     * 2、建议将链接转为二维码展示，让商户法人用微信扫码打开，完成账户验证。
     * 示例值： https://pay.weixin.qq.com/public/apply4ec_sign/s?applymentId=2000002126198476&sign=b207b673049a32c858f3aabd7d27c7ec
     */
    private String legalValidationUrl;
    /**
     * 业务申请编号
     * 必填：是
     * 提交接口填写的业务申请编号。
     * 示例值：APPLYMENT_00000000001
     */
    private String outRequestNo;
    /**
     * 微信支付申请单号
     * 必填：否
     * 微信支付分配的申请单号。
     * 示例值：2000002124775691
     */
    private String applymentId;
}
