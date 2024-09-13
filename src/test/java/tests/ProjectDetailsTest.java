package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.ProjectDetailsPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;

public class ProjectDetailsTest extends BaseTest {

    private WebDriver driver;
    private ProjectDetailsPage projectDetailsPage;

    @BeforeClass
    public void setup() {
        loadTestData("projectDetails.json");
        driver = WebDriverManagerSingleton.getDriver();
    }

    @Test
    public void testFillMandatoryFieldsAndSave() {
        projectDetailsPage = new ProjectDetailsPage(driver);

        JSONObject projectDetails = testData.getJSONObject("projectDetails");
        String title = projectDetails.getString("title");
        String startDate = projectDetails.getString("startDate");
        String endDate = projectDetails.getString("endDate");
        String description = projectDetails.getString("description");
        int number1 = projectDetails.getInt("activityDropdownIndex");
        int number2 = projectDetails.getInt("marketDropdownIndex");
        boolean firstTimeExpansion = projectDetails.getBoolean("firstTimeExpansion");

        projectDetailsPage.enterProjectDetails(title, startDate, endDate, description, number1, number2);
        projectDetailsPage.selectFirstTimeExpansion(firstTimeExpansion);

        projectDetailsPage.clickSave();
    }

    @AfterClass
    public void testNavigateNextPage() {
        projectDetailsPage.clickNext();
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