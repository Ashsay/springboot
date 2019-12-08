package com.single.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OperateExcel {

    public void readExcelFile(InputStream inputStream, String fileName) {
        Workbook workbook = null;
        try {
            //判断文件类型
            if(fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook(inputStream);
            }else if(fileName.endsWith("xlsx")){
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(workbook != null){
            //获取所有工作表数量
            int numOfSheet = workbook.getNumberOfSheets();
            System.out.println(numOfSheet);
            for(int i=0; i<numOfSheet; i++){
                Sheet sheet = workbook.getSheetAt(i);
                if(sheet == null) continue;
                int lastRowNum = sheet.getLastRowNum();
                if(lastRowNum == 0) continue;
                Row row;
                for (int j=0; j<=lastRowNum; j++){
                    row = sheet.getRow(j);
                    if(row == null) continue;
                    short lasCellNull = row.getLastCellNum();
                    for (int k=0; k<lasCellNull; k++){
                        if(row.getCell(k) == null) continue;
                        row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
                        String res = row.getCell(k).getStringCellValue().trim();
                        System.out.println(res);
                    }
                }
            }
        }
    }

    public void writeExcel(OutputStream outputStream){
        /**
         * 这个outputstream可以来自与文件的输出流，
         * 也可以直接输出到response的getOutputStream()里面
         * 然后用户就可以直接解析到你生产的excel文件了
         */
        Workbook wb = new SXSSFWorkbook(100);
        //创建一个工作本
        Sheet sheet = wb.createSheet("sheet");

        //通过一个sheet创建一个Row
        Row row = sheet.createRow(0);

        for(int i =0;i<10;i++){
            //通过row创建一个cell
            Cell  cell = row.createCell(i);
            cell.setCellValue("这是第"+i+"个cell");
        }
        try {
            wb.write(outputStream);
            wb.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
