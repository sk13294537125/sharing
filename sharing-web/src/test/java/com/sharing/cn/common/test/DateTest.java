package com.sharing.cn.common.test;

import cn.hutool.core.date.DateTime;
import com.sharing.cn.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ext.shikai1
 */
@Slf4j
public class DateTest {

    @Test
    public void dateTimeTest() {

        DateTime dateTime = new DateTime();
        System.out.println(dateTime);

        String s = "2022-08-12 00:00:00";
        Date date = DateUtils.strToDate(s);
        dateTime.setTime(date.getTime());
        System.out.println(dateTime);
    }

    @Test
    public void test1() {
        Long applyTaskDeley=48*60*60*1000L;
        long deley = System.currentTimeMillis() + applyTaskDeley;
        Date bizTime = new Date(deley);
        System.out.println(DateUtils.date2LongStr(bizTime));

        Map<String,Object> data = new HashMap<>();
        data.put("isCancel", true);
        System.out.println(data.get("isCancel").toString());
        System.out.println("true".equals(data.get("isCancel").toString()));
    }

    @Test
    public void tets2() {
        String s1 = "2022-10-11 09:09:15";
        String s2 = "2022-10-11 19:19:15";
        Date beginDate = DateUtils.strToDate(s1);
        Date endDate = DateUtils.strToDate(s2);
        Calendar calFormat = Calendar.getInstance();
        Date morning = new Date(beginDate.getTime());
        calFormat.setTime(morning);
        // 将时分秒,毫秒域清零
        calFormat.set(Calendar.HOUR_OF_DAY, 9);
        calFormat.set(Calendar.MINUTE, 0);
        calFormat.set(Calendar.SECOND, 0);
        morning = calFormat.getTime();

        Date afternoon = new Date(beginDate.getTime());
        calFormat.setTime(afternoon);
        // 将时分秒,毫秒域清零
        calFormat.set(Calendar.HOUR_OF_DAY, 15);
        calFormat.set(Calendar.MINUTE, 0);
        calFormat.set(Calendar.SECOND, 0);
        afternoon = calFormat.getTime();

        Date evening = new Date(beginDate.getTime());
        calFormat.setTime(evening);
        // 将时分秒,毫秒域清零
        calFormat.set(Calendar.HOUR_OF_DAY, 19);
        calFormat.set(Calendar.MINUTE, 0);
        calFormat.set(Calendar.SECOND, 0);
        evening = calFormat.getTime();

        if (beginDate.compareTo(morning) < 0 && endDate.compareTo(morning) > 0) {
            System.out.println(1);
        }
        if (beginDate.compareTo(afternoon) < 0 && endDate.compareTo(afternoon) > 0) {
            System.out.println(2);
        }
        if (beginDate.compareTo(evening) < 0 && endDate.compareTo(evening) > 0) {
            System.out.println(3);
        }

    }

    public String format = DateUtils.dateToStr("YYYY-MM-dd HH:mm:ss");
    @Test
    public void date() {
        for (int i = 0; i < 2; i++ ) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(format);


        }
        String s = DateUtils.dateToStr("YYYY-MM-dd HH:mm:ss");
        System.out.println(s);
        System.out.println(format);

    }

    @Test
    public void test3() {
        Date begin = DateUtils.getBeginDayOfWeek();
        Date end = DateUtils.getEndDayOfWeek();
        System.out.println(begin);
        String s = DateUtils.date2Str(begin, DateUtils.PATTERN_YYYY_MM_DD3);
        System.out.println(s);
        System.out.println(end);
        String s1 = DateUtils.date2Str(end, DateUtils.PATTERN_YYYY_MM_DD3);
        System.out.println(s1);
    }

    @Test
    public void test4() {
        //boolean verifyInterval = isVerifyInterval(new Date());
        //System.out.println(verifyInterval);
        String s = "2022-11-29 13:56:53";
        Date endDate = DateUtils.addDays(DateUtils.strToDate(s), 90);
        System.out.println(DateUtils.date2Str(endDate, DateUtils.DATE_LONG_FORMAT));
        System.out.println(endDate.compareTo(new Date()) > 0);
    }

    private boolean isVerifyInterval(Date applyDate) {
        String verifyInterval = "180";
        Calendar applyTime = Calendar.getInstance();
        applyTime.setTime(applyDate);
        applyTime.add(Calendar.MINUTE, Integer.valueOf(verifyInterval));
        Calendar nowTime = Calendar.getInstance();
        System.out.println("applyTime:" + DateUtils.date2LongStr(applyTime.getTime()));
        System.out.println("nowTime:" + DateUtils.date2LongStr(nowTime.getTime()));
        nowTime.setTime(new Date());
        if (nowTime.getTimeInMillis() >= applyTime.getTimeInMillis()) {
            log.info("nowTime compare to true");
            return true;
        }
        return false;
    }
}
