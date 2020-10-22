package com.yicj.rocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {
    @Autowired
    private Source source ;

    @GetMapping("/send")
    public String send(String msg){
        MessageBuilder builder = MessageBuilder.withPayload(msg) ;
        Message message = builder.build() ;
        source.output().send(message) ;
        return "Hello RocketMQ Binder, send : " + msg ;
    }
}
