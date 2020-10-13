package com.yicj.dubbo;

import com.yicj.dubbo.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringBooConsumerApplication {

    @Reference(url = "dubbo://192.168.221.1:20880/com.yicj.dubbo.service.IHelloService")
    private IHelloService helloService ;


    public static void main(String[] args) {
        SpringApplication.run(SpringBooConsumerApplication.class, args) ;
    }

    @Bean
    public ApplicationRunner runner(){
        return args -> log.info("====> " + helloService.sayHello("yicj"));
    }
}
