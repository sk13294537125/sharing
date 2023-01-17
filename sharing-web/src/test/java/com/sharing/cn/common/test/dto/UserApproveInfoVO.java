package com.sharing.cn.common.test.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author ext.shikai1
 */
@Data
public class UserApproveInfoVO {

    /**
     * 办理时间
     */
    private Date currentApproveTime;

    /**
     * 办理结果：0待办 ；1同意；2拒绝；3提交；6驳回；7后加签；8前加签；9转派；10终止
     */
    private Integer operationStatus;

    private String taskType;

    /**
     * 当前状态：0未审批 ；1已审批；2待阅；3已阅
     */
    private String currentStatus;

    /**
     * 流程实例ID
     */
    private String processId;

    /**
     * 创建时间
     */
    private Date currentCreateTime;

    /**
     * 审批意见
     */
    private String approveContent;


    /**
     * taskName
     */
    private String taskName;

    /**
     * 当前审批人
     */
    private String currentApproveErp;

    /**
     * 运行ID
     */
    private String runId;

    /**
     * 任务ID
     */
    private String taskId;
}
