package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ViewPage {
    @FindBy(xpath = "//td[text()='Agency Details:']/following-sibling::td[@class='value']/span")
    public WebElement agencyDetail;
    @FindBy(xpath = "//td[text()='Ref ID:']/following-sibling::td[@class='value']")
    public WebElement refID;
    private final WebDriverWait wait;
    @FindBy(xpath = "//div[contains(@class,'statusline')]/following-sibling::div//h3")
    private WebElement statusLine;

    public ViewPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String validateStatusMessage() {
        return wait.until(ExpectedConditions.visibilityOf(statusLine)).getText();
    }

    public String validateApplicationRefID() {
        return refID.getText();
    }

    public String validateApplicationAgency() {
        return agencyDetail.getText();
    }
}
