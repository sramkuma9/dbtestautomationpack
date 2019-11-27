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

public class EnterOneToOnes {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#metWith")
    WebElement metWithListBox;

    @FindBy(css = "#invitedBy")
    WebElement invitedByListBox;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
    WebElement saveButton;

    @FindBy(css = "#location")
    WebElement locationTextBox;

    @FindBy(css = "#topicsOfConversation")
    WebElement topicsOfConversationTextBox;

    @FindBy(css = "#oneToOneDate")
    WebElement dateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement applicationMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement applicationYear;

    public EnterOneToOnes(WebDriver driver) {
        EnterOneToOnes.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterLocation(String location){
        locationTextBox.sendKeys(location);
    }

    public void enterTopicsOfConversation(String topicsOfConversation){
        topicsOfConversationTextBox.sendKeys(topicsOfConversation);
    }

    public void selectMetWith(String metWith) {
        Select metWithSelect = new Select(metWithListBox);
        metWithSelect.selectByVisibleText(metWith);
    }

    public void selectInvitedBy(String invitedBy) {
        Select invitedBySelect = new Select(invitedByListBox);
        invitedBySelect.selectByVisibleText(invitedBy);
    }
    public void selectVisitMonth(String month) {
        Select visitMonthSelect = new Select(applicationMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectVisitYear(String year) {
        Select visitYearSelect = new Select(applicationYear);
        visitYearSelect.selectByVisibleText(year);
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

    public void clickApplicationDateField() throws InterruptedException {
        dateTextBox.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void clickSaveButton() throws InterruptedException {
        saveButton.click();
        TimeUnit.SECONDS.sleep(2);
    }
}
