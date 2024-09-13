package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BusinessImpactPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;

public class BusinessImpactTest extends BaseTest {

    private WebDriver driver;
    private BusinessImpactPage businessImpactPage;

    @BeforeClass
    public void setup() {
        loadTestData("businessImpact.json");
        driver = WebDriverManagerSingleton.getDriver();
    }

    @Test
    public void testFillMandatoryFieldsAndSave() {
        businessImpactPage = new BusinessImpactPage(driver);

        businessImpactPage.enterFinancialYearEndDate(testData.getString("financialYearEndDate"));

        JSONObject overseasSales = testData.getJSONObject("overseasSales");
        businessImpactPage.enterOverseasSalesCurrentYear(overseasSales.getString("currentYear"));
        businessImpactPage.enterOverseasSalesNextYear(overseasSales.getString("nextYear"));
        businessImpactPage.enterOverseasSalesNext2Year(overseasSales.getString("next2Year"));
        businessImpactPage.enterOverseasSalesNext3Year(overseasSales.getString("next3Year"));

        JSONObject overseasInvestment = testData.getJSONObject("overseasInvestment");
        businessImpactPage.enterOverseasInvestmentCurrentYear(overseasInvestment.getString("currentYear"));
        businessImpactPage.enterOverseasInvestmentNextYear(overseasInvestment.getString("nextYear"));
        businessImpactPage.enterOverseasInvestmentNext2Year(overseasInvestment.getString("next2Year"));
        businessImpactPage.enterOverseasInvestmentNext3Year(overseasInvestment.getString("next3Year"));

        businessImpactPage.enterRationale(testData.getString("rationale"));

        businessImpactPage.enterTangible(testData.getString("tangible"));

        businessImpactPage.clickSave();
    }

    @AfterClass
    public void testNavigateNextPage() {
        businessImpactPage.clickNext();
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