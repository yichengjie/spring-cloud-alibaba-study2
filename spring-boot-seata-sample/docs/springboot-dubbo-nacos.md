#### provider端
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
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    @org.apache.dubbo.config.annotation.Service(version = "1.0.1", group = "yicj")
    public class AccountServiceImpl implements IAccountService {
        // todo
    }
    ```
#### consumer端
1. 添加依赖: 同provider
2. 添加配置：（其中dubbo.protocol.port端口需要修改，否则端口冲突）
    ```text
    spring.application.name=sample-order-service
    server.port=9091
    # dubbo config
    dubbo.application.name=${spring.application.name}
    dubbo.protocol.name=dubbo
    dubbo.protocol.port=20881
    dubbo.registry.address=nacos://192.168.221.128:8848
    # nacos discovery config
    nacos.discovery.server-addr=192.168.221.128:8848
    ```
3. 添加注解：同provider
4. 使用远程服务
    ```text
    @Reference(version = "1.0.1", group = "yicj")
    private IAccountService accountService ;
    ```
5. 注意dubbo-spring-boot-starter的2.7.6注册到nacos上，调用服务的时候指定group，否则报错找不到远程服务
   
 
