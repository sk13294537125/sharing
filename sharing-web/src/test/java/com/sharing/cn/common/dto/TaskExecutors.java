package com.sharing.cn.common.dto;

import com.sharing.cn.common.test.ThreadTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ext.shikai1
 */
public class TaskExecutors {
    private static TaskExecutors instance = new TaskExecutors();

    private TaskExecutors() {

    }
    public static TaskExecutors getInstance() {
        return instance;
    }
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        //创建线程池
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(500);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("threadPoolTaskExecutor");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

}
