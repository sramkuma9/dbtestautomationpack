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
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EnterPalms {

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
    private ReviewVisitor reviewVisitor;
    private EnterChapterPalms enterChapterPalms;
    private ViewChapterPalms viewChapterPalms;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("I enter the application with below details , Operations->Chapter->Meeting Management->Enter PALMS. I am on the Review Visitor Ant - One - Chapter A page")
    public void I_enter_the_application_with_below_details_Operations_Chapter_Meeting_Management_Enter_PALMS_I_am_on_the_Review_Visitor_Ant_One_Chapter_page(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I click continue without Approving visitors button and enter the meeting date and click Enter PALMS")
    public void step_2(DataTable enterPalms) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : enterPalms.asMaps(String.class, String.class)) {
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
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 9);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 11);
            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(5);
            reviewVisitor = new ReviewVisitor(driver);
            reviewVisitor.clickContinueWithoutApprovingVisitorsButton();
            TimeUnit.SECONDS.sleep(3);
            enterChapterPalms = new EnterChapterPalms(driver);
            enterChapterPalms.clickEnterMeetingDateTextBox();
            TimeUnit.SECONDS.sleep(1);
            enterChapterPalms.selectVisitYear(data.get("meetingYear"));
            TimeUnit.SECONDS.sleep(1);
            enterChapterPalms.selectVisitMonth(data.get("meetingMonth"));
            TimeUnit.SECONDS.sleep(1);
            enterChapterPalms.selectDateFromDatePicker(data.get("meetingDay"));
            TimeUnit.SECONDS.sleep(3);
            enterChapterPalms.clickEnterPalmsButton();
            TimeUnit.SECONDS.sleep(5);
            enterChapterPalms.enterSearchCriteria(data.get("firstName") + " " + data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            enterChapterPalms.enterMeeting();
            TimeUnit.SECONDS.sleep(2);
            enterChapterPalms.clickSubmitPalms();
            TimeUnit.SECONDS.sleep(2);
            Alert alert = driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(12);
            viewChapterPalms.checkPalmsEntry();
            TimeUnit.SECONDS.sleep(2);
            signOut.signOutBni();
        }
    }

    @Then("Manually enter 1 against 1-2-1  for Monica Daniel and  click Submit PALMS. In view chapter PALMS Summary page Verify 1 against Monica for 1-2-1 entered successfully")
    public void step_3() throws Exception {
        System.out.println("Enter palms script executed.");
    }
}