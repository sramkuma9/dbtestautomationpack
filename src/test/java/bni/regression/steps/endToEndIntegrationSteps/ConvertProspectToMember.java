package bni.regression.steps.endToEndIntegrationSteps;


import bni.regression.libraries.common.*;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ConvertProspectToMember {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    //private ManageProspectEditProspect manageProspectEditProspect;
    //private EditProspect editProspect;
    private ViewEditVisitorsList viewEditVisitorsList;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public static String fixedDateTime;
    public static String visitorDateTime;
    public String name;
    public String[] prospectDetails = new String[8];
    public String firstName;
    public String lastName;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    private ConvertProspectToMember convertProspectToMember;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    ;
    private EnterNewApplication enterNewApplication;
    private Add add;

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        //   readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //   boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "manageProspects", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    //  Scenario: Manage prospects_Edit Prospect


    @Given("^User Login into BNI application navigates to home page using the below data$")
    public void userLoginIntoBNIApplicationNavigatesToHomePageUsingTheBelowData(DataTable loginDetails) throws Exception {

        //  @Given("^User enters BNI home page with below data$")
        // public void User_enters_BNI_home_page_with_below_data(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I click Operations -Chapter -Manage Memberships and click Enter New Application, enter EmailID, click search button and click Add button$")
    public void iClickOperationsChapterManageMembershipsAndClickEnterNewApplicationEnterEmailIDClickSearchButtonAndClickAddButton(DataTable prospectToMember) throws Exception {


        Integer i = 2;
        for (Map<String, String> data : prospectToMember.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Operations,Chapter");
            TimeUnit.SECONDS.sleep(20);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 5);
            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(25);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(30);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "convertProspectToMember");
            bniConnect = new BNIConnect(driver);
            bniConnect.enterEmailId(data.get("emailId"));
            TimeUnit.SECONDS.sleep(2);
            bniConnect.clickSearchButton();
            TimeUnit.SECONDS.sleep(5);
            bniConnect.clickAddProspectButton();
            TimeUnit.SECONDS.sleep(8);
            add = new Add(driver);
            add.clickApplicationDateField();
            TimeUnit.SECONDS.sleep(20);
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
            add.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            add.enterAddressLine1("");
            add.enterAddressLine1(data.get("addressLine1"));
            TimeUnit.SECONDS.sleep(2);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "convertProspectToMember");
            add.enterPhone(data.get("phone"));
            TimeUnit.SECONDS.sleep(2);
            add.enterFax(data.get("fax"));
            TimeUnit.SECONDS.sleep(2);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500)", "");
            TimeUnit.SECONDS.sleep(1);
            add.selectPaymentOption(data.get("paymentOption"));
            TimeUnit.SECONDS.sleep(2);
            add.selectMemberShipOption(data.get("membershipOption"));
            TimeUnit.SECONDS.sleep(2);
            add.clickSubmitButton();
            TimeUnit.SECONDS.sleep(15);
            signOut.signOutBni();
        }
    }

    @Then("^Prospect is added as Member successfully$")
    public void prospectIsAddedAsMemberSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Prospect has been converted to Member successfully");
    }


}

