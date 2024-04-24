package com.sharing.cn.common.dto.tianyan;

import java.util.List;
import lombok.Data;

@Data
public class VerifyResult {

    private String VerifyResult;

    private List<ChildrenResult> Data;
}
