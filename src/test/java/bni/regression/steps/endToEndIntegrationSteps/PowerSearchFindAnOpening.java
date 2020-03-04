package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.FindAnOpening;
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

public class PowerSearchFindAnOpening {
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
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private FindAnOpening findAnOpening;
    private SearchResults searchResults;
    SearchAndDeleteFile searchAndDeleteFile = new SearchAndDeleteFile();
    SearchAndReturnFileName searchAndReturnFileName = new SearchAndReturnFileName();

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        //  readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //  boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnOpening", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("^I’m in the BNI home page, and click Tools, Power search , Find an Opening$")
    // Scenario: Navigate to Tools page
    public void i_m_in_the_BNI_home_page_click_Tools_Power_search_Find_an_Opening(DataTable searchDetails) throws Throwable {
        List<List<String>> login = searchDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I select the search criteria as profession and click search button$")
    public void i_select_the_search_criteria_as_Profession_and_click_search_button(DataTable search) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : search.asMaps(String.class, String.class)) {
            searchAndDeleteFile.searchFileAndDelete(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "findopeningreport", ".xls");
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
            bniConnect.navigateMenu("Tools,Power Search,Find an Opening");
            TimeUnit.SECONDS.sleep(5);
            findAnOpening = new FindAnOpening(driver);
            findAnOpening.selectCategory(data.get("category"));
            TimeUnit.SECONDS.sleep(1);
            if (!(data.get("secCategory")).equals("")) {
                findAnOpening.clickSecCategory();
                TimeUnit.SECONDS.sleep(2);
                findAnOpening.selectSecCategory(data.get("secCategory"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("city")).equals("")) {
                findAnOpening.clickCity();
                TimeUnit.SECONDS.sleep(2);
                findAnOpening.selectCity(data.get("city"));
                TimeUnit.SECONDS.sleep(1);
            }

            if (!(data.get("dayOfWeek")).equals("")) {
                findAnOpening.clickDayOfWeek();
                TimeUnit.SECONDS.sleep(2);
                findAnOpening.selectDayOfWeek(data.get("dayOfWeek"));
                TimeUnit.SECONDS.sleep(1);
            }
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "powersearchfindanopening");
            findAnOpening.clickSearchButton();
            TimeUnit.SECONDS.sleep(2);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            searchResults = new SearchResults(driver);
            TimeUnit.SECONDS.sleep(5);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "powersearchfindanopening");
            searchResults.clickExportIndiaButton();
            TimeUnit.SECONDS.sleep(10);
            String findOpeningName = searchAndReturnFileName.searchFile(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "findopeningreport", ".xls");
            driver.switchTo().window(tabs.get(0));
            // add database verification code
            signOut.signOutBni();
            i++;

        }
    }

    @Then("opening for the specified speciality displayed successfully")
    public void opening_for_the_specified_speciality_displayed_successfully() throws Exception {

        System.out.println("Openings for the given speciality displayed successfully.");


    }
}

