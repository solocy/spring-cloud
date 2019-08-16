package com.example.nacosdiscoveryconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosDiscoveryConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    public String hello() {
        return "hello  nacos consumer";
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        String result = restTemplate.getForEntity("http://nacos-discovery-producer/",String.class).getBody();
        return result;
    }
}
