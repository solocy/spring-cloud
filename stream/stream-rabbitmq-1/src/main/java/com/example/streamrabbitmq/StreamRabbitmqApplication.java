package com.example.streamrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class StreamRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitmqApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void getMessage(Object object) {
        System.out.println(object.toString());
    }

}
