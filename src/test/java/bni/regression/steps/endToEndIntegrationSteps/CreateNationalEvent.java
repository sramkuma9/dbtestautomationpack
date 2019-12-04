package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.CreateNewNationalEvent;
import bni.regression.pageFactory.NationalManageEvents;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateNationalEvent {

    public static WebDriver driver;
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private NationalManageEvents nationalManageEvents;
    private CreateNewNationalEvent createNewNationalEvent;
    private CurrentDateTime currentDateTime = new CurrentDateTime();

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("I navigate to homepage")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I enter the website with National level admin login details and navigate to Tools , click Events, Manage National Events,  click Create New National Event and enter below details and click Submit button and click Publish")
    public void step_2(DataTable viewPalms) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : viewPalms.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("Tools,Events,Manage National Events");
            TimeUnit.SECONDS.sleep(8);
            nationalManageEvents = new NationalManageEvents(driver);
            nationalManageEvents.clickCreateNewNationalEvent();
            TimeUnit.SECONDS.sleep(8);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            createNewNationalEvent = new CreateNewNationalEvent(driver);
            TimeUnit.SECONDS.sleep(2);
            createNewNationalEvent.selectEventType(data.get("eventType"));
            TimeUnit.SECONDS.sleep(1);
            String eventNameDateTime = currentDateTime.dateTime();
            eventNameDateTime = (eventNameDateTime.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
            createNewNationalEvent.enterEventName(data.get("eventName") + eventNameDateTime);
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.enterShortDescription(data.get("shortDescription"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectContactPerson(data.get("contactPerson"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectLocation(data.get("location"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectTimeZone(data.get("timeZone"), data.get("timeZoneCountry"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.clickEventStartTime();
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectYear(data.get("eventStartYear"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectMonth(data.get("eventStartMonth"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectDateFromDatePicker(data.get("eventStartDay"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectStartTimeHr(data.get("eventStartHour"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectStartTimeMin(data.get("eventStartMin"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectStartTime(data.get("startTime"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.clickEventEndTime();
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectYear(data.get("eventEndYear"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectMonth(data.get("eventEndMonth"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectDateFromDatePicker(data.get("eventEndDay"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectEndTimeHr(data.get("eventEndHour"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectEndTimeMin(data.get("eventEndMin"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectEndTime(data.get("endTime"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.selectHostCountry(data.get("hostCountry"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.enterFirstRemainderSent(data.get("firstRemainderSent"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.enterSecondRemainderSent(data.get("secondRemainderSent"));
            TimeUnit.SECONDS.sleep(1);
            createNewNationalEvent.clickSubmitButton();
            TimeUnit.SECONDS.sleep(5);
            createNewNationalEvent.clickPublishButton();
            TimeUnit.SECONDS.sleep(8);
            driver.switchTo().window(tabs.get(0));
            nationalManageEvents = new NationalManageEvents(driver);
            nationalManageEvents.clickViewNationalEvent();
            TimeUnit.SECONDS.sleep(5);
            nationalManageEvents.enterSearchString(data.get("eventName") + eventNameDateTime);
            TimeUnit.SECONDS.sleep(2);
            nationalManageEvents.checkEventName(data.get("eventName") + eventNameDateTime);
            TimeUnit.SECONDS.sleep(2);
            signOut.signOutBni();
        }
    }

    @Then("National Event will be created and Published successfully. Navigate to Tools menu, Events, Manage National Events . Click View National Events button and verify Event added successfully")
    public void step_3() {
        System.out.println("Create National Events script executed.");
    }

}