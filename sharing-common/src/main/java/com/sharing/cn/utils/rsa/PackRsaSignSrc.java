package com.sharing.cn.utils.rsa;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PackRsaSignSrc {

    public static String packRsaSignSrc(Map map) {
        String sign_str = new String();
        String equal = "=";
        String seq = "&";
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            if(null == value || key.isEmpty())
            {
                continue;
            }
            if (value instanceof Map){
                value = JSONObject.toJSON(value);
            }
            if(value instanceof List){
                value = JSONArray.toJSON(value);
            }
            sign_str = sign_str + key + equal + value + seq;
        }
        String ss = sign_str.substring(0, sign_str.length() -1);
        return ss;
    }

    public static String packRsaSignSrcEncode(Map map) {
        String sign_str = new String();
        String equal = "=";
        String seq = "&";
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            if(null == value || key.isEmpty())
            {
                continue;
            }
            if (value instanceof Map){
                value = JSONObject.toJSON(value);
            }
            if(value instanceof List){
                value = JSONArray.toJSON(value);
            }
            try {
                key = URLEncoder.encode(key,"utf-8");
                value = URLEncoder.encode(value.toString(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sign_str = sign_str + key + equal + value + seq;
        }
        String ss = sign_str.substring(0, sign_str.length() -1);
        return ss;
    }

}
