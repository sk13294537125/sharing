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
        CountDownLatch latch = new CountDownLatch(20);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        try {
            for (int i = 0; i <= 20; i ++) {
                executorService.submit(() -> {
                    int n = 0;
                    System.out.println(n + ":" + SystemUtils.currentTimeMillis());
                    ++n;
                    latch.countDown();
                });
            }
        } catch (Exception e) {
            System.out.println("【地址查询】批量增加区域名称" + e);
        } finally {
            if (!executorService.isShutdown()) {
                executorService.shutdown();
            }
        }
        latch.await();
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
