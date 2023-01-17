package com.sharing.cn.utils;

public class JWJSort {

//    public static void main(String[] args) {
//        int[] arr = {1, 4, 5, 6, 9, 0, 7};
//        System.out.println(System.currentTimeMillis());
//        int[] sort = sort(arr);
//        System.out.println(System.currentTimeMillis());
//        for (int i : sort) {
//            System.out.println(i);
//        }
//
//        System.out.println(System.currentTimeMillis());
//        int[] bubble = bubble(arr);
//        System.out.println(System.currentTimeMillis());
//        for (int i : bubble) {
//            System.out.println(i);
//        }
//    }

    public static int[] bubble(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                //如果后一个数小于前一个数交换
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
        return data;
    }

    public static int[] sort(int[] arrys) {
        int tmp = 0;
        for (int i = 0; i < arrys.length / 2; i++) {
            boolean isSorted = true;
            for (int j = i; j < arrys.length - i - 1; j++) {
                if (arrys[j] > arrys[j + 1]) {
                    tmp = arrys[j];
                    arrys[j] = arrys[j + 1];
                    arrys[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
            isSorted = true;
            for (int j = arrys.length - i - 1; j > i; j--) {
                if (arrys[j] < arrys[j - 1]) {
                    tmp = arrys[j];
                    arrys[j] = arrys[j - 1];
                    arrys[j - 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
        return arrys;
    }
}
