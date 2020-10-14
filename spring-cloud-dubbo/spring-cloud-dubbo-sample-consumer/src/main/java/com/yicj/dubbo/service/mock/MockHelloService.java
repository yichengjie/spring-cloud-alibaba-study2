package com.yicj.dubbo.service.mock;

import com.yicj.dubbo.service.IHelloService;

public class MockHelloService implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "Sorry, 服务无法访问，返回降级数据";
    }
}
