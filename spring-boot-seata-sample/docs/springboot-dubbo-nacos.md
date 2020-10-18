1. 添加依赖
    ```text
    <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>nacos-discovery-spring-boot-starter</artifactId>
        <version>0.2.7</version>
        <exclusions>
            <exclusion>
                <artifactId>spring-context-support</artifactId>
                <groupId>com.alibaba.spring</groupId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <version>2.7.6</version>
    </dependency>
    ```
2. 添加配置（application.properties）
    ```text
    dubbo.application.name=sample-account-service
    dubbo.registry.address=nacos://192.168.221.128:8848
    
    dubbo.protocol.name=dubbo
    dubbo.protocol.port=20880
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