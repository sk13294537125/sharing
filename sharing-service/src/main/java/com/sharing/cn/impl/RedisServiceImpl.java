package com.sharing.cn.impl;

import com.alibaba.fastjson.JSON;
import com.sharing.cn.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author ext.shikai1
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    //@Resource
    //private RedisAdvancedClusterCommands<String, Object> redisAdvancedClusterCommands;

    @Override
    public boolean set(String key, String value, int expire) {
        try {
            //String result = redisAdvancedClusterCommands.set(key, value, SetArgs.Builder.nx());
            //return "OK".equals(result);
        } catch (Exception e) {
            log.error("r2m setex Exception ,key:{}", key, e);
        }
        return Boolean.FALSE;
    }

    @Override
    public String get(String key) {
        //return JSON.toJSONString(redisAdvancedClusterCommands.get(key));
        return null;
    }

    @Override
    public Long del(String key) {
        //return redisAdvancedClusterCommands.del(key);
        return null;
    }

    @Override
    public boolean lock(String key, String threadId, long time) {
        return false;
    }

    @Override
    public boolean unLock(String key, String threadId) {
        return false;
    }
}
