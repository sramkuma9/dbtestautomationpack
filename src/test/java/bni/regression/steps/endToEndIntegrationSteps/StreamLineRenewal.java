package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.common.email.GmailClient;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.*;
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

    private RenewalApplication renewalApplication;
    private ReconcileApplications reconcileApplications;
    private RenewalApproval renewalApproval;
    private GmailClient gmailClient;
    private Reconcile reconcile;
    private ChangePaymentType changePaymentType;
    DbConnect dbConnect = new DbConnect();
    private AddPaymentToOnlineRenewal addPaymentToOnlineRenewal;

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
    public void iLoginBNIAppWithMemberLoginDetailsThenClickRenewNowButtonInTheHomePageAndEnterTheBelowDetailsClickProceedToPaymentButton(DataTable SLR) throws Exception {
        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : SLR.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            driver = launchBrowser.getDriver();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            bniConnect.clickRenewNowLink();
            Alert alert = driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
//            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//            driver.switchTo().window(tabs.get(1));

            renewalApplication = new RenewalApplication(driver);
            TimeUnit.SECONDS.sleep(8);
            JavascriptExecutor js42 = (JavascriptExecutor) driver;
            js42.executeScript("window.scrollBy(0, 850)", "");
            TimeUnit.SECONDS.sleep(3);
          renewalApplication.selectLanguage(data.get("language"));
            TimeUnit.SECONDS.sleep(3);
          renewalApplication.clickApplicationFormButton();
            TimeUnit.SECONDS.sleep(5);
            renewalApplication.clickApplicationFormNextButton();
            String country = data.get("country");
            String region = data.get("region");
            String firstName = data.get("firstName");
            String lastName = data.get("lastName");
            String renewalDateQuery = "select CONVERT(renewal_due_date, char) from bni.membership b1 join " +
                    " bni.user b2 on b1.id_membership = b2.id_membership where last_name ='" + lastName + "' ; ";
            String[][] renewalDateStatusResult = dbConnect.queryAndRetrieveRecords(renewalDateQuery);
            String renewalDate = renewalDateStatusResult[0][0];
            System.out.println("Renewal Date is " + renewalDate);
            String[] date1 = renewalDate.split(" ");
            String exactRenewalDate = date1[0];
            System.out.println("date for renewal is " + exactRenewalDate);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date currentdate = new Date();
            System.out.println("Query Date : " + formatter.format(formatter.parse(exactRenewalDate)));
            System.out.println("Current Date : " + formatter.format(currentdate));
            System.out.println(formatter.parse(exactRenewalDate).before(currentdate));
            if (formatter.parse(exactRenewalDate).before(currentdate)) {
                String actualLateFeeFromUI = renewalApplication.getLateFeeFromApplication();
                String actualTaxForLateFeeFromUI = renewalApplication.getLateFeeTaxFromApplication();
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setLatePercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 8, i - j, actualTaxForLateFeeFromUI);
                boolean setLateFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 10, i - j, actualLateFeeFromUI);
            }
            String memTerm = data.get("membershipTerm");
            renewalApplication.selectMembershipTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(18);
            String expectedMembershipFeeFromUI = renewalApplication.getMembershipFeeFromApplication();
            TimeUnit.SECONDS.sleep(1);
            String expectedTaxForMembershipTermFromUI = renewalApplication.getMemFeeTaxFromApplication();
            TimeUnit.SECONDS.sleep(1);
            String taxPercentage = "select b1.percent from bni.tax_code_sub_elements b1 " +
                    " join  bni.org_tax_code b2 on b2.inbound_id_tax_code = b1.id_tax_code " +
                    " join bni.Org b3 on b3.id_org_tax_code = b2.id_org_tax_code where b3.org_name in ('" + country + "', '" + region + "' ) and percent_effective_to is null order by id_org_type desc limit 1; ";
            String[][] taxCalculatedPercent = dbConnect.queryAndRetrieveRecords(taxPercentage);
            String taxPercent1 = taxCalculatedPercent[0][0];
            renewalApplication.clickCompanyPaidMembership();
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0, 250)", "");
            renewalApplication.enterPayerName(data.get("payerName"));
            TimeUnit.SECONDS.sleep(1);
            renewalApplication.clickReviewApplicationButton();
            TimeUnit.SECONDS.sleep(12);
            renewalApplication.clickEditGB();
            TimeUnit.SECONDS.sleep(5);
            renewalApplication.editPersonalInfo("Test");
            TimeUnit.SECONDS.sleep(5);
            JavascriptExecutor js7 = (JavascriptExecutor) driver;
            js7.executeScript("window.scrollBy(0, 250)", "");
            renewalApplication.clickReviewApplicationButton();
            JavascriptExecutor js6 = (JavascriptExecutor) driver;
            js6.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(8);
            renewalApplication.clickSubmitApplicationbutton();
            TimeUnit.SECONDS.sleep(17);
            renewalApplication.clickDownloadApplicationButton();
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "StreamLineRenewal");
            TimeUnit.SECONDS.sleep(12);
            driver.close();
            String visitorEmailId = data.get("memberEmailID");
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
            System.out.println("EmailID user name is" + emailIdUserName);
            String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("OnlineRenewalOfflinePaymentRenewalForm", "properties/sql.properties"));
            String subject = queryResult[0][0];
            System.out.println("Subject retrieved from sql query is " + subject);   //   String emailSubject = "Copy of Renewal Form";
            gmailClient = new GmailClient();
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
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
//            bniConnect = new BNIConnect(driver);
//            TimeUnit.SECONDS.sleep(3);
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
            reconcileApplications.enterSearchText((data.get("firstName")), data.get("lastName"));
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "StreamLineRenewal");
            reconcileApplications.clickAppStatusLink();
            renewalApproval = new RenewalApproval(driver);
            renewalApproval.clickAgreeCheckBox();
            renewalApproval.clickApproveButton();
            TimeUnit.SECONDS.sleep(2);
            renewalApproval.clickBackButton();
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchText((data.get("firstName")), data.get("lastName"));
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(2);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "StreamLineApplication");
            reconcileApplications.clickPaymentTypeSymbol();
            TimeUnit.SECONDS.sleep(2);
            addPaymentToOnlineRenewal = new AddPaymentToOnlineRenewal(driver);
            TimeUnit.SECONDS.sleep(5);
            addPaymentToOnlineRenewal.selectPaymentOption(data.get("paymentMethod"));
            TimeUnit.SECONDS.sleep(2);
            addPaymentToOnlineRenewal.clickSubmitButton();
            TimeUnit.SECONDS.sleep(5);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchText((data.get("firstName")), (data.get("lastName")));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickPaymentReceivedCheckBox();
            TimeUnit.SECONDS.sleep(5);
            Alert alert71 = driver.switchTo().alert();
            alert71.accept();
            TimeUnit.SECONDS.sleep(10);

            reconcileApplications.clickRecncileButton();
            TimeUnit.SECONDS.sleep(5);
            Alert alert6 = driver.switchTo().alert();
            alert6.accept();
            TimeUnit.SECONDS.sleep(10);
            String reconcileApplicationStatus = "Pass";
            String emailAddress = data.get(" memberEmailID");
            String name = data.get("firstName") + " " + data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '" + name + "' order by invoice_date desc;";
            String[][] invoiceAmountFromDB1 = dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceReferenceNumberDB = invoiceAmountFromDB1[0][0];
            String invoiceAmountFromDB2 = invoiceAmountFromDB1[0][1];
            System.out.println("Invoice Reference number from DB is " + invoiceReferenceNumberDB);
            System.out.println("Invoice Amount from DB is " + invoiceAmountFromDB2);
            String idMembershipQuery = " select b1.id_membership from bni.membership b1 join  bni.user b2 on b1.id_membership = b2.id_membership where last_name ='" + lastName + "' ; ";
            String[][] MembershipIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipQuery);
            String actualMembershipIDFromDB = MembershipIDFromDB[0][0];
            String renewalDateQuery2 = "select CONVERT(renewal_due_date, char) from bni.membership b1 join " +
                    " bni.user b2 on b1.id_membership = b2.id_membership where last_name ='" + lastName + "'  order by renewal_due_date desc limit 1 ; ";
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
            if(memTerm =="12 Months") {
                String memFeeDetails = "  select membership_fee,late_fee,   membership_fee_taxable , late_fee_taxable " +
                        "from bni.org_membership_fee o1 " +
                        " join bni.org o2 on o1.id_org =o2.id_org where o2.org_name in ('" + country + "', '" + region + "')and curdate()" +
                        " between from_date and IFNULL(to_date,curdate()) order by id_org_type desc limit 1;";
                String[][] memFeeDetailsFromDB = dbConnect.queryAndRetrieveRecords(memFeeDetails);
                String memFeeFromDBValue = memFeeDetailsFromDB[0][0];
                String actualLateFeeFromDB = memFeeDetailsFromDB[0][1];
                String memFeeTaxFlag = memFeeDetailsFromDB[0][2];
                String memLateFeeTaxFlag = memFeeDetailsFromDB[0][3];
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 0, i, memTerm);
                boolean setMemFeeTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 1, i, memFeeTaxFlag);
                boolean setMemPercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 2, i - j, memFeeFromDBValue);
                boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 3, i - j, expectedTaxForMembershipTermFromUI);
                boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 5, i - j, expectedMembershipFeeFromUI);
                boolean setLateFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 6, i - j, memLateFeeTaxFlag);
                boolean setLateFeeFromDB = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 9, i - j, actualLateFeeFromDB);

            }
            else if (memTerm =="24 Months"){String memFeeDetails = "  select membership_fee,late_fee,   membership_fee_taxable , late_fee_taxable " +
                    "from bni.org_membership_fee o1 " +
                    " join bni.org o2 on o1.id_org =o2.id_org where o2.org_name in ('" + country + "', '" + region + "')and curdate()" +
                    " between from_date and IFNULL(to_date,curdate()) order by id_org_type desc limit 1;";
                String[][] memFeeDetailsFromDB = dbConnect.queryAndRetrieveRecords(memFeeDetails);
                String memFeeFromDBValue = memFeeDetailsFromDB[0][0];
                String actualLateFeeFromDB = memFeeDetailsFromDB[0][1];
                String memFeeTaxFlag = memFeeDetailsFromDB[0][2];
                String memLateFeeTaxFlag = memFeeDetailsFromDB[0][3];
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 0, i, memTerm);

                boolean setMemFeeTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 1, i, memFeeTaxFlag);
                boolean setMemPercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 2, i - j, memFeeFromDBValue);
                boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 3, i - j, expectedTaxForMembershipTermFromUI);
                boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 5, i - j, expectedMembershipFeeFromUI);

                boolean setLateFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 6, i - j, memLateFeeTaxFlag);
                boolean setLateFeeFromDB = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 9, i - j, actualLateFeeFromDB);


            }

            //String memFeeDetails = "select membership_fee, late_fee from bni.membership_application where id_membership_application ='" + actualMembershipApplicationIDFromDB + "'";
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemFeeInvoiceNumberFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 11, i - j, invoiceReferenceNumberDB);
            boolean setMemFeeInvoiceFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 12, i - j, invoiceAmountFromDB2);
            boolean setRenewalDateFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 13, i - j, exactRenewalDate2);
            boolean setReconcileAppliFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 14, i - j, reconcileApplicationStatus);
            boolean setIDMembershipFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 15, i - j, actualMembershipIDFromDB);
            boolean setFirstNameFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 16, i - j, firstName);
            boolean setLastNameFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 17, i - j, lastName);
            boolean setMembershipApplicationIDFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 18, i - j, actualMembershipApplicationIDFromDB);
            TimeUnit.SECONDS.sleep(10);
            driver.close();


        }
    }

    @Then("Logged out from BNI")
    public void loggedOutFromBNI() throws Exception {
        System.out.println("Stream line renewal script executed.");
    }

}


























