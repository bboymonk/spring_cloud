package com.wjb.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*我们要在一个服务提供者中使用另一个服务提供者的服务，需要使用fegin进行内部服务的调用*/


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.wjb.feign.client")   //feign接口的地址
public class Application_Feign {

    public static void main(String[] args) {
        SpringApplication.run(Application_Feign.class);
    }

}
