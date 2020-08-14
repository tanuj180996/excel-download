package com.tanuj.exceldownload.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.security.MessageDigest;
import java.util.TreeSet;
import java.util.zip.CRC32;
import java.util.zip.Checksum;


public class ExcelGenerator {
    public static byte[] arr = null;

    public static long s = 0L;

    public static ByteArrayInputStream m1() throws Exception {


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //some dummy data
        TreeSet<String> temp_rxGroups = new TreeSet<String>();
        for (int i = 0; i < 5; i++) {
            temp_rxGroups.add("" + i);
        }
        String[] countryName = temp_rxGroups.toArray(new String[temp_rxGroups.size()]);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet realSheet = workbook.createSheet("realSheet");
        XSSFSheet hidden = workbook.createSheet("hidden");
      /*  Row row1 = realSheet.createRow(0);
        int index = 0;

            Cell cell1 = row1.createCell(index);
            cell1.setCellValue("first");*/

        int index = 0;
        XSSFRow rowq = realSheet.createRow(index);
        //create 3 column
        for(int i=0;i<=2;i++){

            Cell cell = rowq.createCell(i);
            cell.setCellValue("header"+i);
        }
        for( int j=0;j<=2;j++){
            for (int i = 1, length = countryName.length; i < length; i++) {
                String name = countryName[i];
                XSSFRow row = hidden.createRow(i);
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(name);
            }
        }



        //validation start from here
        DataValidation dataValidation = null;
        DataValidationConstraint constraint = null;
        DataValidationHelper validationHelper = null;
        validationHelper = new XSSFDataValidationHelper(realSheet);

        //here row means form which row to which row you want to appply this check same for column
        CellRangeAddressList addressList = new CellRangeAddressList(1, 5, 0, 2);
        //line
        constraint = validationHelper.createExplicitListConstraint(countryName);
        dataValidation = validationHelper.createValidation(constraint, addressList);
        dataValidation.setSuppressDropDownArrow(true);
        //when user filled out of the list , then user we see error box
        dataValidation.setShowErrorBox(true);
        dataValidation.setShowPromptBox(true);
        workbook.setSheetHidden(1, true);
        realSheet.addValidationData(dataValidation);

        //set Sheet1 active
        workbook.setActiveSheet(1);
        workbook.write(out);

        return new ByteArrayInputStream(out.toByteArray());


    }

/*
    public static ByteArrayInputStream m2() throws Exception {


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //some data


        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data Validation");
        CellRangeAddressList addressList = new CellRangeAddressList(0, 0, 0, 0);
        DVConstraint dvConstraint = DVConstraint
                .createExplicitListConstraint(new String[] { "10", "20", "30" });
        DataValidation dataValidation = new HSSFDataValidation(addressList,
                dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);
        workbook.write(out);
        //byte[] bytes = calculateChecksum(out);
      */
/*  for (int i = 0; i <  workbook.getSheetAt(0).getPhysicalNumberOfRows(); i++) {


            XSSFRow row = workbook.getSheetAt(0).getRow(i);

            byte[] bytes = row.toString().getBytes();
            getCRC32Checksum(bytes);
        }*//*

        // getCRC32Checksum(workbook.getSheetAt(0).getRow(0).toString().getBytes());
        return new ByteArrayInputStream(out.toByteArray());


    }
*/

    private static byte[] calculateChecksum(final ByteArrayOutputStream f) throws Exception {
        File file = new File("my");
        FileInputStream fos = new FileInputStream(file);
        fos.read(f.toByteArray());

        final MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        try (InputStream is = new FileInputStream(file)) {
            final byte[] bytes = new byte[2048];
            int numBytes;
            while ((numBytes = is.read(bytes)) != -1) {
                md.update(bytes, 0, numBytes);
            }
            return md.digest();
        }
    }

    public static void getCRC32Checksum(byte[] bytes) {
        Checksum crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        s = crc32.getValue();
    }

    private static byte[] calculateChecksum(final ByteArrayInputStream f) throws Exception {
        File file = new File("my");
        FileInputStream fos = new FileInputStream(file);
        fos.read();

        final MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        try (InputStream is = new FileInputStream(file)) {
            final byte[] bytes = new byte[2048];
            int numBytes;
            while ((numBytes = is.read(bytes)) != -1) {
                md.update(bytes, 0, numBytes);
            }
            return md.digest();
        }
    }
}
