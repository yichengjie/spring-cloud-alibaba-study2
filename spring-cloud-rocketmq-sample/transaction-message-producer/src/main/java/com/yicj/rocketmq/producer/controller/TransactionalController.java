package com.yicj.rocketmq.producer.controller;

import com.yicj.rocketmq.producer.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.BinderHeaders;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class TransactionalController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/transactional")
    public String transactional() {
        Order order = new Order("123", "浙江杭州") ;
        String transactionId = UUID.randomUUID().toString() ;
        MessageBuilder builder = MessageBuilder.withPayload(order)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId) ;
        Message message = builder.build() ;
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(
                "OrderTransactionGroup", "TopicOrder", message, order.getOrderId());
        return sendResult.getMsgId();
    }

}
