package com.ssq.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SsqWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsqWalletApplication.class, args);
    }

}
