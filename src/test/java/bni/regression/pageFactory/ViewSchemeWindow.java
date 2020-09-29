package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewSchemeWindow {

    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(xpath = "//*[@id='root']/div[1]/main/div[3]/div/div/div[3]/div/div[3]/div/div[1]/button/span/span")
    WebElement deactivateSchemeButton;

    @FindBy(xpath ="//*[@id='root']/div[1]/main/div[3]/div/div/div[3]/div/div[3]/div/div[2]/button/span/span")
    WebElement scheduleAChange;


    public ViewSchemeWindow(WebDriver driver) {
        ViewSchemeWindow.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

        public void clickDeactivateScheme(){
       deactivateSchemeButton.click();
    }

    public void clickScheduleAChange()
    {
        scheduleAChange.click();
    }
}
