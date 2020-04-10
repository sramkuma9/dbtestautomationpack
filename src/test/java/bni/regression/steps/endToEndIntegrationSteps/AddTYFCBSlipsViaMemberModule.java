package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.EnterOneToOnes;
import bni.regression.pageFactory.SubmitTYFCBSlips;
import bni.regression.pageFactory.chapterOneToOneReport;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;


public class AddTYFCBSlipsViaMemberModule {

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
    private SubmitTYFCBSlips submitTYFCBSlips;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("^Login as a member with below details in AddTYFCBSlipsViaMemberModule$")
    public void loginAsAMemberWithBelowDetailsInAddTYFCBSlipsViaMemberModule(DataTable loginDetails)throws Exception {

        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^the member select Enter TYFCB slip from Member Module, Submit the below details$")
    public void theMemberSelectEnterTYFCBSlipFromMemberModuleSubmitTheBelowDetails(DataTable tyfcbslips) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : tyfcbslips.asMaps(String.class, String.class)) {
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
            String transMenu = readWriteExcel.getCellData("translation", colNumber, 28);
            //Test2
            //  String transMenu = readWriteExcel.getCellData("translation", colNumber, 8);
            bniConnect.selectItemFromSubListMenu(transMenu);
            TimeUnit.SECONDS.sleep(5);
            String user = data.get("userName");
            String thankYouTo = data.get("thankYouTo");
            String referralAmount = data.get("referralAmount");
            String businessType = data.get("businessType");
            String referralType = data.get("referralType");

            submitTYFCBSlips = new SubmitTYFCBSlips(driver);
            submitTYFCBSlips.selectThankYouTo(thankYouTo);
            TimeUnit.SECONDS.sleep(1);
            submitTYFCBSlips.enterReferralAmount(referralAmount);
            TimeUnit.SECONDS.sleep(1);
            submitTYFCBSlips.selectBusinessType(businessType);
            TimeUnit.SECONDS.sleep(1);
            submitTYFCBSlips.selectReferralType(referralType);
            TimeUnit.SECONDS.sleep(1);
            submitTYFCBSlips.clickSubmitButton();
            TimeUnit.SECONDS.sleep(20);


          // Add database verification code.
            //Bussiness flag set to N default for business type "New"
            String businessFlag = "N";
            if (businessType.equalsIgnoreCase("Repeat"))
            {
                businessFlag = "Y";
            }
            String referralID ="1";
            if (referralType.equalsIgnoreCase("2 Tier"))
            {
                referralID="2";
            }else
            {
                referralID="3";
            }



            //Query

           String sqlQuery=" select referral_amount from " +
                   "bni.member_tyfcb_slip where from_id_membership in " +
                   "(select  id_membership from bni.user where Concat(first_name,last_name)='"+thankYouTo.replace(" ","") +"';" ;

            //query
            //  select referral_amount from bni.member_tyfcb_slip where id_user in (select id_user from bni.user where Concat(first_name,last_name)="SeleniumBni22") ;
//select referral_amount from bni.member_tyfcb_slip where from_id_membership in (select id_membership from bni.user where Concat(first_name,last_name)="SeleniumBni+v20200124234344") ;

        /*    String sqlQuery = "select count(*)" +
                    "from bni.member_tyfcb_slip m1 " +
                    "join bni.user u1 on u1.id_user = m1.id_user " +
                    "join bni.user u2 on u2.id_user = m1.id_user " +
                    "where Concat(u1.first_name,u1.last_name)= '"+user +"' " +
                    "and m1.referral_amount='"+referralAmount+"' " +
                    "and m1.repeat_business_flag='"+businessFlag+"'" +
                    "and m1.id_referral_type='"+referralID+"'; ";

         */

            System.out.println(sqlQuery);
            Integer actCount = dbConnect.queryRecordCount(sqlQuery);
            System.out.println("Actual Count from Database is" + actCount);
            String expCount = referralAmount;
            assertEquals("TYFCB slips count mismatched", expCount, actCount);
            i++;
            signOut.signOutBni();
        }

    }

    @Then("a database entry is made , verify to get actual number of slips")
    public void step_3() {
        System.out.println("View palms summary script executed and TYFCB Slips verified.");
    }



}














