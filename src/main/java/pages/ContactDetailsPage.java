package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactDetailsPage {
    @FindBy(id = "react-contact_info-correspondence_address-postal")
    public WebElement postalCodeInput;
    @FindBy(id = "react-contact_info-correspondence_address-block")
    public WebElement blkHseNo;
    @FindBy(id = "react-contact_info-correspondence_address-street")
    public WebElement streetDetails;
    @FindBy(id = "react-contact_info-offeree_name")
    public WebElement letterOfferName;
    @FindBy(id = "react-contact_info-offeree_designation")
    public WebElement letterOfferJobTitle;
    @FindBy(id = "react-contact_info-offeree_email")
    public WebElement letterOfferEmail;
    @FindBy(id = "react-contact_info-correspondence_address-level")
    public WebElement level;
    @FindBy(id = "react-contact_info-correspondence_address-unit")
    public WebElement unit;
    @FindBy(id = "react-contact_info-correspondence_address-building_name")
    public WebElement buildingName;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    @FindBy(id = "react-contact_info-name")
    private WebElement mainContactName;
    @FindBy(id = "react-contact_info-designation")
    private WebElement mainContactJobTitle;
    @FindBy(id = "react-contact_info-phone")
    private WebElement mainContactNo;
    @FindBy(id = "react-contact_info-primary_email")
    private WebElement mainContactEmail;
    @FindBy(id = "react-contact_info-secondary_email")
    private WebElement altContactEmail;
    @FindBy(id = "main_contact_person_mailing_address")
    private WebElement mailingAddress;
    @FindBy(xpath = "//span[@id='react-contact_info-correspondence_address-postal-postal']")
    private WebElement postalLookupButton;
    @FindBy(id = "react-contact_info-correspondence_address-copied")
    private WebElement sameAsRegisteredCheckbox;
    @FindBy(id = "react-contact_info-copied")
    private WebElement sameAsMainContactCheckbox;
    @FindBy(id = "save-btn")
    private WebElement saveButton;
    @FindBy(id = "next-btn")
    private WebElement nextButton;

    public ContactDetailsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillMainContactPerson(String name, String jobTitle, String contactNo, String email, String altEmail) {
        wait.until(ExpectedConditions.visibilityOf(mainContactName));
        mainContactName.sendKeys(name);
        mainContactJobTitle.sendKeys(jobTitle);
        mainContactNo.sendKeys(contactNo);
        mainContactEmail.sendKeys(email);
        altContactEmail.sendKeys(altEmail);

    }

    public void fillPostalCodeAndAutoPopulate(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
        postalLookupButton.click();
        wait.until(ExpectedConditions.visibilityOf(blkHseNo));
    }

    public void useSameAsRegisteredAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(sameAsRegisteredCheckbox));
        actions.moveToElement(sameAsRegisteredCheckbox).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void fillLetterOfOfferAddressee(String name, String jobTitle, String email) {
        letterOfferName.sendKeys(name);
        letterOfferJobTitle.sendKeys(jobTitle);
        letterOfferEmail.sendKeys(email);
    }

    public void useSameAsMainContactPerson() {
        wait.until(ExpectedConditions.elementToBeClickable(sameAsMainContactCheckbox));
        actions.moveToElement(sameAsMainContactCheckbox).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void saveContactDetails() {
        actions.moveToElement(saveButton).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void clickNext() {
        nextButton.click();
    }
}