package com.example.ribbonsimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonSimpleApplication.class, args);
	}

	@Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
	    return new RestTemplate();
    }

    @Autowired
    private RestTemplate template;

    @GetMapping("/dc")
    public String getDC() {
        return template.getForEntity("http://eureka-producer/",String.class).getBody();
    }
}
