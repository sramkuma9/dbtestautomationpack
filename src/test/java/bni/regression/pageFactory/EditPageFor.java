package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditPageFor {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#inputPageTitle")
    WebElement pageTitleTextBox;

    @FindBy(css = "body > div.wrapper > div.content-wrapper > section.content > div:nth-child(2) > div.box-body > form > div.box-footer > div:nth-child(2) > button")
    WebElement saveButton;

    @FindBy(css = "#backbutton > a")
    WebElement backButton;

    public EditPageFor(WebDriver driver) {
        EditPageFor.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickSaveButton(){
        saveButton.click();
    }

    public void enterPageTitle() {
        pageTitleTextBox.sendKeys("Test Automation");
    }

    public void clickBackButton(){
        backButton.click();
    }

}
