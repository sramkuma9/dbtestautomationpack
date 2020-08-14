package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.db.DbConnect;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class AddCEUSlipsViaMemberModule {

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
    private chapterOneToOneReport ChapterOneToOneReport;
    DbConnect dbConnect = new DbConnect();
    private SubmitCEUSlips submitCEUSlips;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Given("^verify CEU details with member login details$")
    public void verifyCEUDetailsWithMemberLoginDetails(DataTable loginDetails) {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^The Member select Enter CEU slip from Member Module, to check Chapter Education Units$")
    public void theMemberSelectEnterCEUSlipFromMemberModuleToCheckChapterEducationUnits(DataTable CEUslips) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : CEUslips.asMaps(String.class, String.class)) {
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
            String transMenu = readWriteExcel.getCellData("translation", colNumber, 29);
            bniConnect.selectItemFromSubListMenu(transMenu);
            TimeUnit.SECONDS.sleep(5);
            String BNICDQty = data.get("BNICD");
            String podcastQty = data.get("podcast");
            String mspQty = data.get("msp");
            String LTTQty = data.get("LTT");
            String advTrainingQty = data.get("AdvancedBNITraining");
            String otherQty = data.get("other");
            String bookQty = data.get("book");
            submitCEUSlips = new SubmitCEUSlips(driver);
            String name = submitCEUSlips.getMemberName();
            submitCEUSlips.enterBNICDQty("");
            submitCEUSlips.enterBNICDQty(BNICDQty);
          submitCEUSlips.enterPodcastQty("");
            submitCEUSlips.enterPodcastQty(podcastQty);
            TimeUnit.SECONDS.sleep(1);
            submitCEUSlips.clickHeader();
            submitCEUSlips.enterMSPQty(mspQty);
            TimeUnit.SECONDS.sleep(1);
            submitCEUSlips.enterLTTQty(LTTQty);
            TimeUnit.SECONDS.sleep(1);
            submitCEUSlips.enterTrainingQty(advTrainingQty);
            TimeUnit.SECONDS.sleep(1);
            submitCEUSlips.enterOtherQty(otherQty);
            TimeUnit.SECONDS.sleep(1);
            submitCEUSlips.enterBookQty(bookQty);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "CEUSlips");
            TimeUnit.SECONDS.sleep(1);
            Integer expectedTotalCount = submitCEUSlips.getTotalCount();
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "AddCEUSlipsViaMemberModule");
            submitCEUSlips.clickSubmitButton();
            TimeUnit.SECONDS.sleep(3);
            TimeUnit.SECONDS.sleep(20);
            //  database verification code.
            String sqlQuery = "select total_credits_earned  from " +
                    "bni.member_ceu_slip where id_user in " +
                    "(select id_user from bni.user where Concat(first_name,last_name)= '" + name.replace(" ", "") + "');";
            System.out.println(sqlQuery);
            Integer actualTotalCount = dbConnect.queryRecordCount(sqlQuery);
            System.out.println("Actual Count from Database is" + actualTotalCount);
            assertEquals("CEU slips count mismatched", expectedTotalCount, actualTotalCount);
            i++;
            signOut.signOutBni();

        }

    }

    @Then("^verify to get actual count on Chapter Education Units earned$")
    public void verifyToGetActualCountOnChapterEducationUnitsEarned() {
        System.out.println("CEU Slips verified.");
    }


}















