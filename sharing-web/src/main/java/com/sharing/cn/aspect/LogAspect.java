package com.sharing.cn.aspect;

import com.alibaba.fastjson.JSONObject;
import com.sharing.cn.annotation.InsLog;
import com.sharing.cn.exception.AppRunException;
import com.sharing.cn.utils.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

/**
 * LogAspect
 * 请求日志
 * Log切面配置
 *
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 请求Id
     */
    private static final String REQ_ID = "requestId";

    /**
     * 注解切面
     */
    @Pointcut("@annotation(com.sharing.cn.annotation.InsLog)")
    public void pointcutInterface() {
    }

    /**
     * Package切面
     */
    @Pointcut("execution(* com.sharing.cn..*.*(..))")
    public void pointcutPackage() {
    }

    @Order(1)
    @Around("pointcutInterface()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, String> mdc = MDC.getCopyOfContextMap();
        String key = joinPoint.getTarget().getClass().getSimpleName() + "#" + getMethodName(joinPoint) + "()";
        Object[] args = joinPoint.getArgs();
        // 日志说明
        String logDesc = "";
        // 是否输出log日志
        boolean reqLog = true, resLog = false, cost = true, throwEx = false;
        InsLog insLog = getInsLog(joinPoint);
        if (null != insLog) {
            if (!insLog.enabled()) {
                return joinPoint.proceed();
            }
            logDesc = insLog.value();
            reqLog = insLog.reqLog();
            resLog = insLog.resLog();
            cost = insLog.cost();
        }
        String reqId = MDC.get(REQ_ID);
        if (StringUtils.isBlank(reqId)) {
            reqId = System.currentTimeMillis() + "-" + Arrays.hashCode(args);
            MDC.put(REQ_ID, reqId);
        }
        Object res;
        if (reqLog) {
            log.info("【Log {}{}】{}，请求参数={}", logDesc, resLog ? "" : " 不输出返回", key, JSONObject.toJSONString(args));
        }
        long beginMs = SystemUtils.currentTimeMillis();
        try {
            res = joinPoint.proceed();
            // 由于多线程，会导致MDC清空，此处补全
            if (StringUtils.isBlank(MDC.get(REQ_ID))) {
                MDC.setContextMap(mdc);
            }
            if (resLog) {
                long costTime = 0L;
                if (cost) {
                    costTime = SystemUtils.currentTimeMillis() - beginMs;
                }
                log.info("【Log {}】{}{},返回结果={}", logDesc, key, costTime > 0L ? " 耗时=" + costTime + "ms" : "", JSONObject.toJSONString(res));
            }
            return res;
        } catch (Exception e) {
            if (!reqLog) {
                log.error("【Log {}】{}，异常-请求参数={}", logDesc, key, JSONObject.toJSONString(args));
            }
            if (e instanceof AppRunException) {
                throwEx = ((AppRunException)e).isShowThrowable();
            }
            if (throwEx) {
                log.error("【Log {}】{}，请求处理异常", logDesc, key, e);
            } else {
                log.error("【Log {}】{}，请求处理异常", logDesc, key);
            }
            throw e;
        }
    }

    /**
     * 获取InsLog
     *
     * @param joinPoint ProceedingJoinPoint
     * @return InsLog
     */
    private InsLog getInsLog(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            return methodSignature.getMethod().getAnnotation(InsLog.class);
        }
        return null;
    }

    /**
     * 获取方法名
     *
     * @param joinPoint ProceedingJoinPoint
     * @return 方法名
     */
    private String getMethodName(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            return methodSignature.getMethod().getName();
        }
        return "未获取到方法";
    }
}