package com.sharing.cn.common.test.test;


import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ListTest {

    @Test
    public void test() {
        int ads = con("-123");
        System.out.println(ads);
    }

    public Integer con(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("非法");
        }
        return 0;
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric1(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("1");
        stopWatch.start("start");
        long l1 = System.nanoTime();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(-1);
        list.add(-2);
        list.add(2);
        list.add(3);
        list.add(4);
stopWatch.stop();
stopWatch.start("stream");
        Set<Integer> collect = list.stream()
                .filter(l -> l > 0)
                .collect(Collectors.toSet());
        System.out.println(JSON.toJSONString(collect));

        List<Integer> list1 = new ArrayList<>(collect);
        Collections.reverse(list1);
        stopWatch.stop();
        System.out.println(list1);

        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println("大额资金数据上报-上传、上报DB文件-定时任务-结束，耗时：{}ms" + (System.nanoTime() - l1) / 1000000);

    }



}
