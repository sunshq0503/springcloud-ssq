package com.ssq.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages="com.ssq.wallet.dao")
public class SsqWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsqWalletApplication.class, args);
    }

}
