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

public class FindAnOrganisation {

    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#name")
    WebElement organisationNameTextBox;

    @FindBy(css = "#company")
    WebElement companyTextBox;

    @FindBy(css = "#orgType")
    WebElement organisationTypeListBox;

    @FindBy(css = "#orgStatus")
    WebElement organisationStatusListBox;

    @FindBy(css = "#Category")
    WebElement chapterListBox;

    @FindBy(css = "#secCategory")
    WebElement chapterListBox2;

    @FindBy(css = "#sizeType")
    WebElement sizeListBox;

    @FindBy(css = "#sizeValue")
    WebElement sizeValueTextBox;

    @FindBy(css = "#queryDateType")
    WebElement queryDateTypeListBox;


    @FindBy(css = "#queryDateDisplay")
    WebElement queryDateDisplayListBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement periodMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement periodYear;

    @FindBy(css = "#renewalDue")
    WebElement renewalDueCheckBox;

    @FindBy(css = "#numberPerPage")
    WebElement numberPerPageTextBox;

    @FindBy(css = "#peopleSearch")
    WebElement searchButton;


    public FindAnOrganisation(WebDriver driver) {
        FindAnOrganisation.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectDateFromDatePicker(String day) throws Exception {
        for (WebElement trElement : datePicker) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("td"));
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
    public void clickName()
    {
        organisationNameTextBox.click();
    }

    public void enterName(String name) {
        organisationNameTextBox.sendKeys(name);

    }
    public void clickCompany()
    {
        companyTextBox.click();
    }
    public void enterCompany(String company) {
        companyTextBox.sendKeys(company);
    }
    public void clickOrgType()
    {
        organisationTypeListBox.click();
    }


    public void selectOrgType(String orgType) {
        Select orgTypeSelect = new Select(organisationTypeListBox);
        orgTypeSelect.selectByVisibleText(orgType);
    }

    public void clickOrgStatus()
    {
        organisationStatusListBox.click();
    }
    public void selectOrgStatus(String orgStatus){
        Select orgStatusSelect = new Select (organisationStatusListBox);
        orgStatusSelect.selectByVisibleText(orgStatus);
    }
    public void clickCategory()
    {
        chapterListBox.click();
    }
    public void selectCategory(String category){
        Select categorySelect = new Select (chapterListBox);
        categorySelect.selectByVisibleText(category);
    }
    public void clickSubCategory()
    {
        chapterListBox2.click();
    }

    public void selectSubCategory(String secCategory){
        Select categorySelect2 = new Select (chapterListBox2);
        categorySelect2.selectByVisibleText(secCategory);
    }
    public void clickSizeType()
    {
        sizeListBox.click();
    }

    public void selectSizeType(String sizeType){
        Select sizeTypeSelect = new Select (sizeListBox);
        sizeTypeSelect.selectByVisibleText(sizeType);
    }


    public void clickDate()throws InterruptedException {
        queryDateDisplayListBox.click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void clickSizeValue()
    {
        sizeValueTextBox.click();
    }

    public void enterSizeValue(String sizeValue) {
        sizeValueTextBox.sendKeys(sizeValue);
    }
    public void clickQueryDateType()
    {
        queryDateTypeListBox.click();
    }
    public void selectQueryDateType(String queryDateType) {
        Select queryDateTypeSelect = new Select(queryDateTypeListBox);
        queryDateTypeSelect.selectByVisibleText(queryDateType);
    }

    public void clickRenewalDue()  {
        {
            renewalDueCheckBox.click();
        }
    }

    public void enterNumberPerPage(String numberPerPage)
    {
        numberPerPageTextBox.clear();
        numberPerPageTextBox.sendKeys(numberPerPage);
    }
    public void clickSearchButton(){
        searchButton.click();
    }
}
