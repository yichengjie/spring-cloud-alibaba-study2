package com.yicj.rocketmq.consumer.listener;

import com.yicj.rocketmq.consumer.channel.InputChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloStreamListener {
    @StreamListener(Sink.INPUT)
    public void receive(String receiveMsg){
        log.info("TopicTest receive : {}, receiveTime: {}", receiveMsg,System.currentTimeMillis());
    }

    @StreamListener(InputChannel.ORDER_INPUT)
    public void receiveOrder(String recevieMsg){
        log.info("receive order :{}", recevieMsg);
    }

    @StreamListener(InputChannel.USER_INPUT)
    public void receiveUser(String recevieMsg){
        log.info("receive order :{}", recevieMsg);
    }
}
