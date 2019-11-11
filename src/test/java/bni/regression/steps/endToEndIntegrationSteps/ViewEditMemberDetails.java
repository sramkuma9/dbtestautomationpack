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
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ViewEditMemberDetails {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public List<List<String>> loginSubList;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private ManageMembers manageMembers;
    private EditProfile editProfile;
    private Edit edit;
    public static String fixedDateTime;
    private CaptureScreenShot captureScreenShot;

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        //readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "addVisitor", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("I’m in the home page, Operations -> Region, select Manage Memberships ->Manage Members")
    public void I_m_in_the_home_page_Operations_Region_select_Manage_Memberships_Manage_Members(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I enter first name and last name click the Search Members button. Click Edit member button, on user profile tab, select language as Japanese and In Main profile tab,  edit company name in Japanese language and click update button")
    public void step_2(DataTable viewEditMember) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : viewEditMember.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 4);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(5);
            manageMembers = new ManageMembers(driver);
            manageMembers.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(2);
            manageMembers.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            manageMembers.clickSearchMembers();
            TimeUnit.SECONDS.sleep(5);
            manageMembers.clickEditMember();
            TimeUnit.SECONDS.sleep(8);
            editProfile = new EditProfile(driver);
            editProfile.clickUserProfile();
            TimeUnit.SECONDS.sleep(3);
            editProfile.selectLanguage(data.get("language"));
            TimeUnit.SECONDS.sleep(1);
            editProfile.clickLanguageUpdateButton();
            TimeUnit.SECONDS.sleep(3);
            editProfile.clickMainProfile();
            TimeUnit.SECONDS.sleep(2);
            editProfile.clickEditButton();
            TimeUnit.SECONDS.sleep(3);
            edit = new Edit(driver);
            edit.enterNewCompanyName(data.get("companyName"));
            TimeUnit.SECONDS.sleep(1);
            edit.enterReasonForChange();
            TimeUnit.SECONDS.sleep(1);
            edit.clickSubmitButton();
            TimeUnit.SECONDS.sleep(10);
            signOut.signOutBni();
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(5);
            manageMembers = new ManageMembers(driver);
            manageMembers.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(2);
            manageMembers.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            manageMembers.clickSearchMembers();
            TimeUnit.SECONDS.sleep(5);
            manageMembers.checkCompanyNameAndStatus(data.get("companyName"),data.get("status"));
            signOut.signOutBni();
        }
    }

    @Then("Logout and login again to check edited details and status are updated")
    public void Logout_and_login_again_to_check_edited_details_and_status__are_updated() throws Exception {
        System.out.println("view edit member script executed.");
    }
}