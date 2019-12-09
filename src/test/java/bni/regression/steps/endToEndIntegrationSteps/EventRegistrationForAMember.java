package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.RegisterForEvent;
import bni.regression.pageFactory.ViewEventDetails;
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

public class EventRegistrationForAMember {

    public static WebDriver driver;
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private ViewEventDetails viewEventDetails;
    private RegisterForEvent registerForEvent;
    private CurrentDateTime currentDateTime = new CurrentDateTime();

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("BNI Home page entered with below login details")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("Click My Network link in the home page, under Events select an event, click Register Me. On Register event page enter payment option click submit button")
    public void step_2(DataTable eventRegistration) throws Exception {
        Integer i = 2;
        Integer excelRow = 1;
        String eventName;
        for (Map<String, String> data : eventRegistration.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            // selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 12);
            bniConnect.selectItemFromLeftSideMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(5);
            if(data.get("eventName").equals("TestAutomation")) {
                readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
                eventName = readWriteExcel.getCellData("createRegionalEvent", 0, excelRow);
            }else{
                eventName = data.get("eventName");
            }
            bniConnect.selectItemFromEventLists(eventName);
            TimeUnit.SECONDS.sleep(6);
            viewEventDetails = new ViewEventDetails(driver);
            viewEventDetails.clickRegisterButton();
            TimeUnit.SECONDS.sleep(6);
            registerForEvent = new RegisterForEvent(driver);
            registerForEvent.selectRole(data.get("role"));
            TimeUnit.SECONDS.sleep(1);
            registerForEvent.selectPaymentOption(data.get("paymentOption"));
            TimeUnit.SECONDS.sleep(1);
            registerForEvent.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();
            //add email verification code.
        }
    }

    @Then("Verify Email is received in registered Email account")
    public void step_3() {
        System.out.println("Create National Events script executed.");
    }

}