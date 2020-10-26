package com.yicj.study.sentinel.component;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomerUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        if (StringUtils.isEmpty(originUrl)){
            return originUrl;
        }
        if (originUrl.startsWith("/clean/")){
            return "/clean/*" ;
        }
        return originUrl;
    }
}
