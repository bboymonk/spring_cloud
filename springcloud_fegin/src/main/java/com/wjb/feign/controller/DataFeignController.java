package com.wjb.feign.controller;

import com.wjb.feign.client.DataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataFeignController {


    @Autowired
    DataClient dataClient;

    @RequestMapping("feign/get")
    public String get(){
        return dataClient.getData();
    }

}
