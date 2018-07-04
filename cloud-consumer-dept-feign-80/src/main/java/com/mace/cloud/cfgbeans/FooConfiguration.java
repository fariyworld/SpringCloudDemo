package com.mace.cloud.cfgbeans;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * <br />
 * Created by mace on 13:52 2018/7/4.
 */
@Configuration
public class FooConfiguration {

    //使用Feign自己的注解，使用springmvc的注解就会报错
    @Bean
    public Contract feignContract() {

        return new feign.Contract.Default();
    }
}
