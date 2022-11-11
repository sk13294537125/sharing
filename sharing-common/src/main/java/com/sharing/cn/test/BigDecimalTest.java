package com.sharing.cn.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author ext.shikai1
 */
public class BigDecimalTest {

    @Test
    public void test() {
        BigDecimal maxRefundPrice = new BigDecimal("0.00");
        maxRefundPrice = maxRefundPrice.subtract(new BigDecimal("0.01"));
        System.out.println(maxRefundPrice);
    }
}
