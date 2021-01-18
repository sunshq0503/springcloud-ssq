package com.ssq.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@SpringBootApplication

public class SsqUcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsqUcenterApplication.class, args);
    }

}
