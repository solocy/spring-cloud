package com.example.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ConfigClientApplication {

    @Value("${spring.application.name}")
    private String name;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @PostConstruct
    private void print() {
        System.out.println(name);
    }


}
