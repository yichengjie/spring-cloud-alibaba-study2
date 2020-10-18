1. 添加依赖
    ```text
    <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>nacos-discovery-spring-boot-starter</artifactId>
        <exclusions>
            <exclusion>
                <artifactId>spring-context-support</artifactId>
                <groupId>com.alibaba.spring</groupId>
            </exclusion>
        </exclusions>
        <version>0.2.7</version>
    </dependency>
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <version>2.7.6</version>
    </dependency>
    ```
2. 添加配置（application.properties）
    ```text
    spring.application.name=sample-account-service
    dubbo.application.name=sample-account-service
    dubbo.registry.address=nacos://192.168.221.128:8848
    dubbo.protocol.name=dubbo
    dubbo.protocol.port=20880
    # nacos discovery config
    nacos.discovery.server-addr=192.168.221.128:8848
    ```
3. 添加注解
    ```text
    @DubboComponentScan
    @SpringBootApplication
    public class NacosSampleProviderApp {
        public static void main(String[] args) {
            SpringApplication.run(NacosSampleProviderApp.class, args) ;
        }
    }
    ```
4. 暴露服务
    ```text
    @org.apache.dubbo.config.annotation.Service
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public class AccountServiceImpl implements IAccountService {
        // todo
    }
    ```