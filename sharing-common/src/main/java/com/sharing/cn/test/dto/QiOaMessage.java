package com.sharing.cn.test.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ext.shikai1
 */
@Data
public class QiOaMessage implements Serializable {

    static final long serialVersionUID = 1821887098985662952L;

    private String eventType;
    private String modelKey;
    private String modelName;
    private String msgInfo;
    private String processID;
    private String systemId;
    private String sourceSystemId;
    private List<String> spaceSystemList;
}
