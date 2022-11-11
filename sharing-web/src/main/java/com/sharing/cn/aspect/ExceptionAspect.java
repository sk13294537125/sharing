package com.sharing.cn.aspect;

import com.sharing.cn.annotation.InsException;
import com.sharing.cn.exception.AppRunException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ExceptionAspect
 * 异常处理切面配置
 *
 */
@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    @Pointcut("@within(com.sharing.cn.annotation.InsException)")
    public void pointcutInterface() {
    }

    @Order(2)
    @Around("pointcutInterface()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            InsException insException = joinPoint.getTarget().getClass().getAnnotation(InsException.class);
            if (null == insException) {
                throw throwable;
            }
            if (insException.throwEx()) {
                throw throwable;
            }
            if (!insException.defaultRes()) {
                return null;
            }
            if (joinPoint.getSignature() instanceof MethodSignature) {
                try {
                    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                    Class<?> methodClass = signature.getMethod().getReturnType();
                    Object ob = methodClass.newInstance();
                    if (StringUtils.isNotBlank(insException.resSuccessField())) {
                        methodClass.getMethod(insException.resSuccessField(), boolean.class).invoke(ob, false);
                    }
                    if (StringUtils.isNotBlank(insException.resCodeField())) {
                        methodClass.getMethod(insException.resCodeField(), String.class).invoke(ob, "9999");
                    }
                    if (StringUtils.isNotBlank(insException.resMsgField())) {
                        methodClass.getMethod(insException.resMsgField(), String.class).invoke(ob, "处理失败");
                        if (throwable instanceof AppRunException) {
                            if (StringUtils.isNotBlank(throwable.getMessage())) {
                                methodClass.getMethod(insException.resMsgField(), String.class).invoke(ob, throwable.getMessage());
                            }
                        }
                    }
                    return ob;
                } catch (Exception e) {
                    // 拖底异常
                    log.error("ExceptionAspect 拖底异常", e);
                }
            }
            return null;
        }
    }
}