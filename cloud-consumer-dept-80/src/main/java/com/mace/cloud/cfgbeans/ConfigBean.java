package com.mace.cloud.cfgbeans;

import com.mace.cloud.api.common.UniversalMenthod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 22:41 2018/6/30.
 */
@Configuration
public class ConfigBean {

    @Autowired
    private RestTemplateBuilder builder;

    /**
     * description: RestTemplate 提供了多种便捷访问 Http 服务的方法
     * 是一种简单便捷的访问 restful 服务模板类，是 Spring 提供的用于访问 Rest 服务的客户端模板工具集
     *
     * REST请求地址、请求参数、HTTP响应的类型
     * (url, requestMap, ResponseType.class)
     *
     *
     * <br /><br />
     * create by mace on 2018/6/30 22:46.
     * @param
     * @return: org.springframework.web.client.RestTemplate
     */
    @Bean
    @ConditionalOnMissingBean({RestTemplate.class})
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){

//        return builder.build();
//        RestTemplate restTemplate = new RestTemplate();
        RestTemplate restTemplate = new RestTemplate(factory);

        //换上 fastjson
        List<HttpMessageConverter<?>> httpMessageConverterList= restTemplate.getMessageConverters();
        Iterator<HttpMessageConverter<?>> iterator=httpMessageConverterList.iterator();
        if(iterator.hasNext()){
            HttpMessageConverter<?> converter=iterator.next();
            //原有的String是ISO-8859-1编码 去掉
            if(converter instanceof StringHttpMessageConverter){
                iterator.remove();
            }

            //由于系统中默认有jackson 在转换json时自动会启用  但是我们不想使用它 可以直接移除或者将fastjson放在首位
            if(converter instanceof GsonHttpMessageConverter || converter instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }

        }

        httpMessageConverterList.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        httpMessageConverterList.add(0,UniversalMenthod.fastJsonHttpMessageConverter());

        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean({ClientHttpRequestFactory.class})
    public ClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //ms
        factory.setConnectTimeout(1*1000*60);
        factory.setReadTimeout(1*1000*60);
        return factory;
    }
}