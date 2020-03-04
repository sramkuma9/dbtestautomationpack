package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindAPerson {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#firstName")
    WebElement firstNameTextBox;

    @FindBy(css = "#lastName")
    WebElement lastNameTextBox;

    @FindBy(css = "#company")
    WebElement companyTextBox;

    @FindBy(css = "#orgString")
    WebElement bniOrganisationTextBox;

    @FindBy(css = "#queryDateType")
    WebElement dateCriteriaListBox;

    @FindBy(css = "#startDateDisplay")
    WebElement startDateTextBox;

    @FindBy(css = "#endDateDisplay")
    WebElement endDateTextBox;

    @FindBy(css = "#queryDateDisplay")
    WebElement queryDateTextBox;

    @FindBy(css = "#idRoleListScreen")
    WebElement roleListBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement periodMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement periodYear;

    @FindBy(css = "#feesSuspendedFlag")
    WebElement feesSuspendedFlag;

    @FindBy(css = "#activeFlag")
    WebElement activeRecordsOnlyFlag;

    @FindBy(css = "#remarksFlag")
    WebElement showRecordsWithRemarksOnlyFlag;

    @FindBy(css = "#deletedFlag")
    WebElement deletedRecordsFlag;

    @FindBy(css = "#numberPerPage")
    WebElement resultsPerPageTextBox;

    @FindBy(css = "#peopleSearch")
    WebElement searchButton;


    public FindAPerson(WebDriver driver) {
        FindAPerson.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectDateFromDatePicker(String day) throws Exception{
        for(WebElement trElement : datePicker)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            for (int row = 0; row < 7; row++) {
                String dayItem = td_collection.get(row).getText();
                if (day.equals(dayItem)) {
                    td_collection.get(row).findElement(By.tagName("a")).click();
                    TimeUnit.SECONDS.sleep(3);
                    break;
                }
            }
        }
    }

    public void selectMonth(String month) {
        Select visitMonthSelect = new Select(periodMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectYear(String year) {
        Select visitYearSelect = new Select(periodYear);
        visitYearSelect.selectByVisibleText(year);
    }

    public void enterFirstName(String firstName){
        firstNameTextBox.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }

    public void clickSearchButton(){
        searchButton.click();
    }

    public void enterCompany(String company){
        companyTextBox.sendKeys(company);
    }

    public void enterBNIOrganisation(String bniOrganisation){
        bniOrganisationTextBox.sendKeys(bniOrganisation);
    }

    public void selectDateCriteria(String dateCriteria) {
        if (!dateCriteria.equals("")) {
            Select dateCriteriaSelect = new Select(dateCriteriaListBox);
            dateCriteriaSelect.selectByVisibleText(dateCriteria);
        }
    }

    public void clickPeriodStartDate() throws InterruptedException {
        startDateTextBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickPeriodEndDate() throws InterruptedException {
        endDateTextBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickQueryDate() throws InterruptedException {
        queryDateTextBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void selectRole(String role) {
        Select roleSelect = new Select(roleListBox);
        roleSelect.selectByVisibleText(role);
    }

    public void clickfeesSuspendedFlag(String feesSuspended) {
        if (feesSuspended.equals("true")) {
            feesSuspendedFlag.click();
        }
    }

    public void clickActiveRecordsOnlyFlag(String activeRecords) {
        if (activeRecords.equals("true")) {
            activeRecordsOnlyFlag.click();
        }
    }

    public void clickShowRecordsWithRemarksOnlyFlag(String showRecords) {
        if (showRecords.equals("true")) {
            showRecordsWithRemarksOnlyFlag.click();
        }
    }

    public void clickDeletedRecordsFlag(String deletedRecords) {
        if (deletedRecords.equals("true")) {
            deletedRecordsFlag.click();
        }
    }

    public void enterResultsPerPage(String resultsPerPage){
        resultsPerPageTextBox.clear();
        resultsPerPageTextBox.sendKeys(resultsPerPage);
    }
}
