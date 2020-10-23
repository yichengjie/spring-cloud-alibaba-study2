package com.yicj.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding({Sink.class})
@SpringBootApplication
public class HelloConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloConsumerApplication.class, args) ;
    }
}
