package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.ContactDetailsPage;
import utils.BaseTest;
import utils.ScreenshotUtil;
import utils.WebDriverManagerSingleton;

import java.io.IOException;

public class ContactDetailsTest extends BaseTest {
    private WebDriver driver;
    private ContactDetailsPage contactDetailsPage;

    @BeforeClass
    public void setup() {
        loadTestData("contactDetails.json");
        driver = WebDriverManagerSingleton.getDriver();
        contactDetailsPage = new ContactDetailsPage(driver);
    }

    @Test(priority = 1)
    public void testFillMainContactDetails() {
        JSONObject mainContact = testData.getJSONObject("mainContact");

        contactDetailsPage.fillMainContactPerson(
                mainContact.getString("name"),
                mainContact.getString("jobTitle"),
                mainContact.getString("phone"),
                mainContact.getString("email"),
                mainContact.getString("altEmail")
        );

        contactDetailsPage.fillPostalCodeAndAutoPopulate(testData.getString("postalCode"));

        Assert.assertNotNull(contactDetailsPage.blkHseNo.getAttribute("value"));
        Assert.assertNotNull(contactDetailsPage.streetDetails.getAttribute("value"));
    }

    @Test(priority = 2)
    public void testUseSameAsRegisteredAddress() {
        JSONObject registeredAddress = testData.getJSONObject("registeredAddress");

        contactDetailsPage.useSameAsRegisteredAddress();

        Assert.assertEquals(contactDetailsPage.postalCodeInput.getAttribute("value"), registeredAddress.getString("postalCode"));
        Assert.assertEquals(contactDetailsPage.blkHseNo.getAttribute("value"), registeredAddress.getString("blkHseNo"));
        Assert.assertEquals(contactDetailsPage.streetDetails.getAttribute("value"), registeredAddress.getString("streetDetails"));
        Assert.assertEquals(contactDetailsPage.level.getAttribute("value"), registeredAddress.getString("level"));
        Assert.assertEquals(contactDetailsPage.unit.getAttribute("value"), registeredAddress.getString("unit"));
        Assert.assertEquals(contactDetailsPage.buildingName.getAttribute("value"), registeredAddress.getString("buildingName"));
    }

    @Test(priority = 3)
    public void testFillLetterOfOffer() {
        JSONObject letterOfOffer = testData.getJSONObject("letterOfOffer");

        contactDetailsPage.fillLetterOfOfferAddressee(
                letterOfOffer.getString("name"),
                letterOfOffer.getString("jobTitle"),
                letterOfOffer.getString("email")
        );
    }

    @Test(priority = 4)
    public void testUseSameAsMainContactPerson() {
        JSONObject mainContact = testData.getJSONObject("mainContact");

        contactDetailsPage.useSameAsMainContactPerson();

        Assert.assertEquals(contactDetailsPage.letterOfferName.getAttribute("value"), mainContact.getString("name"));
        Assert.assertEquals(contactDetailsPage.letterOfferJobTitle.getAttribute("value"), mainContact.getString("jobTitle"));
        Assert.assertEquals(contactDetailsPage.letterOfferEmail.getAttribute("value"), mainContact.getString("email"));

        contactDetailsPage.saveContactDetails();
    }

    @AfterClass
    public void testNavigateNextPage() {
        contactDetailsPage.clickNext();
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