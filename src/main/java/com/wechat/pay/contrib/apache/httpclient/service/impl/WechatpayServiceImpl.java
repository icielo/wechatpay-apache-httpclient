package com.wechat.pay.contrib.apache.httpclient.service.impl;


import com.wechat.pay.contrib.apache.httpclient.WechatpayAPI;
import com.wechat.pay.contrib.apache.httpclient.WechatpayClient;
import com.wechat.pay.contrib.apache.httpclient.WechatpayConfig;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.*;
import com.wechat.pay.contrib.apache.httpclient.exception.WechatpayException;
import com.wechat.pay.contrib.apache.httpclient.service.WechatpayService;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wechat.pay.contrib.apache.httpclient.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付服务类
 *
 * @author Administrator
 */
public class WechatpayServiceImpl implements WechatpayService {

    /**
     * 微信支付客户端
     */
    WechatpayClient wechatpayClient;

    public WechatpayServiceImpl(WechatpayClient wechatpayClient) {
        this.wechatpayClient = wechatpayClient;
    }


    @Override
    public boolean verify(HttpServletRequest request) throws UnsupportedEncodingException {
        return wechatpayClient.verify(request);
    }

    @Override
    public String decrypt(String associatedData, String nonce, String ciphertext) throws IOException, GeneralSecurityException {
        AesUtil aesUtil = new AesUtil(WechatpayConfig.API_V3_KEY.getBytes("utf-8"));
        return aesUtil.decryptToString(associatedData.getBytes("utf-8"), nonce.getBytes("utf-8"), ciphertext);
    }

    @Override
    public UploadResultDTO mediaImageUpload(File file) throws IOException {
        if (!file.exists()) {
            throw new WechatpayException("文件不存在！");
        }
        return wechatpayClient.doUpload(WechatpayAPI.MEDIA_IMAGE_UPLOAD, file);
    }

    @Override
    public ApplymentResultDTO applyment(ApplymentDTO applymentDTO) throws IOException {
        String json = wechatpayClient.doPost(WechatpayAPI.APPLYMENT, applymentDTO);
        return JsonUtil.fromSnakeJson(json, ApplymentResultDTO.class);
    }

    @Override
    public ApplymentStatusDTO applymentStatusQueryByApplymentId(String applymentId) throws IOException, URISyntaxException {
        Map<String, Object> params = new HashMap<>(16);
        params.put("applymentId", applymentId);
        String json = wechatpayClient.doGet(WechatpayAPI.APPLYMENT_STATUS_QUERY_BY_APPLYMENT_ID, params);
        return JsonUtil.fromSnakeJson(json, ApplymentStatusDTO.class);
    }

    @Override
    public ApplymentStatusDTO applymentStatusQueryByOutRequestNo(String outRequestNo) throws IOException, URISyntaxException {
        Map<String, Object> params = new HashMap<>(16);
        params.put("outRequestNo", outRequestNo);
        String json = wechatpayClient.doGet(WechatpayAPI.APPLYMENT_STATUS_QUERY_BY_OUT_REQUEST_NO, params);
        return JsonUtil.fromSnakeJson(json, ApplymentStatusDTO.class);
    }

    @Override
    public String settlementModify(SettlementModifyDTO settlementModifyDTO) throws IOException {
        return wechatpayClient.doPost(WechatpayAPI.SETTLEMENT_MODIFY, settlementModifyDTO);
    }

    @Override
    public SettlementDTO settlementQuery(String subMchid) throws IOException, URISyntaxException {
        Map<String, Object> params = new HashMap<>(16);
        params.put("subMchid", subMchid);
        String json = wechatpayClient.doGet(WechatpayAPI.SETTLEMENT_QUERY, params);
        return JsonUtil.fromSnakeJson(json, SettlementDTO.class);
    }

    @Override
    public String combineTradeApp(CombineTradeDTO combineTradeDTO) throws IOException {
        return wechatpayClient.doPost(WechatpayAPI.COMBINE_TRADE_APP, combineTradeDTO);
    }
}
