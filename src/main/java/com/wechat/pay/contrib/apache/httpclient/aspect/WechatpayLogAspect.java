package com.wechat.pay.contrib.apache.httpclient.aspect;

import cn.hutool.json.JSONUtil;
import com.wechat.pay.contrib.apache.httpclient.WechatpayAPI;
import com.wechat.pay.contrib.apache.httpclient.WechatpayConfig;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.WechatpayLogDTO;
import com.wechat.pay.contrib.apache.httpclient.service.WechatpayLogService;
import com.wechat.pay.contrib.apache.httpclient.util.JsonUtil;
import com.wechat.pay.contrib.apache.httpclient.util.ThrowableUtil;
import com.wechat.pay.contrib.apache.httpclient.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;


/**
 * 微信支付日志切面
 *
 * @author lincl
 */
@Component
@Aspect
@Slf4j
public class WechatpayLogAspect {

    private long currentTime;

    private WechatpayLogDTO wechatpayLogDTO;

    @Autowired
    private WechatpayLogService wechatpayLogService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.wechat.pay.contrib.apache.httpclient.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        currentTime = System.currentTimeMillis();
        wechatpayLogDTO = new WechatpayLogDTO();
        // 记录日志
        Object[] args = joinPoint.getArgs();
        if (args.length == 2) {
            WechatpayAPI wechatpayAPI = (WechatpayAPI) args[0];
            Object params = args[1];
            String requestText = null;
            String requestCiphertext = null;
            if (params instanceof File) {

            } else {
                requestText = JSONUtil.toJsonStr(params);
                requestCiphertext = JsonUtil.toSnakeJson(params);
            }
            wechatpayLogDTO.setName(wechatpayAPI.getName());
            wechatpayLogDTO.setUrl(UrlUtil.getRealUrl(wechatpayAPI.getUrl(), params));
            wechatpayLogDTO.setRequestHeader(null);
            wechatpayLogDTO.setRequestCiphertext(requestCiphertext);
            wechatpayLogDTO.setRequestText(requestText);
        }
        Object result = joinPoint.proceed();
        wechatpayLogDTO.setResponseText(result.toString());
        wechatpayLogDTO.setRequestTime(LocalDateTime.now());
        wechatpayLogDTO.setCostTime(System.currentTimeMillis() - currentTime);
        wechatpayLogService.save(wechatpayLogDTO);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if (wechatpayLogDTO == null) {
            wechatpayLogDTO = new WechatpayLogDTO();
        }
        wechatpayLogDTO.setCostTime(System.currentTimeMillis() - currentTime);
        wechatpayLogDTO.setExceptionDetail(ThrowableUtil.getStackTrace(e));
        wechatpayLogService.save(wechatpayLogDTO);
    }
}
