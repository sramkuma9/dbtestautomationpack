package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.ReconcileOnlineRenewals;
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

public class MembershipCostPaidViaDpsGateway {

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
    private ReconcileOnlineRenewals reconcileOnlineRenewals;
    private PaymentCheckOut paymentCheckOut;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("User logged in as newly added member login")
    public void  step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("click Renew Now button in home page and enter the below details. Click submit button")
    public void step_2(DataTable paymentGateway) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : paymentGateway.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(5);
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
            TimeUnit.SECONDS.sleep(2);
            memberRenewalApplication.clickProceedToPayment();
            TimeUnit.SECONDS.sleep(5);
            memberRenewalApplicationPaymentProcessing = new MemberRenewalApplicationPaymentProcessing(driver);
            memberRenewalApplicationPaymentProcessing.enterPayerName(data.get("payerName"));
            memberRenewalApplicationPaymentProcessing.selectCardPayMethod();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationPaymentProcessing.clickSubmitButton();
            TimeUnit.SECONDS.sleep(3);
            paymentCheckOut = new PaymentCheckOut(driver);
            paymentCheckOut.enterCardNumber(data.get("cardNumber"));
            TimeUnit.SECONDS.sleep(1);
            paymentCheckOut.enterNameOnCard(data.get("nameOnCard"));
            TimeUnit.SECONDS.sleep(1);
            paymentCheckOut.selectExpiryDateMonth(data.get("expiryMonth"));
            TimeUnit.SECONDS.sleep(1);
            paymentCheckOut.selectExpiryDateYear(data.get("expiryYear"));
            TimeUnit.SECONDS.sleep(1);
            paymentCheckOut.enterCvc(data.get("cvc"));
            TimeUnit.SECONDS.sleep(1);
            paymentCheckOut.clickSubmitButton();
            TimeUnit.SECONDS.sleep(8);
            paymentCheckOut.checkTransactionStatus();
            TimeUnit.SECONDS.sleep(1);
            driver.close();
        }
    }

    @Then("Member renewed successfully for the appropriate period")
    public void step_3() throws Exception {
        System.out.println("Payment Gateway script executed.");
    }
}