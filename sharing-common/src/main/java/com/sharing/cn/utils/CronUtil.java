package com.sharing.cn.utils;

import com.sharing.cn.common.TaskScheduleModel;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

/**
 * @author ext.shikai1
 */
@Slf4j
public class CronUtil {

    /**
     * 方法摘要：构建Cron表达式
     *
     * @param taskScheduleModel
     * @return String
     */
    public static String createCronExpression(TaskScheduleModel taskScheduleModel) {

        //拆分时间字符串 年，月，日，时，分，秒
        String[] split1 = taskScheduleModel.getStartDate().split("[ \\-:]");

        StringBuilder cronExp = new StringBuilder();

        if (null == taskScheduleModel.getJobType()) {
            log.info("执行周期未配置");
            return null;
        }

        Integer jobType = taskScheduleModel.getJobType();
        if (null != split1[5] && null != split1[4] && null != split1[3]) {
            if (0 == jobType) {
                //秒
                cronExp.append(split1[5]).append(" ");
                //每分钟
                cronExp.append("* ").append(" ");
                //小时
                cronExp.append("* ");
                //日
                cronExp.append("* ");
                //月
                cronExp.append("* ");
                //周
                cronExp.append("?");
            }
            if (1 == jobType) {
                //秒
                cronExp.append(split1[5]).append(" ");
                //分
                cronExp.append(split1[4]).append(" ");
                //每小时
                cronExp.append("* ");
                //日
                cronExp.append("* ");
                //月
                cronExp.append("* ");
                //周
                cronExp.append("?");
            }
            if (2 == jobType || 3 == jobType ||
                    4 == jobType || 5 == jobType) {
                //秒
                cronExp.append(split1[5]).append(" ");
                //分
                cronExp.append(split1[4]).append(" ");
                //时
                cronExp.append(split1[3]).append(" ");
            }
            //按每日
            if (2 == jobType) {
                //日
                cronExp.append("* ");
                //月
                cronExp.append("* ");
                //周
                cronExp.append("?");
            }
            //按每周
            if (3 == jobType) {
                String[] split2 = taskScheduleModel.getStartDate().split(" ");
                Calendar instance = Calendar.getInstance();
                int dayForWeek = instance.get(Calendar.DAY_OF_WEEK) - 1;
                //获取本周的周几
//                int dayForWeek = DataUtils.dayForWeek(split2[0]);
                //一个月中第几天
                cronExp.append("? ");
                //月份
                cronExp.append("* ");
                //周
                cronExp.append(dayForWeek + 1);
            }
            //按每月
            if (4 == jobType) {
                //一个月中的哪几天
                cronExp.append(split1[2]);
                //月份
                cronExp.append(" * ");
                //周
                cronExp.append("?");
            }
            //按每年
            if (5 == jobType) {

                //一个月中的哪几天
                cronExp.append(split1[2]).append(" ");
                //月份
                cronExp.append(split1[1]).append(" ");
                //周
                cronExp.append("?");
            }
            return cronExp.toString();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        TaskScheduleModel taskScheduleModel = new TaskScheduleModel(2, "2022-12-20 01:00:00");
        String cronExpression = createCronExpression(taskScheduleModel);
        System.out.println(cronExpression);
    }

}

