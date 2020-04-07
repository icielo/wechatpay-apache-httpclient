package com.wechat.pay.contrib.apache.httpclient.domain.dto;

import lombok.Data;

/**
 * 驳回原因详情
 *
 * @author Administrator
 */
@Data
public class AuditDetailDTO {

    /**
     * 参数名称
     * 必填：否
     * 提交申请单的资料项名称。
     * 示例值：id_card_copy
     */
    private String paramName;
    /**
     * 驳回原因
     * 必填：否
     * 提交资料项被驳回原因。
     * 示例值：身份证背面识别失败，请上传更清晰的身份证图片
     */
    private String rejectReason;
}
