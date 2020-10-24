package com.yicj.rocketmq.producer;

import com.yicj.rocketmq.producer.channel.OrderSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

//https://blog.csdn.net/boling_cavalry/article/details/82051356
//https://blog.csdn.net/qq_27808011/article/details/80108622
@EnableBinding({Source.class, OrderSource.class})
@SpringBootApplication
public class HelloProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloProducerApplication.class, args) ;
    }
}
