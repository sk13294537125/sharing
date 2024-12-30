package com.sharing.cn.utils;

import com.sharing.cn.domain.bo.BaseDataBo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectUtils {

    /**
     * 判断对象是否完全为空
     *
     * @param object
     * @return
     */
    public static boolean objectCheckIsNull(Object object) {
        //定义返回结果，默认为true
        boolean flag = true;

        if (Objects.isNull(object)) {
            return flag;
        } else {
            // 得到类对象
            Class<?> clazz = object.getClass();
            // 得到所有属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Object fieldValue = null;
                try {
                    field.setAccessible(true);
                    // 属性值
                    fieldValue = field.get(object);
                    // 属性类型
                    // Type fieldType = field.getGenericType();
                    // 属性名
                    // String fieldName = field.getName();
                } catch (Exception e) {
                    return false;
                }
                //只要有一个属性值不为null 就返回false 表示对象不为null
                if (fieldValue != null) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }


    public static Map<String, Object> toMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // 使得私有字段也可以访问
            Object o = field.get(obj);
            if (!objectCheckIsNull(o)) {
                map.put(field.getName(), o);
            }
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        BaseDataBo baseDataBo = new BaseDataBo();
        Map<String, Object> map = toMap(baseDataBo);
        System.out.println(map);
    }
}
