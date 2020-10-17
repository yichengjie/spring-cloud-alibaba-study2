package com.yicj.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudNacosSampleProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNacosSampleProviderApp.class, args) ;
    }
}
