package com.sharing.cn.utils.rsa;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class MapHashtableUtil {

    public static Map signForInspiry(Map params) {

        SortedMap<Object, Object> objectStringHashMap = new TreeMap<Object, Object>();
        StringBuffer sbkey = new StringBuffer();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (StringUtils.isNotBlank(k)){
                //空值不传递，不参与签名组串
                if (v instanceof Map){
                    Map map = (Map) v;
                    if (map.size() > 0){
                        objectStringHashMap.put(k, v);
                    }
                }else if(v instanceof List){
                    List list = (List) v;
                    if (list.size() > 0){
                        objectStringHashMap.put(k, v);
                    }
                }else {
                    if (null != v && !"".equals(v)) {
                        objectStringHashMap.put(k, v);
                    }
                }
            }
        }
        return objectStringHashMap;
    }

    public static void signForInspiry1(Map params) {

        SortedMap<Object, Object> objectStringHashMap = new TreeMap<Object, Object>();
        StringBuffer sbkey = new StringBuffer();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                objectStringHashMap.put(k, v);
                //sbkey.append(k + "=" + v + "&");
            }
        }
    }

    public static Map TreeMapTest() {
        Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        map.put("c", "ccccc");
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("d", "ddddd");

        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + "=" + map.get(key)+"&");
        }
        return map;
    }


//    public static void main(String[] args){
//        String toString = TreeMapTest().toString();
//        System.out.println(toString);
//    }
}
