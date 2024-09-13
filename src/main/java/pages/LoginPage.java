package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriverWait wait;

    @FindBy(xpath = "//input[@id='entityId']")
    WebElement entityIdField;

    @FindBy(id = "userId")
    WebElement userIdField;

    @FindBy(id = "userRole")
    WebElement userRoleField;

    @FindBy(id = "userFullName")
    WebElement fullNameField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "(//div[@id='mainnav-1']//div[@class='username'])[2]")
    WebElement profileName;

    public LoginPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public void loginAsCorpPassUser(String entityId, String userId, String userRole, String fullName) {
        entityIdField.sendKeys(entityId);
        userIdField.sendKeys(userId);
        userRoleField.sendKeys(userRole);
        fullNameField.sendKeys(fullName);
        loginButton.click();
    }

    public boolean isLoginSuccessful(String expectedProfileName) {
        return wait.until(ExpectedConditions.visibilityOf(profileName)).getText().equals(expectedProfileName);
    }
}