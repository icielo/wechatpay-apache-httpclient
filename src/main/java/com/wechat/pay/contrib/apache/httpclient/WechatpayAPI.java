package com.wechat.pay.contrib.apache.httpclient;

import org.springframework.http.HttpMethod;

/**
 * 微信支付API
 *
 * @author lincl
 */
public enum WechatpayAPI {
    /**
     * 二级商户进件
     */
    APPLYMENT("二级商户进件", "/ecommerce/applyments/", HttpMethod.POST, true),
    /**
     * 通过申请单ID查询申请状态
     */
    APPLYMENT_STATUS_QUERY_BY_APPLYMENT_ID("通过申请单ID查询申请状态", "/ecommerce/applyments/{applyment_id}", HttpMethod.GET),
    /**
     * 通过业务申请编号查询申请状态
     */
    APPLYMENT_STATUS_QUERY_BY_OUT_REQUEST_NO("通过业务申请编号查询申请状态", "/ecommerce/applyments/out-request-no/{out_request_no}", HttpMethod.GET),
    /**
     * 下载平台证书
     */
    DOWNLOAD_CERTIFICATE("下载平台证书", "/certificates", HttpMethod.GET),
    /**
     * 修改结算帐号
     */
    SETTLEMENT_MODIFY("修改结算帐号", "/apply4sub/sub_merchants/{sub_mchid}/modify-settlement", HttpMethod.POST),
    /**
     * 查询结算账户
     */
    SETTLEMENT_QUERY("查询结算账户", "/apply4sub/sub_merchants/{sub_mchid}/settlement", HttpMethod.GET),
    /**
     * 合单下单-APP支付
     */
    COMBINE_TRADE_APP("合单下单-APP支付", "/combine-transactions/app", HttpMethod.POST),
    /**
     * 合单下单-JS支付
     */
    COMBINE_TRADE_JS("合单下单-JS支付", "/combine-transactions/jsapi", HttpMethod.POST),
    /**
     * 合单查询订单
     */
    COMBINE_TRADE_QUERY("合单查询订单", "/combine-transactions/out-trade-no/{combine_out_trade_no}", HttpMethod.GET),
    /**
     * 合单关闭订单API
     */
    COMBINE_TRADE_CLOSE("合单关闭订单API", "/combine-transactions/out-trade-no/{combine_out_trade_no}/close", HttpMethod.POST),
    /**
     * 请求补差
     */
    SUBSIDIES_CREATE("请求补差", "/ecommerce/subsidies/create", HttpMethod.POST),
    /**
     * 请求补差回退
     */
    SUBSIDIES_RETURN("请求补差回退", "/ecommerce/subsidies/return", HttpMethod.POST),
    /**
     * 取消补差
     */
    SUBSIDIES_CANCEL("取消补差", "/ecommerce/subsidies/cancel", HttpMethod.POST),
    /**
     * 请求分账
     */
    PROFIT_SHARING_APPLY("请求分账", "/ecommerce/profitsharing/orders", HttpMethod.POST),
    /**
     * 查询分账结果
     */
    PROFIT_SHARING_QUERY("", "/ecommerce/profitsharing/orders", HttpMethod.GET),
    /**
     * 请求分账回退
     */
    PROFIT_SHARING_RETURN("请求分账回退", "/ecommerce/profitsharing/returnorders", HttpMethod.POST),
    /**
     * 查询分账回退结果
     */
    PROFIT_SHARING_RETURN_QUERY("查询分账回退结果", "/ecommerce/profitsharing/returnorders", HttpMethod.GET),
    /**
     * 完结分账
     */
    PROFIT_SHARING_FINISH("完结分账", "/ecommerce/profitsharing/finish-order", HttpMethod.POST),
    /**
     * 添加分账接收方
     */
    PROFIT_SHARING_ADD_RECEIVER("添加分账接收方", "/ecommerce/profitsharing/receivers/add", HttpMethod.POST),
    /**
     * 删除分账接收方
     */
    PROFIT_SHARING_DELETE_RECEIVER("删除分账接收方", "/ecommerce/profitsharing/receivers/delete", HttpMethod.POST),
    /**
     * 退款申请
     */
    REFUND_APPLY("退款申请", "/ecommerce/refunds/apply", HttpMethod.POST),
    /**
     * 通过微信支付退款单号查询退款
     */
    REFUND_QUERY_BY_REFUND_ID("通过微信支付退款单号查询退款", "/ecommerce/refunds/id/{refund_id}", HttpMethod.POST),
    /**
     * 通过商户退款单号查询退款
     */
    REFUND_QUERY_BY_OUT_REFUND_NO("通过商户退款单号查询退款", "/ecommerce/refunds/out-refund-no/{out_refund_no}", HttpMethod.POST),
    /**
     * 查询二级商户账户实时余额
     */
    MERCHANT_BALANCE_QUERY("查询二级商户账户实时余额", "/ecommerce/fund/balance/{sub_mchid}", HttpMethod.GET),
    /**
     * 查询二级商户账户日终余额
     */
    MERCHANT_BALANCE_QUERY_DAY_END("查询二级商户账户日终余额", "/ecommerce/fund/enddaybalance/{sub_mchid}", HttpMethod.GET),
    /**
     * 查询电商平台账户实时余额
     */
    PLATFORM_BALANCE_QUERY("查询电商平台账户实时余额", "/merchant/fund/balance/{account_type}", HttpMethod.GET),
    /**
     * 查询电商平台账户日终余额
     */
    PLATFORM_BALANCE_QUERY_DAY_END("查询电商平台账户日终余额", "/merchant/fund/dayendbalance/{account_type}", HttpMethod.GET),
    /**
     * 账户余额提现
     */
    WITHDRAW("账户余额提现", "/ecommerce/fund/withdraw", HttpMethod.POST),
    /**
     * 提现状态查询
     */
    WITHDRAW_QUERY("提现状态查询", "/ecommerce/fund/withdraw/{withdraw_id}", HttpMethod.GET),
    /**
     * 按日下载提现异常文件
     */
    WITHDRAW_DOWNLOAD_EXCEPTION("按日下载提现异常文件", "/merchant/fund/withdraw/bill-type/{bill_type}", HttpMethod.GET),
    /**
     * 申请交易账单
     */
    TRADE_BILL("申请交易账单", "/bill/tradebill", HttpMethod.GET),
    /**
     * 申请资金账单
     */
    FUND_FLOW_BILL("申请资金账单", "/bill/fundflowbill", HttpMethod.GET),
    /**
     * 图片上传
     */
    MEDIA_IMAGE_UPLOAD("图片上传", "/merchant/media/upload", HttpMethod.POST),
    /**
     * 视频上传
     */
    MEDIA_VIDEO_UPLOAD("视频上传", "/merchant/media/video_upload", HttpMethod.POST),
    ;

    /**
     * API名称
     */
    private String name;

    /**
     * API请求地址
     */
    private String url;

    /**
     * API请求方法
     */
    private HttpMethod httpMethod;

    /**
     * 是否加密
     */
    private boolean encrypt;

    WechatpayAPI(String name, String url, HttpMethod httpMethod) {
        this.name = name;
        this.url = url;
        this.httpMethod = httpMethod;
        this.encrypt = false;
    }

    WechatpayAPI(String name, String url, HttpMethod httpMethod, boolean encrypt) {
        this.name = name;
        this.url = url;
        this.httpMethod = httpMethod;
        this.encrypt = encrypt;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public boolean isEncrypt() {
        return encrypt;
    }
}
