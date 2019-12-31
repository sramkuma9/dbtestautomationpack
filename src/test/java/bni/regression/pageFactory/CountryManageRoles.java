package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CountryManageRoles {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#datalist_filter > input[type=text]")
    WebElement searchTextBox;

    @FindBy(css = "#datalist > tbody > tr > td:nth-child(3) > a > img")
    WebElement assignRolesButton;

    public CountryManageRoles(WebDriver driver) {
        CountryManageRoles.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }


    public void enterSearchString(String searchString){
        searchTextBox.sendKeys(searchString);
    }

    public void clickAssignRolesButton(){
        assignRolesButton.click();
    }

}
