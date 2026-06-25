package com.sharing.cn.controller.ak.yuchen.transaction;

import com.sharing.cn.controller.ak.yuchen.common.BaseReq;
import lombok.Data;

@Data
public class TranReq  extends BaseReq {
     // 子账户Id
     private Integer sonId;
     // 助记词相关。id和key调接口获取
     private String mnemonicid1;
     private String mnemonickey;
     private String mnemonicstr1;
     private String gCode;
     // 出售数量
     private Integer count;

}
