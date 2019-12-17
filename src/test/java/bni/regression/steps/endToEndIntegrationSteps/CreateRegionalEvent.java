package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.CreateNewEvent;
import bni.regression.pageFactory.ManageEvents;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateRegionalEvent {

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
    private ManageEvents manageEvents;
    private CreateNewEvent createNewEvent;
    private CurrentDateTime currentDateTime = new CurrentDateTime();

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("user navigates to homepage using below details")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("user enter the website with Regional level admin login details and navigate to Tools , click Events, Manage National Events,  click Create New Regional Event and enter below details and click Submit button and click Publish")
    public void step_2(DataTable regionalEvents) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : regionalEvents.asMaps(String.class, String.class)) {
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
            bniConnect.navigateMenu("Tools,Events,Manage Regional Events");
            TimeUnit.SECONDS.sleep(8);
            manageEvents = new ManageEvents(driver);
            manageEvents.clickCreateNewEventButton();
            TimeUnit.SECONDS.sleep(8);
            createNewEvent = new CreateNewEvent(driver);
            TimeUnit.SECONDS.sleep(2);
            createNewEvent.selectRegion(data.get("region"));
            TimeUnit.SECONDS.sleep(2);
            createNewEvent.selectEventType(data.get("eventType"));
            TimeUnit.SECONDS.sleep(1);
            String eventNameDateTime = currentDateTime.dateTime();
            eventNameDateTime = (eventNameDateTime.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
            createNewEvent.enterEventName(data.get("eventName") + eventNameDateTime);
            TimeUnit.SECONDS.sleep(1);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            boolean setEventFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "createRegionalEvent", 0, i, data.get("eventName") + eventNameDateTime);
            createNewEvent.enterShortDescription(data.get("shortDescription"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectContactPerson(data.get("contactPerson"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectLocation(data.get("location"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectTimeZone(data.get("timeZone"), data.get("timeZoneCountry"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.clickEventStartTime();
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectYear(data.get("eventStartYear"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectMonth(data.get("eventStartMonth"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectDateFromDatePicker(data.get("eventStartDay"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectStartTimeHr(data.get("eventStartHour"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectStartTimeMin(data.get("eventStartMin"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectStartTime(data.get("startTime"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.clickEventEndTime();
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectYear(data.get("eventEndYear"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectMonth(data.get("eventEndMonth"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectDateFromDatePicker(data.get("eventEndDay"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectEndTimeHr(data.get("eventEndHour"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectEndTimeMin(data.get("eventEndMin"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.selectEndTime(data.get("endTime"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.enterFirstRemainderSent(data.get("firstRemainderSent"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.enterSecondRemainderSent(data.get("secondRemainderSent"));
            TimeUnit.SECONDS.sleep(1);
            createNewEvent.clickSubmitButton();
            TimeUnit.SECONDS.sleep(5);
            createNewEvent.clickPublishButton();
            TimeUnit.SECONDS.sleep(8);
            manageEvents = new ManageEvents(driver);
            manageEvents.clickViewEventsButton();
            TimeUnit.SECONDS.sleep(5);
            manageEvents.enterSearchString(data.get("eventName") + eventNameDateTime);
            TimeUnit.SECONDS.sleep(2);
            manageEvents.checkEventName(data.get("eventName") + eventNameDateTime);
            TimeUnit.SECONDS.sleep(2);
            i++;
            signOut.signOutBni();
        }
    }

    @Then("Regional Event will be created and Published successfully. Navigate to Tools menu, Events, Manage Regional Events . Click View Regional Events button and verify Event added successfully")
    public void step_3() {
        System.out.println("Create National Events script executed.");
    }

}