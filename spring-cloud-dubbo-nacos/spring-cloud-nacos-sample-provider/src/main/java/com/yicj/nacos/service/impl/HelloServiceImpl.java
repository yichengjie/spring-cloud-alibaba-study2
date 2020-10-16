package com.yicj.nacos.service.impl;

import com.yicj.nacos.service.IHelloService;

public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "Hello World: " + name;
    }
}
