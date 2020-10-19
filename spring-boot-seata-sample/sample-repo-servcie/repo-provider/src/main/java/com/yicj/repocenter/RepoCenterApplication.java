package com.yicj.repocenter;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@DubboComponentScan
@SpringBootApplication
@MapperScan(basePackages = "com.yicj.repocenter.dao")
public class RepoCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepoCenterApplication.class, args) ;
    }
}
