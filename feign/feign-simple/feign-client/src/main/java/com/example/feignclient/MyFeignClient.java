package com.example.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test",url = "localhost:8111")
public interface MyFeignClient {

    @GetMapping("/test")
    String getTest();
}
