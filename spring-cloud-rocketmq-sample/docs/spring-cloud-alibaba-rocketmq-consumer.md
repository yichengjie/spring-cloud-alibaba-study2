#### 环境依赖
1. 环境说明：spring-boot：2.1.13.RELEASE，spring-cloud：Greenwich.SR3，pring-cloud-alibaba：2.1.2.RELEASE
2. 添加依赖
    ```text
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-stream-rocketmq</artifactId>
    </dependency>
    <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
    </dependency>
    <dependency>
        <groupId>org.jboss.logging</groupId>
        <artifactId>jboss-logging</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml</groupId>
        <artifactId>classmate</artifactId>
    </dependency>
    ```
####  使用自带通道消费消息
1. 添加配置
    ```text
    spring.cloud.stream.rocketmq.binder.name-server=192.168.221.128:9876
    spring.cloud.stream.bindings.input.destination=TopicTest
    # group 可以随便填写
    spring.cloud.stream.bindings.input.group=test-group1
    ```
2. 添加注解@EnableBinding
    ```text
    @EnableBinding({Sink.class})
    @SpringBootApplication
    public class HelloConsumerApplication {
        public static void main(String[] args) {
            SpringApplication.run(HelloConsumerApplication.class, args) ;
        }
    }
    ```
3. 业务代码发送消息
    ```text
    @Component
    public class HelloStreamListener {
        @StreamListener(Sink.INPUT)
        public void receive(String receiveMsg){
            log.info("TopicTest receive : {}, receiveTime: {}", receiveMsg,System.currentTimeMillis());
        }
    }
    ```
####  使用自定义通道消费消息
1. 编写自定义接口
    ```text
    public interface InputChannel {
        String USER_INPUT = "userInput" ;
        String ORDER_INPUT = "orderInput" ;
    
        @Input(InputChannel.USER_INPUT)
        SubscribableChannel userInput() ;
    
        @Input(InputChannel.ORDER_INPUT)
        SubscribableChannel orderInput() ;
    }
    ```
2. 添加配置
    ```text
    spring.cloud.stream.bindings.orderInput.destination=TopicOrder
    spring.cloud.stream.bindings.orderInput.group=order-group
    ```
3. 在@EnableBinding注解中应用InputChannel通道接口
    ```text
    @EnableBinding({Sink.class, InputChannel.class})
    ```
4. 编写业务代码
    ```text
    @StreamListener(InputChannel.ORDER_INPUT)
    public void receiveOrder(String recevieMsg){
        log.info("receive order :{}", recevieMsg);
    }
    ```