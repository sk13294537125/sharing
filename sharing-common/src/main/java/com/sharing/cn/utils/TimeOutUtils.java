package com.sharing.cn.utils;


import java.util.Date;

/**
 * TimeOutUtils
 * 计算超时时间工具类
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