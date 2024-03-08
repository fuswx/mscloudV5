package com.fuswx.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.fuswx.cloud.Mapper")  //import tk.mybatis.spring.annotation.MapperScan;
@EnableDiscoveryClient
@RefreshScope //实时刷新
public class Main8849 {
    public static void main(String[] args) {
        SpringApplication.run(Main8849.class, args);
    }
}