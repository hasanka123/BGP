package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeclarationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    @FindBy(id = "react-declaration-criminal_liability_check-false")
    private WebElement criminalLiabilityCheckNo;
    @FindBy(id = "react-declaration-civil_proceeding_check-false")
    private WebElement civilProceedingCheckNo;
    @FindBy(id = "react-declaration-insolvency_proceeding_check-false")
    private WebElement insolvencyProceedingCheckNo;
    @FindBy(id = "react-declaration-project_incentives_check-false")
    private WebElement projectIncentivesCheckNo;
    @FindBy(id = "react-declaration-other_incentives_check-false")
    private WebElement otherIncentivesCheckNo;
    @FindBy(id = "react-declaration-project_commence_check-false")
    private WebElement projectCommenceCheckNo;
    @FindBy(id = "react-declaration-related_party_check-false")
    private WebElement relatedPartyCheckNo;
    @FindBy(id = "react-declaration-debarment_check-false")
    private WebElement debarmentCheckNo;
    @FindBy(id = "react-declaration-consent_acknowledgement_check")
    private WebElement acknowledgementCheck;
    @FindBy(id = "save-btn")
    private WebElement saveButton;
    @FindBy(id = "review-btn")
    private WebElement reviewButton;
    @FindBy(id = "react-declaration-info_truthfulness_check")
    private WebElement truthfulnessCheck;
    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    public DeclarationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void criminalLiability(boolean isYes) {
        if (!isYes) {
            wait.until(ExpectedConditions.visibilityOf(criminalLiabilityCheckNo)).click();
        }
    }

    public void civilProceeding(boolean isYes) {
        if (!isYes) {
            civilProceedingCheckNo.click();
        }
    }

    public void insolvencyProceeding(boolean isYes) {
        if (!isYes) {
            insolvencyProceedingCheckNo.click();
        }
    }

    public void projectIncentives(boolean isYes) {
        if (!isYes) {
            projectIncentivesCheckNo.click();
        }
    }

    public void otherIncentives(boolean isYes) {
        if (!isYes) {
            otherIncentivesCheckNo.click();
        }
    }

    public void projectCommence(boolean isYes) {
        if (!isYes) {
            projectCommenceCheckNo.click();
        }
    }

    public void relatedParty(boolean isYes) {
        if (!isYes) {
            relatedPartyCheckNo.click();
        }
    }

    public void debarment(boolean isYes) {
        if (!isYes) {
            debarmentCheckNo.click();
        }
    }

    public void acknowledgementCheck() {
        wait.until(ExpectedConditions.elementToBeClickable(acknowledgementCheck));
        actions.moveToElement(acknowledgementCheck).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void clickSave() {
        actions.moveToElement(saveButton).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void clickReview() {
        reviewButton.click();
    }

    public void submitApplication() {
        wait.until(ExpectedConditions.elementToBeClickable(truthfulnessCheck));
        actions.moveToElement(truthfulnessCheck).click().perform();

        submitButton.click();
    }
}
