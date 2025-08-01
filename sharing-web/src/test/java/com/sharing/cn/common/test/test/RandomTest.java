package com.sharing.cn.common.test.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTest {

    @Test
    public void lottery() {
        // 生成红球号码
        List<Integer> redBalls = new ArrayList<>();
        for (int i = 1; i <= 35; i++) {
            redBalls.add(i);
        }

        // 随机选择5个红球号码
        Random random = new Random();
        List<Integer> selectedRedBalls = redBalls.subList(random.nextInt(redBalls.size() - 4) + 1, random.nextInt(redBalls.size() - 3) + 1);
        selectedRedBalls.addAll(redBalls.subList(random.nextInt(redBalls.size() - selectedRedBalls.size()) + 1, redBalls.size()));

        // 排序并打印红球号码
        selectedRedBalls.sort((a, b) -> a - b);
        System.out.println("红球号码: " + selectedRedBalls);

        // 生成蓝球号码
        List<Integer> blueBalls = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            blueBalls.add(i);
        }

        // 随机选择2个蓝球号码
        List<Integer> selectedBlueBalls = blueBalls.subList(random.nextInt(blueBalls.size() - 1) + 1, blueBalls.size());

        // 排序并打印蓝球号码
        selectedBlueBalls.sort((a, b) -> a - b);
        System.out.println("蓝球号码: " + selectedBlueBalls);

    }

    @Test
    public void lunch() {
        String[] arr = {"牛肉面", "焖面", "麻辣烫", "和合谷", "汉堡", "米线"};
        // 创建 Random 对象
        Random random = new Random();
        // 生成指定范围内的随机整数
        System.out.println(arr[random.nextInt(arr.length)]);
    }



}
