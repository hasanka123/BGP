package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GrantPickerPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(xpath = "//button[@id='go-to-grant']")
    private WebElement applyButton;

    @FindBy(xpath = "//button[@id='keyPage-form-button']")
    private WebElement proceedButton;

    public GrantPickerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectSector(String sectorName) throws InterruptedException {
        String dynamicXPath = String.format("//input[@id='%s']/ancestor::div[@class='grid-item']", sectorName);
        WebElement dynamicElement = getElement(dynamicXPath);
        actions.moveToElement(dynamicElement).click().perform();
    }

    public void selectDevelopmentArea(String developmentArea) throws InterruptedException {
        String dynamicXPath = String.format("//div[contains(text(), '%s')]", developmentArea);
        WebElement dynamicElement = getElement(dynamicXPath);
        actions.moveToElement(dynamicElement).click().perform();
    }

    public void selectFunctionalArea(String functionalArea) throws InterruptedException {
        String dynamicXPath = String.format("//div[contains(text(), '%s')]", functionalArea);
        WebElement dynamicElement = getElement(dynamicXPath);
        actions.moveToElement(dynamicElement).click().perform();
    }

    public GrantFormPage clickProceed() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", applyButton);
        js.executeScript("arguments[0].click();", applyButton);
        Thread.sleep(2000);

        js.executeScript("arguments[0].scrollIntoView(true);", proceedButton);
        js.executeScript("arguments[0].click();", proceedButton);
        Thread.sleep(1000);

        return new GrantFormPage(driver);
    }

    public WebElement getElement(String dynamicXPath) throws InterruptedException {
        Thread.sleep(1000);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));
    }
}