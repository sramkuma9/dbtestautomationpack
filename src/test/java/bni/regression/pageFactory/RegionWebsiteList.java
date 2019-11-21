package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegionWebsiteList {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#countriesAvailable")
    WebElement countriesListBox;

    @FindBy(css = "#websiteListDatatable > tbody > tr > td:nth-child(7) > div > a:nth-child(1)")
    WebElement settingsButton;

    @FindBy(css = "#websiteListDatatable_filter > label > input")
    WebElement searchTextBox;

    public RegionWebsiteList(WebDriver driver) {
        RegionWebsiteList.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickSettingsButton(){
        settingsButton.click();
    }

    public void enterSearchCriteria(String region){
        searchTextBox.sendKeys(region);
    }

    public void selectCountries(String country){
        Select countriesSelect = new Select(countriesListBox);
        countriesSelect.selectByVisibleText(country);
    }

}
