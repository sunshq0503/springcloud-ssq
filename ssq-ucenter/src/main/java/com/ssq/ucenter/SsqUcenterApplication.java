package com.ssq.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages="com.ssq.ucenter.dao")
public class SsqUcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsqUcenterApplication.class, args);
    }
}
