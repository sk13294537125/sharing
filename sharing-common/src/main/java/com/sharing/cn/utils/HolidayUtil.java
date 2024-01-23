package com.sharing.cn.utils;

import com.sharing.cn.utils.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.util.Date;

/**
 * @author ext.shikai1
 */
@Slf4j
public class HolidayUtil {

    public static final String WORKDAY = "0";
    public static final String WEEKEND = "1";
    public static final String HOLIDAYS = "2";

    /**
     * 判断当前是否为节假日： 0 工作日  1 周末  2 节假日
     * @param httpArg :查询日期
     * @return 返回结果
     */
    public static String queryHoliday(String httpArg) {
        String httpUrl = "http://tool.bitefu.net/jiari/";
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?d=" + httpArg;
        return HttpUtil.get(httpUrl);
    }

    public static void main(String[] args) {
        String jsonResult = HolidayUtil.queryHoliday(DateUtils.date2Str(new Date(), DateUtils.PATTERN_YYYYMMDD));
        // 0 上班 1周末 2节假日
        if (WORKDAY.equals(jsonResult)) {
            log.info("0上班日");
        }

        if (WEEKEND.equals(jsonResult)) {
            log.info("1周末");
        }

        if (HOLIDAYS.equals(jsonResult)) {
            log.info("2节假日");
        }
    }
}
