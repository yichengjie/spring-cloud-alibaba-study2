package com.yicj.hello.excel.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "excel")
public class MyAppProperties {
    // excel文件的全路径
    private String fullFilePath ;
    // sheet页名称
    private String sheetName ;
    // sql模板
    private String sqlTemplate ;
    // 日期列名称
    private String dateNames ;
    // 日期格式
    private String dateFormatStr ;
}
