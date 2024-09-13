package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CostDetailPage {

    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;
    @FindBy(id = "react-project_cost-office_rentals-accordion-header")
    private WebElement marketingPresenceSlider;
    @FindBy(id = "react-project_cost-office_rentals-add-item")
    private WebElement addNewItemButton;
    @FindBy(id = "react-project_cost-office_rentals-0-description")
    private WebElement descriptionField;
    @FindBy(id = "react-project_cost-office_rentals-0-rental_duration")
    private WebElement rentalDurationField;
    @FindBy(id = "react-project_cost-office_rentals-0-amount_in_billing_currency")
    private WebElement monthlyRentalBillingCurrencyField;
    @FindBy(xpath = "//input[@type='file']")
    private WebElement supportingDocumentsInput;
    @FindBy(id = "react-project_cost-office_rentals-0-remarks")
    private WebElement remarksField;
    @FindBy(id = "save-btn")
    private WebElement saveButton;
    @FindBy(id = "next-btn")
    private WebElement nextButton;

    public CostDetailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillProjectCostDetails(String description, String rentalDuration, String rentalInCurrency, String remarks) {
        wait.until(ExpectedConditions.elementToBeClickable(marketingPresenceSlider)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addNewItemButton)).click();

        wait.until(ExpectedConditions.visibilityOf(descriptionField)).sendKeys(description);
        rentalDurationField.sendKeys(rentalDuration);
        monthlyRentalBillingCurrencyField.sendKeys(rentalInCurrency);
        remarksField.sendKeys(remarks);
    }

    public void clickSave() {
        actions.moveToElement(saveButton).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void clickNext() {
        nextButton.click();
    }
}