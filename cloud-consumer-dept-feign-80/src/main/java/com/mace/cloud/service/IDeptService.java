package com.mace.cloud.service;

import com.mace.cloud.api.common.RestPackResponse;
import com.mace.cloud.api.entity.Dept;
import com.mace.cloud.cfgbeans.FooConfiguration;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 13:49 2018/7/4.
 */
@FeignClient(value = "cloud-provider-dept",configuration = FooConfiguration.class)
public interface IDeptService {


    @RequestLine("GET /dept/findAll.do")
    RestPackResponse<List<Dept>> findAll();
}
