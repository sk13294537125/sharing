package com.sharing.cn.aspect;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.annotation.LogPersist;
import com.sharing.cn.manager.OperationLogManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 网关日志相关切面实现
 *
 * @author : baihuaiyu
 * @date : 2019/9/6 9:43
 * @version : 1.0
 */

@Order(1)
@Aspect
@Component
@Slf4j
public class LogPersistProxy {

    /**
     * 网关日志服务
     */
    @Autowired
    private OperationLogManager operationLogManager;

    /**
     * 切面入库
     *
     * @param joinPoint
     * @param logPersist
     * @throws Throwable
     */
    @Around(value = "@annotation(logPersist)")
    public Object doPointCut(ProceedingJoinPoint joinPoint, LogPersist logPersist) throws Throwable {
        String bizType = logPersist.bizType();
        Object[] objects = joinPoint.getArgs();
        //从三个入参中获取业务数据
        String createBy = "";

        if (objects[1] instanceof String) {
            createBy = (String) objects[1];
        }

        Object requestObj = objects[0];
        try {
            Object response = joinPoint.proceed();
            operationLogManager.saveLog(bizType, JSON.toJSONString(requestObj), JSON.toJSONString(response));
            return response;
        } catch (Exception e) {
            log.error("存储操作日志异常", e);
            operationLogManager.saveLog(bizType, JSON.toJSONString(requestObj), e.getLocalizedMessage() + e.getMessage());
            //日志切面最后收到错误信息，直接向上抛出
            throw e;
        }
    }
}
