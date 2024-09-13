package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DeclarationPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;

public class DeclarationTest extends BaseTest {

    private WebDriver driver;
    private DeclarationPage declarationPage;

    @BeforeClass
    public void setup() {
        driver = WebDriverManagerSingleton.getDriver();
    }

    @Test(priority = 1)
    public void testYesNoOptionsForDeclarationQuestions() {
        declarationPage = new DeclarationPage(driver);

        declarationPage.criminalLiability(false);
        declarationPage.civilProceeding(false);
        declarationPage.insolvencyProceeding(false);
        declarationPage.projectIncentives(false);
        declarationPage.otherIncentives(false);
        declarationPage.projectCommence(false);
        declarationPage.relatedParty(false);
        declarationPage.debarment(false);

        declarationPage.acknowledgementCheck();

        declarationPage.clickSave();

        declarationPage.clickReview();
    }

    @Test(priority = 2)
    public void testSubmitApplication() {
        declarationPage.submitApplication();
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
