1. 添加依赖
    ```text
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-stream-rocketmq</artifactId>
    </dependency>
    ```
2. 添加注解(启动类上添加)
    ```text
    @EnableBinding({Source.class,Sink.class})
    @SpringBootApplication
    public class SequenceMessageApplication {
        public static void main(String[] args) {
            SpringApplication.run(SequenceMessageApplication.class, args) ;
        }
    }
    ```
3. 添加配置
    ```text
    server.port=8081
    # spring cloud stream config
    spring.cloud.stream.rocketmq.binder.name-server=192.168.221.128:9876
    # 生产者配置
    spring.cloud.stream.bindings.output.destination=TopicTest
    spring.cloud.stream.rocketmq.bindings.output.producer.group-demo-group
    # 设置同步消息
    spring.cloud.stream.rocketmq.bindings.output.producer.sync=true
    ##########
    # 消费者配置
    spring.cloud.stream.bindings.input.destination=TopicTest
    spring.cloud.stream.bindings.input.group=test-group1
    # 指定顺序消费
    spring.cloud.stream.rocketmq.bindings.input.consumer.orderly=true
    ```
4. 生产消息
    ```text
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
    ```
5. 消费消息
```text
@StreamListener(Sink.INPUT)
public void receive(String receiveMsg){
    log.info("TopicTest receive : {}, receiveTime = {}", receiveMsg, System.currentTimeMillis());
}
```