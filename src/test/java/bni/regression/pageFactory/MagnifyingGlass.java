package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MagnifyingGlass {

    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#html body.connect div#background div#page div#nav a.searchpeople")
    WebElement  magnifyingGlassButton;

    @FindBy(css = "#lastName")
    WebElement lastNameTextBox;

    @FindBy(css = "#memberKeyword")
    WebElement searchKeywordTextBox;

    @FindBy(css = "#basicSearchConnections")
    WebElement searchMembersButton;

    @FindBy(css="#searchConnections")
    WebElement advSearchMembersButton;

    @FindBy(css = "#memberFirstName")
    WebElement firstNameTextBox;

    @FindBy(css = "#advancedSearch")
    WebElement advanceSearchButton;

    @FindBy(css = " #memberKeyword")
    WebElement keywordTextBox;

    @FindBy(css = " #memberKeywords")
    WebElement advKeywordTextBox;

    @FindBy(css = ".modal > img:nth-child(1)")
    WebElement addMyConnectionButton;

    @FindBy(css = " #addtomyconnectbutton")
    WebElement addToMyConnectButton;

    @FindBy(css="#ui-dialog-buttonpane > button:nth-child(1)")
    WebElement submitButton;

    @FindBy (css="#datalist_filter > input:nth-child(1)")
    WebElement searchFieldTextBox;

    // advance search

    @FindBy(css="#memberLastName")
    WebElement getLastNameTextBox;

    @FindBy(css="#memberCompanyName")
    WebElement companyNameTextBox;

    @FindBy (css="#memberIdCountry")
    WebElement countryListBox;

    @FindBy (css="#memberCity")
    WebElement cityTextBox;

    @FindBy (css="#memberState")
    WebElement stateTextBox;

       public MagnifyingGlass(WebDriver driver) {
        bni.regression.pageFactory.FindAPerson.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }
    public void clickSubmitButton() throws InterruptedException
    {
        submitButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickMagnifyingGlass() throws InterruptedException
    {
        magnifyingGlassButton.click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void clickAddToMyConnectButton() throws InterruptedException
    {
        addToMyConnectButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickAdvanceSearch() throws InterruptedException
    {
        advanceSearchButton.click();
        TimeUnit.SECONDS.sleep(2);
    }



    public void enterLastNameAdvSearch(String memberLastName)
    {
        getLastNameTextBox.sendKeys(memberLastName);
    }


    public void enterCompanyName(String companyName){
        companyNameTextBox.sendKeys(companyName);
    }


    public void selectCountry(String country) {
        Select countrySelect = new Select(countryListBox);
       countrySelect.selectByVisibleText(country);
    }


    public void enterCity(String city){
        cityTextBox.sendKeys(city);
    }

    public void enterState(String state){
        stateTextBox.sendKeys(state);
    }
    public void enterKeyword(String memberKeyword){
        keywordTextBox.sendKeys(memberKeyword);
    }

    public void advanceKeyword(String memberKeyword){
        advKeywordTextBox.sendKeys(memberKeyword);
    }
    public void enterLastName(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }

    public void enterSearchText(String searchField){
        searchFieldTextBox.sendKeys(searchField);
    }
    public void clickAddMyConnection() throws InterruptedException
    {
        addMyConnectionButton.click();
        TimeUnit.SECONDS.sleep(2);
    }


    public void enterFirstName(String memberFirstName){
        firstNameTextBox.sendKeys(memberFirstName);
    }
    public void clickSearchMembersButton() throws InterruptedException

    {
        searchMembersButton.click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void clickAdvanceSearchMembersButton() throws InterruptedException

    {
       advSearchMembersButton.click();
        TimeUnit.SECONDS.sleep(2);
    }
}



