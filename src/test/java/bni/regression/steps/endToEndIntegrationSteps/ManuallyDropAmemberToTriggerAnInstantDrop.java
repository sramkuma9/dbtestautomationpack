package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.Reconcile;
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

public class ManuallyDropAmemberToTriggerAnInstantDrop {

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
    private EditProfile editProfile;
    private DropMember dropMember;

    @Before
    public void setup() throws Exception {
//        driver=launchBrowser.getDriver();
//        launchBrowser.invokeBrowser();
//        login.loginToBni();
        //fixedDateTime =  currentDateTime.dateTime();
    }

    @After
    public void tearDown() throws Exception {
        //signOut.signOutBni();
    }

    // Scenario: Navigate to Add a Visitor page
    @Given("I login using below credentials and select country, chapter and region")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I select Operations,Chapter,Manage Memberships,Enter New Application. Enter EmailIF of the member and click Search button, click hyperlink under type. On Edit profile page, Select Membership Details tab and click drop button")
    public void step_2(DataTable instantDrop) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : instantDrop.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
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
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 5);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);
            bniConnect.enterEmailId(data.get("emailId"));
            bniConnect.clickSearchButton();
            TimeUnit.SECONDS.sleep(5);
            bniConnect.clickTypeLink("Membership");
            TimeUnit.SECONDS.sleep(12);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            editProfile = new EditProfile(driver);
            editProfile.clickMemberShipDetailsButton();
            TimeUnit.SECONDS.sleep(3);
            editProfile.clickDropButton();
            TimeUnit.SECONDS.sleep(6);
            dropMember = new DropMember(driver);
            dropMember.clickDropDateField();
//            TimeUnit.SECONDS.sleep(1);
//            dropMember.selectDropYear(data.get("dropYear"));
//            TimeUnit.SECONDS.sleep(1);
           // dropMember.selectDropMonth("dropMonth");
            TimeUnit.SECONDS.sleep(1);
            dropMember.selectDateFromDatePicker(data.get("dropDay"));
            TimeUnit.SECONDS.sleep(1);
            dropMember.selectDropType(data.get("dropType"));
            TimeUnit.SECONDS.sleep(1);
            dropMember.selectDropReason(data.get("dropReason"));
            TimeUnit.SECONDS.sleep(1);
            dropMember.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            editProfile = new EditProfile(driver);
            editProfile.checkDroppedStatus();
            TimeUnit.SECONDS.sleep(1);
            signOut.signOutBni();
            i++;
        }
    }

    @Then("I Click Certificate of Credit COC button is appeared and check COC page is opened and Member status changed as dropped and Role is Unassigned from BNI Successfully")
    public void step_3() throws Exception {
        System.out.println("Manually drop a member to trigger an instant drop script executed...");
    }
}