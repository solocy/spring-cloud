package com.example.feignproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FeignProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProducerApplication.class, args);
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }
}
