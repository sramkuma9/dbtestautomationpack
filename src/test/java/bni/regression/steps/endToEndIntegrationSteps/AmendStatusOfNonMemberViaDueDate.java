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

public class AmendStatusOfNonMemberViaDueDate {

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
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        boolean setFlag = readWriteExcel.deleteRow("src/test/resources/inputFiles/testInput.xlsx", "amendStatusOfNonMemberViaDueDate", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("I navigate to Enter New Application page using below login details")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I Enter EmailId of the active member and click Search button, click hyperlink under type. On Edit profile page,Select Membership Details tab and click Amend Due Date")
    public void step_2(DataTable amendDueDateStatus) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : amendDueDateStatus.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("Admin,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 7);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(5);
            viewRegionBusinessRules = new ViewRegionBusinessRules(driver);
            viewRegionBusinessRules.clickEditRulesButton();
            TimeUnit.SECONDS.sleep(5);
            editBusinessRules = new EditBusinessRules(driver);
            editBusinessRules.enterLateBrDays(data.get("lateBr"));
            TimeUnit.SECONDS.sleep(1);
            editBusinessRules.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Operations,Chapter");
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
            transSubMenu = readWriteExcel.getCellData("translation", colNumber, 5);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            boolean setEventFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "amendStatusOfNonMemberViaDueDate", 0, i, data.get("emailId"));
            bniConnect.enterEmailId(data.get("emailId"));
            bniConnect.clickSearchButton();
            TimeUnit.SECONDS.sleep(5);
            bniConnect.clickTypeLink("Membership");
            TimeUnit.SECONDS.sleep(12);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            editProfile = new EditProfile(driver);
            editProfile.clickMemberShipDetailsButton();
            TimeUnit.SECONDS.sleep(2);
            editProfile.clickAmendDueDateButton();
            TimeUnit.SECONDS.sleep(6);
            amendDueDate = new AmendDueDate(driver);
            amendDueDate.clickNewDueDateField();
            TimeUnit.SECONDS.sleep(1);
            amendDueDate.selectNewDueDateYear(data.get("amendDueYear"));
            TimeUnit.SECONDS.sleep(2);
            amendDueDate.selectNewDueDateMonth(data.get("amendDueMonth"));
            TimeUnit.SECONDS.sleep(2);
            amendDueDate.selectDateFromDatePicker(data.get("amendDueDay"));
            TimeUnit.SECONDS.sleep(2);
            amendDueDate.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();
            i++;
        }
    }

    @Then("signout")
    public void step_3() throws Exception {
        System.out.println("amend due date for a an active member script executed.");
    }
}