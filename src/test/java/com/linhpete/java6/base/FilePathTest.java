package com.linhpete.java6.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class FilePathTest {

    // Lấy thư mục gốc của dự án
    public static final String BASE_DIR = System.getProperty("user.dir");

    // Đường dẫn tương đối đến thư mục chứa Excel trong resources
    public static final String EXCEL_RESOURCE_PATH = "excel/";

    private static void createDirectory(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    /**
     * Đọc dữ liệu Excel từ resources/excel
     *
     * @param fileName  Tên file (VD: "User_TestCases_DAO.xlsx")
     * @param sheetName Tên sheet trong file Excel
     * @return Map dữ liệu
     */
    public static Map<String, String[]> readUserTestData(String fileName, String sheetName) throws IOException {
        String resourcePath = EXCEL_RESOURCE_PATH + fileName;

        // Dùng ClassLoader để đọc file từ resources
        try (InputStream is = FilePathTest.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Không tìm thấy file: " + resourcePath);
            }

            return ExcelHelper.readDataFromInputStream(is, sheetName);
        }
    }
    public static Map<String, String[]> readLoginTestData(String fileName, String sheetName) throws IOException {
        String resourcePath = EXCEL_RESOURCE_PATH + fileName;

        // Dùng ClassLoader để đọc file từ resources
        try (InputStream is = FilePathTest.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Không tìm thấy file: " + resourcePath);
            }

            return ExcelHelper.readDataLoginFromInputStream(is, sheetName);
        }
    }
    public static Map<String, String[]> readSignInTestData(String fileName, String sheetName) throws IOException {
        String resourcePath = EXCEL_RESOURCE_PATH + fileName;

        // Dùng ClassLoader để đọc file từ resources
        try (InputStream is = FilePathTest.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Không tìm thấy file: " + resourcePath);
            }

            return ExcelHelper.readDataSignInFromInputStream(is, sheetName);
        }
    }
}
