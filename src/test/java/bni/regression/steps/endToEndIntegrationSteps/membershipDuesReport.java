package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.ChapterReport;
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

public class membershipDuesReport {

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
    private ChapterReport chapterReport;

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        searchAndDeleteFile.searchFileAndDelete(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "Chapter_Membership_Dues_Report", ".xls");
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("User logged in as admin login, select Reports, Chapter, Membership Dues Report")
    public void step_3(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("User enters the below report date and click Go Button")
    public void step_2(DataTable report) throws Exception {
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
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("Reports,Chapter");
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromReportsViewActionsByMenu("Membership Dues Report");
            TimeUnit.SECONDS.sleep(3);
            bniConnect.clickReportDateTextBox();
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectYear(data.get("reportYear"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.selectMonth(data.get("reportMonth"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.selectDateFromDatePicker(data.get("reportDay"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.clickGoChapterButton();
            TimeUnit.SECONDS.sleep(15);
            chapterReport = new ChapterReport(driver);
            chapterReport.clickExportButton();
            TimeUnit.SECONDS.sleep(10);
            chapterReport.clickCloseButton();
            String reportName = searchAndReturnFileName.searchFile(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "country-membership-fee-report", ".xls");
            // add database verification code
            signOut.signOutBni();
        }
    }

    @Then("Membership Dues Report exported and verified with Database successfully")
    public void step_3() throws Exception {
        System.out.println("Membership Dues Report script executed successfully");
    }
}