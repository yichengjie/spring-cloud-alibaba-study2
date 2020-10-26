package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "sentinel.nacos")
public class NacosPropertiesConfiguration {
    private String serverAddr ;
    private String dataId ;
    private String groupId = "DEFAULT_GROUP" ;
    private String namespace ;
}
