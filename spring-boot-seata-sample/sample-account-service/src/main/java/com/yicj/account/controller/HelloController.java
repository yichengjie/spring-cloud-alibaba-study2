package com.yicj.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public String hello(@RequestParam(name = "name") String name) {
        return String.format("%s say hello!", name);
    }
}
