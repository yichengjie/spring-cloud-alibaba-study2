1. 添加依赖
    ```text
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>nacos-discovery-spring-boot-starter</artifactId>
    </dependency>
    ```
2. 添加配置
    ```text
    spring.application.name=sample-account-service
    server.port=8080
    # nacos config
    nacos.discovery.server-addr=192.168.221.128:8848
    ```
3. 向nacos注册服务注册服务
    ```text
    @Component
    public class MyCommandLineRunner implements CommandLineRunner {
        @NacosInjected
        private NamingService namingService;
    
        @Value("${spring.application.name}")
        private String applicationName;
        @Value("${server.port}")
        private Integer serverPort;
    
        @Override
        public void run(String... args) throws Exception {
            // 通过Naming服务注册实例到注册中心
            namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
        }
    }
    ```
