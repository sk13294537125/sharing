package com.sharing.cn.manager.impl;

import com.sharing.cn.dao.service.OperationLogDao;
import com.sharing.cn.domain.pojo.OperationLog;
import com.sharing.cn.manager.OperationLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ext.shikai1
 */
@Component
public class OperationLogManagerImpl implements OperationLogManager {

    @Autowired
    private OperationLogDao operationLogDao;

    @Override
    public void saveLog(String bizType, String requestJson, String responseJson) {
        OperationLog log = new OperationLog();
        log.setBizType(bizType);
        log.setRequestData(requestJson);
        log.setResponseData(responseJson);
        log.setCreateTime(new Date());
        operationLogDao.save(log);
    }
}
