package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CognitoLoginPage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;

public class LoginTest extends BaseTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        loadTestData("login.json");
        driver = WebDriverManagerSingleton.getDriver();
        driver.get("https://qa-internet.bgp.onl/");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        CognitoLoginPage cognitoLoginPage = new CognitoLoginPage(driver);

        JSONObject cognito = testData.getJSONObject("cognito");
        String cognitoUsername = cognito.getString("username");
        String cognitoPassword = cognito.getString("password");

        JSONObject login = testData.getJSONObject("login");
        String loginUsername = login.getString("username");
        String loginPassword = login.getString("password");
        String loginRole = login.getString("role");
        String loginName = login.getString("name");

        cognitoLoginPage.loginToCognito(cognitoUsername, cognitoPassword);

        cognitoLoginPage.loginButton.click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsCorpPassUser(loginUsername, loginPassword, loginRole, loginName);

        Assert.assertTrue(loginPage.isLoginSuccessful(loginName), "Login failed for " + loginName);
    }

    @BeforeMethod
    public void setUpTest(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        extentTest = extentReports.createTest(testName);
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
            extentTest.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }
}
