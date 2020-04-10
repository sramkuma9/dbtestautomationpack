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
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class AddReferralSlipsViaMemberModule {


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
    private SubmitReferralSlips submitReferralSlips;
    private SubmitReferralSlipsCrossChapter submitReferralSlipsCrossChapter;
    private ReferralTrackingReport referralTrackingReport;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("^With member login details login BNI$")
    public void with_member_login_details_login_BNI(DataTable loginDetails) throws Throwable {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^The Member submit Referral slip from Member Module entering Referral type, Status and other details$")
    public void the_Member_submit_Referral_slip_from_Member_Module_entering_Referral_type_Status_and_other_details(DataTable ReferralSlips) throws Throwable {
        Integer i = 2;
        for (Map<String, String> data : ReferralSlips.asMaps(String.class, String.class)) {
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
            String transMenu = readWriteExcel.getCellData("translation", colNumber, 30);
            bniConnect.selectItemFromSubListMenu(transMenu);
            TimeUnit.SECONDS.sleep(5);
            submitReferralSlips = new SubmitReferralSlips(driver);
            String chapterMember = submitReferralSlips.selectToMember(data.get("memberName"));
            System.out.println("Chapter member is" +chapterMember);
            if (chapterMember == "")
            System.out.println("MEmber is from cross chapter");
        {
                submitReferralSlips.clickCrossChapterButton();
                submitReferralSlipsCrossChapter = new SubmitReferralSlipsCrossChapter(driver);
                submitReferralSlipsCrossChapter.enterFirstName(data.get("firstName"));
                TimeUnit.SECONDS.sleep(1);
                submitReferralSlipsCrossChapter.enterLastName(data.get("lastName"));
                TimeUnit.SECONDS.sleep(1);
                submitReferralSlipsCrossChapter.enterCompanyName(data.get("companyName"));
                TimeUnit.SECONDS.sleep(2);
                submitReferralSlipsCrossChapter.clickSearch();
                TimeUnit.SECONDS.sleep(2);
                submitReferralSlipsCrossChapter.clickRadioButton();
                Alert alert = driver.switchTo().alert();
                alert.accept();
                TimeUnit.SECONDS.sleep(10);
            }
            submitReferralSlips = new SubmitReferralSlips(driver);
            submitReferralSlips.enterReferral(data.get("referral"));
            TimeUnit.SECONDS.sleep(1);
            submitReferralSlips.enterReferralType(data.get("referralType"));
            TimeUnit.SECONDS.sleep(1);
            submitReferralSlips.selectReferralStatus(data.get("status"));
            TimeUnit.SECONDS.sleep(1);
            submitReferralSlips.enterAddress(data.get("address"));
            TimeUnit.SECONDS.sleep(1);
            submitReferralSlips.enterTelephoneNumber(data.get("number"));
            TimeUnit.SECONDS.sleep(1);
            submitReferralSlips.enterEmail(data.get("email"));
            TimeUnit.SECONDS.sleep(1);
            submitReferralSlips.clickSubmitButton();
            TimeUnit.SECONDS.sleep(2);
            signOut.signOutBni();
            //to verify referral given
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMenu1 = readWriteExcel.getCellData("translation", colNumber, 31);
            bniConnect.selectItemFromSubListMenu(transMenu1);
            TimeUnit.SECONDS.sleep(5);
            if (!(data.get("periodStartYear")).equals("")) {
                bniConnect.clickReferralStartDate();
                TimeUnit.SECONDS.sleep(2);
                bniConnect.selectYear(data.get("periodStartYear"));
                TimeUnit.SECONDS.sleep(2);
                bniConnect.selectMonth(data.get("periodStartMonth"));
                TimeUnit.SECONDS.sleep(2);
                bniConnect.selectDateFromDatePicker(data.get("PeriodStartDay"));
                TimeUnit.SECONDS.sleep(2);
            }
            if (!(data.get("periodEndYear")).equals("")) {
                bniConnect.clickReferralEndDate();
                TimeUnit.SECONDS.sleep(2);
                bniConnect.selectYear(data.get("periodEndYear"));
                TimeUnit.SECONDS.sleep(2);
                bniConnect.selectMonth(data.get("periodEndMonth"));
                TimeUnit.SECONDS.sleep(2);
                bniConnect.selectDateFromDatePicker(data.get("periodEndDay"));
                TimeUnit.SECONDS.sleep(2);
            }
            bniConnect.clickGoButton();
            referralTrackingReport = new ReferralTrackingReport(driver);
            referralTrackingReport.clickExportButton();
            String sqlQuery= "select count(*) from"+
                    "bni.member_referral_slip where to_id_user in "+
                    "(select id_user from bni.user where user_name='"+chapterMember +"');";
            System.out.println(sqlQuery);
            Integer actualTotalCount = dbConnect.queryRecordCount(sqlQuery);
            System.out.println("Actual Count from Database is" + actualTotalCount);
            Integer expectedTotalCount = 1;


//original query:
//  select * from bni.member_referral_slip where to_id_user in (select id_user from bni.user where user_name='SeleniumBni14');

            assertEquals("Referral slips count mismatched", expectedTotalCount, actualTotalCount);


            i++;
            signOut.signOutBni();





        }
    }
    @Then("^verify to get actual count on referral slips submitted$")
    public void verify_to_get_actual_count_on_referral_slips_submitted() {

    }
}

