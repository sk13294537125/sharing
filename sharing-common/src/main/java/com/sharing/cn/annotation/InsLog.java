package com.sharing.cn.annotation;

import java.lang.annotation.*;

/**
 * 方法级Log出入参日志
 *
 * @author ext.shikai1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface InsLog {
    /**
     * 是否开启日志
     */
    boolean enabled() default true;

    /**
     * 中文方法名
     */
    String value() default "";

    /**
     * 是否打印入参日志，默认是
     */
    boolean reqLog() default true;

    /**
     * 是否打印出参日志，默认是
     */
    boolean resLog() default true;

    /**
     * 是否打印耗时日志，默认是
     */
    boolean cost() default true;

}