package com.yicj.hello.excel.runner;

import com.yicj.hello.excel.common.ExcelReadHelper;
import com.yicj.hello.excel.properties.MyAppProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Map<String, String>> mapList = excelReadHelper.readExcelData(dateNameList, dateFormatStr);
        mapList.forEach(item -> {
            String sqlTemplate = appProperties.getSqlTemplate().toUpperCase();
            VariableTokenHandler handler = new VariableTokenHandler(item);
            GenericTokenParser parser = new GenericTokenParser("${", "}", handler) ;
            String retContent = parser.parse(sqlTemplate);
            log.info("sql is  : {}", retContent);
        });
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
            return variables.get(content);
        }
    }
}
