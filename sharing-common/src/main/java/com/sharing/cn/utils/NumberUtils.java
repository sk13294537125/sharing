package com.sharing.cn.utils;

import java.util.*;

public class NumberUtils {

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void generateLotteryNumbers() {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(random.nextInt(33) + 1);
        }
        Collections.sort(list);
        System.out.println("红球号码：" + list);

        int blueBall = random.nextInt(16) + 1;
        System.out.println("蓝球号码：" + blueBall);
    }


}
