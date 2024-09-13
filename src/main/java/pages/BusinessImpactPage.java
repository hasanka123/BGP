package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BusinessImpactPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    @FindBy(id = "react-project_impact-fy_end_date_0")
    private WebElement financialYearEndDate;
    @FindBy(id = "react-project_impact-overseas_sales_0")
    private WebElement overseasSalesCurrentYear;
    @FindBy(id = "react-project_impact-overseas_sales_1")
    private WebElement overseasSalesNextYear;
    @FindBy(id = "react-project_impact-overseas_sales_2")
    private WebElement overseasSalesNext2Year;
    @FindBy(id = "react-project_impact-overseas_sales_3")
    private WebElement overseasSalesNext3Year;
    @FindBy(id = "react-project_impact-overseas_investments_0")
    private WebElement overseasInvestmentCurrentYear;
    @FindBy(id = "react-project_impact-overseas_investments_1")
    private WebElement overseasInvestmentNextYear;
    @FindBy(id = "react-project_impact-overseas_investments_2")
    private WebElement overseasInvestmentNext2Year;
    @FindBy(id = "react-project_impact-overseas_investments_3")
    private WebElement overseasInvestmentNext3Year;
    @FindBy(id = "react-project_impact-rationale_remarks")
    private WebElement rationaleProjection;
    @FindBy(id = "react-project_impact-benefits_remarks")
    private WebElement tangibleBenefit;
    @FindBy(id = "save-btn")
    private WebElement saveButton;
    @FindBy(id = "next-btn")
    private WebElement nextButton;

    public BusinessImpactPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterFinancialYearEndDate(String value) {
        wait.until(ExpectedConditions.visibilityOf(financialYearEndDate));
        financialYearEndDate.sendKeys(value);
    }

    public void enterOverseasSalesCurrentYear(String value) {
        overseasSalesCurrentYear.sendKeys(value);
    }

    public void enterOverseasSalesNextYear(String value) {
        overseasSalesNextYear.sendKeys(value);
    }

    public void enterOverseasSalesNext2Year(String value) {
        overseasSalesNext2Year.sendKeys(value);
    }

    public void enterOverseasSalesNext3Year(String value) {
        overseasSalesNext3Year.sendKeys(value);
    }


    public void enterOverseasInvestmentCurrentYear(String value) {
        overseasInvestmentCurrentYear.sendKeys(value);
    }

    public void enterOverseasInvestmentNextYear(String value) {
        overseasInvestmentNextYear.sendKeys(value);
    }

    public void enterOverseasInvestmentNext2Year(String value) {
        overseasInvestmentNext2Year.sendKeys(value);
    }

    public void enterOverseasInvestmentNext3Year(String value) {
        overseasInvestmentNext3Year.sendKeys(value);
    }

    public void enterRationale(String rationale) {
        rationaleProjection.clear();
        rationaleProjection.sendKeys(rationale);
    }

    public void enterTangible(String tangible) {
        tangibleBenefit.clear();
        tangibleBenefit.sendKeys(tangible);
    }

    public void clickSave() {
        actions.moveToElement(saveButton).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void clickNext() {
        nextButton.click();
    }
}