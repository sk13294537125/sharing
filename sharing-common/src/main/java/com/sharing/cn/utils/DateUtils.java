package com.sharing.cn.utils;


import cn.hutool.core.date.DateTime;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * DateUtils
 * 日期工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * CHINA_ZONE
     */
    public static final ZoneId CHINA_ZONE = ZoneId.systemDefault();
    /**
     * 
     */
	public static final String DATE_LONG_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static final String DT_LONG = "yyyyMMddHHmmss";
    public static final String DT_LONG_2 = "yyMMddHHmmss";
    public static final String DT_LONG_3 = "yyMMdd";
    public static final String DATE_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_ALL2 = "yy/MM/dd HH:mm";
    public static final String DATE_FORMAT_ALL3 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_ALL4 = "yy-MM-dd HH:mm";
    public static final String DATE_FORMAT_ALL5 = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_YYYY_MM_DD2 = "yyyy/MM/dd";
    public static final String PATTERN_YYYY_MM_DD3 = "yyyy年MM月dd日";
    public static final String PATTERN_YYYY_MM_DD4 = "MM月dd日";
    public static final String PATTERN_YYYY_MM = "yyyy-MM";
    public static final String PATTERN_YYYY_MM2 = "yyyyMM";
    public static final String PATTERN_YYYY_MM3 = "MMdd";
    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    public static final String PATTERN_MMDD = "MMdd";
    public static final String PATTERN_YYYY_MM_DD_2 = "yyyy.MM.dd";
    public static final String PATTERN_HH_MM_SS = "HHmmss";
    public static final String PATTERN_YYYY = "yyyy";
    public static final String DT_LONG_MILLI = "yyyyMMddHHmmssSSS";

	@Test
    public void get() {
        String stringTime = getStringTime(1629302400000L);
        System.out.println(stringTime);
    }

    /**
     * @desc  时间戳转字符串
     * @example timestamp=1558322327000
     **/
    public static String getStringTime(Long timestamp) {

        return new SimpleDateFormat(DATE_LONG_FORMAT).format(new Date(timestamp));
    }

    /**
     * 当前时间字符串
     *
     * @param format 格式
     * @return 字符串
     */
    public static String nowStr(String format) {
        if (StringUtils.isBlank(format)) {
            return null;
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static Date strToDate(String str) {
        return strToDate(str, DATE_LONG_FORMAT);
    }

    public static Date strToDate(String str, String format) {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return parseDate(str, format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * LocalDateTime 转 字符串
     *
     * @param localDate LocalDateTime对象
     * @param format    格式
     * @return 字符串
     */
    public static String localDateTime2Str(LocalDateTime localDate, String format) {
        if (null == localDate || StringUtils.isBlank(format)) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * date 转 字符串
     * @param date   Date对象
     * @return 字符串
     */
    public static String date2Str(Date date, String format) {
        if (null == date || StringUtils.isBlank(format)) {
            return null;
        }
        return date2LocalDateTime(date).format(DateTimeFormatter.ofPattern(format));
    }
    /**
     * date 转 字符串
     *
     * @param date   Date对象
     * @return 字符串
     */
    public static String date2LongStr(Date date) {
        if (null == date) {
            return null;
        }
        return date2LocalDateTime(date).format(DateTimeFormatter.ofPattern(DATE_LONG_FORMAT));
    }
    /**
     * 字符串 转 LocalDateTime
     *
     * @param str    字符串
     * @param format 格式
     * @return LocalDate对象
     */
    public static LocalDateTime str2LocalDateTime(String str, String format) {
        if (StringUtils.isAnyBlank(str, format)) {
            return null;
        }
        try {
            Date date = parseDate(str, format);
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 构造时分秒为0的LocalDateTime对象
     *
     * @return LocalDateTime对象
     */
    public static LocalDateTime localDateTimeNoTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.of(0, 0, 0, 0);
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * 构造时分秒为0的LocalDateTime对象
     *
     * @return LocalDateTime对象
     */
    public static LocalDateTime localDateTimeNoTime(LocalDateTime localDateTime) {
        LocalTime localTime = LocalTime.of(0, 0, 0, 0);
        return LocalDateTime.of(localDateTime.toLocalDate(), localTime);
    }

    /**
     * LocalDate 转换为 Date
     *
     * @param localDate LocalDate
     * @return Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Optional.ofNullable(localDate).map(o -> Date.from(o.atStartOfDay(CHINA_ZONE).toInstant())).orElse(null);
    }


    /**
     * LocalDateTime 转换为 Date
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime).map(o -> Date.from(o.atZone(CHINA_ZONE).toInstant())).orElse(null);
    }

    /**
     * Date 转换为 LocalDateTime
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return Optional.ofNullable(date).map(o -> o.toInstant().atZone(CHINA_ZONE).toLocalDateTime()).orElse(null);
    }

    public static DateTime dateToDateTime(Date date) {
        DateTime dateTime = new DateTime();
        dateTime.setTime(date.getTime());
        return dateTime;
    }

    public static String dateToStr(String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return format1.format(new Date());
    }

    /**
     * 加几个小时
     */
    public static Date plusHours(Date date, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, hours);
        return c.getTime();
    }


    /**
     * 加 天
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    /**
     * @Title: parseStart
     * @Description: 获取当前日期的零时零分零秒（yyyy-MM-dd 00:00:00）
     * @param  date 传入日期
     * @return Date    返回类型
     */
    public static Date parseStart(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        Date parDate = strToDate(date, PATTERN_YYYY_MM_DD);
        if (null == parDate) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * @Title: compareDate
     * @Description: 比较两个日期大小
     * @param  date1 日期1
     * @param  date2 日期2
     * @return int    返回类型 1:date1大于date2, -1：date1小于date2, 0:date1等于date2
     */
    public static int compareDate(Date date1, Date date2) {
        try {
            return Long.compare(date1.getTime(), date2.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    //获取某个日期的开始时间
    public static Date getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //获取本周的结束时间
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.getTime();
    }
}
