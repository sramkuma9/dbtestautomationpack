package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindASystemEvent {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#IdHistoryType")
    WebElement idHistoryTypeListBox;

    @FindBy(css = "#IdCountry")
    WebElement idCountryListBox;

    @FindBy(css = "#MSIdRegionsScreen")
    WebElement msIdRegionsScreenListBox;

    @FindBy(css = "#MSIdChaptersScreen")
    WebElement msIdChaptersScreenListBox;

    @FindBy(css = "#startDateDisplay")
    WebElement startDateDisplayListBox;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement periodMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement periodYear;

    @FindBy(css = "#endDateDisplay")
    WebElement endDateDisplayListBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;


    @FindBy(css = "#numberPerPage")
    WebElement resultsPerPageTextBox;

    @FindBy(css = "#eventSearch")
    WebElement   eventSearchButton;


    public FindASystemEvent(WebDriver driver) {
        FindASystemEvent.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectDatePicker(String day) throws Exception{
        Integer breaker = 2;
        for(WebElement trElement : datePicker)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            for (int row = 0; row < 7; row++) {
                String dayItem = td_collection.get(row).getText();
                if (day.equals(dayItem)) {
                    td_collection.get(row).findElement(By.tagName("a")).click();
                    TimeUnit.SECONDS.sleep(3);
                    breaker++;
                    break;
                }
            }
            if (breaker==3) {
                break;
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
    public void clickPeriodStartDate() throws InterruptedException {
        startDateDisplayListBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickPeriodEndDate() throws InterruptedException {
        endDateDisplayListBox.click();
        TimeUnit.SECONDS.sleep(2);
    }


    public void clickHistoryType()
    {
        idHistoryTypeListBox.click();
    }
    public void selectHistoryType(String idHistoryType) {
        System.out.println("History");
        Select historyTypeSelect = new Select(idHistoryTypeListBox);
        historyTypeSelect.selectByVisibleText(idHistoryType);
    }
    public void clickIdCountry()
    {
        idCountryListBox.click();
    }
    public void selectIdCountry(String IdCountry) {
        Select idCountrySelect = new Select(idCountryListBox);
        idCountrySelect .selectByVisibleText(IdCountry);
    }
    public void clickMSIdRegionsScreen()
    {
        msIdRegionsScreenListBox.click();
    }
    public void selectMSIdRegionsScreen(String MSIdRegionsScreen){
        Select MSIdRegionsScreenSelect = new Select (msIdRegionsScreenListBox);
        MSIdRegionsScreenSelect.selectByVisibleText(MSIdRegionsScreen);

    }
    public void clickMSIdChaptersScreen()
    {
        msIdChaptersScreenListBox.click();
    }

    public void selectMSIdChaptersScreen(String MSIdChaptersScreen){
        Select MSIdChaptersScreenSelect = new Select (msIdChaptersScreenListBox);
        MSIdChaptersScreenSelect.selectByVisibleText(MSIdChaptersScreen);

    }


    public void enterResultsPerPage(String resultsPerPage){
        resultsPerPageTextBox.clear();
        resultsPerPageTextBox.sendKeys(resultsPerPage);
    }

    public void clickEventSearch()
    {
        eventSearchButton.click();
    }

}
