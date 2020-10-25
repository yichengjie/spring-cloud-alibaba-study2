package com.yicj.rocketmq.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired
    private ObjectMapper objectMapper ;

    @GetMapping("/transactional")
    public String transactional() throws JsonProcessingException {
        Order order = new Order("123", "浙江杭州") ;
        String transactionId = UUID.randomUUID().toString() ;
        String payload = objectMapper.writeValueAsString(order);
        MessageBuilder builder = MessageBuilder.withPayload(payload)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId) ;
        Message message = builder.build() ;
        // 这这里的OrderTransactionGroup 与RocketMQTransactionListener中的txProducerGroup保持一致
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(
                "OrderTransactionGroup", "TopicOrder", message, order.getOrderId());
        return sendResult.getMsgId();
    }

}
