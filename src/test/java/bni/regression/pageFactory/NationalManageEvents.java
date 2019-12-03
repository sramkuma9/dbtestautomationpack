package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NationalManageEvents {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#apps_events_create_new_btn")
    WebElement createNewNationalEventButton;

    @FindBy(css = "#viewEvents")
    WebElement viewNationalEventsButton;

    public NationalManageEvents(WebDriver driver) {
        NationalManageEvents.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickCreateNewNationalEvent() {
        createNewNationalEventButton.click();
    }

    public void clickViewNationalEvent() {
        viewNationalEventsButton.click();
    }
}
