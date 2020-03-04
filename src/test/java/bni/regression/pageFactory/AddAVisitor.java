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

import static junit.framework.TestCase.assertEquals;

public class AddAVisitor {
    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    @FindBy(css = "#droppedMemberEmail")
    WebElement emailTextBox;

    @FindBy(css = "#searchDroppedMember")
    WebElement searchButton;

    @FindBy(css = "#ui-dialog-title-modalwindow")
    WebElement pageTitle;

    @FindBy(css = "#showSearchByFirstAndLastName")
    WebElement searchByNameButton;

    @FindBy(css = "#createNew")
    WebElement createNewButton;

    @FindBy(css = "#droppedMemberFirstName")
    WebElement firstNameTextBox;

    @FindBy(css = "#droppedMemberLastName")
    WebElement lastNameTextBox;

    @FindBy(css = "#visitorChapter")
    WebElement chapterListBox;

    @FindBy(css = "#visitorPrimaryCategory")
    WebElement professionListBox;

    @FindBy(css = "#visitorInvitedBy")
    WebElement invitedByListBox;

    @FindBy(css = "#visitorSecondaryCategory")
  WebElement specialityListBox;

//    @FindBy(css = "#visitorSecondaryCategory")
//    List<  WebElement> specialityListBox;


    @FindBy(css = "#visitDate")
    WebElement visitDateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#visitorTitle")
    WebElement visitorTitle;

    @FindBy(css = "#visitorFirstName")
    WebElement visitorFirstName;

    @FindBy(css = "#visitorSecondName")
    WebElement visitorLastName;

    @FindBy(css = "#visitorCountry")
    WebElement visitorCountry;

    @FindBy(css = "#visitorPhoneNumber")
    WebElement visitorPhoneNumber;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement visitMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement visitYear;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
    WebElement saveButton;

    @FindBy(css = "#datalist1 > tbody > tr")
    List<WebElement> searchResults;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(3)")
    WebElement closeButton;

    @FindBy(css = "#visitorCompanyName")
    WebElement companyNameTextBox;

    @FindBy(css = "#recordVisitBtn")
    WebElement addButton;

    public AddAVisitor(WebDriver driver) {
        AddAVisitor.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterEmail(String emailId){
        emailTextBox.sendKeys(emailId);
    }

    public void enterFirstName(String firstName){
        firstNameTextBox.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }

    public void selectProfession(String profession) {
        Select professionSelect = new Select(professionListBox);
        professionSelect.selectByVisibleText(profession);
    }

    public void selectSpeciality(String speciality) {
        Select specialitySelect = new Select(specialityListBox);

        specialitySelect.selectByVisibleText(speciality);
    }

//    public void selectSpeciality (String speciality)
//    {
//
//     List<WebElement> mylist = (List<WebElement>) new Select(driver.findElement(By.tagName("option")));
//
//mylist.get(mylist.size-1)
//    }
    public void selectInvitedBy(String invitedBy) {
        Select invitedBySelect = new Select(invitedByListBox);
        invitedBySelect.selectByVisibleText(invitedBy);
    }

    public void selectChapter(String chapter) {
        Select chapterSelect = new Select(chapterListBox);
        chapterSelect.selectByVisibleText(chapter);
    }

    public void clickSearchButton(){
        searchButton.click();
         }

    public void clickAddButton(){
        addButton.click();
    }

    public void clickVisitDateField() throws InterruptedException {
        visitDateTextBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickSearchByNameButton(){
        searchByNameButton.click();
    }

    public void clickCreateNewButton(){
        createNewButton.click();
    }

    public String getPageTitle() {
        return pageTitle.getText();
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

    public void selectVisitorTitle(String title) {
        Select titleSelect = new Select(visitorTitle);
        titleSelect.selectByVisibleText(title);
    }

    public void enterVisitorFirstName(String visitFirstName){
        visitorFirstName.sendKeys(visitFirstName);
    }

    public void enterVisitorLastName(String visitLastName){
        visitorLastName.sendKeys(visitLastName);
    }

    public void selectVisitorCountry(String visitCountry) {
        Select visitorCountrySelect = new Select(visitorCountry);
        visitorCountrySelect.selectByVisibleText(visitCountry);
    }

    public void enterVisitorPhoneNumber(String phoneNumber){
        visitorPhoneNumber.sendKeys(phoneNumber);
    }

    public void clickSaveButton(){
        saveButton.click();
    }

    public void clickCloseButton(){
        closeButton.click();
    }

    public String[] getSearchResults() throws Exception{
        Integer recordCount = 0;
        String [] addAVisitorDetails = new String [8];
        for(WebElement trElement : searchResults)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            String type = td_collection.get(1).getText();
            if (type.equals("Visit")) {
                addAVisitorDetails[0] = td_collection.get(0).getText();
                addAVisitorDetails[1] = td_collection.get(2).getText();
                addAVisitorDetails[2] = td_collection.get(3).getText();
                addAVisitorDetails[3] = td_collection.get(5).getText();
                addAVisitorDetails[4] = td_collection.get(6).getText();
                addAVisitorDetails[5] = td_collection.get(4).getText();
                }
            recordCount++;
        }
        Integer expRecordCount = 2;
        assertEquals("Add a visitor search result does not have 2 records", expRecordCount, recordCount );
        return addAVisitorDetails;
    }

    public void selectVisitMonth(String month) {
        Select visitMonthSelect = new Select(visitMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectVisitYear(String year) {
        Select visitYearSelect = new Select(visitYear);
        visitYearSelect.selectByVisibleText(year);
    }

    public void enterCompanyName(String companyName){
        companyNameTextBox.sendKeys(companyName);
    }
}
