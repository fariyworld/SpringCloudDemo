package com.mace.cloud;

import com.mace.cloud.api.common.UniversalMenthod;
import com.terran4j.commons.api2doc.config.EnableApi2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * description:
 * <br />
 * Created by mace on 20:14 2018/6/30.
 */
@SpringBootApplication/*(scanBasePackages = "com.mace.cloud")*/
@EnableApi2Doc//启用 Api2Doc 服务 http://你的项目地址/api2doc/home.html
@EnableEurekaClient//本服务启动后会自动注册进eureka服务中
@ImportResource("classpath:spring-tr.xml")//启用模糊匹配的事务管理
//@ServletComponentScan(value = "com.mace.cloud.config")//扫描Servlet
//@ComponentScan
public class DeptProvider_9091_Application {

    public static void main(String[] args) {

        SpringApplication.run(DeptProvider_9091_Application.class, args);
    }

    /**
     * description: SpringBoot 使用 fastjson 解析
     * <br /><br />
     * create by mace on 2018/7/2 18:02.
     * @param
     * @return: org.springframework.boot.autoconfigure.web.HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){

        return UniversalMenthod.fastJsonHttpMessageConverters();
    }
}
