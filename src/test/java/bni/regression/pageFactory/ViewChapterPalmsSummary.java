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

import static org.junit.Assert.assertEquals;

public class ViewChapterPalmsSummary {
    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(css = "#Search")
    WebElement viewReportsButton;

    @FindBy(css = "#datalist > tbody > tr.odd > td:nth-child(3) > a")
    WebElement statusLink;

    @FindBy(css = "#fromDate")
    WebElement enterFromDateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> enterFromDatePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement enterFromDateMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement enterFromDateYear;

    @FindBy(css = "#datalist_filter > input[type=text]")
    WebElement searchTextBox;

    public ViewChapterPalmsSummary(WebDriver driver) {
        ViewChapterPalmsSummary.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectVisitMonth(String month) {
        Select visitMonthSelect = new Select(enterFromDateMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectVisitYear(String year) {
        Select visitYearSelect = new Select(enterFromDateYear);
        visitYearSelect.selectByVisibleText(year);
    }

    public void selectDateFromDatePicker(String day) throws Exception{
        Integer breaker = 2;
        for(WebElement trElement : enterFromDatePicker)
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

    public void clickViewReportsButton() throws InterruptedException {
        viewReportsButton.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void clickEnterFromDateTextBox() throws InterruptedException {
        enterFromDateTextBox.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void enterSearchCriteria(String searchString){
        searchTextBox.sendKeys(searchString);
    }

    public void clickStatusLink(){
        statusLink.click();
    }

    public void checkStatusLink(String expStatus){
        String actualStatus = statusLink.getText();
        assertEquals("Holiday for the given date is not saved sucessfully...",expStatus, actualStatus);
    }

}
