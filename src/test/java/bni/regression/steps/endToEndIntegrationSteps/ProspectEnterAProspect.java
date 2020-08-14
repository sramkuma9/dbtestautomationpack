package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProspectEnterAProspect {


    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private EnterProspect enterAProspect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public static String fixedDateTime;
    public static String visitorDateTime;
    public String name;
    public String[] prospectDetails = new String[8];
    public String firstName;
    public String lastName;
    private ViewEditVisitorsList viewEditVisitorsList;
    private EnterNewApplication enterNewApplication;
    private Add add;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "enterAProspect", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("User Login into BNI navigates to home page using the below data")
    public void User_Login_into_BNI_navigates_to_home_page_using_the_below_data(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    //  When I click Manage Prospects - Enter a Prospect, enter email ID and click search button. Click Add button and then enter below details and click the submit button
    @When("^I click Manage Prospects - Enter a Prospect, enter email ID and click search button. Click Add button and then enter below details and click the submit button$")
    public void enterProspect(DataTable enterAProspectTable) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : enterAProspectTable.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(3);
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 20);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 21);
            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(15);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            enterAProspect = new EnterProspect(driver);
            TimeUnit.SECONDS.sleep(15);
            enterAProspect.enterDroppedMemberEmail(data.get("droppedMemberEmail"));
            TimeUnit.SECONDS.sleep(2);
            enterAProspect.enterSearch();
            TimeUnit.SECONDS.sleep(5);
            enterAProspect.clickSearchByName();
            TimeUnit.SECONDS.sleep(4);
            enterAProspect.enterDroppedMemberFirstName(data.get("droppedMemberFirstName"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterDroppedMemberLastName(data.get("droppedMemberLastName"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.clickSearchName();
            TimeUnit.SECONDS.sleep(5);
            enterAProspect.clickCreateNewButton();
            TimeUnit.SECONDS.sleep(10);
            enterAProspect.clickAddButton();
            TimeUnit.SECONDS.sleep(2);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 850)", "");
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.selectIndustry(data.get("prospectIndustry"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.selectClassification(data.get("classification"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.selectTitle(data.get("title"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterSuffix(data.get("suffix"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterCompanyName(data.get("companyName"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.scrollDown();
            TimeUnit.SECONDS.sleep(2);
            enterAProspect.enterAddressLine1(data.get("prospectAddressLine1"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterAddressLine2(data.get("prospectAddressLine2"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterCity(data.get("city"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterState(data.get("prospectState"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.selectCountry(data.get("prospectCountry"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterPostalCode(data.get("prospectZipCode"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterPhoneNumber(data.get("prospectPhoneNumber"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.enterFax(data.get("fax"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.setSelectContactByListBox(data.get("contactBy"));
            TimeUnit.SECONDS.sleep(1);
            enterAProspect.clickSubmit();
            TimeUnit.SECONDS.sleep(20);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "ProspectEnterAProspect");
            TimeUnit.SECONDS.sleep(5);
            i++;
            signOut.signOutBni();
        }
    }

    @Then("Prospect details saved successfully")
    public void Prospect_details_saved_successfully() throws Exception {
        System.out.println("Prospect details added successfully.");
    }

    @And("^Sign out from BNI$")
    public void Sign_out_from_BNI() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        //signOut.signOutBni();
    }
}



