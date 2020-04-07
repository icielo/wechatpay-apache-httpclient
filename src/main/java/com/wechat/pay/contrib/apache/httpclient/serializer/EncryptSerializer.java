package com.wechat.pay.contrib.apache.httpclient.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.wechat.pay.contrib.apache.httpclient.WechatpayConfig;
import com.wechat.pay.contrib.apache.httpclient.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 对象转json序列化时，进行加密处理
 *
 * @author lincl
 */
@Slf4j
public class EncryptSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        //原来的值code
        String value = object.toString();
        String result = value;
        try {
            result = RSAUtil.rsaEncryptOAEP(value, WechatpayConfig.CERTIFICATE);
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(), e);
        }
        out.writeString(result);
    }
}
