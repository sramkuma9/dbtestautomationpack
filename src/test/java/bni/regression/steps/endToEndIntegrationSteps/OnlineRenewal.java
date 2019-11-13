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

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OnlineRenewal {

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
    private TermsOfUse termsOfUse;
    private MemberRenewalApplication memberRenewalApplication;
    private MemberRenewalApplicationPaymentProcessing memberRenewalApplicationPaymentProcessing;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given(" A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Post-Approval only")
    public void  A_member_is_added_newly_to_the_region_and_member_status_is_active_now_On_Admin_menuy(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I login BNI app with Member login details and accept TOS, check latest TOS version is displayed,then click Renew Now button in the home page and enter the below details click Proceed to payment button. Enter card details and Proceed to payment")
    public void step_2(DataTable onlineRenewal) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : onlineRenewal.asMaps(String.class, String.class)) {
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
            editBusinessRules.selectAllowOnlineRenewals(data.get("allowOnlineRenewals"));
            TimeUnit.SECONDS.sleep(1);
            editBusinessRules.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            termsOfUse.checkLastUpdatedDate();
            TimeUnit.SECONDS.sleep(1);
            termsOfUse.clickCheckBox();
            TimeUnit.SECONDS.sleep(2);
            termsOfUse.clickAcceptButton();
            TimeUnit.SECONDS.sleep(12);
            bniConnect = new BNIConnect(driver);
            bniConnect.clickRenewNowLink();
            TimeUnit.SECONDS.sleep(5);
            memberRenewalApplication = new MemberRenewalApplication(driver);
            memberRenewalApplication.clickMembershipPeriodCheckBox();
            memberRenewalApplication.clickNetworkingOrgCheckBox();
            memberRenewalApplication.clickInvitePeopleCheckBox();
            memberRenewalApplication.clickReferralCheckBox();
            memberRenewalApplication.clickConvictedCheckBox();
            memberRenewalApplication.clickleadershipTeamCheckBox();
            memberRenewalApplication.clickRecommendCheckBox();
            memberRenewalApplication.clickrefferalChapterCheckBox();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplication.enterDescribeBNI();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplication.clickTermsAndConditionsCheckBox();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplication.clickProceedToPayment();
            TimeUnit.SECONDS.sleep(5);
            memberRenewalApplicationPaymentProcessing = new MemberRenewalApplicationPaymentProcessing(driver);
            memberRenewalApplicationPaymentProcessing.enterPayerName(data.get("payerName"));
            memberRenewalApplicationPaymentProcessing.selectPayMethod();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationPaymentProcessing.clickSubmitButton();
            TimeUnit.SECONDS.sleep(3);
            memberRenewalApplicationPaymentProcessing.checkConfirmationMessage();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationPaymentProcessing.clickOkButton();
            TimeUnit.SECONDS.sleep(8);
            signOut.signOutBni();
        }
    }

    @Then("A confirmation message is displayed and I sign out from BNI")
    public void A_confirmation_message_is_displayed_and_I_sign_out_from_BNI() throws Exception {
        System.out.println("online renewal script executed.");
    }
}