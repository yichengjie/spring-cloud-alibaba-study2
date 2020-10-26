package com.yicj.study.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/say")
    @SentinelResource(value = "hello", blockHandler = "blockHandlerHello")
    public String say(){
        return "hello ,yicj" ;
    }

    public String blockHandlerHello(BlockException e){
        return "被限流了" ;
    }
}
