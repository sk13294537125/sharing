package com.sharing.cn.annotation;

import java.lang.annotation.*;

/**
 * InsException
 * 异常catch
 *
 * @author ext.shikai1
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InsException {

    /**
     * 是否抛出异常,默认不抛出
     *
     * @return 是否抛出异常
     */
    boolean throwEx() default false;

    /**
     * 是否默认返回
     *
     * @return 是否默认返回
     */
    boolean defaultRes() default true;

    /**
     * 返回字段列表-是否成功
     *
     * @return 是否成功
     */
    String resSuccessField() default "setSuccess";

    /**
     * 返回字段列表-返回码
     *
     * @return 返回码
     */
    String resCodeField() default "setResultCode";

    /**
     * 返回字段列表-返回消息
     *
     * @return 返回消息
     */
    String resMsgField() default "setResultMsg";
}
