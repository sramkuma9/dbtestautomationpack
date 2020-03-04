package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.AddAVisitor;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.RegisterAProspectiveVisitor;
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

public class RegisterProspectiveVisitor {

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
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
      //  boolean setFlag = readWriteExcel.deleteRow("src/test/resources/inputFiles/testInput.xlsx", "registerAProspectiveVisitor", 0);
       // boolean setFlag1 = readWriteExcel.deleteRow("src/test/resources/inputFiles/testInput.xlsx", "registerAProspectiveVisitor", 1);
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("^User login details to navigate into BNI Home page$")

   // @Given("User navigates to BNI homepage using the below login credentials")
    public void User_login_details_to_navigate_into_BNI_Home_page(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I enter the below details and click the submit button to register a Prospective visitor$")
    public void When_I_enter_a_valid_existing_email_id_and_click_search_and_Add_button_and_I_enter_the_below_details_and_click_the_save_button_and_search_the_added_visitor(DataTable addPVVisitor) throws Exception {
        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : addPVVisitor.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);

            bniConnect.navigateMenu("Operations,Chapter");
            TimeUnit.SECONDS.sleep(25);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(15);
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 17);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 18);
            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(15);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(10);
            RegisterAProspectiveVisitor registerAProspectiveVisitor = new RegisterAProspectiveVisitor(driver);
            TimeUnit.SECONDS.sleep(10);
             // To click Register a Prospective Visitor
           registerAProspectiveVisitor.clickProfession();
            TimeUnit.SECONDS.sleep(1);
            registerAProspectiveVisitor.selectProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(5);
            registerAProspectiveVisitor.clickSpeciality();
            TimeUnit.SECONDS.sleep(1);
            registerAProspectiveVisitor.selectSpeciality(data.get("specialty"));
            TimeUnit.SECONDS.sleep(15);
              if (!(data.get("visitYear")).equals("")) {
                registerAProspectiveVisitor.clickVisitDate();
                TimeUnit.SECONDS.sleep(1);
                registerAProspectiveVisitor.selectYear(data.get("visitYear"));
                TimeUnit.SECONDS.sleep(2);
                registerAProspectiveVisitor.selectMonth(data.get("visitMonth"));
                TimeUnit.SECONDS.sleep(2);
                registerAProspectiveVisitor.selectDateFromDatePicker(data.get("visitDate"));
                TimeUnit.SECONDS.sleep(2);
            }

            registerAProspectiveVisitor.selectTitle(data.get("title"));
            TimeUnit.SECONDS.sleep(1);
            registerAProspectiveVisitor.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(1);
            registerAProspectiveVisitor.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(1);
            registerAProspectiveVisitor.enterSuffix(data.get("suffix"));
            TimeUnit.SECONDS.sleep(1);
            registerAProspectiveVisitor.enterCompanyName(data.get("companyName"));
            TimeUnit.SECONDS.sleep(5);

            registerAProspectiveVisitor.enterPhone(data.get("phone"));
            TimeUnit.SECONDS.sleep(1);

            String dateTimeStamp = currentDateTime.dateTime();
            visitorDateTime = (dateTimeStamp.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
            String lastName = data.get("lastName") + visitorDateTime;
            registerAProspectiveVisitor.enterEmailAddress(data.get("firstName") + lastName + "@gmail.com");
            TimeUnit.SECONDS.sleep(20);
            registerAProspectiveVisitor.clickSubmitButton();
            TimeUnit.SECONDS.sleep(30);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            boolean setEmailFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "registerAProspectiveVisitor", 0, i, data.get("firstName") + data.get("lastName") + visitorDateTime + "@gmail.com");
            boolean setLastNameFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "registerAProspectiveVisitor", 1, i-j, lastName);
            TimeUnit.SECONDS.sleep(2);

            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "RegisterAProspectiveVisitor");



            i++;
            signOut.signOutBni();
        }
    }

    @Then("Prospective visitor details saved successfully")
    public void Prospective_visitor_details_saved_successfully() throws Exception {
        System.out.println("Prospective visitor details saved successfully");
    }



}
