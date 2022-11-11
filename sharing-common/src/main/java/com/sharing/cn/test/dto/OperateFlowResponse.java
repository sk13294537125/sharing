package com.sharing.cn.test.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author ext.shikai1
 */
@Data
public class OperateFlowResponse {

    /**
     * 成功：20000 失败：500
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据列表实例
     */
    private Map<String,Object> data;

    private boolean success;
}
