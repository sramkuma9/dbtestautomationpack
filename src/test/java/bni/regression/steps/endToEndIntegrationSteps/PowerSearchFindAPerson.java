package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.AddAVisitor;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.FindAPerson;
import bni.regression.pageFactory.SearchResults;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class PowerSearchFindAPerson {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public String firstName;
    public String lastName;
    public List<List<String>> loginSubList;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private FindAPerson findAPerson;
    private SearchResults searchResults;
    public static String fixedDateTime;
    private CaptureScreenShot captureScreenShot;

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        //readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "addVisitor", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("I’m in the BNI home page, and click Tools->Power search")
    public void I_m_in_the_BNI_home_page_and_click_Tools_Power_search(DataTable searchDetails) throws Exception {
        List<List<String>> login = searchDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I click “Find a Person” and enter the below details and click search button")
    public void I_click_Find_a_Person_and_enter_the_below_details_and_click_search_button(DataTable search) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : search.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("Tools,Power Search,Find a Person");
            TimeUnit.SECONDS.sleep(5);
            findAPerson = new FindAPerson(driver);
            findAPerson.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(1);
            findAPerson.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(1);
            findAPerson.enterCompany(data.get("company"));
            TimeUnit.SECONDS.sleep(1);
            findAPerson.enterBNIOrganisation(data.get("bniOrganisation"));
            TimeUnit.SECONDS.sleep(1);
            findAPerson.selectDateCriteria(data.get("dateCriteria"));
            TimeUnit.SECONDS.sleep(1);
            if (!(data.get("periodStartYear")).equals("")) {
                findAPerson.clickPeriodStartDate();
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectYear(data.get("periodStartYear"));
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectMonth(data.get("periodStartMonth"));
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectDateFromDatePicker(data.get("PeriodStartDay"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("periodEndYear")).equals("")) {
                findAPerson.clickPeriodEndDate();
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectYear(data.get("periodEndYear"));
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectMonth(data.get("periodEndMonth"));
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectDateFromDatePicker(data.get("periodEndDay"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("queryYear")).equals("")) {
                findAPerson.clickQueryDate();
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectYear(data.get("queryYear"));
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectMonth(data.get("queryMonth"));
                TimeUnit.SECONDS.sleep(2);
                findAPerson.selectDateFromDatePicker(data.get("queryDay"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("role")).equals("")) {
                findAPerson.selectRole(data.get("role"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("feesSuspended")).equals("")) {
                findAPerson.clickfeesSuspendedFlag(data.get("feesSuspended"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("activeRecordsOnly")).equals("")) {
                findAPerson.clickActiveRecordsOnlyFlag(data.get("activeRecordsOnly"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("showRecordsWithRemarksOnly")).equals("")) {
                findAPerson.clickShowRecordsWithRemarksOnlyFlag(data.get("showRecordsWithRemarksOnly"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("deletedRecords")).equals("")) {
                findAPerson.clickDeletedRecordsFlag(data.get("deletedRecords"));
                TimeUnit.SECONDS.sleep(1);
            }
            findAPerson.enterResultsPerPage(data.get("resultsPerPage"));
            TimeUnit.SECONDS.sleep(1);
            findAPerson.clickSearchButton();
            TimeUnit.SECONDS.sleep(10);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            searchResults = new SearchResults(driver);
            searchResults.clickExportIndiaButton();
            String searchResultsExportxls = searchResults.getDateTime();
            System.out.println(searchResultsExportxls);
            TimeUnit.SECONDS.sleep(10);
            driver.switchTo().window(tabs.get(0));
            // add database verification code
            signOut.signOutBni();
        }
    }

    @Then("Reports for the specified person displayed successfully- verify with DB")
    public void Reports_for_the_specified_person_displayed_successfully_verify_with_DB() throws Exception {
        System.out.println("Visitor details added successfully.");
    }
}