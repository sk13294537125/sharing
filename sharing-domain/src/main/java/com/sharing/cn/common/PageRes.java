package com.sharing.cn.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 公共分页返回
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRes<T> extends Res implements Serializable {

    private static final long serialVersionUID = 3;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 总条数
     */
    private Long totalCount;

    /**
     * 信息
     */
    private List<T> values;
}
