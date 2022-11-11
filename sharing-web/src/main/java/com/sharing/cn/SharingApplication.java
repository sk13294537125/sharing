package com.sharing.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties
@EnableTransactionManagement
@MapperScan(basePackages = {"com.sharing.cn.dao.mapper"})
@ImportResource(locations = {"classpath:spring/*.xml"})
@SpringBootApplication
public class SharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharingApplication.class, args);
    }

}
