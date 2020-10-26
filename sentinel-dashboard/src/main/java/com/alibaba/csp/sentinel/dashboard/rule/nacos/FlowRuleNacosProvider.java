package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class FlowRuleNacosProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {

    @Autowired
    private NacosPropertiesConfiguration nacosPropertiesConfiguration ;
    @Autowired
    private ConfigService configService ;
    @Autowired
    private Converter<String, List<FlowRuleEntity>> converter ;


    @Override
    public List<FlowRuleEntity> getRules(String appName) throws Exception {
        String dateId = new StringBuffer(appName).append(NacosConstants.DATA_ID_POSTFIX).toString() ;
        String rules = configService.getConfig(dateId, nacosPropertiesConfiguration.getGroupId(), 3000) ;
        log.info("pull FlowRule from Nacos Config : {}", rules);
        if (StringUtils.isEmpty(rules)){
            return Collections.EMPTY_LIST ;
        }
        return converter.convert(rules);
    }
}
