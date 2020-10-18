package com.yicj.account.service.impl;

import com.yicj.account.service.IAccountService;
import org.apache.dubbo.config.annotation.Service;

//@Service
public class AccountServiceImpl implements IAccountService {

    @Override
    public String hello(String name) {
        return "hello world :" + name;
    }
}
