package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class FlowRuleNacosPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {
    @Autowired
    private NacosPropertiesConfiguration nacosPropertiesConfiguration;
    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<List<FlowRuleEntity>, String> converter;

    @Override
    public void publish(String appName, List<FlowRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(appName, "appName cannot be empty");
        if (rules == null) {
            return;
        }
        String dataID = new StringBuilder(appName).append(NacosConstants.DATA_ID_POSTFIX).toString();
        configService.publishConfig(dataID, nacosPropertiesConfiguration.getGroupId(), converter.convert(rules));
    }
}
