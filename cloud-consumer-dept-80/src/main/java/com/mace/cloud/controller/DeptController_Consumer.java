package com.mace.cloud.controller;

import com.mace.cloud.api.common.RestPackResponse;
import com.mace.cloud.api.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * description: dept 消费
 * <br />
 * Created by mace on 22:48 2018/6/30.
 */
@RestController
@RequestMapping("/consumer/dept")
public class DeptController_Consumer {

    private static final String REST_URL_PREFIX = "http://127.0.0.1:9091";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/findAll.do")
    public RestPackResponse<List<Dept>> findAll(){

        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/findAll.do", RestPackResponse.class);
    }

}
