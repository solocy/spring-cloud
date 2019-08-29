package com.example.streamkafkaoutput;

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
public class StreamKafkaOutputApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamKafkaOutputApplication.class, args);
    }

    @PostConstruct
    @InboundChannelAdapter( channel = Source.OUTPUT,  poller = @Poller(fixedRate = "5000"))
    public Message<String> sendMessage() {
        System.out.println("output");
        return MessageBuilder.withPayload("hello kafka").build();
    }

}
