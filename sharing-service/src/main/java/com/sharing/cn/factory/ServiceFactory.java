package com.sharing.cn.factory;

import com.sharing.cn.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BussnessServiceFactory
 *
 * @author ext.shikai1
 */
@Component
@Slf4j
public class ServiceFactory {

    Map<String, Service> businessServiceMap = new HashMap<>();

    public ServiceFactory(List<Service> bussnessOaServices) {
        for (Service service : bussnessOaServices) {
            businessServiceMap.put(service.getCode(), service);
        }
    }
    //
    //public AfsStepResultService getCodeByServiceType(String stepType, String afsResultType) {
    //    StepTypeEnum stepTypeEnum = StepTypeEnum.getCodeByEnum(stepType);
    //    AfsResultTypeEnum afsResultTypeEnum = AfsResultTypeEnum.getCodeByEnum(afsResultType);
    //
    //    AfsStepResultService serviceImpl = null;
    //    // 创建
    //    if (StepTypeEnum.APPLY.getCode().equals(stepType) && AfsResultTypeEnum.APPLY.getCode().equals(afsResultType)) {
    //        serviceImpl = businessServiceMap.get(AfsStepResultServiceEnum.AFS_STEP_RESULT_APPLY_SERVICE.getCode());
    //    }
    //
    //    // 取消
    //    if (StepTypeEnum.AUDIT.getCode().equalsIgnoreCase(stepType)) {
    //        if (AfsResultTypeEnum.AUDIT_FAIL.getCode().equalsIgnoreCase(afsResultType)
    //                || AfsResultTypeEnum.CANCEL.getCode().equalsIgnoreCase(afsResultType)) {
    //            serviceImpl = businessServiceMap.get(AfsStepResultServiceEnum.AFS_STEP_RESULT_CANCEL_SERVICE.getCode());
    //        }
    //    }
    //    if (StepTypeEnum.PROCESS.getCode().equalsIgnoreCase(stepType) && AfsResultTypeEnum.FORCECOMPLETE.getCode().equalsIgnoreCase(afsResultType)) {
    //        serviceImpl = businessServiceMap.get(AfsStepResultServiceEnum.AFS_STEP_RESULT_CANCEL_SERVICE.getCode());
    //    }
    //
    //    // 退款
    //    if (StepTypeEnum.CONFIRM.getCode().equalsIgnoreCase(stepType) && AfsResultTypeEnum.REFUND.getCode().equalsIgnoreCase(afsResultType)) {
    //        serviceImpl = businessServiceMap.get(AfsStepResultServiceEnum.AFS_STEP_RESULT_REFUND_SERVICE.getCode());
    //    }
    //
    //    if (null != serviceImpl) {
    //        return serviceImpl;
    //    } else {
    //        log.error("根据code未能获取到业务单服务");
    //        return null;
    //    }
    //}
}
