package com.sharing.cn.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AppRunException
 * 内部异常
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AppRunException extends RuntimeException {

    /**
     * 是否输出堆栈
     */
    private boolean showThrowable;

    /**
     * 异常构造函数
     *
     * @param message 错误信息
     */
    public AppRunException(String message) {
        super(message);
    }

    /**
     * 异常构造函数
     *
     * @param message 错误信息
     * @param cause   堆栈
     */
    public AppRunException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 异常构造函数
     *
     * @param cause 堆栈
     */
    public AppRunException(Throwable cause) {
        super(cause);
        this.showThrowable = true;
    }
}