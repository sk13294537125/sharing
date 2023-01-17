package com.sharing.cn.common.test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * GlobalOcsResult
 *
 */
@Getter
@Setter
@ToString
public class GlobalOcsResult {
    /**
     * 响应编码
     */
    private String code;

    /**
     * 响应原因描述
     */
    private String reason;

    /**
     * 响应数据
     */
    private String data;
}
