package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.Reconcile;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.Add;
import bni.regression.pageFactory.AddAVisitor;
import bni.regression.pageFactory.BNIConnect;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static bni.regression.steps.endToEndIntegrationSteps.AddAndSearchBrandNewVisitor.visitorDateTime;

public class AddVisitorForExistingIndividual {

    public static WebDriver driver;
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private AddAVisitor addAVisitor;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    private Add add;
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private Reconcile reconcile = new Reconcile();

    @Before
    public void setup() throws Exception {
//        driver=launchBrowser.getDriver();
//        launchBrowser.invokeBrowser();
//        login.loginToBni();
        //fixedDateTime =  currentDateTime.dateTime();
    }

    @After
    public void tearDown() throws Exception{
        //signOut.signOutBni();
    }

    // Scenario: Navigate to Add a Visitor page
    @Given("user logs into BNI and navigates to home page using the below data")
    public void user_logs_into_BNI_and_navigates_to_home_page_using_the_below_data(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I enter a valid existing email id and click search and Add button and I enter the below details and click the save button")
    public void I_enter_a_valid_existing_email_id_and_click_search_and_Add_button_and_I_enter_the_below_details_and_click_the_save_button(DataTable addPVVisitor) throws Exception{
        Integer i = 2;
        for (Map<String, String> data : addPVVisitor.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ",""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("Operations,Chapter");
            TimeUnit.SECONDS.sleep(2);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation",colNumber,3);
            String transSubMenu = readWriteExcel.getCellData("translation",colNumber,5);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);
            bniConnect.enterEmailId(data.get("emailId"));
            bniConnect.clickSearchButton();
            TimeUnit.SECONDS.sleep(5);
            bniConnect.clickAddButton();
            try {
                Alert alert = driver.switchTo().alert();
                alert.dismiss();
            }catch(Exception e) {
                System.out.println("CofC popup is not displayed");
            }
            TimeUnit.SECONDS.sleep(8);
            String dateTimeStamp = currentDateTime.dateTime();
            visitorDateTime = (dateTimeStamp.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
            String lastName = data.get("lastName") + visitorDateTime;
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            boolean setLastNameFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "addVisitorForExistingIndividual", 0, i, lastName);
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
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(2);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String MainMenu = readWriteExcel.getCellData("translation",colNumber,3);
            String SubMenu = readWriteExcel.getCellData("translation",colNumber,6);
            bniConnect.selectItemFromMainListMenu(MainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(SubMenu);
            TimeUnit.SECONDS.sleep(6);
            System.out.println(lastName);
            reconcile.reconcileApp(data.get("firstName"),lastName);
            i++;
            signOut.signOutBni();
        }
    }

    @Then("visitor details saved successfully")
    public void visitor_details_saved_successfully() throws Exception{
        System.out.println("Visitor details added sucessfully.");
    }

    @And("I successfully sign out from BNI")
    public void I_successfully_sign_out_from_BNI() throws Exception{
        TimeUnit.SECONDS.sleep(2);
       // signOut.signOutBni();
    }
}