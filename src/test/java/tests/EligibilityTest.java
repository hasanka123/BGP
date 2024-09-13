package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.EligibilityPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;
import java.time.Duration;

public class EligibilityTest extends BaseTest {

    private WebDriver driver;
    private EligibilityPage eligibilityPage;

    @BeforeClass
    public void setup() {
        driver = WebDriverManagerSingleton.getDriver();
        eligibilityPage = new EligibilityPage(driver);
    }

    // User Story 1, AC 1: Verify that the eligibility section contains 5 questions
    @Test(priority = 1)
    public void testAllEligibilityQuestionsPresent() {
        Assert.assertTrue(eligibilityPage.isApplicantRegisteredQuestionPresent(), "Applicant registered question should be present.");
        Assert.assertTrue(eligibilityPage.isGroupSalesTurnoverQuestionPresent(), "Group sales turnover question should be present.");
        Assert.assertTrue(eligibilityPage.isLocalEquityQuestionPresent(), "Local equity question should be present.");
        Assert.assertTrue(eligibilityPage.isTargetMarketQuestionPresent(), "Target market question should be present.");
        Assert.assertTrue(eligibilityPage.isAllStatementsTrueQuestionPresent(), "Statements question should be present.");
    }

    //  User Story 1, AC 2: Verify that each question can be answered using Yes or No radio buttons
    @Test(priority = 2)
    public void testYesNoOptionsForEligibilityQuestions() {
        eligibilityPage.selectApplicantRegistered(true);
        eligibilityPage.selectGroupSales(true);
        eligibilityPage.selectLocalEquity(true);
        eligibilityPage.selectTargetMarket(true);
        eligibilityPage.selectAllStatementsTrue(true);

        eligibilityPage.clickSave();
    }

    //  User Story 1, AC 3: Answering No for any question should display a warning message
    @Test(priority = 3)
    public void testWarningMessageDisplayedOnNoSelection() {
        eligibilityPage.selectApplicantRegistered(false);
        Assert.assertTrue(eligibilityPage.isWarningMessageDisplayed(), "Warning message should be displayed when 'No' is selected.");
    }

    // User Story 1, AC 4: Verify that the FAQ link launches in a new window/tab
    @Test(priority = 4)
    public void testFaqLinkOpensNewTab() {
        eligibilityPage.selectApplicantRegistered(false);

        eligibilityPage.clickFaqLink();

        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.gobusiness.gov.sg/business-grants-portal-faq/", "The new tab should open the FAQ page.");

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    // User Story 1, AC 5: Verify that clicking 'Save' saves the Applicant's inputs and refreshing the page reloads the saved values
    @Test(priority = 5)
    public void testSaveAndReloadEligibilityForm() throws InterruptedException {
        eligibilityPage.selectApplicantRegistered(true);
        eligibilityPage.selectGroupSales(true);
        eligibilityPage.selectLocalEquity(true);
        eligibilityPage.selectTargetMarket(true);
        eligibilityPage.selectAllStatementsTrue(true);

        eligibilityPage.clickSave();

        driver.navigate().refresh();

        //Thread.sleep(1000);
        Assert.assertTrue(eligibilityPage.isApplicantRegisteredYesSelected(), "Applicant registered should still be selected as 'Yes'.");
        Assert.assertTrue(eligibilityPage.isGroupSalesYesSelected(), "Group sales should still be selected as 'Yes'.");
        Assert.assertTrue(eligibilityPage.isLocalEquityYesSelected(), "Local equity should still be selected as 'Yes'.");
        Assert.assertTrue(eligibilityPage.isTargetMarketYesSelected(), "Target market should still be selected as 'Yes'.");
        Assert.assertTrue(eligibilityPage.isAllStatementsTrueYesSelected(), "All statements should still be selected as 'Yes'.");
    }

    // Negative Test Case: Verify error when no options are selected
    @Test(priority = 6, enabled = false)
    public void testErrorWhenNoOptionsSelected() {
        eligibilityPage.clickSave();
        Assert.assertTrue(eligibilityPage.isValidationMessageDisplayed(), "Validation message should be displayed for unanswered questions.");
    }

    // Negative Test Case: Verify warning message is not displayed if all answers are "Yes"
    @Test(priority = 7, enabled = false)
    public void testNoWarningMessageOnAllYes() {
        eligibilityPage.selectApplicantRegistered(true);
        eligibilityPage.selectGroupSales(true);
        eligibilityPage.selectLocalEquity(true);
        eligibilityPage.selectTargetMarket(true);
        eligibilityPage.selectAllStatementsTrue(true);

        eligibilityPage.clickSave();

        Assert.assertFalse(eligibilityPage.isWarningMessageDisplayed(), "Warning message should not be displayed when all answers are 'Yes'.");
    }

    @AfterClass
    public void testNavigateNextPage() {
        eligibilityPage.clickNext();
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