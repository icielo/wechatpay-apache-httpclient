package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 商户进件
 *
 * @author lincl
 */
@Data
public class ApplymentDTO {

    /**
     * 业务申请编号
     * 必填：是
     * 1、服务商自定义的商户唯一编号。
     * 2、每个编号对应一个申请单，每个申请单审核通过后会生成一个微信支付商户号。
     * 3、若申请单被驳回，可填写相同的“业务申请编号”，即可覆盖修改原申请单信息 。
     * 示例值：APPLYMENT_00000000001
     */
    private String outRequestNo;

    /**
     * 主体类型：4个体，2企业
     * 必填：是
     * 枚举值：
     * 2401：小微商户，指无营业执照的商户。
     * 4：个体工商户，营业执照上的主体类型一般为个体户、个体工商户、个体经营。
     * 2：企业，营业执照上的主体类型一般为有限公司、有限责任公司。
     * 3：党政、机关及事业单位，包括国内各级、各类政府机构、事业单位等（如：公安、党团、司法、交通、旅游、工商税务、市政、医疗、教育、学校等机构）。
     * 1708：其他组织，不属于企业、政府/事业单位的组织机构（如社会团体、民办非企业、基金会），要求机构已办理组织机构代码证。
     * 示例值：2401
     */
    private String organizationType;

    /**
     * 营业执照/登记证书信息
     * 必填：条件选填
     * 1、主体为“小微”时，不填。
     * 2、主体为“个体工商户/企业”时，请上传营业执照。
     * 3、主体为“党政、机关及事业单位/其他组织”时，请上传登记证书。
     */
    private BusinessLicenseInfoDTO businessLicenseInfo;

    /**
     * 组织机构代码证信息
     * 必填：条件选填
     * 主体为企业/党政、机关及事业单位/其他组织，且证件号码不是18位时必填。
     */
    private OrganizationCertInfoDTO organizationCertInfo;

    /**
     * 经营者/法人证件类型
     * 必填：否
     * 1、主体为“小微”，只可选择：身份证。
     * 2、主体为“个体户/企业/党政、机关及事业单位/其他组织”，可选择：任一证件类型。
     * 3、若没有填写，系统默认选择：身份证。
     * 枚举值:
     * IDENTIFICATION_TYPE_MAINLAND_IDCARD：中国大陆居民-身份证
     * IDENTIFICATION_TYPE_OVERSEA_PASSPORT：其他国家或地区居民-护照
     * IDENTIFICATION_TYPE_HONGKONG：中国香港居民–来往内地通行证
     * IDENTIFICATION_TYPE_MACAO：中国澳门居民–来往内地通行证
     * IDENTIFICATION_TYPE_TAIWAN：中国台湾居民–来往大陆通行证
     * 示例值：IDENTIFICATION_TYPE_MACAO
     */
    private String idDocType;

    /**
     * 经营者/法人身份证信息
     * 必填：条件选填
     * query请填写经营者/法人的身份证信息
     * 证件类型为“身份证”时填写。
     */
    private IdCardInfoDTO idCardInfo;

    /**
     * 经营者/法人其他类型证件信息
     * 必填：条件选填
     * 证件类型为“来往内地通行证、来往大陆通行证、护照”时填写。
     */
    private IdDocInfoDTO idDocInfo;

    /**
     * 是否填写结算账户信息
     * 必填：是
     * 可根据实际情况，填写“true”或“false”。
     * 1、若为“true”，则需填写结算账户信息。
     * 2、若为“false”，则无需填写结算账户信息。
     * 示例值：true
     */
    private Boolean needAccountInfo;

    /**
     * 结算账户信息
     * 必填：条件选填
     * 若"是否填写结算账户信息"填写为“true”, 则必填，填写为“false”不填 。
     */
    private AccountInfoDTO accountInfo;

    /**
     * 超级管理员信息
     * 必填：是
     * 请填写店铺的超级管理员信息。
     * 超级管理员需在开户后进行签约，并可接收日常重要管理信息和进行资金操作，请确定其为商户法定代表人或负责人。
     */
    private ContactInfoDTO contactInfo;

    /**
     * 店铺信息
     * 必填：是
     * 请填写店铺信息
     */
    private SalesSceneInfoDTO salesSceneInfo;

    /**
     * 商户简称
     * 必填：是
     * UTF-8格式，中文占3个字节，即最多16个汉字长度。将在支付完成页向买家展示，需与商家的实际售卖商品相符 。
     * 示例值：腾讯
     */
    private String merchantShortname;

    /**
     * 特殊资质
     * 必填：否
     * 1、若店铺业务包含互联网售药，则需上传特殊资质-《互联网药品交易服务证》。
     * 2、最多可上传5张照片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
     * 示例值：[\"jTpGmxUX3FBWVQ5NJInE4d2I6_H7I4\"]
     */
    private String qualifications;

    /**
     * 补充材料
     * 必填：否
     * 最多可上传5张照片，请填写通过图片上传接口预先上传图片生成好的MediaID 。
     * 示例值：[\"jTpGmg05InE4d2I6_H7I4\"]
     */
    private String businessAdditionPics;

    /**
     * 补充说明
     * 必填：否
     * 可填写512字以内 。
     * 示例值：特殊情况，说明原因
     */
    private String businessAdditionDesc;
}
