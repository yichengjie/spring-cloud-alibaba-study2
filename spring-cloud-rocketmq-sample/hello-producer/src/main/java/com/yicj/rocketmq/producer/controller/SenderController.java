package com.yicj.rocketmq.producer.controller;

import com.yicj.rocketmq.producer.channel.OrderSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SenderController {
    @Autowired
    private Source source;


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public String send(String msg) {
        String namesrvAddr = rocketMQTemplate.getProducer().getNamesrvAddr();
        log.info("=======> namesrvAddr :{}", namesrvAddr);
        MessageBuilder builder = MessageBuilder.withPayload(msg);
        Message message = builder.build();
        source.output().send(message);
        return "Hello RocketMQ Binder, send : " + msg;
    }


    @Autowired
    private OrderSource orderSource ;

    @GetMapping("/customSend")
    public String customSend(String msg) {
        MessageBuilder builder = MessageBuilder.withPayload(msg);
        Message message = builder.build();
        orderSource.output().send(message);
        return "Hello RocketMQ Binder, custom send : " + msg;
    }
}
