package com.yicj.nacos;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@DubboComponentScan
@SpringBootApplication
public class NacosSampleProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosSampleProviderApp.class, args) ;
    }
}
