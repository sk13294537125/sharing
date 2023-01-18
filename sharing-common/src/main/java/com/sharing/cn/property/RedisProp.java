package com.sharing.cn.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis
 * @author ext.shikai1
 */
@Data
@Component
//@ConfigurationProperties(prefix = "spring.redis")
public class RedisProp {

    private String timeout;
    private String host;
    private String port;
    private String database;
    private String password;
    private Lettuce lettuce;

    @Data
    public static class Lettuce {
        private Pool pool;
    }

    @Data
    public static class Pool {
        private String maxActive;
        private String maxWait;
        private String maxIdle;
        private String minIdle;
    }
}
