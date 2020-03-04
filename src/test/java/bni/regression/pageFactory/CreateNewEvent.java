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

public class CreateNewEvent {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#selectRegions")
    List<WebElement> regionListBox;

    @FindBy(css = "#selectAllRegions")
    WebElement selectAllRegionsCheckBox;

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

    @FindBy(css = "#edit-date-default-timezone")
    WebElement timeZonepopup;

    @FindBy(css = "#edit-site-default-country")
    WebElement countrypopup;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement selectButton;

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

    @FindBy(css = "#eventFirstReminderSent")
    WebElement firstRemainderSent;

    @FindBy(css = "#eventSecondReminderSent")
    WebElement secondRemainderSent;

    @FindBy(css="#eventCostMember")
    WebElement costOfMemberTextBox;

    @FindBy(css="#eventCostNonMember")
    WebElement costOfNonMemberTextBox;

    @FindBy(css = "#submitBTN")
    WebElement submitButton;

    @FindBy(css = "#confirmBTN")
    WebElement publishButton;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement regionalEventMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement regionalEventYear;

    public CreateNewEvent(WebDriver driver) {
        CreateNewEvent.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
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

    public void enterCostForMember(String costOfMember) {
        costOfMemberTextBox.sendKeys(costOfMember);
    }

    public void enterCostForNonMember(String costOfNonMember) {
        costOfNonMemberTextBox.sendKeys(costOfNonMember);
    }

    public void selectTimeZone(String timeZone, String country) throws InterruptedException {
        timeZoneListBox.click();
        TimeUnit.SECONDS.sleep(3);
        Select timeZonePopUpSelect = new Select(timeZonepopup);
        timeZonePopUpSelect.selectByVisibleText(timeZone);
        TimeUnit.SECONDS.sleep(1);
        Select countryPopUpSelect = new Select(countrypopup);
        countryPopUpSelect.selectByVisibleText(country);
        TimeUnit.SECONDS.sleep(1);
        selectButton.click();
    }

    public void clickEventStartTime(){
        eventStartDateTextBox.click();
    }

    public void clickEventEndTime(){
        eventEndDateTextBox.click();
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
        Select monthSelect = new Select(regionalEventMonth);
        monthSelect.selectByVisibleText(month);
    }

    public void selectYear(String year) {
        Select yearSelect = new Select(regionalEventYear);
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

    public void selectRegion(String region) throws InterruptedException {
        selectAllRegionsCheckBox.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement trElement : regionListBox) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("option"));
            Integer listSize = td_collection.size();
            for (int i = 0; i < listSize; i++) {
                String regionItem = td_collection.get(i).getText();
                if (region.equals(regionItem)) {
                    td_collection.get(i).click();
                    break;
                }
            }
        }
    }
}
