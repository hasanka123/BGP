package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GrantFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "eligibilitySection")
    private WebElement eligibilitySection;

    @FindBy(id = "contactDetailsSection")
    private WebElement contactDetailsSection;

    @FindBy(id = "proposalSection")
    private WebElement proposalSection;

    @FindBy(id = "businessImpactSection")
    private WebElement businessImpactSection;

    @FindBy(id = "costSection")
    private WebElement costSection;

    @FindBy(id = "declareAndReviewSection")
    private WebElement declareAndReviewSection;

    public GrantFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isFormSectionVisible(String formName) {
        String dynamicXPath = String.format("//span[text()='%s']", formName);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(dynamicXPath)))).isDisplayed();
    }
}