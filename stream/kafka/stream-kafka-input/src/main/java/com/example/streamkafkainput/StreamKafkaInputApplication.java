package com.example.streamkafkainput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableBinding(Sink.class)
public class StreamKafkaInputApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamKafkaInputApplication.class, args);
    }

    @StreamListener(value = Sink.INPUT)
    public void getMessage(Object o) {
        System.out.println(o.toString());
    }
}
