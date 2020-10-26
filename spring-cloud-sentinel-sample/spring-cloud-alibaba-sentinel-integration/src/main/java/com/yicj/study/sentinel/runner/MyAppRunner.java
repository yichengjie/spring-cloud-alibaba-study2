package com.yicj.study.sentinel.runner;

import com.yicj.study.sentinel.rulefunc.FlowRuleInitFunc;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyAppRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 加载流控规则
        new FlowRuleInitFunc().init();
    }
}
