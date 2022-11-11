package com.sharing.cn.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 公共入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Req implements Serializable {

    private static final long serialVersionUID = 2;

    /**
     * 当前页数
     */
    private Integer pageIndex;

    /**
     * 每页条数
     */
    private Integer pageSize;
}
