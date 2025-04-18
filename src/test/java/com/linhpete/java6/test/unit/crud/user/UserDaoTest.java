package com.linhpete.java6.test.unit.crud.user;

import com.linhpete.java6.Java6Application;
import com.linhpete.java6.base.ExcelHelper;
import com.linhpete.java6.base.FilePathTest;
import com.linhpete.java6.base.UserRepoClearHelper;
import com.linhpete.java6.persistence.entity.User;
import com.linhpete.java6.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = Java6Application.class)
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepoClearHelper userRepoClearHelper;

    private SoftAssert softAssert;
    private ExcelHelper excelHelper;
    private final List<Object[]> excelResults = new ArrayList<>();

    private Map<String, String[]> testData;

    @BeforeClass
    public void beforeClass() throws IOException {
        softAssert = new SoftAssert();
        testData = FilePathTest.readUserTestData("User_TestCases_DAO.xlsx", "Users");

        excelHelper = new ExcelHelper("Results User DAO");
        excelHelper.createHeaderRow(new String[]{
                "Test Case", "Email", "Password", "FullName", "Avatar", "Birthday", "Phone", "Gender", "Role", "Expected", "Actual", "Result"
        });
    }

    @DataProvider(name = "userTestData")
    public Object[][] provideTestData(Method method) {
        String operation = "";
        if (method.getName().contains("Create")) {
            operation = "create";
        } else if (method.getName().contains("Update")) {
            operation = "update";
        } else if (method.getName().contains("Delete")) {
            operation = "delete";
        }

        List<Object[]> filtered = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : testData.entrySet()) {
            String[] values = entry.getValue();
            String operationType = values[0];

            if (operationType.equalsIgnoreCase(operation)) {
                Object[] row = new Object[values.length];
                String testCaseId = entry.getKey();
                row[0] = testCaseId;
                System.arraycopy(values, 1, row, 1, values.length - 1);
                filtered.add(row);
            }
        }

        return filtered.toArray(new Object[0][]);
    }

    @Test(dataProvider = "userTestData")
    public void testCreateUser(String testCaseId, String userId, String email, String password, String fullName,
                               String phone, String gender,
                               String role, String expected) {
        runTestOperationCreate(testCaseId, userId, email, password, fullName, phone, gender, role, expected);
    }

    @Test(dataProvider = "userTestData")
    public void testUpdateUser(String testCaseId, String userId, String email, String password, String fullName,
                               String phone, String gender,
                               String role, String expected) {
        runTestOperationUpdate(testCaseId, userId, email, password, fullName, phone, gender, role, expected);
    }

    @Test(dataProvider = "userTestData" )
    public void testDeleteUser(String testCaseId, String userId, String email, String password, String fullName,
                               String avatar, String birthday, String phone, String gender,
                               String role, String expected) {
        runTestOperationDelete(testCaseId, userId, email, password, fullName, avatar, birthday, phone, gender, role, expected);
    }

    private void runTestOperationDelete(String testCaseId, String userId, String email, String password, String fullName, String avatar, String birthday, String phone, String gender, String role, String expected) {

        String actual;
        boolean passed;

        try {
            userRepository.deleteById(userId);
            actual = "Success";
        } catch (Exception e) {
            actual = e.getMessage();
        }
        passed = actual.contains(expected);
        softAssert.assertTrue(passed, "Test case failed: " + testCaseId);

        excelResults.add(new Object[]{
                testCaseId, email, password, fullName, avatar, birthday, phone, gender, role,
                expected, actual, "PASSED"
        });
    }

    private void runTestOperationUpdate(String testCaseId, String userId, String email, String password, String fullName,
                                        String phone, String gender,
                                        String role, String expected) {
        String actual;
        boolean passed;

        try {
            User user = User.builder()
                    .id(userId)
                    .email(email.isBlank() ? null : email)
                    .password(password.isBlank() ? null : password)
                    .fullName(fullName.isBlank() ? null : fullName)
                    .phone(phone.isBlank() ? null : phone)
                    .gender(gender.equalsIgnoreCase("true"))
                    .role(role)
                    .build();

            userRepository.save(user);
            actual = "Success";
        } catch (Exception e) {
            actual = e.getMessage();
        }

        passed = actual.contains(expected);
        softAssert.assertTrue(passed, "Test case failed: " + testCaseId);

        excelResults.add(new Object[]{
                testCaseId, email, password, fullName, phone, gender, role,
                expected, actual, "PASSED"
        });
    }

    private void runTestOperationCreate(String testCaseId, String userId, String email, String password, String fullName,
                                        String phone, String gender,
                                        String role, String expected) {
        String actual;
        boolean passed;

        try {
            User user = User.builder()
                    .email(email.isBlank() ? null : email)
                    .password(password.isBlank() ? null : password)
                    .fullName(fullName.isBlank() ? null : fullName)
                    .phone(phone.isBlank() ? null : phone)
                    .gender(gender.equalsIgnoreCase("true"))
                    .role(role)
                    .build();

            userRepository.save(user);
            actual = "Success";
        } catch (Exception e) {
            actual = e.getMessage();
        }

        passed = actual.contains(expected);
        softAssert.assertTrue(passed, "Test case failed: " + testCaseId);

        excelResults.add(new Object[]{
                testCaseId, email, password, fullName, phone, gender, role,
                expected, actual, "PASSED"
        });
    }

    @AfterClass
    public void afterClass() {
        userRepoClearHelper.clearAllUsersAndResetAutoIncrement();
        softAssert.assertAll();
    }

}
