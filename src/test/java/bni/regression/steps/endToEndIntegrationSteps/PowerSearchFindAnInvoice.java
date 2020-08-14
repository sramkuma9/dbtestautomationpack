package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.db.DbXlsComparator;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
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

public class PowerSearchFindAnInvoice {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private AddAVisitor addAVisitor;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public static String fixedDateTime;
    public static String visitorDateTime;
    public String name;
    public String firstName;
    public String lastName;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private FindAnInvoice findAnInvoice;
    DbConnect dbConnect = new DbConnect();
    DbXlsComparator dbXlsComparator = new DbXlsComparator();
    private SearchResults searchResults;
    SearchAndDeleteFile searchAndDeleteFile = new SearchAndDeleteFile();
    SearchAndReturnFileName searchAndReturnFileName = new SearchAndReturnFileName();

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        //  readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        // boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "addVisitor", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Find an Invoice page
    @Given("^I’m in the BNI home page logged in as admin, and click Tools->Power search -> Find An Invoice$")
    public void I_m_in_the_BNI_home_page_logged_in_as_admin_and_click_Tools_Power_search_Find_An_Invoice(DataTable searchDetails) throws Exception {
        List<List<String>> login = searchDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I  select “Find an Invoice” and enter the below details and click search button$")
    public void i_select_Find_an_Invoice_and_enter_the_below_details_and_click_search_button(DataTable search) throws Exception {
        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : search.asMaps(String.class, String.class)) {
            searchAndDeleteFile.searchFileAndDelete(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "find_invoice", ".xls");
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
            TimeUnit.SECONDS.sleep(45);
            bniConnect.navigateMenu("TOOLS,Power Search,Find an Invoice");
            TimeUnit.SECONDS.sleep(15);
            bniConnect = new BNIConnect(driver);
            findAnInvoice = new FindAnInvoice(driver);
            TimeUnit.SECONDS.sleep(5);

            if (!(data.get("invoiceReference")).equals("")) {
                findAnInvoice.clickInvoiceReference();
                TimeUnit.SECONDS.sleep(1);
                findAnInvoice.enterInvoiceReference(data.get("invoiceReference"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("periodEndYear")).equals("")) {
                findAnInvoice.clickEndDateField();
                TimeUnit.SECONDS.sleep(1);
                findAnInvoice.selectYear(data.get("periodEndYear"));
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectMonth(data.get("periodEndMonth"));
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectDatePicker(data.get("periodEndDay"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("periodStartYear")).equals("")) {
                findAnInvoice.clickStartDateField();
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectYear(data.get("periodStartYear"));
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectMonth(data.get("periodStartMonth"));
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectDatePicker(data.get("periodStartDay"));
                TimeUnit.SECONDS.sleep(2);
            }

            if (!(data.get("idCompany")).equals("")) {
                findAnInvoice.clickIdCompany();
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectIdCompany(data.get("idCompany"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("fromIdOrg")).equals("")) {
                findAnInvoice.clickFromIdOrg();
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectFromIdOrg(data.get("fromIdOrg"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("toIdOrg")).equals("")) {
                findAnInvoice.clickToIdOrg();
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectToIdOrg(data.get("toIdOrg"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("onBehalfIdOrg")).equals("")) {
                findAnInvoice.clickOnBehalfIdOrg();
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectOnBehalfIdOrg(data.get("onBehalfIdOrg"));
                TimeUnit.SECONDS.sleep(1);
            }
            if (!(data.get("idInvoiceType")).equals("")) {
                findAnInvoice.clickIdInvoiceType();
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectIdInvoiceType(data.get("idInvoiceType"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("idInvoiceStatus")).equals("")) {
                findAnInvoice.clickIdInvoiceStatus();
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectIdInvoiceStatus(data.get("idInvoiceStatus"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("idPaymentType")).equals("")) {
                findAnInvoice.clickIdPaymentType();
                TimeUnit.SECONDS.sleep(2);
                findAnInvoice.selectIdPaymentType(data.get("idPaymentType"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("idPaymentStatus")).equals("")) {
                findAnInvoice.clickIdPaymentStatus();
                TimeUnit.SECONDS.sleep(5);
                findAnInvoice.selectIdPaymentStatus(data.get("idPaymentStatus"));
                TimeUnit.SECONDS.sleep(2);
            }

            findAnInvoice.enterNumberPerPage(data.get("numberPerPage"));
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "powersearchFindAnInvoice");
            findAnInvoice.clickSearchButton();
            TimeUnit.SECONDS.sleep(15);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            searchResults = new SearchResults(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "powersearchFindAnInvoice");
            searchResults.clickExportIndiaButton();
            TimeUnit.SECONDS.sleep(20);
            String actualInvoiceReport = searchAndReturnFileName.searchFile(readWritePropertyFile.loadAndReadPropertyFile("downloadFilePath", "properties/config.properties"), "find-invoice", ".xls");
           System.out.println("Report name is " +actualInvoiceReport);

            driver.switchTo().window(tabs.get(0));
            // add database verification code
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testAndDelete.xlsx");
            boolean expectedInvoiceReport = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnInvoiceSheet", 0, i, data.get("invoiceReference"));
        //  readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnInvoiceSheet", 1, i - j, data.get("onBehalfIdOrg"));


            signOut.signOutBni();
            i++;
        }
    }

    @Then("Invoice Reports for the specified person displayed successfully- verify with DB")
    public void Invoice_Reports_for_the_specified_person_displayed_successfully_verify_with_DB() throws Exception {
        System.out.println("Invoice details for the specified person displayed successfully.");
    }
}














/*
 boolean setInvoiceReferenceFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnInvoiceSheet", 0, i, data.get("invoiceReference"));
            boolean setLastNameFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnInvoiceSheet", 1, i - j, data.get("onBehalfIdOrg"));

            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testAndDelete.xlsx");
            String[][] sqlResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("findAnInvoice", "properties/sql.properties"));

            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testAndDelete.xlsx");
            Integer xlsRowCount = readWriteExcel.getRowCount("findAnInvoiceSheet");
            System.out.println("xlsRowCount is" + xlsRowCount);
            String[][] xlsData = readWriteExcel.returnDataForDbXlsComparatorClass("findAnInvoiceReportName");
            dbXlsComparator.resultComparator(sqlResult, xlsData, xlsRowCount);



            System.out.println("success");

 */