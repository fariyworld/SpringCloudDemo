package com.mace.cloud.api.common;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * description: 抽取一些通用方法
 * <br />
 * Created by mace on 23:13 2018/6/30.
 */
public class UniversalMenthod {

    public static HttpMessageConverters fastJsonHttpMessageConverters(){

        return new HttpMessageConverters(fastJsonHttpMessageConverter());
    }

    public static FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //格式化
                SerializerFeature.PrettyFormat,
                //统一转换时间戳: yyyy-MM-dd HH:mm:ss
                SerializerFeature.WriteDateUseDateFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        mediaTypes.add(new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8")));
        mediaTypes.add(new MediaType(MediaType.TEXT_HTML, Charset.defaultCharset()));
        fastConverter.setSupportedMediaTypes(mediaTypes);

        return fastConverter;
    }

}
