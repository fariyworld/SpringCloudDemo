package com.mace.cloud;

import com.mace.cloud.api.common.UniversalMenthod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * description:
 * <br />
 * Created by mace on 10:22 2018/7/5.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudOauth2Application {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudOauth2Application.class, args);
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){

        return UniversalMenthod.fastJsonHttpMessageConverters();
    }

}
