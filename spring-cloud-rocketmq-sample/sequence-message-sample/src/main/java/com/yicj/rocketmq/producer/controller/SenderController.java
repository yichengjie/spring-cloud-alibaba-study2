package com.yicj.rocketmq.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.BinderHeaders;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class SenderController {
    @Autowired
    private Source source;

    @GetMapping("/orderly")
    public String orderly() {
        List<String> typeList = Arrays.asList("创建","支付","退款") ;
        for (String type: typeList){
            MessageBuilder builder = MessageBuilder.withPayload(type)
                    .setHeader(BinderHeaders.PARTITION_HEADER,0);
            Message message = builder.build() ;
            source.output().send(message) ;
        }
        return "OK";
    }
}
