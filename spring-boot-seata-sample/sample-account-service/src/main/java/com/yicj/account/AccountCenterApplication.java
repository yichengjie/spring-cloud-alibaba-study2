package com.yicj.account;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://www.cnblogs.com/throwable/p/12134282.html
//@DubboComponentScan
@SpringBootApplication
public class AccountCenterApplication{

    public static void main(String[] args) {
        SpringApplication.run(AccountCenterApplication.class, args) ;
    }
}
