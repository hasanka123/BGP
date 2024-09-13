package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ViewPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;

public class ViewTest extends BaseTest {

    private WebDriver driver;
    private ViewPage viewPage;

    @BeforeClass
    public void setup() {
        driver = WebDriverManagerSingleton.getDriver();
    }

    @Test
    public void testApplicationSubmissionSuccess() {
        viewPage = new ViewPage(driver);

        String status = viewPage.validateStatusMessage();
        Assert.assertEquals(status, "Your application has been submitted.");

        String refId = viewPage.validateApplicationRefID();
        Assert.assertFalse(refId.isEmpty(), "Application Ref ID is missing or empty!");

        String agencyName = viewPage.validateApplicationAgency();
        Assert.assertEquals(agencyName, "Enterprise Singapore");
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
