package com.sharing.cn.utils.bean;

import java.util.Collection;
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
     * 实体类与VO对象列表的copy
     * 转换后以源列表为主，默认为ArrayList
     *
     * @param source      数据源列表
     * @param targetClass 目标列表中对象类Class
     * @param <T>         目标列表类型
     * @param <E>         数据源列表类型
     * @return
     */
    public static<T, E> List<T> copyList(List<E> source, Class<T> targetClass) {
        List<T> target;
        try {
            target = source.getClass().newInstance();
            copyCollection(source, target, targetClass);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 复制集合
     * @param source
     * @param target
     * @param targetClass
     * @param <T>
     * @param <S>
     */
    private static<T, S> void copyCollection(Collection<S> source, Collection<T> target, Class<T> targetClass) {
        source.stream().forEach(item -> {
            try {
                T t;
                t = targetClass.newInstance();
                org.springframework.beans.BeanUtils.copyProperties(item, t);
                target.add(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
