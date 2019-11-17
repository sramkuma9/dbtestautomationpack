package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CountryWebsiteList {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#websiteListDatatable_filter > label > input")
    WebElement countrySearchTextBox;

    @FindBy(css = "#websiteListDatatable > tbody > tr:nth-child(1) > td:nth-child(7) > div > a:nth-child(1)")
    WebElement settingsButton;

    @FindBy(css = "#websiteViewPagesDatatable_filter > label > input")
    WebElement pagesSearchTextBox;

    @FindBy(css = "#websiteViewPagesDatatable > tbody > tr:nth-child(1) > td.text-center > div > a:nth-child(1) > i")
    WebElement previewButton;

    @FindBy(css = "body > div.wrapper > aside > section > ul > li:nth-child(4) > a")
    WebElement regionWebsiteListLink;

    public CountryWebsiteList(WebDriver driver) {
        CountryWebsiteList.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickPreviewButton() {
        previewButton.click();
    }

    public void clickSettingsButton(){
        settingsButton.click();
    }

    public void clickRegionWebsiteListLink(){
        regionWebsiteListLink.click();
    }

    public void enterCountrySearch(String country){
        countrySearchTextBox.sendKeys(country);
    }

    public void enterPagesSearch(String pageSearch){
        pagesSearchTextBox.sendKeys(pageSearch);
    }
}
