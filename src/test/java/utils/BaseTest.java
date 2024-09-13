package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.InputStream;

public class BaseTest {
    protected JSONObject testData;
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;

    @BeforeSuite
    public void setupReport() {
        extentReports = ExtentManager.getExtentReports();
    }

    protected void loadTestData(String jsonFileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testdata/" + jsonFileName);
        if (inputStream == null) {
            throw new RuntimeException("Cannot find " + jsonFileName + " file.");
        }
        testData = new JSONObject(new JSONTokener(inputStream));
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test Passed");
        }
    }

    @AfterSuite
    public void teardown() {
        WebDriverManagerSingleton.quitDriver();
        extentReports.flush();
    }
}