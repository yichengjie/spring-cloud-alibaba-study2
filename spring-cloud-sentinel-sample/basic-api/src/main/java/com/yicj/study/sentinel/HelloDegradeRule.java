package com.yicj.study.sentinel;


import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HelloDegradeRule {

    private static void initDegradeRule(){
        List<DegradeRule> rules = new ArrayList<>() ;
        DegradeRule degradeRule = new DegradeRule() ;
        degradeRule.setResource("KEY") ;
        degradeRule.setCount(10) ;
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT) ;
        degradeRule.setTimeWindow(10) ;
        degradeRule.setMinRequestAmount(5) ;
        degradeRule.setRtSlowRequestAmount(5) ;
        rules.add(degradeRule) ;
        DegradeRuleManager.loadRules(rules);
    }


    public static void main(String[] args) {

    }
}
