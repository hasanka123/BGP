package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CognitoLoginPage {
    @FindBy(xpath = "(//input[@id='signInFormUsername'])[2]")
    WebElement usernameField;
    @FindBy(xpath = "(//input[@id='signInFormPassword'])[2]")
    WebElement passwordField;
    @FindBy(xpath = "(//input[@name='signInSubmitButton'])[2]")
    WebElement signInButton;
    @FindBy(id = "login-button")
    public WebElement loginButton;
    private final WebDriver driver;

    public CognitoLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginToCognito(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        signInButton.click();
    }
}