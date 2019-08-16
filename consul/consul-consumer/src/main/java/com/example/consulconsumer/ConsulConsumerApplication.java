package com.example.consulconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulConsumerApplication.class, args);
	}

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @GetMapping("/service")
    public String service() {
        String services = "Services: " + discoveryClient.getInstances("producer1");
        System.out.println(services);
        return services;
    }
}
