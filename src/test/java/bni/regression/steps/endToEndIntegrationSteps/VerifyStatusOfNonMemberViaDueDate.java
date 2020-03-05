package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
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

public class VerifyStatusOfNonMemberViaDueDate {

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
    private ViewRegionBusinessRules viewRegionBusinessRules;
    private EditBusinessRules editBusinessRules;
    private EditProfile editProfile;
    private AmendDueDate amendDueDate;


    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("I navigate to Enter New Application page")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I Enter EmailId of the active member and click Search button and click hyperlink under type. On Edit profile page,Select Membership Details tab and check amend due date")
    public void step_2(DataTable verifyDueDateStatus) throws Exception {
        Integer i = 2;
        Integer excelRow = 1;
        for (Map<String, String> data : verifyDueDateStatus.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Operations,Chapter");
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 5);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String emailId = readWriteExcel.getCellData("amendStatusOfNonMemberViaDueDate", 0, excelRow);
            bniConnect.enterEmailId(emailId);
            bniConnect.clickSearchButton();
            TimeUnit.SECONDS.sleep(5);
            bniConnect.clickTypeLink("Membership");
            TimeUnit.SECONDS.sleep(12);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            editProfile = new EditProfile(driver);
            editProfile.clickMemberShipDetailsButton();
            TimeUnit.SECONDS.sleep(2);
            editProfile.checkLateStatus(data.get("status"));
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "verifyStatusOfNonMemberViaDueDate");
            signOut.signOutBni();
            i++;
        }
    }

    @Then("Signout BNI")
    public void step_3() throws Exception {
        System.out.println("verify due date for a an active member script executed.");
    }
}