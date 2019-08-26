package com.example.hystrixclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@RestController
public class HystrixClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixClientApplication.class, args);
	}


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/dc")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getDC() {
        return restTemplate.getForEntity("http://192.168.1.106:8010/health",String.class).getBody();
    }

    public String fallback() {
        return "fallback";
    }
}
