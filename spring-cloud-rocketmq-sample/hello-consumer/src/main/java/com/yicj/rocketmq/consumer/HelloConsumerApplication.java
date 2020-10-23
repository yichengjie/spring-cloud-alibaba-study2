package com.yicj.rocketmq.consumer;

import com.yicj.rocketmq.consumer.channel.InputChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding({Sink.class, InputChannel.class})
@SpringBootApplication
public class HelloConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloConsumerApplication.class, args) ;
    }
}
