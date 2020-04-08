package com.wechat.pay.contrib.apache.httpclient;

import com.wechat.pay.contrib.apache.httpclient.domain.dto.ApplymentDTO;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.IdCardInfoDTO;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.SettlementModifyDTO;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.UploadResultDTO;
import com.wechat.pay.contrib.apache.httpclient.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lincl
 */
public class WechatpayClientTest {

    private WechatpayClient wechatpayClient;

    @Before
    public void setup() throws IOException {
        WechatpayConfig.init("E:\\cert");
        this.wechatpayClient = new WechatpayClient();
    }

    /**
     * 替换path参数为实际值
     */
    @Test
    public void getRealUrl() {
        Map map = new HashMap();
        map.put("applymentId", "123");
        System.out.println(wechatpayClient.getRealUrl(WechatpayAPI.APPLYMENT_STATUS_QUERY_BY_APPLYMENT_ID.getUrl(), map));

        SettlementModifyDTO reqVO = new SettlementModifyDTO();
        reqVO.setSubMchid("132");

        System.out.println(wechatpayClient.getRealUrl(WechatpayAPI.SETTLEMENT_MODIFY.getUrl(), reqVO));
    }

    /**
     * 获取key为下划线分割的json字符串，并对@JSONField(serializeUsing = EncryptSerializer.class)进行加密处理
     */
    @Test
    public void snakeJsonAndEncrypt() {
        ApplymentDTO reqVO = new ApplymentDTO();
        reqVO.setOutRequestNo("123456");
        IdCardInfoDTO idcardInfo = new IdCardInfoDTO();
        idcardInfo.setIdCardName("身份证");
        idcardInfo.setIdCardNumber("350303198812341234");
        reqVO.setIdCardInfo(idcardInfo);
        String json = JsonUtil.toSnakeJson(reqVO);
        System.out.println("对象转json：" + json);
        ApplymentDTO reqVO1 = JsonUtil.fromSnakeJson(json, ApplymentDTO.class);
        System.out.println("json转对象：" + reqVO1.toString());
    }

    /**
     * 下载平台证书
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void DOWNLOAD_CERTIFICATE() throws IOException, URISyntaxException {
        String string = wechatpayClient.doGet(WechatpayAPI.DOWNLOAD_CERTIFICATE, null);
        System.out.println(string);
    }

    /**
     * 查询申请状态
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void MERCHANT_APPLY_STATUS_QUERY() throws IOException, URISyntaxException {
        Map<String, Object> params = new HashMap<>();
        params.put("applymentId", "2000002124775691");
        String string = wechatpayClient.doGet(WechatpayAPI.APPLYMENT_STATUS_QUERY_BY_APPLYMENT_ID, params);
        System.out.println(string);
    }

    /**
     * 查询结算账户
     *
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void SETTLEMENT_QUERY() throws IOException, URISyntaxException {
        Map<String, Object> params = new HashMap<>();
        params.put("subMchid", "2000002124775691");
        String string = wechatpayClient.doGet(WechatpayAPI.SETTLEMENT_QUERY, params);
        System.out.println(string);
    }

    /**
     * 图片上传
     *
     * @throws IOException
     */
    @Test
    public void MEDIA_UPLOAD() throws IOException {
        String fileName = "e:\\hehe.jpg";
        UploadResultDTO result = wechatpayClient.doUpload(WechatpayAPI.MEDIA_IMAGE_UPLOAD, new File(fileName));
        System.out.println(result);
    }

}
