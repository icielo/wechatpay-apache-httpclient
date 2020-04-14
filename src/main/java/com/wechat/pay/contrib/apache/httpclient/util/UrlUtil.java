package com.wechat.pay.contrib.apache.httpclient.util;

import cn.hutool.core.util.ReUtil;
import com.wechat.pay.contrib.apache.httpclient.WechatpayConfig;
import com.wechat.pay.contrib.apache.httpclient.exception.WechatpayException;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * url工具类
 *
 * @author lincl
 */
public class UrlUtil {

    /**
     * path参数
     */
    private static final Pattern PATH_PATTERN = Pattern.compile("(\\{\\w+\\})");

    /**
     * 替换path参数为实际值
     *
     * @param url
     * @param params
     * @return
     */
    public static String getRealUrl(String url, Object params) {
        List<String> list = ReUtil.findAll(PATH_PATTERN, url, 1);
        for (String item : list) {
            String fieldName = DataUtil.toCamel(item.replace("{", "").replace("}", ""));
            if (params == null) {
                throw new WechatpayException("缺少请求参数：" + fieldName);
            }
            Object value;
            if (params instanceof Map) {
                value = ((Map) params).get(fieldName);
            } else {
                value = ReflectionUtil.getFieldValue(params, fieldName);
            }
            if (value == null) {
                throw new WechatpayException("缺少请求参数：" + fieldName);
            }
            url = url.replace(item, value.toString());
        }
        return url;
    }
}
