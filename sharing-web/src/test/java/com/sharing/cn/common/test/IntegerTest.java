package com.sharing.cn.common.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author ext.shikai1
 */
@Slf4j
public class IntegerTest {

    @Test
    public void parseInt() throws NumberFormatException {
        String s1 = "920220819092925030229";
        String s = s1.substring(0, 9);
        System.out.println(s);
        int radix = 10;
        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " greater than Character.MAX_RADIX");
        }

        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+') {
                    System.out.println("For input string");
                }

                if (len == 1) {
                    // Cannot have lone "+" or "-"
                    System.out.println("For input string");
                }
                i++;
            }
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) {
                    System.out.println("For input string");
                }
                if (result < multmin) {
                    System.out.println("For input string");
                }
                result *= radix;
                if (result < limit + digit) {
                    System.out.println("For input string");
                }
                result -= digit;
            }
        } else {
            System.out.println("For input string");
        }
        int i1 = negative ? result : -result;
        System.out.println(i1);
    }



}
