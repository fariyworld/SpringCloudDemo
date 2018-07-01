package com.mace.cloud;

import com.mace.cloud.api.common.UniversalMenthod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

/**
 * description:
 * <br />
 * Created by mace on 23:04 2018/6/30.
 */
@SpringBootApplication
public class DeptConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(DeptConsumerApplication.class, args);
    }

    /**
     * description:SpringBoot实现FastJson解析json数据 统一设置时间戳转换
     *
     * 格式化日期格式
     * @JSONField(format = "yyyy-MM-dd HH:mm:ss")
     *
     * 不进行序列化
     * @JSONField(serialize = false)
     *
     * SerializerFeature属性
     *
     * <br /><br />
     * create by mace on 2018/5/27 19:41.
     * @param
     * @return: org.springframework.boot.autoconfigure.http.HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){

        return UniversalMenthod.fastJsonHttpMessageConverters();
    }
}
