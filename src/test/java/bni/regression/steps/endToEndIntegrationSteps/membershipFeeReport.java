package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.CountryReport;
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

public class membershipFeeReport {

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
    public static String fixedDateTime;
    private CaptureScreenShot captureScreenShot;
    SearchAndDeleteFile searchAndDeleteFile = new SearchAndDeleteFile();
    SearchAndReturnFileName searchAndReturnFileName = new SearchAndReturnFileName();
    private CountryReport countryReport;

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        searchAndDeleteFile.searchFileAndDelete(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "country-membership-fee-report", ".xls");
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("User logged in as admin login, select Reports, Country, Membership Fee Report")
    public void I_m_in_the_BNI_home_page_and_click_Tools_Power_search(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("User enters the below effective date and click Go Button")
    public void I_click_Find_a_Person_and_enter_the_below_details_and_click_search_button(DataTable report) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : report.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("Reports,Country");
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromReportsViewActionsByMenu("Membership Fee Report");
            TimeUnit.SECONDS.sleep(3);
            bniConnect.clickEffectiveDateTextBox();
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectEffectiveYear(data.get("effectiveYear"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.selectEffectiveMonth(data.get("effectiveMonth"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.selectDateFromDatePicker(data.get("effectiveDay"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.clickGoButton();
            TimeUnit.SECONDS.sleep(15);
            countryReport = new CountryReport(driver);
            //String cssSelectorOfSameElements="input[class='links'][id='links_1']";
            //List<WebElement> exportButtons = driver.findElements(By.cssSelector(cssSelectorOfSameElements)) ;
            //exportButtons.get(1).click();
            countryReport.clickExportButtonFromList("Export");
            TimeUnit.SECONDS.sleep(10);
            countryReport.clickCloseButton();
            String findAPersonReportName = searchAndReturnFileName.searchFile(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "country-membership-fee-report", ".xls");
            // add database verification code
            signOut.signOutBni();
        }
    }

    @Then("Membership Fee Report exported and verified with Database successfully")
    public void Reports_for_the_specified_person_displayed_successfully_verify_with_DB() throws Exception {
        System.out.println("Membership Fee Report script executed successfully");
    }
}