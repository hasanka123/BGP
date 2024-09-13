package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EligibilityPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    @FindBy(xpath = "//label[contains(text(),'Is the applicant registered in Singapore?')]")
    private WebElement applicantRegisteredQuestion;
    @FindBy(xpath = "//input[@id='react-eligibility-sg_registered_check-true']")
    private WebElement applicantRegisteredYes;
    @FindBy(xpath = "//input[@id='react-eligibility-sg_registered_check-false']")
    private WebElement applicantRegisteredNo;
    @FindBy(xpath = "//label[contains(text(),\"Is the applicant\") and contains(text(),\"group sales turnover less than or equal to S$100m or is the applicant\")]\n")
    private WebElement groupSalesTurnoverQuestion;
    @FindBy(xpath = "//input[@id='react-eligibility-turnover_check-true']")
    private WebElement groupSalesYes;
    @FindBy(xpath = "//label[contains(normalize-space(),'Does the applicant have at least 30%') and contains(normalize-space(),\"local equity\")]")
    private WebElement localEquityQuestion;
    @FindBy(xpath = "//input[@id='react-eligibility-global_hq_check-true']")
    private WebElement localEquityYes;
    @FindBy(xpath = "//label[contains(normalize-space(),\"Are the\") and contains(normalize-space(),\"target market(s) that you are applying for a new market?\")]\n")
    private WebElement targetMarketQuestion;
    @FindBy(xpath = "//input[@id='react-eligibility-new_target_market_check-true']")
    private WebElement targetMarketYes;
    @FindBy(xpath = "//label[contains(normalize-space(),'Are all the following statements true for this project?')]")
    private WebElement allStatementsTrueQuestion;
    @FindBy(xpath = "//input[@id='react-eligibility-started_project_check-true']")
    private WebElement allStatementsTrueYes;
    @FindBy(id = "save-btn")
    private WebElement saveButton;
    @FindBy(id = "next-btn")
    private WebElement nextButton;
    @FindBy(xpath = "//a[text()='FAQ']")
    private WebElement faqLink;

    public EligibilityPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isApplicantRegisteredQuestionPresent() {
        return applicantRegisteredQuestion.isDisplayed();
    }

    public boolean isGroupSalesTurnoverQuestionPresent() {
        return groupSalesTurnoverQuestion.isDisplayed();
    }

    public boolean isLocalEquityQuestionPresent() {
        return localEquityQuestion.isDisplayed();
    }

    public boolean isTargetMarketQuestionPresent() {
        return targetMarketQuestion.isDisplayed();
    }

    public boolean isAllStatementsTrueQuestionPresent() {
        return allStatementsTrueQuestion.isDisplayed();
    }

    public void selectApplicantRegistered(boolean isYes) {
        if (isYes) {
            applicantRegisteredYes.click();
        } else {
            actions.moveToElement(applicantRegisteredNo).pause(Duration.ofSeconds(1)).click().perform();
        }
    }

    public void selectGroupSales(boolean isYes) {
        if (isYes) {
            groupSalesYes.click();
        } else {
        }
    }

    public void selectLocalEquity(boolean isYes) {
        if (isYes) {
            localEquityYes.click();
        } else {
        }
    }

    public void selectTargetMarket(boolean isYes) {
        if (isYes) {
            targetMarketYes.click();
        } else {
        }
    }

    public void selectAllStatementsTrue(boolean isYes) {
        if (isYes) {
            allStatementsTrueYes.click();
        } else {
        }
    }

    public void clickSave() {
        actions.moveToElement(saveButton).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void clickNext() {
        nextButton.click();
    }

    public void clickFaqLink() {
        faqLink.click();
    }

    public boolean isWarningMessageDisplayed() {
        try {
            WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='field-warning-text']")));
            return warningMessage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isValidationMessageDisplayed() {
        try {
            WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='validation-error']")));
            return validationMessage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isApplicantRegisteredYesSelected() {
        wait.until(ExpectedConditions.visibilityOf(applicantRegisteredYes));
        return applicantRegisteredYes.isSelected();
    }

    public boolean isGroupSalesYesSelected() {
        return groupSalesYes.isSelected();
    }

    public boolean isLocalEquityYesSelected() {
        return localEquityYes.isSelected();
    }

    public boolean isTargetMarketYesSelected() {
        return targetMarketYes.isSelected();
    }

    public boolean isAllStatementsTrueYesSelected() {
        return allStatementsTrueYes.isSelected();
    }
}