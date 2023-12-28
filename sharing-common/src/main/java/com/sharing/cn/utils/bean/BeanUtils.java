package com.sharing.cn.utils.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ext.shikai1
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /** Bean方法名中属性名开始的下标 */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    public static void copyPropertiesList(List<Object> sourceList, List<Object> targetList) {
        for (Object source : sourceList) {
            Object target = new Object();
            copyProperties(source, target);
            targetList.add(target);
        }
    }

    public static List<Object> copyPropertiesList(List<Object> sourceList, Object target) {
        List<Object> targetList = new ArrayList<>();
        for (Object source : sourceList) {
            copyProperties(source, target);
            targetList.add(target);
        }
        return targetList;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     *
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

}
