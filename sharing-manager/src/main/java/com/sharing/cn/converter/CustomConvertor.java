package com.sharing.cn.converter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sharing.cn.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * CustomConvertor
 * 自定义转换
 */
@Slf4j
public class CustomConvertor {

    /**
     * LocalDateTime 转换为 Date
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return DateUtils.localDateTime2Date(localDateTime);
    }

    /**
     * Date 转换为 LocalDateTime
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return DateUtils.date2LocalDateTime(date);
    }

    /**
     * map to json
     *
     * @param map map
     * @return json
     */
    public static String map2Json(Map<String, String> map) {
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        return JSONObject.toJSONString(map);
    }

    /**
     * json to map
     *
     * @param json json
     * @return map
     */
    public static Map<String, String> json2Map(String json) {
        try {
            return JSONObject.parseObject(json, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            log.error("json2Map Exception", e);
        }
        return null;
    }



}
