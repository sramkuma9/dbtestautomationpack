package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeactivateSchemeWindow {

    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(xpath = "//*[@id='root']/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div[4]/div/button/span/span")
    WebElement selectDerivedScheme;

    @FindBy(xpath = "//*[@id='root']/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div[5]/div/button/span/span")
    WebElement selectDiscontinueSKU;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div[6]/div/div/div/span/span/div/div/div[1]/div/input")
    WebElement effectiveDateField;

    @FindBy(xpath="/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[3]/div/button/span/span")
    WebElement deactivateButton;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div[6]/div/div/div/span/span/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div[1]/div")
    WebElement currentMonth;

    @FindBy(xpath = "//span[@class='react-datepicker__year-read-view--selected-year']")
    WebElement currentYear;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div[6]/div/div/div/span/span/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/div/div")
    List<WebElement> effectiveMonth;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div[6]/div/div/div/span/span/div/div/div[2]/div/div[2]/div/div[2]/div/div")
    List<WebElement> datePicker;

    @FindBy(xpath = "/html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div")
    List<WebElement> effectiveYear;

    @FindBy(xpath = "//*[@id='root']/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[1]/div/input")
    WebElement dateBox;

    @FindBy(xpath ="/html/body/div[3]/div[3]/div/div/div[2]/button[2]/span/span")
    WebElement yesButtonToDeactivate;

    public DeactivateSchemeWindow(WebDriver driver) {
        DeactivateSchemeWindow.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickSelectDerivedScheme() {
        selectDerivedScheme.click();
    }

    public void clickSelectDiscontinueSKU() {
        selectDiscontinueSKU.click();
    }

    public void clickEffectiveDate() {
        effectiveDateField.click();
    }

    public void selectDateFromDatePicker(String day) throws Exception {
        Actions action = new Actions(driver);
        action.clickAndHold(effectiveDateField);
        for (WebElement dayElement : datePicker) {
            String calendarDay = dayElement.getText();
            System.out.println("Day element is" + dayElement);
            String classElement = dayElement.getAttribute("class");
            if (!classElement.contains("outside-month")) {
                TimeUnit.SECONDS.sleep(1);
                if (calendarDay.contains(day)) {
                    effectiveDateField.sendKeys(Keys.ARROW_DOWN);
                    if (calendarDay.contains(day)) {
                        WebDriverWait wait = new WebDriverWait(driver, 5);
                        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div[6]/div/div/div/span/span/div/div/div[2]/div/div[2]/div/div[2]/div/div"))));
                        dayElement.click();
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    }
                }
            }
        }
    }


    public void selectMonth(String month) throws Exception {
        currentMonth.click();
        for (WebElement monthElement : effectiveMonth) {
            String calendarMonth = monthElement.getText();
            System.out.println("Calendar Month selected is " + calendarMonth + ":" + calendarMonth.contains(month));
            TimeUnit.SECONDS.sleep(1);
            if (calendarMonth.contains(month)) {
                monthElement.click();
                TimeUnit.SECONDS.sleep(3);
                break;

            }
        }
    }

    public void selectYear(String year) throws Exception {
        currentYear.click();
        for (WebElement yearElement : effectiveYear) {
            String calendarYear = yearElement.getText();
            System.out.println("Calendar Year selected is " + calendarYear + ":" + calendarYear.contains(year));
            TimeUnit.SECONDS.sleep(1);
            if (calendarYear.contains(year)) {
                yearElement.click();
                TimeUnit.SECONDS.sleep(3);
                break;

            }
        }
    }

    public void clickDeactivate()
    {
        deactivateButton.click();
    }

    public void clickYesButtonToDeactivate()
    {
       yesButtonToDeactivate.click();
    }

}
