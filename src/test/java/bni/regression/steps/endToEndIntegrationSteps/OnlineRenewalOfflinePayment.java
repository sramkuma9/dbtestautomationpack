package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
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

import java.text.SimpleDateFormat;
import java.util.*;
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
        Integer j = 1;
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
            String region = data.get("region");
            String firstName = data.get("firstName");
            String lastName = data.get("lastName");
            bniConnect.clickRenewNowLink();
            TimeUnit.SECONDS.sleep(5);
            memberRenewalApplicationTest2 = new MemberRenewalApplicationTest2(driver);
            memberRenewalApplicationTest2.clickMembershipPeriodCheckBox(data.get("membershipPeriod"));
            TimeUnit.SECONDS.sleep(2);
            String renewalDateQueryBefore = "select CONVERT(renewal_due_date, char) from bni.membership b1 join " +
                    " bni.user b2 on b1.id_membership = b2.id_membership where first_name='" + firstName + "' and  last_name ='" + lastName + "' ; ";
            String[][] renewalDateStatusResultBefore = dbConnect.queryAndRetrieveRecords(renewalDateQueryBefore);
            String renewalDateBefore = renewalDateStatusResultBefore[0][0];
            System.out.println("Renewal Date is " + renewalDateBefore);
            String[] date1 = renewalDateBefore.split(" ");
            String exactRenewalDateBefore = date1[0];
            System.out.println("date for renewal is " + exactRenewalDateBefore);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date currentdate = new Date();
            System.out.println("Query Date : " + formatter.format(formatter.parse(exactRenewalDateBefore)));
            System.out.println("Current Date : " + formatter.format(currentdate));
            System.out.println(formatter.parse(exactRenewalDateBefore).before(currentdate));
            if (formatter.parse(exactRenewalDateBefore).before(currentdate)) {
                String expectedMembershipFeeFromUI = memberRenewalApplicationTest2.getMembershipFeeIfMemIsLate();
                String expectedTaxForMembershipTermFromUI = memberRenewalApplicationTest2.getTaxForMembershipFeeIfMemIsLate();
                String actualLateFeeFromUI = memberRenewalApplicationTest2.getMembershipLateFee();
                String actualTaxForLateFeeFromUI = memberRenewalApplicationTest2.getTaxForLateFee();
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setMemPercentTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 2, i - j, expectedTaxForMembershipTermFromUI);
                boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 3, i - j, expectedTaxForMembershipTermFromUI);
                boolean setMemFeeFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 4, i - j, expectedMembershipFeeFromUI);
                boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 5, i - j, expectedMembershipFeeFromUI);
                boolean setLatePercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 7, i - j, actualTaxForLateFeeFromUI);
                boolean setLatePercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 8, i - j, actualTaxForLateFeeFromUI);
                boolean setLateFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 9, i - j, actualLateFeeFromUI);
                boolean setLateFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 10, i - j, actualLateFeeFromUI);

            } else {

                String memTerm = data.get("membershipPeriod");
                String expectedMembershipFeeFromUI = memberRenewalApplicationTest2.getMembershipFee();
                TimeUnit.SECONDS.sleep(2);
                String expectedTaxForMembershipTermFromUI = memberRenewalApplicationTest2.getTaxForMembershipTerm();
                TimeUnit.SECONDS.sleep(2);
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 0, i, memTerm);
                boolean setMemPercentTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 2, i - j, expectedTaxForMembershipTermFromUI);
                boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 3, i - j, expectedTaxForMembershipTermFromUI);
                boolean setMemFeeFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 4, i - j, expectedMembershipFeeFromUI);
                boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 5, i - j, expectedMembershipFeeFromUI);

            }

            memberRenewalApplicationTest2.clickNetworkingOrgCheckBox();
            memberRenewalApplicationTest2.clickInvitePeopleCheckBox();
            memberRenewalApplicationTest2.clickReferralCheckBox();
            memberRenewalApplicationTest2.clickConvictedCheckBox();
            memberRenewalApplicationTest2.clickLeadershipPosition();
            memberRenewalApplicationTest2.clickRecommendCheckBox();
            memberRenewalApplicationTest2.clickrefferalChapterCheckBox();
            memberRenewalApplicationTest2.clickLicenceStatus();
            TimeUnit.SECONDS.sleep(1);
           memberRenewalApplicationTest2.enterDescribeBNI();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickTermsAndConditionsCheckBox();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterSpeciality(data.get("speciality"));
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterProductDescribeBNI();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickProceedToPayment();
            TimeUnit.SECONDS.sleep(5);
            gmailClient = new GmailClient();
            TimeUnit.SECONDS.sleep(20);
            String visitorEmailId = data.get("emailID");
            System.out.println("EmailID" + visitorEmailId);
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
            System.out.println("EmailID user name is" + emailIdUserName);
            String renewalFormReceived = "select subject from bni_mailer.message_template b1  join " +
                    "  bni_mailer.mail_event_template_org b2 on b1.id_message_template =b2.message_template_id_message_template " +
                    "  join bni.org b3 on b2.id_org = b3.id_org " +
                    " where b3.org_name in ('HQ', '"+country+"', '"+region+"') " +
                    "  and b2.mail_event_id_mail_event ='58' order by  id_org_type desc limit 1 ;";
            String[][] queryResult = dbConnect.queryAndRetrieveRecords(renewalFormReceived);
            String subject = queryResult[0][0];
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
            Alert alert = driver.switchTo().alert();
            alert.accept();
            memberRenewalApplicationPaymentProcessing.clickOkButton();
            TimeUnit.SECONDS.sleep(8);
            signOut.signOutBni();


//            String visitorEmailId = data.get("emailID");
//            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
//            String lastName = data.get("lastName");
//            String firstName = data.get("firstName");
//            gmailClient = new GmailClient();
            TimeUnit.SECONDS.sleep(10);
            String renewalPreApprovedReceived = "select subject from bni_mailer.message_template b1  join " +
                    "  bni_mailer.mail_event_template_org b2 on b1.id_message_template =b2.message_template_id_message_template " +
                    "  join bni.org b3 on b2.id_org = b3.id_org " +
                    " where b3.org_name in ('HQ', '"+country+"', '"+region+"') " +
                    "  and b2.mail_event_id_mail_event ='64' order by  id_org_type desc limit 1 ;";
            String[][] queryResult21 = dbConnect.queryAndRetrieveRecords(renewalPreApprovedReceived);
            String subject21 = queryResult21[0][0];
            TimeUnit.SECONDS.sleep(3);

            gmailClient.checkEmail(emailIdUserName, subject21, visitorEmailId, "", 1);
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
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "OnlineRenewalOfflinePayment");
            reconcileApplications.clickRecncileButton();
            Alert alert17 = driver.switchTo().alert();
            alert17.accept();
            TimeUnit.SECONDS.sleep(5);
            String name1 = data.get("firstName") + " " + data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '" + name1 + "' order by invoice_reference desc ;";
            String[][] invoiceAmountFromDB1 = dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceReferenceNumberDB = invoiceAmountFromDB1[0][0];
            String invoiceAmountFromDB2 = invoiceAmountFromDB1[0][1];
            System.out.println("Invoice Reference number from DB is " + invoiceReferenceNumberDB);
            System.out.println("Invoice Amount from DB is " + invoiceAmountFromDB2);
            String reconcileApplicationStatus = "Pass";
            String renewalDateQuery2 = "select CONVERT(renewal_due_date, char) from bni.membership b1 join " +
                    " bni.user b2 on b1.id_membership = b2.id_membership where last_name ='" + lastName + "' ; ";
            String[][] renewalDateStatusResult2 = dbConnect.queryAndRetrieveRecords(renewalDateQuery2);
            String renewalDate2 = renewalDateStatusResult2[0][0];
            System.out.println("Renewal Date is " + renewalDate2);
            String[] date12 = renewalDate2.split(" ");
            String exactRenewalDate2 = date12[0];
            System.out.println("date for renewal is " + exactRenewalDate2);
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("Query Date : " + formatter2.format(formatter2.parse(exactRenewalDate2)));
            String idMembershipApplication = "select id_membership_application from bni.membership_application b1 join  bni.user b2 on b1.id_membership = b2.id_membership where first_name ='" + firstName + "'  and last_name ='" + lastName + "'  order by id_membership_application desc ;";
            String[][] MembershipApplicationIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipApplication);
            String actualMembershipApplicationIDFromDB = MembershipApplicationIDFromDB[0][0];
            boolean setMemFeeInvoiceNumberFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 11, i - j, invoiceReferenceNumberDB);
            boolean setMemFeeInvoiceFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 12, i - j, invoiceAmountFromDB2);
            boolean setRenewalDateFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 13, i - j, exactRenewalDate2);
            boolean setReconcileAppliFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 14, i - j, reconcileApplicationStatus);
            boolean setLateMemFeeFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 14, i - j, reconcileApplicationStatus);
            boolean setMembershipApplicationIDFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 15, i - j, actualMembershipApplicationIDFromDB);
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