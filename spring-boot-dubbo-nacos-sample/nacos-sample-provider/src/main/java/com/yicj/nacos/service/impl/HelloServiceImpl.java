package com.yicj.nacos.service.impl;

import com.yicj.nacos.service.IHelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "Hello world: " + name;
    }
}
