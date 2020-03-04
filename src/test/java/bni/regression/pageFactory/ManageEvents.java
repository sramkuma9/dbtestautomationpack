package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ManageEvents {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#apps_events_create_new_btn")
    WebElement createNewEventButton;

    @FindBy(css = "#viewEvents")
    WebElement viewEventsButton;

    @FindBy(css = "#selectedrole > table > thead > tr > th:nth-child(2) > button")
    WebElement unSelectAllButton;

    @FindBy(css = "#datalist > tbody > tr:nth-child(1) > td:nth-child(1) > a")
    WebElement eventName;

    @FindBy(css = "#filterregion")
    List<WebElement> selectRegion;

    @FindBy(css = "#datalist_filter > input[type=text]")
    WebElement searchTextBox;

    public ManageEvents(WebDriver driver) {
        ManageEvents.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickCreateNewEventButton() {
        createNewEventButton.click();
    }

    public void clickViewEventsButton() {
        viewEventsButton.click();
    }

    public void clickunSelectAllButton() {
        unSelectAllButton.click();
    }

    public void selectItemFromSubListMenu(String region) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        for (WebElement trElement : selectRegion) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("label"));
            String regionName = td_collection.get(0).findElement(By.tagName("span")).getText();
            System.out.println(regionName);
            if (regionName.equals(region)) {
                td_collection.get(0).findElement(By.tagName("span")).click();
                TimeUnit.SECONDS.sleep(3);
                break;
            }
        }
    }

    public void enterSearchString(String eventName){
        searchTextBox.sendKeys(eventName);
    }

    public void checkEventName(String expectedEventName){
        String actualEventName = eventName.getText();
        assertEquals("Regional event is not created successfully...", expectedEventName, actualEventName);
    }
}
