package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.EnterChapterPalms;
import bni.regression.pageFactory.ReviewVisitor;
import bni.regression.pageFactory.ViewChapterPalmsSummary;
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

public class EnterPalmsAndSubmitPalms {

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
    private ViewChapterPalmsSummary viewChapterPalmsSummary;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("Given I enter the application with below details, Operations, Chapter, Meeting Management, Enter PALMS and click Continue without Approving visitors button. In Enter Chapter PALMS page, select Enter Meeting Date and click Enter PALMS button")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I Click “Submit PALMS” button at the bottom of the page, click ok on the message box. Navigate to Operations,Chapter,Meeting Management,View PALMS Summary, enter the meeting date given above and click view Reports")
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
            enterChapterPalms.clickSubmitPalms();
            TimeUnit.SECONDS.sleep(2);
            Alert alert = driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
            viewChapterPalmsSummary.clickEnterFromDateTextBox();
            TimeUnit.SECONDS.sleep(2);
            viewChapterPalmsSummary.selectVisitYear(data.get("meetingYear"));
            TimeUnit.SECONDS.sleep(2);
            viewChapterPalmsSummary.selectVisitMonth(data.get("meetingMonth"));
            TimeUnit.SECONDS.sleep(2);
            viewChapterPalmsSummary.selectDateFromDatePicker(data.get("meetingDay"));
            TimeUnit.SECONDS.sleep(2);
            viewChapterPalmsSummary.clickViewReportsButton();
            TimeUnit.SECONDS.sleep(6);
            viewChapterPalmsSummary.enterSearchCriteria("Completed");
            TimeUnit.SECONDS.sleep(2);
            viewChapterPalmsSummary.checkStatusLink("Completed");
            TimeUnit.SECONDS.sleep(2);
            signOut.signOutBni();
        }
    }

    @Then("Verify PALMS is entered in Completed Status successfully")
    public void step_3() throws Exception {
        System.out.println("Enter PALMS and submit palms script executed.");
    }
}