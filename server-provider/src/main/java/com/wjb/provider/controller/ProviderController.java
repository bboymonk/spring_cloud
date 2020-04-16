package com.wjb.provider.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @RequestMapping("provider/get")
    public String getData(){
        return "服务提供：提供了数据";
    }

}
