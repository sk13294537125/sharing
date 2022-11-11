package com.sharing.cn.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    /**
     * 人民币 元 转换为 分 的字符串
     *
     * @param bigDecimal
     * @return
     */
    public static String yuanToFenStr(BigDecimal bigDecimal) {
        return bigDecimal.multiply(ONE_HUNDRED).setScale(0, RoundingMode.DOWN).toString();
    }

    /**
     * 人民币 分 字符串 转换为 元
     *
     * @param fenStr
     * @return
     */
    public static BigDecimal fenStrToYuan(String fenStr) {
        BigDecimal bigDecimal = new BigDecimal(fenStr);
        return bigDecimal.divide(ONE_HUNDRED).setScale(2, RoundingMode.DOWN);
    }

    public static void main(String[] args) {

    }

}
