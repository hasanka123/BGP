package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.JSONArray;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GrantApplicationPage;
import pages.GrantFormPage;
import pages.GrantPickerPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;

public class GrantApplicationTest extends BaseTest {
    private WebDriver driver;
    private GrantApplicationPage grantApplicationPage;
    private GrantPickerPage grantPickerPage;
    private GrantFormPage grantFormPage;

    @BeforeClass
    public void setup() {
        loadTestData("grantApplication.json");
        driver = WebDriverManagerSingleton.getDriver();
    }

    @Test
    public void testGrantApplicationProcess() throws InterruptedException {
        grantApplicationPage = new GrantApplicationPage(driver);

        grantApplicationPage.clickGrantButton();

        grantPickerPage = new GrantPickerPage(driver);

        String sector = testData.getString("sectors");
        String developmentArea = testData.getString("developmentArea");
        String functionalArea = testData.getString("functionalArea");

        grantPickerPage.selectSector(sector);
        grantPickerPage.selectDevelopmentArea(developmentArea);
        grantPickerPage.selectFunctionalArea(functionalArea);

        grantFormPage = grantPickerPage.clickProceed();

        JSONArray sections = testData.getJSONArray("sections");

        for (int i = 0; i < sections.length(); i++) {
            String section = sections.getString(i);
            Assert.assertTrue(grantFormPage.isFormSectionVisible(section), section + " section is not visible");
        }
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