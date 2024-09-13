package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CostDetailPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;

public class CostDetailTest extends BaseTest {

    private WebDriver driver;
    private CostDetailPage costDetailPage;

    @BeforeClass
    public void setup() {
        loadTestData("costDetail.json");
        driver = WebDriverManagerSingleton.getDriver();
    }

    @Test
    public void testFillMandatoryFieldsAndSave() {
        costDetailPage = new CostDetailPage(driver);

        JSONObject projectCostDetails = testData.getJSONObject("projectCostDetails");

        String description = projectCostDetails.getString("description");
        String rentalDuration = projectCostDetails.getString("rentalDuration");
        String monthlyRental = projectCostDetails.getString("monthlyRental");
        String remarks = projectCostDetails.getString("remarks");

        costDetailPage.fillProjectCostDetails(description, rentalDuration, monthlyRental, remarks);

        costDetailPage.clickSave();
    }

    @AfterClass
    public void testNavigateNextPage() {
        costDetailPage.clickNext();
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