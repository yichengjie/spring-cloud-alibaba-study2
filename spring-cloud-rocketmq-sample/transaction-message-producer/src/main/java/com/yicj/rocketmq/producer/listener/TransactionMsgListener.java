package com.yicj.rocketmq.producer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicj.rocketmq.producer.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


// 实现RocketMQLocalTransactionListener接口，使用@RocketMQTransactionListener注解用于接收本地事务的监听
// txProducerGroup是事务分组名称，和前面定义的OrderTransactionGroup保持一致,
@Slf4j
@Component
@RocketMQTransactionListener(txProducerGroup = "OrderTransactionGroup")
public class TransactionMsgListener implements RocketMQLocalTransactionListener {

    @Autowired
    private ObjectMapper objectMapper ;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            // 获取前面生成的事务id
            String transactionId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID) ;
            // 以事务id为主键，执行本地事务
            byte [] payload = (byte[]) msg.getPayload();
            Order order = objectMapper.readValue(payload, Order.class) ;
            boolean result = this.saveOrder(order, transactionId) ;
            return result ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
        }catch (Exception e){
            log.error("transaction error : ", e);
            return RocketMQLocalTransactionState.ROLLBACK ;
        }
    }

    // 数据库业务事务方法
    private boolean saveOrder(Order order, String transactionId) {
        // 将事务ID设置为唯一键
        // 调用数据库 insert to 订单表
        return true ;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // 获取事务id
        String transactionId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID) ;
        // 以事务id为主键，查询本地事务主执行情况
        if (isSuccess(transactionId)){
            return RocketMQLocalTransactionState.COMMIT ;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    private boolean isSuccess(String transactionId) {
        // 查询数据库 select from 订单表
        return true ;
    }
}
