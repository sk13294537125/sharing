package com.sharing.cn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResultCodeEnum简介
 * 返回Code枚举
 *
 */
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {
    /**
     * 成功返回枚举
     */
    SUCCESS("0000", "处理成功"),
    /**
     * 失败返回枚举
     */
    FAILED("9999", "处理失败"),
    ;
    /**
     * 返回码
     */
    private final String code;
    /**
     * 返回码说明
     */
    private final String msg;
}