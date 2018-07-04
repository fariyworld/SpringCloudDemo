package com.mace.cloud.controller;

import com.mace.cloud.api.common.RestPackResponse;
import com.mace.cloud.api.entity.Dept;
import com.mace.cloud.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: dept 消费
 * <br />
 * Created by mace on 22:48 2018/6/30.
 */
@RestController
@RequestMapping("/consumer/dept")
public class DeptController_Consumer {

//    private static final String REST_URL_PREFIX = "http://127.0.0.1:9091";

    @Autowired
//    private IDeptService_MVC iDeptService_MVC;
    private IDeptService iDeptService;
//    private RestTemplate restTemplate;

    @GetMapping(value = "/findAll.do")
    public RestPackResponse<List<Dept>> findAll(){

//        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/findAll.do", RestPackResponse.class);
        return iDeptService.findAll();
//        return iDeptService_MVC.findAll();
    }

}
