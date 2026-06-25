package com.sharing.cn.controller.yuchen.ak.common;

import lombok.Data;

import java.util.List;

@Data
public class DataDTO<T> {

     private Integer PageSize;
     private Integer Count;
     private List<T> List;
}
