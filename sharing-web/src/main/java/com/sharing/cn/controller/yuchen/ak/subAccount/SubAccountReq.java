package com.sharing.cn.controller.yuchen.ak.subAccount;

import com.sharing.cn.controller.yuchen.ak.common.BaseReq;
import lombok.Data;

@Data
public class SubAccountReq extends BaseReq {
     private Integer p = 1;
     private Integer pageSize = 100;


}
