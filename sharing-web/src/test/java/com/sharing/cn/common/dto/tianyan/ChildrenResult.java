package com.sharing.cn.common.dto.tianyan;

import java.util.List;
import lombok.Data;

@Data
public class ChildrenResult {

    private String Name;
    private String Level;
    private String Percent;
    private String ShouldCapi;
    private List<ChildrenResult> ChildrenList;
}
