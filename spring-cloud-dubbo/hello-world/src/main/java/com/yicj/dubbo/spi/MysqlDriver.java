package com.yicj.dubbo.spi;

public class MysqlDriver implements Driver{
    @Override
    public String connect() {
        return "连接Mysql 数据库";
    }
}
