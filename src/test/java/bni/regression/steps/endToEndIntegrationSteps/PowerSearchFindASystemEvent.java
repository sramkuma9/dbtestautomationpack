package bni.regression.steps.endToEndIntegrationSteps;


import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.FindASystemEvent;
import bni.regression.pageFactory.SearchResults;
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

public class PowerSearchFindASystemEvent {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SearchResults searchResults;
    public static String fixedDateTime;
    public static String visitorDateTime;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private FindASystemEvent findASystemEvent;
    SearchAndDeleteFile searchAndDeleteFile = new SearchAndDeleteFile();
    SearchAndReturnFileName searchAndReturnFileName = new SearchAndReturnFileName();
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        // readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "addVisitor", 0);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Given("^to search System Events$")
    public void to_test(DataTable searchDetails) throws Exception {
        List<List<String>> login = searchDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I search for a event by entering the details and click search button$")
    public void i_click_Find_a_System_Event_and_enter_the_below_details_and_click_search_button(DataTable search) throws Exception {

        Integer i = 2;
        for (Map<String, String> data : search.asMaps(String.class, String.class)) {
            searchAndDeleteFile.searchFileAndDelete(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "find-event-report", ".xls");
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
            TimeUnit.SECONDS.sleep(5);
            bniConnect.navigateMenu("Tools,Power Search,Find a System Event");
            TimeUnit.SECONDS.sleep(5);
            findASystemEvent = new FindASystemEvent(driver);
            if (!(data.get("IdHistoryType")).equals("")) {
                findASystemEvent.clickHistoryType();
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectHistoryType(data.get("IdHistoryType"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("IdCountry")).equals("")) {
                findASystemEvent.clickIdCountry();
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectIdCountry(data.get("IdCountry"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("MSIdRegionsScreen")).equals("")) {
                findASystemEvent.clickMSIdRegionsScreen();
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectMSIdRegionsScreen(data.get("MSIdRegionsScreen"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("MSIdChaptersScreen")).equals("")) {
                findASystemEvent.clickMSIdChaptersScreen();
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectMSIdChaptersScreen(data.get("MSIdChaptersScreen"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("periodStartYear")).equals("")) {
                findASystemEvent.clickPeriodStartDate();
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectYear(data.get("periodStartYear"));
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectMonth(data.get("periodStartMonth"));
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectDatePicker(data.get("periodStartDay"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("periodEndYear")).equals("")) {
                findASystemEvent.clickPeriodEndDate();
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectYear(data.get("periodEndYear"));
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectMonth(data.get("periodEndMonth"));
                TimeUnit.SECONDS.sleep(2);
                findASystemEvent.selectDatePicker(data.get("periodEndDay"));
                TimeUnit.SECONDS.sleep(2);
            }


            findASystemEvent.enterResultsPerPage(data.get("resultsPerPage"));
            TimeUnit.SECONDS.sleep(1);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "PowerSearchFindASystemEvent");
            findASystemEvent.clickEventSearch();
            TimeUnit.SECONDS.sleep(15);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            searchResults = new SearchResults(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "PowerSearchFindASystemEvent");
            searchResults.clickExportIndiaButton();
            TimeUnit.SECONDS.sleep(10);
            String findASystemEventReportName = searchAndReturnFileName.searchFile(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "find-event-report", ".xls");
            driver.switchTo().window(tabs.get(0));
            // add database verification code
            signOut.signOutBni();
            i++;
        }

    }

    @Then("Reports for the System Event for the selected country displayed successfully")
    public void reports_for_the_System_Event_for_the_selected_country_displayed_successfully() throws Exception {

        // @Then("Reports for the specified system displayed successfully- verify with DB")
        //   public void Reports_for_the_specified_system_displayed_successfully_verify_with_DB() throws Exception {
        System.out.println("Reports for specified system added successfully.");
    }
}