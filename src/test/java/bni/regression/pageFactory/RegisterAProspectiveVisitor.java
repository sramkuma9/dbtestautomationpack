package bni.regression.pageFactory;

import bni.regression.libraries.common.ReadWritePropertyFile;
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

public class RegisterAProspectiveVisitor {

    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    @FindBy(css = "#selectCountry")
    WebElement countryListBox;

    @FindBy(css = "#selectRegion")
    WebElement getRegionListBox;

    @FindBy(css = "#selectChapter")
    WebElement getChapterListBox;

    @FindBy(css = "#idPrimaryCategory")
    WebElement getProfessionListBox;

    @FindBy(css = "#idSecondaryCategory")
    WebElement getSpecialityListBox;

    @FindBy(css = "#idLanguage")
    WebElement  getLanguageListBox;

    @FindBy(css = "#visitDateStr")
    WebElement visitDateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement visitMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement visitYear;

    @FindBy(css = "#idTitle")
    WebElement getTitleListBox;


    @FindBy(css = "#firstName")
    WebElement firstNameTextBox;

    @FindBy(css = "#lastName")
    WebElement lastNameTextBox;

    @FindBy(css = "#suffix")
    WebElement suffixTextBox;

    @FindBy(css = "#companyName")
    WebElement companyNameTextBox;

    @FindBy(css = "addressLine1")
    WebElement addressLine1TextBox;

    @FindBy(css = "addressLine2")
    WebElement addressLine2TextBox;
    @FindBy(css = "#city")
    WebElement cityTextBox;

    @FindBy(css = "#state")
    WebElement stateTextBox;

    @FindBy(css = "#country")
    WebElement getCountryListBox;

    @FindBy(css = "#postcode")
    WebElement getPostCodeTextBox;

    @FindBy(css = "#phone")
    WebElement getPhoneTextBox;

    @FindBy(css = "#mobile")
    WebElement getMobileTextBox;

    @FindBy(css = "fax")
    WebElement getFaxTextBox;

    @FindBy(css = "#email")
    WebElement getEmailTextBox;


    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement submitButton;


    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
    WebElement closeButton;


    @FindBy(css="#tabs-4-1 > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(1)")
    WebElement registerAProspectiveVisitorLink;

    public RegisterAProspectiveVisitor(WebDriver driver) {
        RegisterAProspectiveVisitor.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectCountry(String country) {
        Select countrySelect = new Select(countryListBox);
        countrySelect.selectByVisibleText(country);
    }
    public void selectRegion(String region){
        Select regionSelect = new Select (getRegionListBox);
        regionSelect.selectByVisibleText(region);
    }




    public void clickProfession() throws InterruptedException {
        getProfessionListBox.click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void selectProfession(String profession) {
        Select professionSelect = new Select(getProfessionListBox);
        professionSelect.selectByVisibleText(profession);
    }
    public void clickSpeciality() throws InterruptedException {
        getSpecialityListBox.click();
        TimeUnit.SECONDS.sleep(2);}

    public void selectSpeciality(String specialty) {
        Select specialitySelect = new Select(getSpecialityListBox);
        specialitySelect.selectByVisibleText(specialty);
    }


    public void clickVisitDate() throws InterruptedException {
        visitDateTextBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void selectDateFromDatePicker(String day) throws Exception{
        for(WebElement trElement : datePicker)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            for (int row = 0; row < 7; row++) {
                String dayItem = td_collection.get(row).getText();
                if (day.equals(dayItem)) {
                    td_collection.get(row).findElement(By.tagName("a")).click();
                    TimeUnit.SECONDS.sleep(3);
                    break;
                }
            }
        }
    }

    public void selectMonth(String month) {
        Select visitMonthSelect = new Select(visitMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectYear(String year) {
        Select visitYearSelect = new Select(visitYear);
        visitYearSelect.selectByVisibleText(year);
    }

    public void selectTitle(String title) {
        Select titleSelect = new Select(getTitleListBox);
        titleSelect.selectByVisibleText(title);
    }

    public void enterFirstName(String firstName){
        firstNameTextBox.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }
    public void enterSuffix(String suffix){
        suffixTextBox.sendKeys(suffix);
    }



    public void enterCompanyName(String companyName){
        companyNameTextBox.sendKeys(companyName);
    }



    public void enterPhone(String phone){
        getPhoneTextBox.sendKeys(phone);
    }
    public void enterMobile(String mobile){
        getMobileTextBox.sendKeys(mobile);
    }
    public void enterFax(String fax){
        getFaxTextBox.sendKeys(fax);
    }
    public void enterEmailAddress(String email){
        getEmailTextBox.sendKeys(email);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public void clickCloseButton(){
        closeButton.click();
    }


}


