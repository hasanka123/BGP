package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GrantApplicationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//a[@id='dashboard-menubox-app-apply-grant']//div[@class='dashboard-action-text-wrapper']")
    private WebElement getNewGrantButton;

    public GrantApplicationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        PageFactory.initElements(driver, this);
    }

    public void clickGrantButton() {
        WebElement grantButton = wait.until(ExpectedConditions.elementToBeClickable(getNewGrantButton));
        grantButton.click();
    }
}