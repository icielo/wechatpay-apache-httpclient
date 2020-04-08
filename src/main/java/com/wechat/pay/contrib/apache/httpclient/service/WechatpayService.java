package com.wechat.pay.contrib.apache.httpclient.service;

import com.wechat.pay.contrib.apache.httpclient.domain.dto.CombineTradeCloseDTO;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

/**
 * 微信支付服务类
 *
 * @author lincl
 */
public interface WechatpayService {

    /**
     * 通知回调验证签名
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    boolean verify(HttpServletRequest request) throws UnsupportedEncodingException;

    /**
     * 通知回调解密
     *
     * @param associatedData
     * @param nonce
     * @param ciphertext
     * @return
     */
    String decrypt(String associatedData, String nonce, String ciphertext) throws IOException, GeneralSecurityException;

    /**
     * 图片上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    UploadResultDTO mediaImageUpload(File file) throws IOException;

    /**
     * 二级商户进件
     *
     * @param applymentDTO
     * @return
     * @throws IOException
     */
    ApplymentResultDTO applyment(ApplymentDTO applymentDTO);

    /**
     * 通过申请单ID查询申请状态
     *
     * @param applymentId 微信支付申请单号
     *                    申请单的主键，唯一定义此资源的标识。
     *                    示例值：2000002124775691
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    ApplymentStatusDTO applymentStatusQueryByApplymentId(String applymentId);

    /**
     * 通过业务申请编号查询申请状态
     *
     * @param outRequestNo 业务申请编号
     *                     1、服务商自定义的商户唯一编号。
     *                     2、每个编号对应一个申请单，每个申请单审核通过后会生成一个微信支付商户号。
     *                     示例值：APPLYMENT_00000000001
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    ApplymentStatusDTO applymentStatusQueryByOutRequestNo(String outRequestNo);

    /**
     * 修改结算帐号
     *
     * @param settlementModifyDTO
     * @return
     * @throws IOException
     */
    String settlementModify(SettlementModifyDTO settlementModifyDTO);

    /**
     * 查询结算账户
     *
     * @param subMchid 特约商户号
     * @return
     */
    SettlementDTO settlementQuery(String subMchid);

    /**
     * 合单下单-APP支付API
     *
     * @param combineTradeDTO
     * @return
     */
    String combineTradeApp(CombineTradeDTO combineTradeDTO);

    /**
     * 合单查询订单
     *
     * @param combineOutTradeNo
     * @return
     */
    CombineTradeStatusDTO combineTradeQuery(String combineOutTradeNo);

    /**
     * 合单关闭订单
     *
     * @param combineTradeCloseDTO
     */
    void combineTradeClose(CombineTradeCloseDTO combineTradeCloseDTO);

    /**
     * 完结分账
     *
     * @param profitSharingFinishDTO
     * @return
     */
    ProfitSharingFinishResultDTO profitSharingFinish(ProfitSharingFinishDTO profitSharingFinishDTO);


    /**
     * 退款申请
     *
     * @param refundApplyDTO
     * @return
     */
    RefundApplyResultDTO refundApply(RefundApplyDTO refundApplyDTO);
}
