package com.yicj.dubbo.controller;

import com.yicj.dubbo.service.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // check默认true，启动时provider
    @Reference(check = false, mock = "com.yicj.dubbo.service.mock.MockHelloService", cluster = "failfast")
    private IHelloService helloService ;

    @GetMapping("/say")
    public String sayHello(){
        return helloService.sayHello("yicj") ;
    }
}
