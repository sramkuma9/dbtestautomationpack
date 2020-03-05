package bni.regression.pageFactory;
import bni.regression.libraries.common.ReadWritePropertyFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class ManageProspectMemberConvert {


    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    @FindBy(css = "#startDate")
    WebElement startDate;

    @FindBy(css = "#endDate")
    WebElement endDate;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#searchProspects")
    WebElement goButton;



    public ManageProspectMemberConvert(WebDriver driver) {
        ManageProspectMemberConvert.driver = driver;
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
}


