package com.sharing.cn.service;

/**
 * @author ext.shikai1
 */
public interface RedisService {

    /**
     * 写入缓存数据
     *
     * @param key    缓存Key
     * @param value  缓存Value
     * @param expire 缓存时间,单位秒
     * @return 写入结果
     */
    boolean set(String key, String value, int expire);

    /**
     * 获取缓存数据
     *
     * @param key 缓存Key
     * @return 缓存Value
     */
    String get(String key);

    /**
     * 移除缓存数据
     *
     * @param key 缓存Key
     * @return 删除结果
     */
    Long del(String key);

    /**
     * 加锁
     *
     * @param key      加锁的Key
     * @param threadId 标识设备
     * @param time     加锁时间
     * @return 加锁结果
     */
    boolean lock(String key, String threadId, long time);

    /**
     * 解锁
     *
     * @param key      加锁的Key
     * @param threadId 标识设备
     * @return 解锁结果
     */
    boolean unLock(String key, String threadId);
}
