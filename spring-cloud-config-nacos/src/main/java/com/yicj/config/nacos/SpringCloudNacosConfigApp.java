package com.yicj.config.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringCloudNacosConfigApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(SpringCloudNacosConfigApp.class, args);
        // 从Environment中读取配置
        String info = context.getEnvironment().getProperty("info");
        log.info("====> info : {}", info);
    }
}
