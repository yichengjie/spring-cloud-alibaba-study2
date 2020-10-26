1. 添加依赖
    ```text
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>
    
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-datasource-nacos</artifactId>
    </dependency>
    ```
2. 添加配置
    ```text
    spring.application.name=spring-cloud-sentinel-dynamic
    # sentinel dashboard config
    spring.cloud.sentinel.transport.dashboard=192.168.221.128:8080
    # sentinel-nacos config
    spring.cloud.sentinel.datasource.ds1.nacos.server-addr=192.168.221.128:8848
    spring.cloud.sentinel.datasource.ds1.nacos.data-id=${spring.application.name}-flow
    spring.cloud.sentinel.datasource.ds1.nacos.group-id=DEFAULT_GROUP
    spring.cloud.sentinel.datasource.ds1.nacos.data-type=json
    spring.cloud.sentinel.datasource.ds1.nacos.rule-type=flow
    ```
3. 在nacos添加data-id为spring-cloud-sentinel-dynamic-flow,且group为DEFAULT_GROUP配置格式为json的配置
    ```text
    [
        {
            "resource":"/dynamic",
            "limitApp":"default",
            "grade":1,
            "count":5,
            "stragegy":0,
            "controlBehavior":0,
            "clusterMode":false
        }
    ]
    ```