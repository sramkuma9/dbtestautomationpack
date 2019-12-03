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

public class CreateNewNationalEvent {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#selectCountries > option")
    WebElement countryListBox;

    @FindBy(css = "#eventTypeSelect")
    WebElement eventTypeListBox;

    @FindBy(css = "#eventName")
    WebElement eventNameTextBox;

    @FindBy(css = "#shortDesc")
    WebElement shortDescriptionTextBox;

    @FindBy(css = "#eventContactPerson")
    WebElement contactPersonListBox;

    @FindBy(css = "#eventLocation")
    WebElement locationListBox;

    @FindBy(css = "#timezone")
    WebElement timeZoneListBox;

    @FindBy(css = "#eventStartDate")
    WebElement eventStartDateTextBox;

    @FindBy(css = "#eventEndDate")
    WebElement eventEndDateTextBox;

    @FindBy(css = "#starthr")
    WebElement startTimeHr;

    @FindBy(css = "#startmin")
    WebElement startTimeMin;

    @FindBy(css = "#starthalf")
    WebElement startTime;

    @FindBy(css = "#endhr")
    WebElement endTimeHr;

    @FindBy(css = "#endmin")
    WebElement endTimeMin;

    @FindBy(css = "#endhalf")
    WebElement endTime;

    @FindBy(css = "#eventTemplateOrg")
    WebElement hostCountryListBox;

    @FindBy(css = "#eventFirstReminderSent")
    WebElement firstRemainderSent;

    @FindBy(css = "#eventSecondReminderSent")
    WebElement secondRemainderSent;

    @FindBy(css = "#submitBTN")
    WebElement submitButton;

    @FindBy(css = "#confirmBTN")
    WebElement publishButton;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement nationalEventMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement nationalEventYear;

    public CreateNewNationalEvent(WebDriver driver) {
        CreateNewNationalEvent.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectCountry(String country) {
        Select countrySelect = new Select(countryListBox);
        countrySelect.selectByVisibleText(country);
    }

    public void selectEventType(String eventType){
        Select eventTypeSelect = new Select(eventTypeListBox);
        eventTypeSelect.selectByVisibleText(eventType);
    }

    public void enterEventName(String eventName){
        eventNameTextBox.sendKeys(eventName);
    }

    public void enterShortDescription(String shortDesc){
        shortDescriptionTextBox.clear();
        shortDescriptionTextBox.sendKeys(shortDesc);
    }

    public void selectContactPerson(String contactPerson){
        Select contactPersonSelect = new Select(contactPersonListBox);
        contactPersonSelect.selectByVisibleText(contactPerson);
    }

    public void selectLocation(String location){
        Select locationSelect = new Select(locationListBox);
        locationSelect.selectByVisibleText(location);
    }

    public void selectTimeZone(String timeZone){
        Select timeZoneSelect = new Select(timeZoneListBox);
        timeZoneSelect.selectByVisibleText(timeZone);
    }

    public void clickEventStartTime(){
        eventStartDateTextBox.clear();
    }

    public void clickEventEndTime(){
        eventEndDateTextBox.clear();
    }

    public void selectStartTimeHr(String stTimeHr){
        Select startTimeHrSelect = new Select(startTimeHr);
        startTimeHrSelect.selectByVisibleText(stTimeHr);
    }

    public void selectStartTimeMin(String stTimeMin){
        Select startTimeMinSelect = new Select(startTimeMin);
        startTimeMinSelect.selectByVisibleText(stTimeMin);
    }

    public void selectStartTime(String stTime){
        Select startTimeSelect = new Select(startTime);
        startTimeSelect.selectByVisibleText(stTime);
    }

    public void selectEndTimeHr(String enTimeHr){
        Select endTimeHrSelect = new Select(endTimeHr);
        endTimeHrSelect.selectByVisibleText(enTimeHr);
    }

    public void selectEndTimeMin(String enTimeMin){
        Select endTimeMinSelect = new Select(endTimeMin);
        endTimeMinSelect.selectByVisibleText(enTimeMin);
    }

    public void selectEndTime(String enTime){
        Select endTimeSelect = new Select(endTime);
        endTimeSelect.selectByVisibleText(enTime);
    }

    public void selectHostCountry(String hostCountry) {
        Select hostCountrySelect = new Select(hostCountryListBox);
        hostCountrySelect.selectByVisibleText(hostCountry);
    }

    public void enterFirstRemainderSent(String firstRemSent){
        firstRemainderSent.sendKeys(firstRemSent);
    }

    public void enterSecondRemainderSent(String secondRemSent){
        secondRemainderSent.sendKeys(secondRemSent);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public void clickPublishButton(){
        publishButton.click();
    }

    public void selectMonth(String month) {
        Select monthSelect = new Select(nationalEventMonth);
        monthSelect.selectByVisibleText(month);
    }

    public void selectYear(String year) {
        Select yearSelect = new Select(nationalEventYear);
        yearSelect.selectByVisibleText(year);
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

}
