package com.yicj.hello.excel.runner;

import com.yicj.hello.excel.common.ExcelReadHelper;
import com.yicj.hello.excel.properties.MyAppProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MyAppRunner implements ApplicationRunner {
    @Autowired
    private MyAppProperties appProperties ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String filePath = appProperties.getFullFilePath() ;
        String sheetName = appProperties.getSheetName() ;
        String dateNames = appProperties.getDateNames();
        List<String> dateNameList = parseDateColumnNames(dateNames) ;
        String dateFormatStr = appProperties.getDateFormatStr() ;
        ExcelReadHelper excelReadHelper = new ExcelReadHelper(filePath, sheetName);
        // 1. 获取excel中的数据
        List<Map<String, String>> dataMapList = excelReadHelper.readExcelData(dateNameList, dateFormatStr);
        // 2. 特殊处理自增字段
        this.specialDealSqlNumValue(dataMapList);
        // 3. 组装sql模板与数据
        List<String> sqlList = this.assembleSql(dataMapList);
        // 4. 打印sql
        printSqlList(sqlList);
    }

    private void specialDealSqlNumValue(List<Map<String, String>> dateMapList){
        String incrementFieldName = appProperties.getIncrementFieldName();
        Integer incrementStartValue = appProperties.getIncrementStartValue();
        for (Map<String, String> item : dateMapList) {
            item.put(incrementFieldName,incrementStartValue.toString()) ;
            incrementStartValue = incrementStartValue +1 ;
        }
    }

    private void printSqlList(List<String> sqlList) throws IOException {
        String filePath = appProperties.getFullFilePath() ;
        File file = new File(filePath) ;
        String rootPath = file.getParent();
        File tmpSqlFile = new File(rootPath, System.currentTimeMillis()+".sql") ;
        BufferedWriter bw = null;
        try {
            FileWriter fout = new FileWriter(tmpSqlFile) ;
            bw = new BufferedWriter(fout) ;
            for (String sql: sqlList){
                bw.write(sql);
                bw.newLine();
            }
        }finally {
            IOUtils.closeQuietly(bw);
        }
    }

    private List<String> assembleSql(List<Map<String, String>> dateMapList){
        return dateMapList.stream().map(item -> {
            String sqlTemplate = appProperties.getSqlTemplate().toUpperCase();
            VariableTokenHandler handler = new VariableTokenHandler(item);
            GenericTokenParser parser = new GenericTokenParser("${", "}", handler);
            String retContent = parser.parse(sqlTemplate);
            return retContent;
        }).collect(Collectors.toList());
    }


    private List<String> parseDateColumnNames(String dateNames){
        List<String> dateNameList = new ArrayList<>() ;
        if (!StringUtils.isEmpty(dateNames)){
            String[] infos = dateNames.toUpperCase().split(",");
            for (String name: infos){
                if (!StringUtils.isEmpty(name)){
                    dateNameList.add(name) ;
                }
            }
        }
        return dateNameList ;
    }


    private class VariableTokenHandler implements TokenHandler {
        private Map<String, String> variables = new HashMap<>();

        private VariableTokenHandler(Map<String, String> variables) {
            this.variables = variables;
        }
        @Override
        public String handleToken(String content) {
            String value = variables.get(content);
            if (StringUtils.isEmpty(value)){
                return null ;
            }else {
                return "'"+value+"'" ;
            }
        }
    }
}
