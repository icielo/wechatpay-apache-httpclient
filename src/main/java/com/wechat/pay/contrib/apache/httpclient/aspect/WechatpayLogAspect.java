package com.wechat.pay.contrib.apache.httpclient.aspect;

import com.wechat.pay.contrib.apache.httpclient.WechatpayAPI;
import com.wechat.pay.contrib.apache.httpclient.domain.dto.WechatpayLogDTO;
import com.wechat.pay.contrib.apache.httpclient.service.WechatpayLogService;
import com.wechat.pay.contrib.apache.httpclient.util.ThrowableUtil;
import org.apache.http.client.methods.HttpUriRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDateTime;


/**
 * 微信支付日志切面
 *
 * @author lincl
 */
public class WechatpayLogAspect {

    private long currentTime;

    private WechatpayLogDTO log;

    private WechatpayLogService wechatpayLogService;

    public WechatpayLogAspect(WechatpayLogService wechatpayLogService) {
        this.wechatpayLogService = wechatpayLogService;
    }

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
        log = new WechatpayLogDTO();
        // 记录日志
        Object[] args = joinPoint.getArgs();
        if (args.length == 4) {
            HttpUriRequest httpUriRequest = (HttpUriRequest) args[0];
            WechatpayAPI wechatpayAPI = (WechatpayAPI) args[1];
            String requestText = (String) args[2];
            String requestCiphertext = (String) args[3];
            log.setName(wechatpayAPI.getName());
            log.setUrl(httpUriRequest.getURI().toString());
            log.setRequestHeader(httpUriRequest.getAllHeaders().toString());
            log.setRequestCiphertext(requestCiphertext);
            log.setRequestText(requestText);
        }
        Object result = joinPoint.proceed();
        log.setResponseText(result.toString());
        log.setRequestTime(LocalDateTime.now());
        log.setCostTime(System.currentTimeMillis() - currentTime);
        wechatpayLogService.save(log);
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
        if (log == null) {
            log = new WechatpayLogDTO();
        }
        log.setCostTime(System.currentTimeMillis() - currentTime);
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
        wechatpayLogService.save(log);
    }
}
