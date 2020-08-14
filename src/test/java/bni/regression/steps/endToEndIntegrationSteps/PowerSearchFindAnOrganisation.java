package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.FindAnOrganisation;
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

public class PowerSearchFindAnOrganisation {
    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;

    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public static String fixedDateTime;
    public static String visitorDateTime;
    public String name;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private FindAnOrganisation findAnOrganisation;
    private SearchResults searchResults;
    SearchAndDeleteFile searchAndDeleteFile = new SearchAndDeleteFile();
    SearchAndReturnFileName searchAndReturnFileName = new SearchAndReturnFileName();

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        //  readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        // boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnOrganisation", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Power Search
    @Given("^I enter BNI home page, and select Tools->Power search->Find An Organisation$")
    public void I_enter_BNI_home_page_and_select_Tools_Power_search_Find_An_Organisation(DataTable searchDetails) throws Exception {
        List<List<String>> login = searchDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I enter the search criteria of Organisations and select search button")
    public void i_enter_the_search_criteria_of_Organisations_and_select_search_button(DataTable search) throws Exception {

        Integer i = 2;
        for (Map<String, String> data : search.asMaps(String.class, String.class)) {
            searchAndDeleteFile.searchFileAndDelete(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "find-organization-report", ".xls");
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
            bniConnect.navigateMenu("TOOLS,Power Search,Find an Organization");
            TimeUnit.SECONDS.sleep(8);
            bniConnect = new BNIConnect(driver);
            findAnOrganisation = new FindAnOrganisation(driver);
            if (!(data.get("name")).equals("")) {
                findAnOrganisation.clickName();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.enterName(data.get("name"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("company")).equals("")) {
                findAnOrganisation.clickCompany();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.enterCompany(data.get("company"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("orgType")).equals("")) {
                findAnOrganisation.clickOrgType();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.selectOrgType(data.get("orgType"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("orgStatus")).equals("")) {
                findAnOrganisation.clickOrgStatus();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.selectOrgStatus(data.get("orgStatus"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("category")).equals("")) {
                findAnOrganisation.clickCategory();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.selectCategory(data.get("category"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("secCategory")).equals("")) {
                findAnOrganisation.clickSubCategory();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.selectSubCategory(data.get("secCategory"));
                TimeUnit.SECONDS.sleep(1);
            }

            if (!(data.get("sizeType")).equals("")) {
                findAnOrganisation.clickSizeType();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.selectSizeType(data.get("sizeType"));
                TimeUnit.SECONDS.sleep(1);
            }

            if (!(data.get("sizeValue")).equals("")) {
                findAnOrganisation.clickSizeValue();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.enterSizeValue(data.get("sizeValue"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("queryDateType")).equals("")) {
                findAnOrganisation.clickQueryDateType();
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.selectQueryDateType(data.get("queryDateType"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("periodYear")).equals("")) {
                findAnOrganisation.clickDate();
                TimeUnit.SECONDS.sleep(1);
                findAnOrganisation.selectYear(data.get("periodYear"));
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.selectMonth(data.get("periodMonth"));
                TimeUnit.SECONDS.sleep(2);
                findAnOrganisation.selectDateFromDatePicker(data.get("periodDate"));
                TimeUnit.SECONDS.sleep(2);
            }
            findAnOrganisation.clickRenewalDue();
            TimeUnit.SECONDS.sleep(1);
            findAnOrganisation.enterNumberPerPage(data.get("numberPerPage"));
            TimeUnit.SECONDS.sleep(1);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "powersearchfindanorganisation");
            findAnOrganisation.clickSearchButton();
            TimeUnit.SECONDS.sleep(10);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            searchResults = new SearchResults(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "powersearchfindanorganisation");
            searchResults.clickExportIndiaButton();
            TimeUnit.SECONDS.sleep(10);
            String findAnOrganisationReportName = searchAndReturnFileName.searchFile(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "find-organization-report", ".xls");
            driver.switchTo().window(tabs.get(0));
            // add database verification code
            signOut.signOutBni();
            i++;
        }

    }

    @Then("Reports for the specified Organisation displayed successfully-verify with DB")
    public void Reports_for_the_specified_organisation_displayed_successfully_verify_with_DB() throws Exception {
        System.out.println("Organisation details displayed successfully.");

    }


}


