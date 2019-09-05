package com.example.feigneurekaclient;

import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class FeignEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignEurekaClientApplication.class, args);
    }

    @Autowired
    private MyFeignClient myFeignClient;

    @Bean
    public IRule iLoadBalancer() {
        return new CustomRule();
    }

    @GetMapping("/")
    public String getData() {
        return  myFeignClient.getData();
    }
}
