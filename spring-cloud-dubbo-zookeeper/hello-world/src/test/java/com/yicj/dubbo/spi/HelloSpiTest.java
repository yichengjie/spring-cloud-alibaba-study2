package com.yicj.dubbo.spi;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

@Slf4j
public class HelloSpiTest {

    @Test
    public void connect(){
        ExtensionLoader<Driver> extensionLoader =
                ExtensionLoader.getExtensionLoader(Driver.class);
        Driver driver = extensionLoader.getExtension("mysqlDriver");
        //Driver driver = extensionLoader.getExtension("true");
        log.info(driver.connect());
    }

    @Test
    public void compile(){
        ExtensionLoader<Driver> extensionLoader =
                ExtensionLoader.getExtensionLoader(Driver.class);
        Driver adaptiveExtension = extensionLoader.getAdaptiveExtension();
        System.out.println(adaptiveExtension.connect());
    }
}
