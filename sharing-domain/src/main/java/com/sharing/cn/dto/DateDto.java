package com.sharing.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ext.shikai1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateDto {

    /**
     * 开始
     */
    private Date startDate;
    /**
     * 结束
     */
    private Date endDate;
}
