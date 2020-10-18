1. 添加依赖
    ```text
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>nacos-config-spring-boot-starter</artifactId>
    </dependency>
    ```
2. 添加配置
    ```text
    spring.application.name=config-sample-nacos
    server.port=8080
    nacos.config.server-addr=192.168.221.128:8848
    ```
3. 获取配置业务代码
    ```text
    @RestController
    @NacosPropertySource(dataId = "example", autoRefreshed = true)
    public class HelloController {
        @NacosValue(value = "${info: local value for info}", autoRefreshed = true)
        private String info;
    
        @GetMapping(path = "/get")
        public String get() {
            return String.format("info value:%s", info);
        }
    }
    ```
