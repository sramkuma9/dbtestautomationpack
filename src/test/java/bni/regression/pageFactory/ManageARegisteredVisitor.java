
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

public class ManageARegisteredVisitor {

    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    @FindBy(css = "#datalist_filter > input:nth-child(1)")
    WebElement searchTextBox;

   // @FindBy(css="#tr.even:nth-child(6) > td:nth-child(7) > a:nth-child(1) > img:nth-child(1)")
     @FindBy(css=".editIcon")
    WebElement editAProspectiveVisitorImage;

    @FindBy(css = "#attended")
    WebElement clickVisitorAttendedCheckBox;

    @FindBy(css = "#visitDateStr")
    WebElement clickVisitDateTextBox;

    @FindBy(css="#visitorInvitedBy")
    WebElement selectVisitorInvitedByListBox;

    @FindBy(css="#idLanguage")
    WebElement selectLanguageListBox;

    @FindBy(css="#idTitle")
    WebElement selectTitleListBox;

    @FindBy(css="#suffix")
    WebElement getSuffixTextBox;

    @FindBy(css="#companyName")
    WebElement getCompanyNameTextBox;

    @FindBy (css="#addressLine1")
    WebElement getAddressLine1TextBox;

    @FindBy (css="#addressLine2")
    WebElement getAddressLine2TextBox;

    @FindBy (css="#city")
    WebElement getCityTextBox;

    @FindBy (css="#state")
    WebElement getStateTextBox;

    @FindBy (css="#country")
    WebElement selectCountryListBox;

    @FindBy (css="#postcode")
    WebElement getPostalcodeTextBox;

    @FindBy (css="#mobile")
    WebElement  getMobileTextBox;

    @FindBy (css="#fax")
    WebElement  getFaxTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;


    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement visitMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement visitYear;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement submitButton;

    @FindBy(css="#visitorPrimaryCategory")
    WebElement industryListBox;

    @FindBy(css="#visitorSecondaryCategory")
    WebElement classificationListBox;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
    WebElement closeButton;

    @FindBy(css="#tabs-4-1 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(1)")
   // @FindBy(css="#tabs-4-1 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a")
    WebElement manageARegisteredVisitorLink;

    public ManageARegisteredVisitor(WebDriver driver) {
        ManageARegisteredVisitor.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }
    public void clickManageAProspectiveVisitor() throws InterruptedException
    {
        manageARegisteredVisitorLink.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void enterSearch(String searchText) {
       searchTextBox.sendKeys(searchText);
    }
    public void clickEditAProspectiveVisitorImage() throws InterruptedException{
        editAProspectiveVisitorImage.click();
        TimeUnit.SECONDS.sleep(4);

    }
    public void clickVisitorAttended() throws InterruptedException{
        clickVisitorAttendedCheckBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void selectVisitorInvitedBy(String visitorInvitedBy)
    {
        Select visitorInvitedBySelect = new Select (selectVisitorInvitedByListBox);
        visitorInvitedBySelect.selectByVisibleText(visitorInvitedBy);
    }

    public void selectIndustry(String industry)
    {
        Select industrySelect = new Select (industryListBox);
        industrySelect.selectByVisibleText(industry);
    }

    public void selectClassification(String classification)
    {
        Select classificationSelect = new Select (classificationListBox);
        classificationSelect.selectByVisibleText(classification);
    }
    public void selectLanguage(String language)
    {
        Select languageSelect = new Select (selectLanguageListBox);
        languageSelect.selectByVisibleText(language);
    }
    public void selectTitle(String title)
    {
        Select titleSelect = new Select (selectTitleListBox);
        titleSelect.selectByVisibleText(title);
    }


    public void enterSuffix(String suffix) {
        getSuffixTextBox.sendKeys(suffix);
    }
    public void enterCompanyName(String companyName) {
        getCompanyNameTextBox.sendKeys(companyName);
    }
    public void enterAddressLine1(String addressLine1) {
        getAddressLine1TextBox.sendKeys(addressLine1);
    }
    public void enterAddressLine2(String addressLine2) {
        getAddressLine2TextBox.sendKeys(addressLine2);
    }
    public void enterCity(String city) { getCityTextBox.sendKeys(city); }

    public void enterState(String state) { getStateTextBox.sendKeys(state); }

    public void enterPostalCode(String postCode) { getPostalcodeTextBox.sendKeys(postCode); }

    public void enterMobile(String mobile) { getMobileTextBox.sendKeys(mobile); }

    public void enterFax(String fax) { getFaxTextBox.sendKeys(fax); }

    public void clickVisitDate() throws InterruptedException {
        clickVisitDateTextBox.click();
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



    public void selectCountry(String country)
    {
        Select countrySelect = new Select (selectCountryListBox);
        countrySelect.selectByVisibleText(country);
    }

    public void clickSubmitButton() throws InterruptedException
    {
        submitButton.click();
        TimeUnit.SECONDS.sleep(10);
    }
    public void clickClose() throws InterruptedException{
        closeButton.click();
        TimeUnit.SECONDS.sleep(2);
    }


}