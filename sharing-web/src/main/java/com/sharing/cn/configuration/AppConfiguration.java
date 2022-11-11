package com.sharing.cn.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.sharing.cn.property.ThreadPoolProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;


/**
 * AppConfiguration
 * App相关配置
 */
@Slf4j
@EnableAsync
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class AppConfiguration {

    /**
     * 线程池相关配置
     */
    @Resource
    private ThreadPoolProp threadPoolProp;

    /**
     * 线程池支持
     *
     * @return Executor
     */
    @Bean
    public Executor threadPoolTaskExecutor() {
        log.info("init thread");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(threadPoolProp.getCorePoolSize());
        taskExecutor.setMaxPoolSize(threadPoolProp.getMaxPoolSize());
        taskExecutor.setQueueCapacity(threadPoolProp.getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(threadPoolProp.getKeepAliveSeconds());
        taskExecutor.setThreadNamePrefix(threadPoolProp.getThreadNamePrefix());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(threadPoolProp.isWaitForTasksToCompleteOnShutdown());
        taskExecutor.setAwaitTerminationSeconds(threadPoolProp.getAwaitTerminationSeconds());
        taskExecutor.setRejectedExecutionHandler(threadPoolProp.getRejectedExecutionHandler());
        log.info("Executor injected!");
        return taskExecutor;
    }

    /**
     * mybatis plus 分页
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}