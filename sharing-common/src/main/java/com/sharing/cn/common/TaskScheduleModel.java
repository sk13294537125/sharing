package com.sharing.cn.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ext.shikai1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskScheduleModel {
    /**
     * 所选作业类型:
     * 0  -> 每分钟
     * 1  -> 每小时
     * 2  -> 每天
     * 3  -> 每周
     * 4  -> 每月
     * 5  -> 每年
     */
    private Integer jobType;
    //启动时间
    private String startDate;


}

