package com.yicj.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


@Slf4j
@EnableBinding({Sink.class})
@SpringBootApplication
public class TransactionConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionConsumerApplication.class, args) ;
    }

    @StreamListener(Sink.INPUT)
    public void receive(String receiveMsg){

        log.info("TopicTest receive : {}, receiveTime = {}", receiveMsg, System.currentTimeMillis());
    }
}
