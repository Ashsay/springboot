package com.single.controller;

import com.alibaba.fastjson.JSONObject;
import com.single.utils.OperateExcel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello(){
        return "Hello SpringBoot";
    }

    @RequestMapping("/index")
    public Object index(Model model){
        model.addAttribute("title","Hello World");
        return "/index";
    }

    @PostMapping("/xls")
    @ResponseBody
    public void excel(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        OperateExcel operateExcel = new OperateExcel();
        operateExcel.readExcelFile(inputStream, fileName);
    }

    @GetMapping("/req")
    @ResponseBody
    public Object req() throws IOException {

        String url = "http://v.juhe.cn/weather/index?format=2&cityname=上海&key=99698482990070cd396893dc19cdeeab";
        String result = restTemplate.getForObject(url, String.class);
        String[] titles = {"date", "weather_id", "week", "temperature", "weather", "wind"};
        String filePath = System.getProperty("user.dir") + "/uploads/weather.xlsx";
        FileOutputStream outputStream = new FileOutputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("weather");
        XSSFRow row = sheet.createRow(0);
        for(int i=0; i<titles.length; i++){
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }

        Map map = (Map)JSONObject.parse(result);
        Map map2 = (Map) map.get("result");
//        System.out.println(map.get("result"));
        System.out.println(map2.get("future"));
        List<Map> list = (List<Map>) map2.get("future");

        for(int i=0; i<list.size(); i++){
            Map res = list.get(i);
            XSSFRow nextRow = sheet.createRow(i+1);
            System.out.println(res);
            for(int j=0; j<titles.length; j++){
                Object value = res.get(titles[j]);
                XSSFCell nextCell = nextRow.createCell(j);
                nextCell.setCellValue(String.valueOf(value));
            }
        }

        workbook.write(outputStream);

        return result;
    }

}
