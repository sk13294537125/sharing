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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("欢迎来到大乐透游戏！");

        int[] lotteryWhiteBalls = generateLotteryNumbers(random);
        int lotteryRedBall = random.nextInt(17) + 1;

        System.out.println("本期大乐透号码为：");
        System.out.print("白球号码：");
        for (int ball : lotteryWhiteBalls) {
            System.out.print(ball + " ");
        }
        System.out.println("\n红球号码：" + lotteryRedBall);

        int[] userWhiteBalls = new int[5];
        System.out.println("请输入5个白球号码（1-35）：");
        for (int i = 0; i < userWhiteBalls.length-1; i++) {
            userWhiteBalls[i] = scanner.nextInt();
        }

        int userRedBall = scanner.nextInt();
        System.out.println("请输入红球号码（1-17）：");

        checkWin(lotteryWhiteBalls, lotteryRedBall, userWhiteBalls, userRedBall);
    }

    private static int[] generateLotteryNumbers(Random random) {
        int[] result = new int[5];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(35) + 1;
        }
        return result;
    }

    private static void checkWin(int[] lotteryWhiteBalls, int lotteryRedBall, int[] userWhiteBalls, int userRedBall) {
        int matchCount = 0;
        for (int i = 0; i < userWhiteBalls.length; i++) {
            if (lotteryWhiteBalls[i] == userWhiteBalls[i]) {
                matchCount++;
            }
        }

        if (lotteryRedBall == userRedBall && matchCount == 5) {
            System.out.println("恭喜你中了一等奖！");
        } else if (matchCount == 4 && lotteryRedBall == userRedBall) {
            System.out.println("恭喜你中了二等奖！");
        } else if (matchCount == 3 && lotteryRedBall == userRedBall) {
            System.out.println("恭喜你中了三等奖！");
        } else if (matchCount == 2 && lotteryRedBall == userRedBall) {
            System.out.println("恭喜你中了四等奖！");
        } else if (matchCount == 1 && lotteryRedBall == userRedBall) {
            System.out.println("恭喜你中了五等奖！");
        } else {
            System.out.println("很遗憾，你没有中奖。");
        }
    }



}
