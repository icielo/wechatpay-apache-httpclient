package com.wechat.pay.contrib.apache.httpclient.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * JSON工具类
 *
 * @author lincl
 */
public class JsonUtil {

    /**
     * 将 “数据对象” 转换为 “JSON字符串”
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 将 “JSON字符串” 转换为 “数据对象”
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 将 “数据对象” 转换为 “key为下划线分割的JSON字符串”
     *
     * @param obj
     * @return
     */
    public static String toSnakeJson(Object obj) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        return JSON.toJSONString(obj, config);
    }

    /**
     * 将 “key为下划线分割的JSON字符串” 转换为 “数据对象”
     */
    public static <T> T fromSnakeJson(String json, Class<T> clazz) {
        ParserConfig config = new ParserConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        return JSON.parseObject(json, clazz, config);
    }
}
