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
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AssignRolesAtRegionLevel {

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
    private RegionWideAccess regionWideAccess;
    private RegionManageRoles regionManageRoles;


    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("Iam in the BNI home page, and click Admin, Region")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I click Manage Roles and click view/allocate roles, and against Region wide access, click Assign role image under options. Click Add person to role and enter Email ID, select user and click Assign. Click save button")
    public void step_2(DataTable regionRoles) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : regionRoles.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("Admin,Region");
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
            regionManageRoles = new RegionManageRoles(driver);
            regionManageRoles.enterSearchString(data.get("role"));
            TimeUnit.SECONDS.sleep(2);
            regionManageRoles.clickAssignRolesButton();
            TimeUnit.SECONDS.sleep(8);
            regionWideAccess = new RegionWideAccess(driver);
            regionWideAccess.clickAddPersonToRoleButton();
            TimeUnit.SECONDS.sleep(3);
            regionWideAccess.enterEmailId(data.get("emailId"));
            TimeUnit.SECONDS.sleep(1);
            regionWideAccess.clickSearchButton();
            TimeUnit.SECONDS.sleep(3);
            regionWideAccess.clickAssignRoleButton();
            TimeUnit.SECONDS.sleep(3);
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (ElementNotVisibleException e) {
                System.out.println("warning alert not found...");
            }


            regionWideAccess.clickSaveButton();
            TimeUnit.SECONDS.sleep(10);
            regionManageRoles = new RegionManageRoles(driver);
            regionManageRoles.enterSearchString(data.get("role"));
            TimeUnit.SECONDS.sleep(2);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "assignRolesAtRegionalLevel");
            regionManageRoles.clickAssignRolesButton();
            TimeUnit.SECONDS.sleep(8);
            regionWideAccess = new RegionWideAccess(driver);
            String result = regionWideAccess.checkRoleAssigned(data.get("firstName") + " " + data.get("lastName"));
            System.out.println(result);
            TimeUnit.SECONDS.sleep(2);
            // add email verification code
            signOut.signOutBni();
            i++;
        }
    }

    @Then("Role is assigned at region level and confirmation email is received")
    public void step_3() {
        System.out.println("Region level role assigned script executed...");
    }

}