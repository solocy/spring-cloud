package com.example.feigneurekaclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(serviceId = "eureka-producer")
public interface MyFeignClient {

    @GetMapping("/")
    String getData();
}
