package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BNIConnectSupport {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "body > footer > div > a")
    WebElement bniSupportLink;

    @FindBy(css = "#user-name")
    WebElement menuDropDown;

    @FindBy(css = "#user-menu > a:nth-child(3)")
    WebElement signOutLink;

    public BNIConnectSupport(WebDriver driver) {
        BNIConnectSupport.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void checkBniSupportLink() {
        Boolean linkStatus = bniSupportLink.isDisplayed();
        assertEquals("BNI support link is not present", true, linkStatus);
    }

    public void signOutBniSupport() throws InterruptedException {
        menuDropDown.click();
        TimeUnit.SECONDS.sleep(2);
        signOutLink.click();
        TimeUnit.SECONDS.sleep(8);
        driver.quit();
    }

}
