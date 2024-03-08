package com.fuswx.cloud.Config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer myRetryer(){
         return Retryer.NEVER_RETRY;  //Feign默认配置是不走重试策略的

        // 最大请求次数是3(1+2),初始间隔时间为100ms，重试最大间隔时间为1s  初始一次，重试两次
        //return new Retryer.Default(100, 1, 3);
    }

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
