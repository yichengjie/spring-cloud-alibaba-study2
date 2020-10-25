package com.yicj.study.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HelloFlowRule {

    private static void doSomething(){
        try (Entry entry = SphU.entry("doSomething")){
            // 业务方法逻辑处理
            log.info("hello world : {}", System.currentTimeMillis());
        } catch (BlockException e) {
            // 处理被流控的逻辑
            log.info("处理被流控的逻辑...");
        }
    }

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>() ;
        FlowRule rule = new FlowRule() ;
        rule.setResource("doSomething") ;
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS) ;
        rule.setCount(20) ;
        rules.add(rule) ;
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        initFlowRules();
        while (true){
            doSomething();
        }
    }
}
