package com.sharing.cn.controller.ak.yuchen.common;

import lombok.Data;


@Data
public class AkResp {

     private boolean Error;

     private DataDTO Data;

     public AkResp(DataDTO data) {
          Data = data;
     }
}
