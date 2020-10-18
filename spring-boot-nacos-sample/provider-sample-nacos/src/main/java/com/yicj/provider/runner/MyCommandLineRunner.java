package com.yicj.provider.runner;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @NacosInjected
    private NamingService namingService;

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private Integer serverPort;

    @Override
    public void run(String... args) throws Exception {
        // 通过Naming服务注册实例到注册中心
        namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
    }
}
