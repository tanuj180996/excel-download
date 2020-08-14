package com.tanuj.exceldownload.controller;

import com.tanuj.exceldownload.util.ExcelGenerator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerExcelDownloadRestAPI {

    @GetMapping(value = "/download/customers")
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws Exception {
        //  List<Customer> customers = (List<Customer>) customerRepository.findAll();

        ByteArrayInputStream in = ExcelGenerator.m1();
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PostMapping("/import")
    public void mapReapExcelDatatoDB(@RequestParam("file3") MultipartFile reapExcelDataFile) throws IOException {

        List<String> tempStudentList = new ArrayList<String>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {


            XSSFRow row = worksheet.getRow(i);

            String datta = "";
            datta = Double.toString(row.getCell(0).getNumericCellValue()) ;

            tempStudentList.add(datta);
        }
        System.out.println("hiii");
        tempStudentList.forEach(System.out::println);
    }
}
