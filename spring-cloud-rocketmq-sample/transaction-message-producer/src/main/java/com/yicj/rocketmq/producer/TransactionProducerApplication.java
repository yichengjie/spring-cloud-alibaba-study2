package com.yicj.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@Slf4j
@EnableBinding({Source.class})
@SpringBootApplication
public class TransactionProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionProducerApplication.class, args) ;
    }
}
