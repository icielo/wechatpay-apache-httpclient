package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.wechat.pay.contrib.apache.httpclient.serializer.EncryptSerializer;
import lombok.Data;

/**
 * 超级管理员信息
 *
 * @author lincl
 */
@Data
public class ContactInfoDTO {

    /**
     * 超级管理员类型 65-法人/经营者、66- 负责人
     * 必填：是
     * 1、小微商户，选择：65-法人/经营者。
     * 2、个体工商户/企业/党政、机关及事业单位/其他组织，可选择：65-法人/经营者、66- 负责人。 （负责人：经商户授权办理微信支付业务的人员，授权范围包括但不限于签约，入驻过程需完成账户验证）。
     * 示例值：65
     */
    private String contactType;

    /**
     * 超级管理员姓名
     * 必填：是
     * 1、若管理员类型为“法人”，则该姓名需与法人身份证姓名一致。
     * 2、若管理员类型为“经办人”，则可填写实际经办人的姓名。
     * 3、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * （后续该管理员需使用实名微信号完成签约）
     * 示例值： pVd1HJ6zyvPedzGaV+X3IdGdbDnuC4Eelw/wDa4SzfeespQO/0kjiwfqdfg==
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String contactName;

    /**
     * 超级管理员身份证件号码
     * 必填：是
     * 1、若管理员类型为法人，则该身份证号码需与法人身份证号码一致。若管理员类型为经办人，则可填写实际经办人的身份证号码。
     * 2、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码。
     * 3、超级管理员签约时，校验微信号绑定的银行卡实名信息，是否与该证件号码一致。
     * 4、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * 示例值：pVd1HJ6zmty7/mYNxLMpRSvMRtelw/wDa4SzfeespQO/0kjiwfqdfg==
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String contactIdCardNumber;

    /**
     * 超级管理员手机
     * 必填：是
     * 1、请填写管理员的手机号，11位数字， 用于接收微信支付的重要管理信息及日常操作验证码 。
     * 2、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * 示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+F6mfjbzQIzfb3HHLEjZ4YiNWWNeespQO/0kjiwfqdfg==
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String mobilePhone;

    /**
     * 超级管理员邮箱
     * 必填：是
     * 1、用于接收微信支付的开户邮件及日常业务通知。
     * 2、需要带@，遵循邮箱格式校验 。
     * 3、该字段需进行加密处理，加密方法详见敏感信息加密说明。
     * 示例值：pVd1HJ6zyvPedzGaV+X3qtmrq9bb9tPROvwia4ibL+FWWNUlw/wDa4SzfeespQO/0kjiwfqdfg==
     */
    @JSONField(serializeUsing = EncryptSerializer.class)
    private String contactEmail;

}