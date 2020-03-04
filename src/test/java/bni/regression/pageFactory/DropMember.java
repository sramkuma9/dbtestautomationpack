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

public class DropMember {
    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    @FindBy(css =  "#memberDropType")
    WebElement dropTypeListBox;

    @FindBy(css =  "#reasonId")
    WebElement dropReasonListBox;

    @FindBy(css =  "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement submitButton;

    @FindBy(css = "#memberDropDate")
    WebElement dropDateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement dropMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement dropYear;


    public DropMember(WebDriver driver) {
        DropMember.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public void clickDropDateField() throws InterruptedException {
        dropDateTextBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void selectDropType(String dropType) {
        Select dropTypeSelect = new Select(dropTypeListBox);
        dropTypeSelect.selectByVisibleText(dropType);
    }

    public void selectDropReason(String dropReason) {
        Select dropReasonSelect = new Select(dropReasonListBox);
        dropReasonSelect.selectByVisibleText(dropReason);
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

    public void selectDropMonth(String month) {
        Select visitMonthSelect = new Select(dropMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectDropYear(String year) {
        Select visitYearSelect = new Select(dropYear);
        visitYearSelect.selectByVisibleText(year);
    }

}
