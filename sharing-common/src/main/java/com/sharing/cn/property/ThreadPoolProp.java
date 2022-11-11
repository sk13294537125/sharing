package com.sharing.cn.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadPoolProp
 * 线程池对象
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "thread-pool")
public class ThreadPoolProp {
    /**
     * 核心线程数量
     * 线程池创建时候初始化的线程数
     */
    private int corePoolSize = 5;
    /**
     * 最大线程数
     * 只有在缓冲队列满了之后才会申请超过核心线程数的线程
     */
    private int maxPoolSize = 20;
    /**
     * 队列最大长度
     * 用来缓冲执行任务的队列
     */
    private int queueCapacity = 500;
    /**
     * 线程池维护线程所允许的空闲时间
     * 当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
     */
    private int keepAliveSeconds = 60;
    /**
     * 调度器shutdown被调用时等待当前被调度的任务完成
     * 用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
     */
    private boolean waitForTasksToCompleteOnShutdown = true;
    /**
     * 该方法用来设置线程池中任务的等待时间
     * 如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
     */
    private int awaitTerminationSeconds = 60;
    /**
     * 线程名称前缀
     */
    private String threadNamePrefix = "threadPoolTaskExecutor";
    /**
     * 线程池对拒绝任务的处理策略
     * AbortPolicy:如果线程池队列满了,会丢掉这个任务并且抛出RejectedExecutionException异常
     * DiscardPolicy:如果线程池队列满了,会直接丢掉这个任务,并且不会有任何异常
     * DiscardOldestPolicy:如果线程池队列满了,会丢弃队列最前面的任务,然后重新尝试执行任务（重复此过程）
     * CallerRunsPolicy:如果添加到线程池失败,那么主线程会自己去执行该任务,不会等待线程池中的线程去执行
     */
    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
}
