package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.CountryManageRoles;
import bni.regression.pageFactory.NationalAdmin;
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

public class AssignRolesAtCountryLevel {

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
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private CountryManageRoles countryManageRoles;
    private NationalAdmin nationalAdmin;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("Iam in the BNI home page, and click Admin, Country")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I click Manage Roles and click view/allocate roles, and select Assign Role image under options against National Admin. Click Add person to role and enter EmailID, select user and click Assign Role button. Click save button")
    public void step_2(DataTable countryRoles) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : countryRoles.asMaps(String.class, String.class)) {
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
            bniConnect.navigateMenu("Admin,Country");
            TimeUnit.SECONDS.sleep(8);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 13);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 14);
            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(8);
            countryManageRoles = new CountryManageRoles(driver);
            countryManageRoles.enterSearchString(data.get("role"));
            TimeUnit.SECONDS.sleep(2);
            countryManageRoles.clickAssignRolesButton();
            TimeUnit.SECONDS.sleep(8);
            nationalAdmin = new NationalAdmin(driver);
            nationalAdmin.clickAddPersonToRoleButton();
            TimeUnit.SECONDS.sleep(3);
            nationalAdmin.enterEmailId(data.get("emailId"));
            TimeUnit.SECONDS.sleep(1);
            nationalAdmin.clickSearchButton();
            TimeUnit.SECONDS.sleep(3);
            nationalAdmin.clickAssignRoleButton();
            TimeUnit.SECONDS.sleep(3);
            nationalAdmin.clickSaveButton();
            TimeUnit.SECONDS.sleep(10);
            countryManageRoles = new CountryManageRoles(driver);
            countryManageRoles.enterSearchString(data.get("role"));
            TimeUnit.SECONDS.sleep(2);
            countryManageRoles.clickAssignRolesButton();
            TimeUnit.SECONDS.sleep(8);
            nationalAdmin = new NationalAdmin(driver);
            String result = nationalAdmin.checkRoleAssigned(data.get("firstName") + " " + data.get("lastName"));
            System.out.println(result);
            TimeUnit.SECONDS.sleep(2);
            // add email verification code
            signOut.signOutBni();
            i++;
        }
    }

    @Then("Role is assigned and confirmation email is received")
    public void step_3() {
        System.out.println("National level role assigned script executed.");
    }

}