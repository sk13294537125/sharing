package com.sharing.cn.controller.ak.yuchen.common;

import lombok.Data;

import java.util.List;

@Data
public class DataDTO<T> {

     private Integer PageSize;
     private Integer Count;
     private List<T> List;
}
