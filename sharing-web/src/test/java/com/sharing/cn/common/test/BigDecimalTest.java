package com.sharing.cn.common.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author ext.shikai1
 */
public class BigDecimalTest {

    @Test
    public void test() {
        BigDecimal maxRefundPrice = new BigDecimal("-0.10");
        BigDecimal maxRefundPrice1 = maxRefundPrice.subtract(new BigDecimal("-0.01"));
        System.out.println(maxRefundPrice.compareTo(maxRefundPrice1));
    }
}
