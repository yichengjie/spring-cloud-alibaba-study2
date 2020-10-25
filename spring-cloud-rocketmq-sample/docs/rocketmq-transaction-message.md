1. 添加依赖
    ```text
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-stream-rocketmq</artifactId>
    </dependency>
    ```    
#### 生产消息端开发
1. 添加注解：无
2. 添加配置
    ```text
    spring.cloud.stream.rocketmq.binder.name-server=192.168.221.128:9876
    ```
3. 发送事务消息-预提交
    ```text
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
    ```
4. 执行本地事务，提交或回滚事务消息
    ```text
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
    ``` 
#### 消费消息端开发
1. 添加注解
    ```text
    @EnableBinding({Sink.class})
    ```
2. 添加配置
    ```text
    spring.cloud.stream.rocketmq.binder.name-server=192.168.221.128:9876
    spring.cloud.stream.bindings.input.destination=TopicOrder
    spring.cloud.stream.bindings.input.group=test-group1
    ```
3. 接收事务消息
    ```text
     @StreamListener(Sink.INPUT)
     public void receive(String receiveMsg){
        log.info("TopicTest receive : {}, receiveTime = {}", receiveMsg, System.currentTimeMillis());
     }
    ```
4. 注意事项
```text
   注意想要要使用web项目，rocketmq会使用,否则接到消息时会报错：
   Dispatcher has no subscribers for channel 'application.input'.;
   nested exception is org.springframework.integration.MessageDispatchingException:
   Dispatcher has no subscribers, failedMessage=GenericMessage
```