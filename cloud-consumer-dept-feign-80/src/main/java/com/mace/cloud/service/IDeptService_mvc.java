package com.mace.cloud.service;

import com.mace.cloud.api.common.RestPackResponse;
import com.mace.cloud.api.entity.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 14:06 2018/7/4.
 */
@FeignClient(value = "cloud-provider-dept")
public interface IDeptService_mvc {

    @GetMapping(value = "/dept/findAll.do")
    RestPackResponse<List<Dept>> findAll();
}
