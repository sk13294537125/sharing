package com.sharing.cn.manager;

/**
 * @author ext.shikai1
 */
public interface OperationLogManager {

    /**
     * 请求操作
     * @param bizType 请求类型
     * @param requestJson 入参
     * @param responseJson 出餐
     */
    void saveLog(String bizType, String requestJson, String responseJson);
}
