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
2. 添加配置
    ```text
    # spring cloud stream config
    spring.cloud.stream.rocketmq.binder.name-server=192.168.221.128:9876
    # 用来指定topic
    spring.cloud.stream.bindings.output.destination=TopicTest
    ```
3. 添加注解@EnableBinding
    ```text
    @EnableBinding({Source.class})
    @SpringBootApplication
    public class ProducerApplication {
        public static void main(String[] args) {
            SpringApplication.run(ProducerApplication.class, args) ;
        }
    }
    ```
4. 业务代码发送消息
    ```text
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
    ```