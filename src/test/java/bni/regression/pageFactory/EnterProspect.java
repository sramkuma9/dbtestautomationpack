package bni.regression.pageFactory;


import org.openqa.selenium.*;
import bni.regression.libraries.common.ReadWritePropertyFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EnterProspect {

    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    @FindBy(css = "#droppedMemberEmail")
    WebElement getEmailTextBox;

    @FindBy(css = "#searchDroppedMember")
    WebElement searchButton;

    @FindBy(css="#showSearchByFirstAndLastName")
    WebElement searchByNameButton;

    @FindBy(css="#droppedMemberFirstName")
    WebElement droppedMemberFirstNameTextBox;

    @FindBy(css="#droppedMemberLastName")
    WebElement droppedMemberLastNameTextBox;

    @FindBy(css="#searchByNameBtn > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3) > div:nth-child(1) > button:nth-child(1)")
    WebElement searchNameButton;


    @FindBy(css="#createNew")
    WebElement createNewButton;

    @FindBy(css = "#existingProspectVisitorOptions")
    WebElement addButton;

    @FindBy(css="#prospectEmail")
    WebElement getProspectEmailTextBox;

    @FindBy(css = "#prospectPrimaryCategory")
    WebElement selectIndustryListBox;

    @FindBy(css = "#prospectSecondaryCategory")
    WebElement selectClassificationListBox;

    @FindBy (css="#prospectFirstName")
    WebElement firstNameTextBox;

    @FindBy(css="#prospectSecondName")
    WebElement lastNameTextBox;

    @FindBy(css="#idLanguage")
    WebElement selectLanguageListBox;

    @FindBy(css="#prospectTitle")
    WebElement selectTitleListBox;

    @FindBy(css="#prospectSuffix")
    WebElement getSuffixTextBox;

    @FindBy(css="#prospectCompanyName")
    WebElement getCompanyNameTextBox;

    @FindBy (css="#prospectAddressLine1")
    WebElement getAddressLine1TextBox;

    @FindBy (css="#prospectAddressLine2")
    WebElement getAddressLine2TextBox;

    @FindBy (css="#prospectCity")
    WebElement getCityTextBox;

    @FindBy (css="#prospectState")
    WebElement getStateTextBox;

    @FindBy (css="#prospectCountry")
    WebElement selectCountryListBox;

    @FindBy (css="#prospectZipCode")
    WebElement getPostalcodeTextBox;

    @FindBy (css="#prospectPhoneNumber")
    WebElement  getPhoneNumberTextBox;

    @FindBy (css="#prospectMobileNumber")
    WebElement  getMobileNumberTextBox;


    @FindBy (css="#prospectFax")
    WebElement  getFaxTextBox;


    @FindBy (css="#prospectSource")
    WebElement  selectProspectSourceListBox;

    @FindBy (css="#prospectInterestLevel")
    WebElement  selectProspectInterestLevelListBox;

    @FindBy (css="#prospectStatus")
    WebElement  selectProspectStatusListBox;


    @FindBy (css="#prospectGeoArea")
    WebElement  selectProspectGeoAreaListBox;


    @FindBy (css=" #followupDate")
    WebElement  selectFollowupDateListBox;


    @FindBy (css=" #lastContactDate")
    WebElement  selectLastContactDateListBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-calendar > tbody:nth-child(2)> select.ui-datepicker-date")
    WebElement prospectFollowupDate;

    @FindBy(css = "#ui-datepicker-calendar > tbody:nth-child(2)> select.ui-datepicker-month")
    WebElement prospectFollowupMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement prospectFollowupYear;

    @FindBy (css=" #contactBy")
    WebElement  selectContactByListBox;


    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement submitButton;

    @FindBy(css = "#.ui-dialog-buttonpane > button:nth-child(2)")
    WebElement closeButton;

    @FindBy(css="#tabs-3-5 > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(1)")
    WebElement enterAProspectLink;

    public EnterProspect(WebDriver driver) {
        EnterProspect.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }
    public void clickEnterAProspect() throws InterruptedException
    {
        enterAProspectLink.click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void scrollDown() throws InterruptedException
    {
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0, 350)", "");
        TimeUnit.SECONDS.sleep(1);
    }

    public void enterSearch() {
        searchButton.click();

    }
    /*public void enterSearch(String searchText) {
        Select searchSelect = new Select(searchButton);
        searchSelect.selectByVisibleText(searchText );
    }

     */
    public void clickAddButton() throws InterruptedException{
        addButton.click();
        TimeUnit.SECONDS.sleep(4);

    }
    public void enterDroppedMemberEmail(String droppedMemberEmail ) throws InterruptedException{
        getEmailTextBox.sendKeys(droppedMemberEmail);
        TimeUnit.SECONDS.sleep(2);
    }
public void enterDroppedMemberFirstName(String droppedMemberFirstName)throws  InterruptedException
{
    droppedMemberFirstNameTextBox.sendKeys(droppedMemberFirstName);
    TimeUnit.SECONDS.sleep(2);
}
    public void enterDroppedMemberLastName(String droppedMemberLastName)
    {
        droppedMemberLastNameTextBox.sendKeys(droppedMemberLastName);
    }
    public void clickCreateNewButton()
    {
        createNewButton.click();
    }
    public void clickSearchName()
    {
        searchNameButton.click();
    }

    public void clickSearchByName()
    {
        searchByNameButton.click();
    }
    public void enterFirstName(String firstName)
    {
       firstNameTextBox.sendKeys(firstName);
    }
    public void enterLastName(String lastName)
    {
        lastNameTextBox.sendKeys(lastName);
    }
    public void enterProspectEmail() throws InterruptedException{
        getProspectEmailTextBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void selectIndustry(String prospectIndustry)
    {
        Select prospectIndustrySelect = new Select (selectIndustryListBox);
        prospectIndustrySelect.selectByVisibleText(prospectIndustry);
    }
    public void selectLanguage(String language)
    {
        Select languageSelect = new Select (selectLanguageListBox);
        languageSelect.selectByVisibleText(language);
    }
    public void selectClassification(String classification)
    {
        Select classificationSelect = new Select (selectClassificationListBox);
        classificationSelect.selectByVisibleText(classification);
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

    public void clickAddressLine1()
    {
        getAddressLine1TextBox.click();
    }
    public void enterAddressLine1(String prospectAddressLine1) {
        getAddressLine1TextBox.sendKeys(prospectAddressLine1);
    }
    public void enterAddressLine2(String prospectAddressLine2) {
        getAddressLine2TextBox.sendKeys(prospectAddressLine2);
    }
    public void enterCity(String city) { getCityTextBox.sendKeys(city); }
    public void selectCountry(String prospectCountry)
    {
        Select countrySelect = new Select (selectCountryListBox);
        countrySelect.selectByVisibleText(prospectCountry);
    }
    public void enterState(String prospectState) { getStateTextBox.sendKeys(prospectState); }

    public void enterPostalCode(String prospectZipCode) { getPostalcodeTextBox.sendKeys(prospectZipCode); }

    public void enterPhoneNumber(String prospectPhoneNumber) { getPhoneNumberTextBox.sendKeys(prospectPhoneNumber); }

    public void enterFax(String fax) { getFaxTextBox.sendKeys(fax); }

    public void selectProspectSource(String prospectSource) {
        Select prospectSourceSelect = new Select(selectProspectSourceListBox);
        prospectSourceSelect.selectByVisibleText(prospectSource);
    }
    public void selectProspectInterestLevel(String prospectInterestLevel) {
        Select prospectInterestLevelSelect = new Select (selectProspectInterestLevelListBox);
        prospectInterestLevelSelect.selectByVisibleText(prospectInterestLevel);

}
    public void selectProspectStatus(String prospectStatus) {
        Select prospectStatusSelect = new Select (selectProspectStatusListBox);
        prospectStatusSelect.selectByVisibleText(prospectStatus);

    }
    public void selectProspectGeoArea(String prospectGeoArea) {
        Select prospectGeoAreaSelect = new Select (selectProspectStatusListBox);
        prospectGeoAreaSelect.selectByVisibleText(prospectGeoArea);

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



    public void selectProspectFollowupDate(String date) {
        Select prospectFollowupDateSelect = new Select(prospectFollowupDate);
        prospectFollowupDateSelect.selectByVisibleText(date);
    }

    public void selectProspectFollowupMonth(String month) {
        Select prospectFollowupMonthSelect = new Select(prospectFollowupMonth);
        prospectFollowupMonthSelect.selectByVisibleText(month);
    }

    public void selectProspectFollowupYear(String year) {
        Select prospectFollowupYearSelect = new Select(prospectFollowupYear);
        prospectFollowupYearSelect.selectByVisibleText(year);
    }


    public void setSelectContactByListBox(String contactBy) {
        Select contactBySelect = new Select(selectContactByListBox);
        contactBySelect.selectByVisibleText(contactBy);
    }

    public void clickSubmit () throws InterruptedException{
        submitButton.click();
        TimeUnit.SECONDS.sleep(5);
    }
    public void clickSearchButton() throws InterruptedException {
        searchButton.click();
        TimeUnit.SECONDS.sleep(5);
    }


}
