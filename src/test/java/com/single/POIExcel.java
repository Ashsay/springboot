package com.single;

import com.single.common.exception.CommonServiceException;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.annotation.processing.FilerException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@XmlRootElement(name = "excel")
@XmlAccessorType(XmlAccessType.FIELD)
@Data

public class POIExcel {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void PIOExcel() {
        String filePath = "/Users/ashsay/IdeaProjects/single/uploads/first.xlsx";
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {
            XSSFSheet sheet = workbook.createSheet("普通表");

            // 自定义样式：居中、蓝色、加粗
            final XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setColor(IndexedColors.BLUE.getIndex());
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);

            // 设置首行
            String[] titles = new String[]{"行首1", "行首2", "行首3", "行首4", "行首5"};
            XSSFRow first = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                XSSFCell cell = first.createCell(i);
                cell.setCellValue(titles[i]);
                cell.setCellStyle(cellStyle);
            }

            // 填充数据
            for (int i = 1; i < 10; i++) {
                XSSFRow row = sheet.createRow(i);
                for (int j = 0; j < 5; j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(String.format("第%s行，第%s列", i + 1, j + 1));
                }
            }

            // 设置宽度自适应
            for (int o = 0; o < 5; o++) {
                sheet.autoSizeColumn(o, true);
                sheet.setColumnWidth(o, sheet.getColumnWidth(o) * 17 / 10);
            }
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExcel() throws IOException {
        String filePath = System.getProperty("user.dir") + "/uploads/demo.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream outputStream = new FileOutputStream(filePath);

        XSSFSheet sheet = workbook.createSheet("demo");
        XSSFRow row = sheet.createRow(0);
        String[] titles = {"id", "name", "sex"};
        for(int i=0; i<titles.length; i++){
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }

        workbook.write(outputStream);
        workbook.close();

    }

    @Test
    public void TestPDF(){

    }

}
