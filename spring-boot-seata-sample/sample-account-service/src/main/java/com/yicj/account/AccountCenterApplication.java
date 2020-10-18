package com.yicj.account;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@DubboComponentScan
@SpringBootApplication
@MapperScan(basePackages = "com.yicj.account.dao")
public class AccountCenterApplication{

    public static void main(String[] args) {
        SpringApplication.run(AccountCenterApplication.class, args) ;
    }
}
