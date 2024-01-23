package com.sharing.cn.utils;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 线程工具类
 */
public class ThreadUtils {

    /**
     * 线程池
     */
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * new 多少线程
     * @throws InterruptedException
     */
    @Test
    public void thread1() throws InterruptedException {

    }

    /**
     * 调用 Boolean 方法
     */
    @Test
    public void thread2() {
        try {
            //起子线程去执行任务，如果发生异常，先捕捉，不进行本地事务回滚
            AtomicBoolean syncFlag = new AtomicBoolean(false);
            threadPoolTaskExecutor.execute(() -> {
                syncFlag.set(true);
            });
            if (!syncFlag.get()) {

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
