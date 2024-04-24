package com.sharing.cn.common.test.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ext.shikai1
 */
@Data
public class MsgInfo implements Serializable {

    static final long serialVersionUID = 1821887098985662951L;

    private String formData;
    private String processStatus;
    private String submitTime;
    private String submitResult;
}
