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

import static junit.framework.TestCase.assertEquals;

public class Add {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#memberEmail")
    WebElement emailTextBox;

    @FindBy(css = "#memberSelectRegion")
    WebElement regionListbox;

    @FindBy(css = "#memberSelectCountry")
    WebElement countryListbox;

    @FindBy(css = "#memberSecondaryCategory")
    WebElement specialityListbox;

    @FindBy(css = "#memberFirstName")
    WebElement firstNameTextBox;

    @FindBy(css = "#memberSecondName")
    WebElement lastNameTextBox;

    @FindBy(css = "#memberSelectChapter")
    WebElement chapterListBox;

    @FindBy(css = "#memberPrimaryCategory")
    WebElement professionListBox;

    @FindBy(css = "#memberPaymentOption")
    WebElement paymentOptionListBox;

    @FindBy(css = "#memberMembershipOption")
    WebElement membershipOptionListBox;

    @FindBy(css = "#applicationDate")
    WebElement applicationDateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#memberAddressLine1")
    WebElement addressLine1TextBox;

    @FindBy(css = "#memberPhoneNumber")
    WebElement PhoneNumber;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement applicationMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement applicationYear;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement submitButton;

    @FindBy(css = "#datalist1 > tbody > tr")
    List<WebElement> searchResults;

    @FindBy (css="#memberFax")
    WebElement faxTextBox;

    public Add(WebDriver driver) {
        Add.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterEmail(String emailId){
        emailTextBox.sendKeys(emailId);
    }

    public void enterFirstName(String firstName){
        firstNameTextBox.clear();
        firstNameTextBox.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameTextBox.clear();
        lastNameTextBox.sendKeys(lastName);
    }

    public void enterPhone(String phone){
        PhoneNumber.clear();
        PhoneNumber.sendKeys(phone);
    }

    public void enterAddressLine1(String addressLine1){
        addressLine1TextBox.sendKeys(addressLine1);
    }

    public void selectProfession(String profession) {
        Select professionSelect = new Select(professionListBox);
        professionSelect.selectByVisibleText(profession);
    }

    public void selectPaymentOption(String paymentOption) {
        Select paymentOptionSelect = new Select(paymentOptionListBox);
        paymentOptionSelect.selectByVisibleText(paymentOption);
    }

    public void selectMemberShipOption(String memberShipOption) {
        Select memberShipOptionSelect = new Select(membershipOptionListBox);
        memberShipOptionSelect.selectByVisibleText(memberShipOption);
    }

    public void selectCountry(String country) {
        Select countrySelect = new Select(countryListbox);
        countrySelect.selectByVisibleText(country);
    }

    public void selectRegion(String region) {
        Select regionSelect = new Select(regionListbox);
        regionSelect.selectByVisibleText(region);
    }

    public void selectChapter(String chapter) {
        Select chapterSelect = new Select(chapterListBox);
        chapterSelect.selectByVisibleText(chapter);
    }

    public void selectSpeciality(String speciality) {
        Select specialitySelect = new Select(specialityListbox);
        specialitySelect.selectByVisibleText(speciality);
    }

    public void clickApplicationDateField() throws InterruptedException {
        applicationDateTextBox.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void selectDateFromDatePicker(String day) throws Exception{
        Integer breaker = 2;
        for(WebElement trElement : datePicker)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            for (int row = 0; row < 7; row++) {
                String dayItem = td_collection.get(row).getText();
                if (day.equals(dayItem)) {
                    td_collection.get(row).findElement(By.tagName("a")).click();
                    TimeUnit.SECONDS.sleep(3);
                    breaker++;
                    break;
                }
            }
            if (breaker==3) {
                break;
            }
        }
    }

    public void clickSubmitButton(){
        submitButton.click();
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
        Select visitMonthSelect = new Select(applicationMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectVisitYear(String year) {
        Select visitYearSelect = new Select(applicationYear);
        visitYearSelect.selectByVisibleText(year);
    }

    public void enterFax(String fax) {
        faxTextBox.sendKeys(fax);
    }
}
