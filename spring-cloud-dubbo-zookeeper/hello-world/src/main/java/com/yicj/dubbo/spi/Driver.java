package com.yicj.dubbo.spi;

import org.apache.dubbo.common.extension.SPI;

@SPI("mysqlDriver")
public interface Driver {
    String connect() ;
}
