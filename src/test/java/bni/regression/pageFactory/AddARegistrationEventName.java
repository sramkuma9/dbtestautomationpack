package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddARegistrationEventName {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#searchUsers")
    WebElement searchUsersButton;

    @FindBy(css = "#userName")
    WebElement nameTextBox;

    @FindBy(css = "#country")
    WebElement countryListBox;

    @FindBy(css = "#datalist > tbody > tr > td:nth-child(4) > a > img")
    WebElement addARegistrationButton;

    public AddARegistrationEventName(WebDriver driver) {
        AddARegistrationEventName.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickSearchUsersButton(){
        searchUsersButton.click();
    }

    public void enterNameTextBox(String name){
        nameTextBox.sendKeys(name);
    }

    public void selectCountry(String country){
        Select countrySelect = new Select(countryListBox);
        countrySelect.selectByVisibleText(country);
    }
    public void clickAddARegistrationButton(){
        addARegistrationButton.click();
    }
}
