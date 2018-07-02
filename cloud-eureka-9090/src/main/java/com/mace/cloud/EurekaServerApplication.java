package com.mace.cloud;

import com.mace.cloud.api.common.UniversalMenthod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

/**
 * description:
 * <br />
 * Created by mace on 9:45 2018/7/1.
 */
@SpringBootApplication
@EnableEurekaServer// 开启 eureka-server 服务中心 (http://127.0.0.1:9090/), 接受其他微服务注册进来
@EnableDiscoveryClient// 开启服务发现
public class EurekaServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(EurekaServerApplication.class,args);
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){

        return UniversalMenthod.fastJsonHttpMessageConverters();
    }
}
