package com.example.nacosconfigproducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class NacosConfigProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigProducerApplication.class, args);
    }

    @Value("${application.test}")
    private String test;

    @GetMapping("/")
    public String getTest() {
        return test;
    }
}
