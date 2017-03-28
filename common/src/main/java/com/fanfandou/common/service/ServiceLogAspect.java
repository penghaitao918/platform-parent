package com.fanfandou.common.service;

import com.alibaba.fastjson.JSON;
import com.fanfandou.common.base.BaseLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:服务层log切面.
 * Date:2016/10/17
 */
@Aspect
@Component
public class ServiceLogAspect extends BaseLogger {
    private String requestPath = null; // 请求地址
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private Map<?, ?> inputParamMap = null; // 传入参数
    private Map<String, Object> outputParamMap = new HashMap<>(); // 存放输出结果
    private String requestParams = null;


    @Before("execution(* com.fanfandou.platform..service..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        logger.debug("doBeforeInServiceLayer");
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

    @After("execution(* com.fanfandou.platform..service..*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        logger.debug("doAfterInServiceLayer");
        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
        this.printLog();
    }

    @Around("execution(* com.fanfandou.platform..service..*.*(..))")
    public Object doAroud(ProceedingJoinPoint pjp) throws Throwable {
        StringBuilder paramSb = new StringBuilder();
        for (int i = 0; i < pjp.getArgs().length; i++) {
            paramSb = paramSb.append(pjp.getArgs()[i]);
            if (i != pjp.getArgs().length - 1) {
                paramSb.append(",");
            }
        }
        requestPath = pjp.getSignature().getName();
        requestParams = paramSb.toString();

        Object result = pjp.proceed();
        outputParamMap.put("result", result);
        return result;
    }

    private void printLog() {
        logger.info("printLog");
        String inputContent = requestParams;
        String outPutContent = JSON.toJSONString(outputParamMap);
        logger.info("请求内容为:{},\n 输出内容为:{},\n 耗时:{} 毫秒", requestPath + ":" + inputContent, outPutContent,
                endTimeMillis - startTimeMillis);
    }
}
