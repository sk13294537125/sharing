package com.sharing.cn.common.dto.tianyan;

import lombok.Data;

@Data
public class ParentResult {

    private String Status;
    private String Message;
    private String OrderNumber;
    private VerifyResult Result;

}
