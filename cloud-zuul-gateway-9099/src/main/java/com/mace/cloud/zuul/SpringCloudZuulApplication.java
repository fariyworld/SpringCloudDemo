package com.mace.cloud.zuul;

import com.mace.cloud.api.common.UniversalMenthod;
import com.mace.cloud.zuul.config.AccessFilter;
import com.mace.cloud.zuul.config.GatewayZuulFallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * description:
 * <br />
 * Created by mace on 16:27 2018/7/4.
 */

@EnableDiscoveryClient//向 eureka 注册服务
@EnableZuulProxy//开启Zuul的API网关服务功能
@SpringBootApplication
public class SpringCloudZuulApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudZuulApplication.class, args);
    }


    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){

        return UniversalMenthod.fastJsonHttpMessageConverters();
    }

    @Bean
    public AccessFilter accessFilter() {

        return new AccessFilter();
    }


    @Bean
    public GatewayZuulFallback zuulFallback() {

        return new GatewayZuulFallback();
    }
}
