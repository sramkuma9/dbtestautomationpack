package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.Reconcile;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static junit.framework.TestCase.assertEquals;

public class ConvertVisitorToMember {
    public static WebDriver driver;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    DbConnect dbConnect = new DbConnect();
    private AddAVisitor addAVisitor;
    private CaptureScreenShot captureScreenShot;
    private ViewEditVisitorsList viewEditVisitorsList;
    private EnterNewApplication enterNewApplication;
    private Add add;
    public List<List<String>> loginSubList;
    public  String [] convertToMemberDetails = new String [8];
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    private Reconcile reconcile = new Reconcile();

    @Before
    public void setup() throws Exception {
//        driver = launchBrowser.getDriver();
//        launchBrowser.invokeBrowser();
//        login.loginToBni();
    }

    @After
    public void tearDown() throws Exception {
        //signOut.signOutBni();
    }

    // Scenario: Navigate to Add a Visitor page
    // Scenario: Navigate to Add a Visitor page
    @Given("I am on the Enter New Application page using the below data")
    public void I_am_on_the_Enter_New_Application_page_using_the_below_data(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I search emailid and click add and enter the below details and click save")
    public void I_search_emailid_and_click_add_and_enter_the_below_details_and_click_save(DataTable convertToMember) throws Exception {
        Integer i =1;
        Integer j = 2;
        for (Map<String, String> data : convertToMember.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(j - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ",""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(2);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMenu = readWriteExcel.getCellData("translation",colNumber,2);
            bniConnect.selectItemFromSubListMenu(transMenu);
            TimeUnit.SECONDS.sleep(10);
            viewEditVisitorsList = new ViewEditVisitorsList(driver);
            viewEditVisitorsList.clickFromStartDateField();
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.selectYear("2010");
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.selectMonth("Jan");
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.selectDateFromDatePicker("15");
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.clickToEndDateField();
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.selectYear("2023");
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.selectMonth("Sep");
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.selectDateFromDatePicker("10");
            TimeUnit.SECONDS.sleep(2);
            viewEditVisitorsList.clickGoButton();
            TimeUnit.SECONDS.sleep(5);
            viewEditVisitorsList.clickConvertToMemberButton();
            TimeUnit.SECONDS.sleep(2);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String visitorEmailId = readWriteExcel.getCellData("addBrandNewVisitor",0,i);
            String lastName = readWriteExcel.getCellData("addBrandNewVisitor",1,i);
            enterNewApplication = new EnterNewApplication(driver);
            enterNewApplication.enterEmail(visitorEmailId);
            TimeUnit.SECONDS.sleep(2);
            enterNewApplication.clickSearchButton();
            TimeUnit.SECONDS.sleep(5);
            enterNewApplication.clickAddButton();
            TimeUnit.SECONDS.sleep(8);
            add = new Add(driver);
            add.clickApplicationDateField();
            TimeUnit.SECONDS.sleep(2);
            add.selectVisitYear(data.get("applicationYear"));
            TimeUnit.SECONDS.sleep(2);
            add.selectVisitMonth(data.get("applicationMonth"));
            TimeUnit.SECONDS.sleep(2);
            add.selectDateFromDatePicker(data.get("applicationDay"));
            TimeUnit.SECONDS.sleep(2);
            add.selectRegion(data.get("region"));
            TimeUnit.SECONDS.sleep(2);
            add.selectChapter(data.get("chapter"));
            TimeUnit.SECONDS.sleep(2);
            add.selectProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(2);
            add.selectSpeciality(data.get("speciality"));
            TimeUnit.SECONDS.sleep(2);
            add.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(2);
            add.enterLastName(lastName);
            TimeUnit.SECONDS.sleep(2);
            add.enterAddressLine1(data.get("addressLine1"));
            TimeUnit.SECONDS.sleep(2);
            add.selectCountry(data.get("country"));
            TimeUnit.SECONDS.sleep(2);
            add.enterPhone(data.get("phone"));
            TimeUnit.SECONDS.sleep(2);
            add.selectPaymentOption(data.get("paymentOption"));
            TimeUnit.SECONDS.sleep(2);
            add.selectMemberShipOption(data.get("membershipOption"));
            TimeUnit.SECONDS.sleep(2);
            add.clickSubmitButton();
            TimeUnit.SECONDS.sleep(15);
            enterNewApplication = new EnterNewApplication(driver);
            enterNewApplication.enterEmail(visitorEmailId);
            TimeUnit.SECONDS.sleep(2);
            enterNewApplication.clickSearchButton();
            TimeUnit.SECONDS.sleep(3);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String expectedDate = dtf.format(now).toString();
            enterNewApplication = new EnterNewApplication(driver);
            convertToMemberDetails = enterNewApplication.getSearchResults();
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "convertVisitorToMember");
            assertEquals("Visit date is not correct", expectedDate, convertToMemberDetails[0] );
            assertEquals("First Name is not correct", data.get("firstName"), convertToMemberDetails[1] );
            assertEquals("Last Name is not correct", lastName, convertToMemberDetails[2] );
            assertEquals("Region is not correct", data.get("region"), convertToMemberDetails[3] );
            assertEquals("Chapter is not correct", data.get("chapter"), convertToMemberDetails[4] );
            assertEquals("Company Name is not correct", data.get("companyName"), convertToMemberDetails[5] );
            i++;
            j++;
            Integer actCount = 1;
            Integer expCount = dbConnect.queryRecordCount(readWritePropertyFile.loadAndReadPropertyFile("convertVisitorToMember", "properties/sql.properties") + visitorEmailId + "';");
            assertEquals("Email count in DataBase is not correct",actCount, expCount);
            reconcile.reconcileApp(data.get("firstName"),lastName);
            signOut.signOutBni();
        }
    }

    @Then("visitor is successfully converted to a member and I signout from BNI")
    public void visitor_is_successfully_converted_to_a_member_and_I_signout_from_BNI() throws Exception{
        TimeUnit.SECONDS.sleep(2);
        //signOut.signOutBni();
    }
}