package com.yicj.hello.excel;

import com.yicj.hello.excel.properties.MyAppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MyAppProperties.class)
public class SqlGeneratorApp {
    public static void main(String[] args) {
        SpringApplication.run(SqlGeneratorApp.class, args) ;
    }
}
