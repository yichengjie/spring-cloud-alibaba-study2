package com.yicj.provider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://www.cnblogs.com/throwable/p/12134282.html
//@DubboComponentScan
@SpringBootApplication
public class ProviderCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderCenterApplication.class, args) ;
    }
}
