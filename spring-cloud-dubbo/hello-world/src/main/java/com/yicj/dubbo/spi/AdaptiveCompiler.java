package com.yicj.dubbo.spi;

import org.apache.dubbo.common.extension.Adaptive;

@Adaptive
public class AdaptiveCompiler implements Driver {
    @Override
    public String connect() {
        return "adaptive test";
    }
}
