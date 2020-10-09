package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.common.email.GmailClient;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;


import static junit.framework.TestCase.assertEquals;

public class BounceStatusVerification {


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
    public String[] addAVisitorDetails = new String[8];
    public String firstName;
    public String lastName;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot = new CaptureScreenShot(driver);
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private GmailClient gmailClient = new GmailClient();
    DbConnect dbConnect = new DbConnect();
    private ViewEditVisitorsList viewEditVisitorsList;
    private RegionReport regionReport;
    private EditVisitor editVisitor;


    @Given("^login with credentials$")
    public void loginWithCredentials(DataTable loginDetails) throws Exception {

        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I add a visitor with an EmailId  bounce Status$")
    public void bounceStatus(DataTable bounce) throws Exception {

        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : bounce.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(4);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMenu = readWriteExcel.getCellData("translation", colNumber, 1);
            bniConnect.selectItemFromSubListMenu(transMenu);
            addAVisitor = new AddAVisitor(driver);
            TimeUnit.SECONDS.sleep(24);
            String dateTimeStamp = currentDateTime.dateTime();
            visitorDateTime = (dateTimeStamp.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
            System.out.println("Timestamp" + visitorDateTime);
            addAVisitor.enterEmail(data.get("email"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickSearchButton();
            addAVisitor.clickSearchByNameButton();
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(1);
            addAVisitor.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(1);
            String lastName = data.get("lastName");
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("submit"))));
            driver.findElement(By.name("submit")).click();
            TimeUnit.SECONDS.sleep(1);
            addAVisitor.clickCreateNewButton();
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectChapter(data.get("chapter"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectSpeciality(data.get("speciality"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectLanguage(data.get("language"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectInvitedBy(data.get("person"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickVisitDateField();
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectVisitYear(data.get("visitYear"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectVisitMonth(data.get("visitMonth"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectDateFromDatePicker(data.get("visitDay"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectVisitorTitle(data.get("title"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterAddressLine1(data.get("address"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterVisitorFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(1);
            addAVisitor.enterVisitorLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterCompanyName(data.get("companyName"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectVisitorCountry(data.get("country"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterVisitorPhoneNumber(data.get("phone"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickSaveButton();
            TimeUnit.SECONDS.sleep(10);
            String sqlquery = "(select email_bounce_type, email_verified_on , email_bounced_on from bni.individual where last_name = '" + lastName + "');";
            String[][] bounceStatus = dbConnect.queryAndRetrieveRecords(sqlquery);
            System.out.println("Bounce Type is" + bounceStatus[0][0]);
            System.out.println("Email Verified on is" + bounceStatus[0][1]);
            System.out.println("Email Bounced on is" + bounceStatus[0][2]);
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(4);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMenu2 = readWriteExcel.getCellData("translation", colNumber, 2);
            bniConnect.selectItemFromSubListMenu(transMenu2);
            TimeUnit.SECONDS.sleep(8);
            viewEditVisitorsList = new ViewEditVisitorsList(driver);
            viewEditVisitorsList.clickFromStartDateField();
            viewEditVisitorsList.selectYear(data.get("startYear"));
            TimeUnit.SECONDS.sleep(1);
            viewEditVisitorsList.selectMonth(data.get("startMonth"));
            TimeUnit.SECONDS.sleep(1);
            viewEditVisitorsList.selectDateFromDatePicker(data.get("startDate"));
            TimeUnit.SECONDS.sleep(1);
            viewEditVisitorsList.clickToEndDateField();
            viewEditVisitorsList.selectYear(data.get("endYear"));
            TimeUnit.SECONDS.sleep(1);
            viewEditVisitorsList.selectMonth(data.get("endMonth"));
            TimeUnit.SECONDS.sleep(1);
            viewEditVisitorsList.selectDateFromDatePicker(data.get("endDate"));
            TimeUnit.SECONDS.sleep(1);
            viewEditVisitorsList.clickGoButton();
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.enterSearchText(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.clickEditVisitor();
            TimeUnit.SECONDS.sleep(1);
            editVisitor = new EditVisitor(driver);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 350)", "");
            TimeUnit.SECONDS.sleep(10);
            editVisitor.getEmailVerificationStatus();
            TimeUnit.SECONDS.sleep(10);
            editVisitor.clickCloseButton();
            TimeUnit.SECONDS.sleep(1);
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("REPORTS,Region");
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromReportsViewActionsByMenu("Unverified Email Addresses Report");
            TimeUnit.SECONDS.sleep(3);
            bniConnect.clickUnverifiedEmailTextBox();
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectYear(data.get("year"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.selectMonth(data.get("month"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.selectDateFromDatePicker(data.get("date"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.clickUnverifiedEmailReportGoButton();
            TimeUnit.SECONDS.sleep(10);
            CaptureScreenShot captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "UnverifiedEmailReport-Bounce");
            UnverifiedEmailAddressesReport unverifiedEmailAddressesReport = new UnverifiedEmailAddressesReport(driver);
            unverifiedEmailAddressesReport.clickCloseButton();
            signOut.signOutBni();
        }
    }


}








































