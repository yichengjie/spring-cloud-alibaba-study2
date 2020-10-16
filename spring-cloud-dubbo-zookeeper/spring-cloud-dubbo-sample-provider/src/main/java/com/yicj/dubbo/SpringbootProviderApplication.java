package com.yicj.dubbo;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@DubboComponentScan
@SpringBootApplication
public class SpringbootProviderApplication {

    public static void main(String[] args) {
        //System.setProperty("dubbo.application.logger","log4j2") ;
        SpringApplication.run(SpringbootProviderApplication.class, args) ;
    }
}
