package com.yicj.config.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class HelloController {
    @NacosValue(value = "${info: local value for info}", autoRefreshed = true)
    private String info;

    @GetMapping(path = "/get")
    public String get() {
        return String.format("info value:%s", info);
    }
}
