package com.wjb.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(value = "Test-provider")  //要调用的服务名称
@RestController
public interface DataClient {

    @RequestMapping("provider/get")
    public String getData();

}
