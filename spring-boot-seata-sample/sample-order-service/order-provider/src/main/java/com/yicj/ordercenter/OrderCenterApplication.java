package com.yicj.ordercenter;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@DubboComponentScan
@SpringBootApplication
@MapperScan(basePackages = "com.yicj.ordercenter.dao")
public class OrderCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCenterApplication.class, args) ;
    }
}
