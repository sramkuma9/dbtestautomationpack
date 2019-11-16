package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultiRegArgSite {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#publish-website")
    WebElement publishButton;

    @FindBy(css = "#websiteViewPagesDatatable > tbody > tr:nth-child(1) > td.text-center > div > a:nth-child(2)")
    WebElement editPageButton;

    @FindBy(css = "#websiteViewPagesDatatable_filter > label > input")
    WebElement searchTextBox;

    public MultiRegArgSite(WebDriver driver) {
        MultiRegArgSite.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickPublishButton(){
        publishButton.click();
    }

    public void enterSearchCriteria(String searchWord) {
        searchTextBox.sendKeys(searchWord);
    }

    public void clickEditPageButton(){
        editPageButton.click();
    }

}
