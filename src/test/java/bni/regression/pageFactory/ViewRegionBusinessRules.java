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

import static junit.framework.TestCase.assertEquals;

public class ViewRegionBusinessRules {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#edit_brules_btn")
    WebElement editRulesButton;

    public ViewRegionBusinessRules(WebDriver driver) {
        ViewRegionBusinessRules.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickEditRulesButton() throws InterruptedException {
        editRulesButton.click();
        TimeUnit.SECONDS.sleep(2);
    }
}
