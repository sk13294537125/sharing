package com.sharing.cn.common.test.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ext.shikai1
 */
@Data
public class FormData implements Serializable {

    static final long serialVersionUID = 1821887098985662953L;

    private String ybGoodsSku;
    private String ybGoodsSkuUrl;
    private String firstLevelExaminer;
    private String orderSource;
    private String applyHandleProgramme;
    private String applyReasonClass;
    private String approvalType;
    private String applyHandleDetailedReason;
    private String mainGoods;
    private String userId;
    private String applyUserId;
    private String firstApprovalId;
    private String ybOrderId;
    private String applyFormSerialNo;
    private String performanceOrderNo;
    private String jdPrice;
    private String ybGoodsName;
    private String applyTime;
    private String applyInfo;
    private String applyTitle;

}
