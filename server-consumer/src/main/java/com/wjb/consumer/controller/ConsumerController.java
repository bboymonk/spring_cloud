package com.wjb.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private static final String url = "http://localhost:8001/provider/get";

    @RequestMapping("consumer/get")
    public String getData(){
        RestTemplate template = new RestTemplate();
        String data = template.getForObject(url, String.class);
        return data;
    }

}
