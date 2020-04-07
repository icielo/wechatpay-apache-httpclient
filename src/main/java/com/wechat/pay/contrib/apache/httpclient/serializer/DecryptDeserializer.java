package com.wechat.pay.contrib.apache.httpclient.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.wechat.pay.contrib.apache.httpclient.WechatpayConfig;
import com.wechat.pay.contrib.apache.httpclient.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * json转对象反序列化时，进行解密处理
 *
 * @author lincl
 */
@Slf4j
public class DecryptDeserializer implements ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer jsonLexer = parser.lexer;
        String value = jsonLexer.stringVal();
        String result = value;
        try {
            result = RSAUtil.rsaDecryptOAEP(value, WechatpayConfig.PRIVATE_KEY);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return (T) result;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
