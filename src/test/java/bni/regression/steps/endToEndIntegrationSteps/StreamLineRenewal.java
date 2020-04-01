package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.common.email.GmailClient;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class StreamLineRenewal {
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
    private BNIConnectApplicationPortal bniConnectApplicationPortal;
    private BNIConnectApplicationPortal BniConnectApplicationPortal;
    private ReconcileApplications reconcileApplications;
    private RenewalApproval renewalApproval;
    private GmailClient gmailClient;




    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("^A member is added newly to the region and member status is active now\\. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules\\. Keep “Allow Online Renewals” as Streamline - do NOT choose, but contact Support for implementation plan$")
    public void aMemberIsAddedNewlyToTheRegionAndMemberStatusIsActiveNowOnAdminMenuSelectRegionManageRegionViewEditRegionBusinessRulesKeepAllowOnlineRenewalsAsStreamlineDoNOTChooseButContactSupportForImplementationPlan(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

        @When("^I login BNI app with Member login details then click Renew Now button in the home page and enter the below details click Proceed to payment button\\.$")
        public void iLoginBNIAppWithMemberLoginDetailsThenClickRenewNowButtonInTheHomePageAndEnterTheBelowDetailsClickProceedToPaymentButton(DataTable paymentGateway) throws Exception {
                Integer i = 2;
        for (Map<String, String> data : paymentGateway.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
                driver = launchBrowser.getDriver();
         /*
  login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(5);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Admin,Country");
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
            editBusinessRules.selectAllowOverride(data.get("allowOverride"));
            editBusinessRules.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();
//Edit Region BR
        /*    driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(5);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Admin,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transSubMenu2 = readWriteExcel.getCellData("translation", colNumber, 7);
            bniConnect.selectItemFromSubListMenu(transSubMenu2);
            TimeUnit.SECONDS.sleep(5);
            viewRegionBusinessRules = new ViewRegionBusinessRules(driver);
            viewRegionBusinessRules.clickEditRulesButton();
            TimeUnit.SECONDS.sleep(5);
            editBusinessRules = new EditBusinessRules(driver);
            editBusinessRules.selectAllowOnlineRenewals(data.get("allowRegionOnlineRenewals"));
            TimeUnit.SECONDS.sleep(1);
           // editBusinessRules.selectAllowOverride(data.get("allowOverride"));
            editBusinessRules.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();



           driver = launchBrowser.getDriver();


          */
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
           TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
           bniConnect = new BNIConnect(driver);
            bniConnect.clickRenewNowLink();
            Alert alert = driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
//            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//            driver.switchTo().window(tabs.get(1));
            TimeUnit.SECONDS.sleep(10);
            BniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
            TimeUnit.SECONDS.sleep(8);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(13);


            BniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(3);
            BniConnectApplicationPortal.clickDropdown();
            TimeUnit.SECONDS.sleep(3);
            BniConnectApplicationPortal.selectMembershipTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(18);
            BniConnectApplicationPortal.clickCompanyPaidMembership();
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.enterPayerName(data.get("payerName"));
            TimeUnit.SECONDS.sleep(1);

           // BniConnectApplicationPortal.clickCOCButton();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.clickMembershipTermNextButton();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.enterExperienceProfessionalClassification(data.get("professionalExp"));
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.enterLengthProfessionalClassification(data.get("lengthProfExp"));
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.selectLicenceStatus();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js3 = (JavascriptExecutor) driver;
            js3.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.enterBackground(data.get("background"));
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(5);
            BniConnectApplicationPortal.enterCommitment();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.enterSubstitute();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js4 = (JavascriptExecutor) driver;
            js4.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.enterAlreadyMember();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.clickMembershipTermNextButton();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js5 = (JavascriptExecutor) driver;
            js5.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.clickAcceptCheckBox();
            TimeUnit.SECONDS.sleep(3);

            BniConnectApplicationPortal.clickReviewApplicationButton();
            TimeUnit.SECONDS.sleep(12);
            System.out.println(("Review button clicked"));

            BniConnectApplicationPortal.clickEditGB();
            TimeUnit.SECONDS.sleep(5);
            BniConnectApplicationPortal.editPersonalInfo("Test");

            TimeUnit.SECONDS.sleep(5);
            JavascriptExecutor js7 = (JavascriptExecutor) driver;
            js7.executeScript("window.scrollBy(0, 250)", "");

            BniConnectApplicationPortal.clickReviewApplicationButton();
            JavascriptExecutor js6 = (JavascriptExecutor) driver;
            js6.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(8);
            BniConnectApplicationPortal.clickSubmitApplicationbutton();
            System.out.println(("submit button clicked"));
            TimeUnit.SECONDS.sleep(17);


            BniConnectApplicationPortal.clickDownloadApplicationButton();
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NewMemberApplication");
            TimeUnit.SECONDS.sleep(12);
            System.out.println(("Download button clicked"));


            BniConnectApplicationPortal.clickSignOutButton();
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
           readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String visitorEmailId = readWriteExcel.getCellData("addBrandNewVisitor",0,1);
            System.out.println("EmailID" +visitorEmailId);
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
            System.out.println("EmailID user name is" +emailIdUserName);
            gmailClient.checkEmail(emailIdUserName, "Renewal Approved…Leadership Team" , visitorEmailId, "applicant", 1);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String url= readWriteExcel.getCellData( "applicant", 0,0);
            System.out.println("URL is " +url);
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");


            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));

            TimeUnit.SECONDS.sleep(12);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String language31[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber3 = Integer.parseInt(language31[1]);
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber3, 3);
           String transSubMenu = readWriteExcel.getCellData("translation", colNumber3, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
           readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            reconcileApplications.enterSearchText(data.get("lastName"));
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "StreamLineRenewal");
            reconcileApplications.clickAppStatusLink();
             renewalApproval = new RenewalApproval(driver);
             renewalApproval.clickAgreeCheckBox();
             renewalApproval.clickApproveButton();
            TimeUnit.SECONDS.sleep(2);
            renewalApproval.clickPDFImage();
            TimeUnit.SECONDS.sleep(2);
            driver.close();


            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            bniConnect = new BNIConnect(driver);
            bniConnect.clickRenewNowLink();
           BniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
           BniConnectApplicationPortal.clickPaynow();

            TimeUnit.SECONDS.sleep(1);
           BniConnectApplicationPortal.selectPaymentMethod2(data.get("paymentMethod"));
            TimeUnit.SECONDS.sleep(2);
BniConnectApplicationPortal.clickRenewalApplicationSubmitButton();
            TimeUnit.SECONDS.sleep(2);

        }
    }

   @Then  ("Logged out from BNI")
   public void loggedOutFromBNI() throws Exception {
       System.out.println("Stream line renewal script executed.");
   }

}