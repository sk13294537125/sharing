package com.sharing.cn.controller.yuchen.ak.common;

import lombok.Data;

@Data
public class BaseReq {
     private String account;
     private String password;

     private String key = "DFE4C37175ACE3C28EE1E80A6B62BD02";
     private String UserID = "4532629";
     private Integer v = 2121;
     private String lang = "cn";

}
