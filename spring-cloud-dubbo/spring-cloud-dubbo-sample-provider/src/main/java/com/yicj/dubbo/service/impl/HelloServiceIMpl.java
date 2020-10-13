package com.yicj.dubbo.service.impl;

import com.yicj.dubbo.service.IHelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class HelloServiceIMpl implements IHelloService {

    @Value("${dubbo.application.name}")
    private String serviceName ;

    @Override
    public String sayHello(String name) {
        return String.format("[%s]: Hello, %s" ,serviceName, name);
    }
}
