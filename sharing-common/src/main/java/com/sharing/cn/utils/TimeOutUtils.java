package com.sharing.cn.utils;


import java.util.Date;

/**
 * TimeOutUtils
 * 计算超时时间工具类
 *
 * @date 2022/10/11 19:38
 */
public class TimeOutUtils {
    /**
     * 超时时间计算
     *
     * @param currentTime  开始时间
     * @param timeOutHours 超时时长
     * @return 超时时间
     */
    public static Date getTimeOutDateForHours(Date currentTime, int timeOutHours) {
        return getTimeOutDateForSecond(currentTime, timeOutHours * 60 * 60);
    }

    private static Date getTimeOutDateForSecond(Date currentTime, int timeOutSecond) {
        //createTime 服务单生成时间
        //当天时间凌晨
        Date todayOfZeroClock = DateUtils.parseStart(DateUtils.date2Str(currentTime, DateUtils.PATTERN_YYYY_MM_DD));
        //当前时间9点
        Date todayOfNineClock = DateUtils.plusHours(todayOfZeroClock, 9);
        //当天时间18点
        Date todayOfEighteenClock = DateUtils.plusHours(todayOfZeroClock, 18);
        // 判断当前时间是否是工作日
        boolean workDay = isWorkDay(currentTime);
        if (workDay) {
            //服务单生成时间早于9点, 则按照9点的时间顺延 timeOutSecond
            if (DateUtils.compareDate(currentTime, todayOfNineClock) < 0) {
                return DateUtils.plusHours(todayOfNineClock, timeOutSecond);
            }
            //服务单生成时间早于或等于18点, 则按照 服务单生成 的时间顺延 timeOutSecond
            if (DateUtils.compareDate(currentTime, todayOfEighteenClock) <= 0) {
                Date date = DateUtils.plusHours(currentTime, timeOutSecond);
                //如果当日的有效时间不够扣, 则剩余时间从第二天的有效时间中扣除
                if (DateUtils.compareDate(date, todayOfEighteenClock) <= 0) {
                    return date;
                }
                //计算出第二天要扣的有效时间
                int diffSecondsOfTomorrow = (int) (date.getTime() - todayOfEighteenClock.getTime()) / 1000;
                //剩余时间从第二天汇总扣除
                return getTimeOutDateForSecond(DateUtils.addDay(todayOfNineClock, 1), diffSecondsOfTomorrow);
            }
            //服务单生成时间晚于18点, 则按照 第二天9点 顺延 timeOutSecond
            return getTimeOutDateForSecond(DateUtils.addDay(todayOfNineClock, 1), timeOutSecond);
        }
        //如果不是工作日则
        return getTimeOutDateForSecond(DateUtils.addDay(todayOfNineClock, 1), timeOutSecond);
    }

    /**
     * 判断当前日期是不是工作日
     *
     * @param date
     * @return
     */
    private static boolean isWorkDay(Date date) {
        if (HolidayUtil.WORKDAY.equals(HolidayUtil.queryHoliday(DateUtils.date2Str(date, DateUtils.DT_LONG_3)))) {
            return false;
        }
        return true;
        // mock
        //if ("2022-09-26".equals(format)) { return true; }
        //if ("2022-09-27".equals(format)) { return true; }
        //if ("2022-09-28".equals(format)) { return true; }
        //if ("2022-09-29".equals(format)) { return true; }
        //if ("2022-09-30".equals(format)) { return true; }
        //if ("2022-10-01".equals(format)) { return false; }
        //if ("2022-10-02".equals(format)) { return false; }
        //if ("2022-10-03".equals(format)) { return false; }
        //if ("2022-10-04".equals(format)) { return false; }
        //if ("2022-10-05".equals(format)) { return false; }
        //if ("2022-10-06".equals(format)) { return false; }
        //if ("2022-10-07".equals(format)) { return false; }
        //if ("2022-10-08".equals(format)) { return true; }
        //if ("2022-10-09".equals(format)) { return true; }
        //if ("2022-10-10".equals(format)) { return true; }
        //if ("2022-10-11".equals(format)) { return true; }
        //if ("2022-10-12".equals(format)) { return true; }
        //if ("2022-10-13".equals(format)) { return true; }
        //if ("2022-10-14".equals(format)) { return true; }
        //if ("2022-10-15".equals(format)) { return false; }
        //if ("2022-10-16".equals(format)) { return false; }
        //if ("2022-10-17".equals(format)) { return true; }
        //if ("2022-10-18".equals(format)) { return true; }
        //if ("2022-10-19".equals(format)) { return true; }
        //if ("2022-10-20".equals(format)) { return true; }
        //if ("2022-10-21".equals(format)) { return true; }
        //if ("2022-10-22".equals(format)) { return false; }
        //if ("2022-10-23".equals(format)) { return false; }
        //return true;
    }

    public static void main(String[] args) {
        Date date2 = new Date();
        Date date = DateUtils.plusHours(date2, 2);
        Date date1 = DateUtils.addDay(date, 2);
        System.out.println("currentTime = " + date1);
        Date timeOutDate = getTimeOutDateForHours(date, 6);
        System.out.println("timeOutDate = " + timeOutDate);
    }
}