package com.linhpete.java6.test.ui.login;

import com.linhpete.java6.base.ExcelHelper;
import com.linhpete.java6.base.FilePathTest;
import com.linhpete.java6.base.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

public class LoginTest {

    private WebDriver driver;
    private SeleniumHelper seleniumHelper;
    private ExcelHelper excelHelper;

    private static final String SHEET_NAME = "Sheet1";
    private static final String EXCEL_FILE_NAME = "LoginTestCases.xlsx";

    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By ERROR_MESSAGE = By.cssSelector(".error-message");

    private int rowIndex = 1;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:5173/login");

        seleniumHelper = new SeleniumHelper(driver, 10);
        excelHelper = new ExcelHelper(SHEET_NAME);
        excelHelper.createHeaderRow(new String[]{"TestCaseID", "Description", "Email", "Password", "Expedition", "Actual", "Result", "ImageError"});
    }

    @DataProvider(name = "loginTestData")
    public Object[][] loginTestData() throws IOException {
        Map<String, String[]> testData = FilePathTest.readLoginTestData(EXCEL_FILE_NAME, SHEET_NAME);
        Object[][] data = new Object[testData.size()][];
        int i = 0;
        for (Map.Entry<String, String[]> entry : testData.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            data[i++] = new Object[]{key, values[0], values[1], values[2], values[3]};
        }
        return data;
    }

    @Test(dataProvider = "loginTestData")
    public void testLogin(String key, String description, String email, String password, String expected) throws IOException {
        try {
            driver.navigate().refresh();
            seleniumHelper.sendKeys(EMAIL_INPUT, email);
            seleniumHelper.sendKeys(PASSWORD_INPUT, password);
            seleniumHelper.click(LOGIN_BUTTON);

            String actual = "";
            try {
                actual = seleniumHelper.getText(ERROR_MESSAGE);
            } catch (Exception e) {
                actual = "Success";
            }

            boolean passed = expected.equalsIgnoreCase(actual);
            Object[] row = {key, description, email, password, expected, actual, passed ? "PASSED" : "FAILED"};
            excelHelper.addRow(row);
        } catch (Exception ex) {
            Object[] row = {key, description, email, password, expected, "Exception xảy ra", "FAILED"};
            excelHelper.addRow(row);
        }
        rowIndex++;
    }

    @AfterClass
    public void tearDown() throws IOException {
        if (driver != null) {
            driver.quit();
        }
    }
}
