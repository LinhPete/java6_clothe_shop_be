package com.linhpete.java6.base;

import lombok.Getter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ExcelHelper {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private int currentRowNum = 0;

    public ExcelHelper(String sheetName) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    public void createHeaderRow(String[] headers) {
        Row row = sheet.createRow(currentRowNum++);
        int cellNum = 0;
        for (String header : headers) {
            Cell cell = row.createCell(cellNum++);
            cell.setCellValue(header);
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(style);
        }
    }

    public void addRow(Object[] data) {
        Row row = sheet.createRow(currentRowNum++);
        int cellNum = 0;
        for (Object value : data) {
            Cell cell = row.createCell(cellNum++);
            if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Double) {
                cell.setCellValue((Double) value);
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            } else if (value instanceof Date) {
                cell.setCellValue((Date) value);
            }
        }
    }

    public void autosizeColumns() {
        Row headerRow = sheet.getRow(0);
        if (headerRow != null) {
            int colCount = headerRow.getLastCellNum();
            for (int i = 0; i < colCount; i++) {
                sheet.autoSizeColumn(i);
            }
        }
    }

    public void writeToFile(String filePath) throws IOException {
        autosizeColumns();
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            workbook.write(fos);
        }
        workbook.close();
    }

    public static Map<String, String[]> readDataFromExcelUser(String filePath, String sheetName) throws IOException {
        Map<String, String[]> dataMap = new LinkedHashMap<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Không tìm thấy sheet: " + sheetName);
                return dataMap;
            }
            DataFormatter formatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() < 1) continue;
                String operationType = formatter.formatCellValue(row.getCell(0));
                String key = formatter.formatCellValue(row.getCell(1));
                String id = formatter.formatCellValue(row.getCell(2));
                String email = formatter.formatCellValue(row.getCell(3));
                String password = formatter.formatCellValue(row.getCell(4));
                String fullName = formatter.formatCellValue(row.getCell(5));
                String phone = formatter.formatCellValue(row.getCell(8));
                String gender = formatter.formatCellValue(row.getCell(9));
                String role = formatter.formatCellValue(row.getCell(10));
                String expected = formatter.formatCellValue(row.getCell(11));

                dataMap.put(key, new String[]{
                        operationType,
                        id,
                        email,
                        password,
                        fullName,
                        phone,
                        gender,
                        role,
                        expected
                });
            }
        }
        return dataMap;
    }

    public static Map<String, String[]> readDataFromInputStream(InputStream is, String sheetName) throws IOException {
        Map<String, String[]> dataMap = new LinkedHashMap<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Không tìm thấy sheet: " + sheetName);
                return dataMap;
            }
            DataFormatter formatter = new DataFormatter();
            for (Row row : sheet) {
                if (row.getRowNum() < 1) continue;

                String operationType = formatter.formatCellValue(row.getCell(0));
                String key = formatter.formatCellValue(row.getCell(1));
                String id = formatter.formatCellValue(row.getCell(2));
                String email = formatter.formatCellValue(row.getCell(3));
                String password = formatter.formatCellValue(row.getCell(4));
                String fullName = formatter.formatCellValue(row.getCell(5));
                String phone = formatter.formatCellValue(row.getCell(8));
                String gender = formatter.formatCellValue(row.getCell(9));
                String role = formatter.formatCellValue(row.getCell(10));
                String expected = formatter.formatCellValue(row.getCell(11));

                dataMap.put(key, new String[]{
                        operationType, id, email, password, fullName,
                        phone, gender, role, expected
                });
            }
        }
        return dataMap;
    }

    public static Map<String, String[]> readDataLoginFromInputStream(InputStream is, String sheetName) throws IOException {
        Map<String, String[]> dataMap = new LinkedHashMap<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Không tìm thấy sheet: " + sheetName);
                return dataMap;
            }
            DataFormatter formatter = new DataFormatter();
            for (Row row : sheet) {
                if (row.getRowNum() < 1) continue;

                String key = formatter.formatCellValue(row.getCell(0));
                String description = formatter.formatCellValue(row.getCell(1));
                String email = formatter.formatCellValue(row.getCell(2));
                String password = formatter.formatCellValue(row.getCell(3));
                String expected = formatter.formatCellValue(row.getCell(4));

                dataMap.put(key, new String[]{
                        description, email, password, expected
                });
            }
        }
        return dataMap;
    }

    public static Map<String, String[]> readDataSignInFromInputStream(InputStream is, String sheetName) throws IOException {
        Map<String, String[]> dataMap = new LinkedHashMap<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Không tìm thấy sheet: " + sheetName);
                return dataMap;
            }
            DataFormatter formatter = new DataFormatter();
            for (Row row : sheet) {
                if (row.getRowNum() < 1) continue;

                String key = formatter.formatCellValue(row.getCell(0));
                String description = formatter.formatCellValue(row.getCell(1));
                String email = formatter.formatCellValue(row.getCell(2));
                String password = formatter.formatCellValue(row.getCell(3));
                String firstname = formatter.formatCellValue(row.getCell(4));
                String lastname = formatter.formatCellValue(row.getCell(5));
                String gender = formatter.formatCellValue(row.getCell(6));
                String phone = formatter.formatCellValue(row.getCell(8));
                String expected = formatter.formatCellValue(row.getCell(9));

                dataMap.put(key, new String[]{
                        description,
                        email,
                        password,
                        firstname,
                        lastname,
                        gender,
                        phone,
                        gender,
                        expected
                });
            }
        }
        return dataMap;
    }
}
