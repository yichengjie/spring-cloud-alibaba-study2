### 服务提供方
1. 添加依赖
    ```text
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
### 服务消费方
1. 添加配置
    ```text
    <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>nacos-discovery-spring-boot-starter</artifactId>
    </dependency>
    ```
2. 添加配置
    ```text
    spring.application.name=consumer-sample-nacos
    server.port=8081
    # nacos config
    nacos.discovery.server-addr=192.168.221.128:8848
    ```
3. 编写消费业务
    ```text
    @Component
    public class MyCommandRunner implements CommandLineRunner {
        @NacosInjected
        private NamingService namingService ;
     
        @Override
        public void run(String... args) throws Exception {
            // 根据服务名从注册中心获取一个健康的服务实例
            Instance instance = namingService.selectOneHealthyInstance("provider-sample-nacos");
            // 这里只是为了方便才新建RestTemplate实例
            RestTemplate template = new RestTemplate();
            String url = String.format("http://%s:%d/hello?name=yicj", instance.getIp(), instance.getPort());
            String result = template.getForObject(url, String.class);
            System.out.println(String.format("请求URL:%s,响应结果:%s", url, result));
        }
    }
    ```
