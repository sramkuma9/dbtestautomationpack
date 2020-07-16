package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.common.email.GmailClient;
import bni.regression.libraries.db.DbConnect;
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
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OnlineRenewalOfflinePayment {

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
    private ReconcileApplications reconcileApplications;
    private MemberRenewalApplicationTest2 memberRenewalApplicationTest2;
    private GmailClient gmailClient;
    private MemberRenewalApplicationPaymentProcessing memberRenewalApplicationPaymentProcessing;
    DbConnect dbConnect = new DbConnect();
    private Reconcile reconcile;
    private RenewalApproval renewalApproval;
    private AddPaymentToOnlineRenewal addPaymentToOnlineRenewal;

    public OnlineRenewalOfflinePayment() {
    }

    @Before
    public void setup() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Given("A member is added newly to the region and member status is active now. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules. Keep “Allow Online Renewals” as Post-Approval only")
    public void A_member_is_added_newly_to_the_region_and_member_status_is_active_now_On_Admin_menuy(DataTable loginDetails) throws Exception {
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
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(12);
            String country = data.get("country");
            System.out.println("Country is" + country);
            String region = data.get("region");
            System.out.println("Region is" + region);
            String chapter = data.get("chapter");
            bniConnect.clickRenewNowLink();
            TimeUnit.SECONDS.sleep(5);
//            bniConnect.closeDialogBox();
            TimeUnit.SECONDS.sleep(2);

            memberRenewalApplicationTest2 = new MemberRenewalApplicationTest2(driver);
            memberRenewalApplicationTest2.clickMembershipPeriodCheckBox(data.get("membershipPeriod"));
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Membership checked");

            memberRenewalApplicationTest2.getMembershipFee();
            TimeUnit.SECONDS.sleep(2);
            String memTerm = data.get("membershipPeriod");
            HashMap<String , String> memOption = new HashMap<String , String>();
            memOption.put("12 Month", "5");
            memOption.put("24 Month","6");
            memOption.put("12 Month BNI#", "2");
            memOption.put("24 Month BNI#","3");
//            List<String> option=new ArrayList<>();
//            option.add("MEMBER");
//            option.add("ALUMNI");
//            option.add("VISITOR");
//            List<String> option2=new ArrayList<>();
//            option2.add("ACTIVE");
//            option2.add("COREGROUP");
//            option2.add("SUSPENDED");

            String membershipFeeFromSqlQuery =   "select value from pricing.scheme_line  s1 " +
                    " join pricing.scheme s2 on s2.id =s1.id_scheme "+
                    " join pricing.sku s3 on s3.id =s2.id_sku " +
                    " join pricing.org s4 on s4.id =s2.id_org and  s2.id_sku='"+memOption.get(memTerm)+"'  and  s4.name='"+country+"'; "  ;



            String[][] actualMembershipFeeFromDB1 =dbConnect.queryAndRetrieveRecords(membershipFeeFromSqlQuery);
            String actualMembershipFeeFromDB= actualMembershipFeeFromDB1[0][0];

            System.out.println("Actual Membership Fee from DB is "+actualMembershipFeeFromDB);






            memberRenewalApplicationTest2.getTaxForMembershipTerm();
            TimeUnit.SECONDS.sleep(2);
            memberRenewalApplicationTest2.clickNetworkingOrgCheckBox();
            System.out.println("Networking checked");
            memberRenewalApplicationTest2.clickInvitePeopleCheckBox();
            memberRenewalApplicationTest2.clickReferralCheckBox();
            memberRenewalApplicationTest2.clickConvictedCheckBox();
            memberRenewalApplicationTest2.clickLeadershipPosition();
            memberRenewalApplicationTest2.clickRecommendCheckBox();
            memberRenewalApplicationTest2.clickrefferalChapterCheckBox();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterDescribeBNI();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickTermsAndConditionsCheckBox();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterSpeciality(data.get("speciality"));
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterProductDescribeBNI();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickProceedToPayment();
            TimeUnit.SECONDS.sleep(5);




            gmailClient = new GmailClient();
            //verify Copy of Renewal Form from Gmail account
            TimeUnit.SECONDS.sleep(20);
            String visitorEmailId = data.get("emailID");
            System.out.println("EmailID" + visitorEmailId);
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
            System.out.println("EmailID user name is" + emailIdUserName);
            String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("OnlineRenewalOfflinePaymentRenewalForm", "properties/sql.properties"));
            String subject = queryResult[0][0];
            System.out.println("Subject retrieved from sql query is " + subject);   //   String emailSubject = "Copy of Renewal Form";
            gmailClient.checkEmail(emailIdUserName, subject, visitorEmailId, "", 1);


            memberRenewalApplicationPaymentProcessing = new MemberRenewalApplicationPaymentProcessing(driver);
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationPaymentProcessing.getMembershipTermFee();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationPaymentProcessing.getTaxForMembershipTerm();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationPaymentProcessing.enterPayerName(data.get("payerName"));
            memberRenewalApplicationPaymentProcessing.selectNonCardPayMethod();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationPaymentProcessing.clickSubmitButton();
            TimeUnit.SECONDS.sleep(3);
//            Alert alert = driver.switchTo().alert();
//             alert.accept();
            memberRenewalApplicationPaymentProcessing.clickOkButton();
            TimeUnit.SECONDS.sleep(8);
            signOut.signOutBni();
            gmailClient = new GmailClient();
            //verify Renewal Approved - Member from Gmail account
            TimeUnit.SECONDS.sleep(20);
            String[][] queryResult2 = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("OnlineRenewalOfflinePaymentRenewalApproved", "properties/sql.properties"));
            String subject2 = queryResult2[0][0];
            System.out.println("Subject retrieved from sql query is " + subject2);   //   String emailSubject = "Renewal Approved - Member";
            gmailClient.checkEmail(emailIdUserName, subject, visitorEmailId, "", 1);




            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(3);
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String language3[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber3 = Integer.parseInt(language3[1]);
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber3, 3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber3, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(driver);
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.enterSearchText((data.get("firstName")), data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);

            reconcileApplications.clickAppStatusLink();
            renewalApproval = new RenewalApproval(driver);
            renewalApproval.clickAgreeCheckBox();
            renewalApproval.clickApproveButton();
            TimeUnit.SECONDS.sleep(2);
            renewalApproval.clickBackButton();
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications = new ReconcileApplications(driver);
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.enterSearchText((data.get("firstName")), data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickPaymentReceivedCheckBox();
            TimeUnit.SECONDS.sleep(5);
            Alert alert7 = driver.switchTo().alert();
            alert7.accept();
            TimeUnit.SECONDS.sleep(10);
           /*
            String name = data.get("firstName")+data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '"+name+"'  and id_membership_application is null;" ;
            String[][] invoiceAmountFromDB1 =dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceAmountFromDB= invoiceAmountFromDB1[0][0];
            System.out.println("Invoice amount from DB is "+invoiceAmountFromDB);

            */
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "OnlineRenewalOfflinePayment");
            reconcileApplications.clickRecncileButton();
            Alert alert17 = driver.switchTo().alert();
            alert17.accept();
            TimeUnit.SECONDS.sleep(5);


            signOut.signOutBni();
            i++;


        }
    }

    @Then("A confirmation message is displayed and I sign out from BNI")
    public void A_confirmation_message_is_displayed_and_I_sign_out_from_BNI() throws Exception {
        System.out.println("online renewal script executed.");
    }
}































   /*bniConnect = new BNIConnect(driver);
          bniConnect.navigateMenu("ADMIN,Region");
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
            driver = launchBrowser.getDriver();
         /*   termsOfUse = new TermsOfUse(driver);
            //termsOfUse.checkLastUpdatedDate();
            termsOfUse.clickCheckBox();
            TimeUnit.SECONDS.sleep(2);
            termsOfUse.clickAcceptButton();
            TimeUnit.SECONDS.sleep(12);
          bniConnect = new BNIConnect(driver);
            bniConnect.clickRenewNowLink();
         try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (ElementNotVisibleException e) {
                System.out.println("warning alert not found...");
            }
             */





















  /*
              memberRenewalApplication = new MemberRenewalApplication(driver);
           memberRenewalApplication.clickMembershipPeriodCheckBox();
           System.out.println("Membership checked");
            memberRenewalApplication.clickNetworkingOrgCheckBox();
            System.out.println("Networking checked");
            memberRenewalApplication.clickInvitePeopleCheckBox();
            memberRenewalApplication.clickReferralCheckBox();
            memberRenewalApplication.clickConvictedCheckBox();
           memberRenewalApplication.clickleadershipTeamCheckBox();
          //  memberRenewalApplication.clickRecommendCheckBox();
          //  memberRenewalApplication.clickrefferalChapterCheckBox();
            TimeUnit.SECONDS.sleep(1);
          //  memberRenewalApplication.enterDescribeBNI();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplication.clickTermsAndConditionsCheckBox();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplication.clickProceedToPayment();
            TimeUnit.SECONDS.sleep(5);
            memberRenewalApplicationPaymentProcessing= new MemberRenewalApplicationPaymentProcessing(driver);
           memberRenewalApplicationPaymentProcessing.enterPayerName(data.get("payerName"));
            memberRenewalApplicationPaymentProcessing.selectNonCardPayMethod();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationPaymentProcessing.clickSubmitButton();
            TimeUnit.SECONDS.sleep(3);
            memberRenewalApplicationPaymentProcessing.clickOkButton();
            TimeUnit.SECONDS.sleep(8);
            signOut.signOutBni();
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            reconcileOnlineRenewals = new ReconcileOnlineRenewals();
            reconcileOnlineRenewals.reconcileApp(data.get("firstName"), data.get("lastName"),splitCredentials[2],splitCredentials[3],splitCredentials[4]);
            captureScreenShot = new CaptureScreenShot(driver);
            signOut.signOutBni();
            i++;
             */