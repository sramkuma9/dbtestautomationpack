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
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ViewPalmsSummary {

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
    private EnterOneToOnes enterOneToOnes;
    private ViewChapterPalmsSummary viewChapterPalmsSummary;
    private ViewChapterPalms viewChapterPalms;
    private SlipsAuditReport slipsAuditReport;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("User logged in with member login, select Enter One to Ones from Member Module, Enter the below details and click save")
    public void user_logged_in_with_member_login_select_Enter_One_to_Ones_from_Member_Module_Enter_the_below_details_and_click_save(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I Login as admin and enter the From Date and click view reports")
    public void step_2(DataTable viewPalms) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : viewPalms.asMaps(String.class, String.class)) {
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
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMenu = readWriteExcel.getCellData("translation", colNumber, 8);
            bniConnect.selectItemFromSubListMenu(transMenu);
            TimeUnit.SECONDS.sleep(5);
            enterOneToOnes = new EnterOneToOnes(driver);
            enterOneToOnes.selectMetWith(data.get("metWith"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectInvitedBy(data.get("invitedBy"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.enterLocation("location");
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.enterTopicsOfConversation("topicsOfConversation");
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.clickApplicationDateField();
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectVisitYear(data.get("year"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectVisitMonth(data.get("month"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectDateFromDatePicker(data.get("day"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.clickSaveButton();
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (ElementNotVisibleException e) {
                System.out.println("warning alert not found...");
            }
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            selectCountryRegionChapter.selectCountryRegChap(data.get("country"), data.get("region"), data.get("chapter"));
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 9);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 10);
            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(5);
            viewChapterPalmsSummary = new ViewChapterPalmsSummary(driver);
            viewChapterPalmsSummary.clickEnterFromDateTextBox();
            TimeUnit.SECONDS.sleep(1);
            viewChapterPalmsSummary.selectVisitYear(data.get("enterFromYear"));
            TimeUnit.SECONDS.sleep(1);
            viewChapterPalmsSummary.selectVisitMonth(data.get("enterFromMonth"));
            TimeUnit.SECONDS.sleep(1);
            viewChapterPalmsSummary.selectDateFromDatePicker(data.get("enterFromDay"));
            TimeUnit.SECONDS.sleep(2);
            viewChapterPalmsSummary.clickViewReportsButton();
            TimeUnit.SECONDS.sleep(5);
            viewChapterPalmsSummary.enterSearchCriteria("searchString");
            TimeUnit.SECONDS.sleep(2);
            viewChapterPalmsSummary.clickStatusLink();
            TimeUnit.SECONDS.sleep(5);
            viewChapterPalms.clickSlipsAuditReportButton();
            TimeUnit.SECONDS.sleep(8);
            slipsAuditReport.checkSlipsAuditReport();
            TimeUnit.SECONDS.sleep(2);
            slipsAuditReport.clickCloseButton();
            TimeUnit.SECONDS.sleep(3);
            signOut.signOutBni();
        }
    }

    @Then("palms Summary report is displayed")
    public void palms_Summary_report_is_displayed() throws Exception {
        System.out.println("view palms summary script executed.");
    }
}