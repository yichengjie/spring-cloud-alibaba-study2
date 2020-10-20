package com.yicj.hello.excel.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ExcelReadHelper {
    private Sheet sheet;

    /**
     * 构造函数，初始化excel数据
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    public ExcelReadHelper(String filePath, String sheetName){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            sheet = workbook.getSheet(sheetName) ;
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        }
    }

    /**
     * 根据行和列的索引获取单元格的数据
     * @param row
     * @param column
     * @return
     */
    public String getExcelDateByIndex(int row,int column){
        Row row1 = sheet.getRow(row);
        String cell = row1.getCell(column).toString();
        return cell;
    }

    /**
     * 根据某一列值为“******”的这一行，来获取该行第x列的值
     * @param caseName
     * @param currentColumn 当前单元格列的索引
     * @param targetColumn 目标单元格列的索引
     * @return
     */
    public String getCellByCaseName(String caseName,int currentColumn,int targetColumn){
        String operateSteps="";
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0; i<rows;i++){
            Row row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if(cell.equals(caseName)){
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }

    //打印excel数据
    public List<Map<String,String>> readExcelData(){
        List<Map<String,String>> list = new ArrayList<>() ;
        // 读取表头
        List<String> titleNames = readRow(sheet.getRow(0)) ;
        titleNames = titleNames.stream().map(String::toUpperCase).collect(Collectors.toList()) ;
        // 读取内容
        //1. 获取excel总共有多少行
        int rowCount = sheet.getPhysicalNumberOfRows();
        for(int i = 1; i < rowCount; i++){
            Map<String,String> tempMap = new HashMap<>() ;
            Row row = sheet.getRow(i);
            // 获取当前行总列数
            int columns = row.getPhysicalNumberOfCells();
            for(int j = 0; j < columns; j++){
                String name = titleNames.get(j) ;
                String value = row.getCell(j).toString();
                tempMap.put(name, value) ;
                log.info("name : {}, value:{}", name, value);
            }
            list.add(tempMap) ;
        }
        return list ;
    }



    private List<String> readRow(Row row){
        List<String> list = new ArrayList<>();
        int columns = row.getPhysicalNumberOfCells();
        for(int j = 0; j < columns; j++){
            String cell = row.getCell(j).toString();
            list.add(cell) ;
        }
        return list ;
    }

}
