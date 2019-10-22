package bni.regression.pageFactory;

import bni.regression.libraries.common.ReadWritePropertyFile;
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

public class ViewEditVisitorsList {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#startDate")
    WebElement fromStartDate;

    @FindBy(css = "#endDate")
    WebElement toEndDate;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement selectMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement selectYear;

    @FindBy(css = "#visitor_datalist > tbody > tr:nth-child(1) > td:nth-child(5) > a:nth-child(3) > img")
    WebElement convertToMemberButton;

    @FindBy(css = "#searchProspects")
    WebElement goButton;

    public ViewEditVisitorsList(WebDriver driver) {
        ViewEditVisitorsList.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickFromStartDateField() throws InterruptedException {
        fromStartDate.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickConvertToMemberButton() throws InterruptedException {
        convertToMemberButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickToEndDateField() throws InterruptedException {
        toEndDate.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void selectDateFromDatePicker(String day) throws Exception{
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
        Select monthSelect = new Select(selectMonth);
        monthSelect.selectByVisibleText(month);
    }

    public void selectYear(String year) {
        Select yearSelect = new Select(selectYear);
        yearSelect.selectByVisibleText(year);
    }

    public void clickGoButton() throws InterruptedException {
        goButton.click();
        TimeUnit.SECONDS.sleep(5);
    }
}
