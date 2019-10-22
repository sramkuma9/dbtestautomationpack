package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.AddAVisitor;
import bni.regression.pageFactory.BNIConnect;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
            String transMenu = readWriteExcel.getCellData("translation",colNumber,1);
            System.out.println(transMenu);
            bniConnect.selectItemFromSubListMenu(transMenu);
            addAVisitor = new AddAVisitor(driver);
            TimeUnit.SECONDS.sleep(15);
            addAVisitor.enterEmail(data.get("emailId"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickSearchButton();
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickAddButton();
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectChapter(data.get("chapter"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectSpeciality(data.get("speciality"));
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
            TimeUnit.SECONDS.sleep(15);
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