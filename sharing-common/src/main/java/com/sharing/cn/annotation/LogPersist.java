package com.sharing.cn.annotation;


import java.lang.annotation.*;

/**
 * 网关请求流水持久化注解类
 *
 * @author : baihuaiyu
 * @date : 2019/9/6 9:45
 * @version : 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogPersist {

    /**
     * 熔断标识值（服务唯一）
     * @return
     */
    String bizType();

}
