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

public class EnterChapterPalms {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#Search")
    WebElement enterPalmsButton;

    @FindBy(css = "#submit")
    WebElement submitPalmsButton;

    @FindBy(css = "#datalist_filter > input[type=text]")
    WebElement searchTextBox;

    @FindBy(css = "#datalist > tbody > tr > td:nth-child(9)")
    WebElement meetingTextBox;

    @FindBy(css = "#fromDate")
    WebElement enterMeetingDateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> enterMeetingDatePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement enterMeetingDateMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement enterMeetingDateYear;

    public EnterChapterPalms(WebDriver driver) {
        EnterChapterPalms.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectVisitMonth(String month) {
        Select visitMonthSelect = new Select(enterMeetingDateMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectVisitYear(String year) {
        Select visitYearSelect = new Select(enterMeetingDateYear);
        visitYearSelect.selectByVisibleText(year);
    }

    public void selectDateFromDatePicker(String day) throws Exception{
        Integer breaker = 2;
        for(WebElement trElement : enterMeetingDatePicker)
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

    public void clickEnterPalmsButton() throws InterruptedException {
        enterPalmsButton.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void clickSubmitPalms() throws InterruptedException {
        submitPalmsButton.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void clickEnterMeetingDateTextBox() throws InterruptedException {
        enterMeetingDateTextBox.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void enterSearchCriteria(String searchString) throws InterruptedException{
        searchTextBox.sendKeys(searchString);
    }

    public void enterMeeting() throws InterruptedException{
        meetingTextBox.sendKeys("1");
    }
}
