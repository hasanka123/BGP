package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProjectDetailsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    @FindBy(id = "react-project-title")
    private WebElement projectTitle;

    @FindBy(id = "react-project-start_date")
    private WebElement startDate;

    @FindBy(id = "react-project-end_date")
    private WebElement endDate;

    @FindBy(id = "react-project-description")
    private WebElement projectDescription;

    @FindBy(id = "react-select-project-activity--value")
    private WebElement activityDropdown;

    @FindBy(id = "react-select-project-primary_market--value")
    private WebElement targetMarketDropdown;

    @FindBy(id = "react-project-is_first_time_expand-true")
    private WebElement firstTimeYes;

    @FindBy(id = "save-btn")
    private WebElement saveButton;

    @FindBy(id = "next-btn")
    private WebElement nextButton;

    public ProjectDetailsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterProjectDetails(String title, String start, String end, String description, int activityIndex, int marketIndex) {
        wait.until(ExpectedConditions.visibilityOf(projectTitle));
        projectTitle.sendKeys(title);
        startDate.sendKeys(start);
        endDate.sendKeys(end);
        projectDescription.sendKeys(description);
        selectActivityDropdown(activityIndex);
        selectTargetMarketDropdown(marketIndex);
    }

    public void selectFirstTimeExpansion(boolean isFirstTime) {
        if (isFirstTime) {
            wait.until(ExpectedConditions.elementToBeClickable(firstTimeYes)).click();
        }
    }

    public void clickSave() {
        actions.moveToElement(saveButton).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void clickNext() {
        nextButton.click();
    }

    public void selectActivityDropdown(int index) {
        activityDropdown.click();

        for (int i = 0; i < index; i++) {
            actions.sendKeys(Keys.DOWN).perform();
        }
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void selectTargetMarketDropdown(int index) {
        targetMarketDropdown.click();

        for (int i = 0; i < index; i++) {
            actions.sendKeys(Keys.DOWN).perform();
        }
        actions.sendKeys(Keys.ENTER).perform();
    }
}