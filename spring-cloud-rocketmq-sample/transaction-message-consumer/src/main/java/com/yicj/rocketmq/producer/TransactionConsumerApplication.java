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

   /* @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper() ;
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }*/
}
