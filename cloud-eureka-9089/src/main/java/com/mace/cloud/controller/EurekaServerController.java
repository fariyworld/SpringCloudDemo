package com.mace.cloud.controller;

import com.mace.cloud.api.common.RestPackResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 15:49 2018/7/1.
 */
@RestController
@RequestMapping("/eureka/server")
@Slf4j
public class EurekaServerController {


    @Autowired
    private DiscoveryClient client;


    @GetMapping(value = "/discovery.do")
    public RestPackResponse<DiscoveryClient> discovery(){

        List<String> services = client.getServices();

        log.info("注册到 eureka 的服务有：", services);

        for (String service : services) {

            List<ServiceInstance> serviceInstanceList = client.getInstances(service.toUpperCase());

            for (ServiceInstance serviceInstance : serviceInstanceList) {

                log.info(serviceInstance.getServiceId() + "\t" +
                         serviceInstance.getHost() + "\t" +
                         serviceInstance.getPort() + "\t" +
                         serviceInstance.getUri());
            }
        }

        return RestPackResponse.createBySuccess("服务发现成功", this.client);
    }
}
