package com.yicj.study.sentinel.rulefunc;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.List;

public class FlowRuleInitFunc implements InitFunc {
    @Override
    public void init() throws Exception {
        List<FlowRule> rules = new ArrayList<>() ;
        FlowRule rule = new FlowRule() ;
        rule.setCount(1) ;
        rule.setResource("hello") ;
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS) ;
        rule.setLimitApp("default") ;
        rules.add(rule) ;
        FlowRuleManager.loadRules(rules);
    }
}
