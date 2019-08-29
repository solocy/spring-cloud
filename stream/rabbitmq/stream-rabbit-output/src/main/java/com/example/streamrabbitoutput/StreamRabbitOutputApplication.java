package com.example.streamrabbitoutput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableBinding(Source.class)
public class StreamRabbitOutputApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitOutputApplication.class, args);
    }


    @PostConstruct
    @InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedRate = "5000"))
    public Message<String> sendMessage() {
        System.out.println("hello");
        return MessageBuilder.withPayload("hello").build();
    }
}
