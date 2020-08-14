package bni.regression.Pricing;

import bni.regression.libraries.common.*;
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

import static junit.framework.TestCase.assertEquals;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class StreamLineRenewalV2 {
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

    @Given("^A member is added newly to the region and member status is active now\\. On Admin menu ->Select Region->Manage Region -> View/Edit Region Business Rules\\. Keep “Allow Online Renewals” as Streamline - do NOT choose, but contact Support for implementation plan following V2 pricing$")
    public void aMemberIsAddedNewlyToTheRegionAndMemberStatusIsActiveNowOnAdminMenuSelectRegionManageRegionViewEditRegionBusinessRulesKeepAllowOnlineRenewalsAsStreamlineDoNOTChooseButContactSupportForImplementationPlan(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I login BNI app with Member login details then click Renew Now  button in the home page and enter the below details click Proceed to payment button following V pricing\\.$")
    public void iLoginBNIAppWithMemberLoginDetailsThenClickRenewNowButtonInTheHomePageAndEnterTheBelowDetailsClickProceedToPaymentButtonFollowingVPricing(DataTable SLR) throws Exception {
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
            // if not alert window opens in next tab
            //If opening first time, alert witll be there, hence no need to switch tab..
            Alert alert = driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(3);
                         //  ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                          // driver.switchTo().window(tabs.get(1));
            renewalApplication = new RenewalApplication(driver);
            TimeUnit.SECONDS.sleep(18);
            JavascriptExecutor js42 = (JavascriptExecutor) driver;
            js42.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(10);

            renewalApplication.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(25);
            String country = data.get("country");
            String region = data.get("region");
            String chapter = data.get("chapter");
            String memTerm = data.get("membershipTerm");
            HashMap<String, String> memOption = new HashMap<String, String>();
            memOption.put("12 Month", "5");
            memOption.put("24 Month", "6");
            memOption.put("12 Months", "5");
            memOption.put("24 Months", "6");
            memOption.put("12 Month BNI#", "5");
            memOption.put("24 Month BNI#", "6");
            memOption.put("12 Month S", "5");
            memOption.put("24 Month S", "6");
            memOption.put("12 Month S", "5");
            memOption.put("24 Month S", "6");
            memOption.put("24 Month Sgb", "6");
            String option = data.get("option");
            String option2 = data.get("option2");
            String templateType = data.get("templateType");
            HashMap<String, String> tempType = new HashMap<>();
            tempType.put("Organisation", "1");
            tempType.put("Individual Type", "2");
            tempType.put("Chapter Status Type", "3");
            tempType.put("Individual and Chapter Status Type", "4");
            String individualOption = data.get("option");
            String chapterOption = data.get("option2");
            renewalApplication.selectMembershipTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(18);
            String taxFlag_query = "select s2.id,s2.name,s1.taxable_flag " +
                    "from pricing.scheme s1 " +
                    "JOIN pricing.org s2 ON s1.id_org = s2.id " +
                    "where curdate() between s1.effective_from and IFNULL(s1.effective_to,curdate()) " +
                    "and s1.id_sku = '" + memOption.get(memTerm) + "' " +
                    "and s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "order by s2.org_type desc limit 1;";
            String[][] taxFlag_result = dbConnect.queryAndRetrieveRecords(taxFlag_query);
            String qval_taxflag_orgid = taxFlag_result[0][0];
            String qval_taxflag_orgname = taxFlag_result[0][1];
            String qval_taxable_flag = taxFlag_result[0][2];
            System.out.println("Taxable Flag from DB is " + qval_taxable_flag);
            String taxPercentage_query = "select b1.id_org,b1.org_name,b3.percent,CASE WHEN '" + qval_taxable_flag + "'='N' THEN 0 ELSE b3.percent END cal_tax_per from bni.Org b1 " +
                    "JOIN bni.org_tax_code b2 ON b1.id_org_tax_code = b2.id_org_tax_code " +
                    "JOIN bni.tax_code_sub_elements b3 ON b2.inbound_id_tax_code = b3.id_tax_code " +
                    "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null ;  ";
            String[][] taxPercentage_result = dbConnect.queryAndRetrieveRecords(taxPercentage_query);
            String qval_tax_orgid = taxPercentage_result[0][0];
            String qval_tax_orgname = taxPercentage_result[0][1];
            String qval_tax_percent = taxPercentage_result[0][2];
            String qval_tax_cal_percent = taxPercentage_result[0][3];
            System.out.println(" Percentage of Tax is" + qval_tax_percent);
            System.out.println("Tax calculated percent" + qval_tax_cal_percent);
            String taxCalculation_query = "select s2.id,s2.name,ifnull(s5.option,'DEFAULT') variable_option,s3.value,(Round(s3.value*(" + qval_tax_cal_percent + ")/100,2)) cal_tax_value " +
                    "from pricing.scheme s1 " +
                    "JOIN pricing.org s2 ON s1.id_org = s2.id " +
                    "JOIN pricing.scheme_line s3 ON s1.id = s3.id_scheme " +
                    "LEFT JOIN pricing.scheme_line_option s4 ON s3.id = s4.id_scheme_line " +
                    "LEFT JOIN pricing.variable_option s5 ON s4.id_variable_option = s5.id " +
                    "where curdate() between s1.effective_from and IFNULL(s1.effective_to,curdate()) " +
                    "and s1.id_sku = '" + memOption.get(memTerm) + "' " +
                    "  and id_template ='" + tempType.get(templateType) + "' " +
                    //   "and s2.id="+qval_taxflag_orgid+" " +
                    "and s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "and ifnull(s5.option,'DEFAULT') in ('" + individualOption + "','" + chapterOption + "') " +
                    "order by s2.org_type desc limit 1;";
            String[][] taxCalculation_result = dbConnect.queryAndRetrieveRecords(taxCalculation_query);
            String qval_var_option = taxCalculation_result[0][2];
            String qval_var_option_fee_value = taxCalculation_result[0][3];
            String qval_var_option_cal_tax_value = taxCalculation_result[0][4];
            System.out.println("Membership fee from DB   is " + qval_var_option_fee_value);
            System.out.println("Tax for Membership fee from DB   is " + qval_var_option_cal_tax_value);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "StreamLineRenewal");
            String expectedMembershipFeeFromUI = renewalApplication.getMembershipFeeFromApplication();
            TimeUnit.SECONDS.sleep(1);
            String expectedTaxForMembershipTermFromUI = renewalApplication.getMemFeeTaxFromApplication();
            TimeUnit.SECONDS.sleep(1);
            assertEquals("Actual Tax for MembershipFee is not correct", expectedTaxForMembershipTermFromUI, qval_var_option_cal_tax_value);
            assertEquals("Actual  MembershipFee is not correct", expectedMembershipFeeFromUI, qval_var_option_fee_value);
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 0, i, memTerm);
            boolean setMemTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 1, i - j, qval_taxable_flag);
            boolean setMemPercentTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 2, i - j, qval_var_option_cal_tax_value);
            boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 3, i - j, expectedTaxForMembershipTermFromUI);
            boolean setMemFeeFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 4, i - j, qval_var_option_fee_value);
            boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 5, i - j, expectedMembershipFeeFromUI);
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

                String taxFlagForLateFee = " select IFNULL(taxable_flag,'null') from pricing.scheme s1 " +
                        " join  pricing.org s2 on s2.id = s1.id_org  " +
                        " where s2.name in ('" + country + "', '" + region + "', '" + chapter + "') " +
                        "  and id_sku ='4' " +
                        " and id_template ='" + tempType.get(templateType) + "' " +
                        " and curdate() between  effective_from   and IFNULL(effective_to,curdate()) order by s2.org_type desc limit 1;";
                TimeUnit.SECONDS.sleep(5);
                String[][] taxFlagResultForLateFee = dbConnect.queryAndRetrieveRecords(taxFlagForLateFee);
                String FlagTaxForLateFee = taxFlagResultForLateFee[0][0];
                String taxPercentageLateFee_query = "select b1.id_org,b1.org_name,b3.percent,CASE WHEN '" + FlagTaxForLateFee + "'='N' THEN 0 ELSE b3.percent END cal_tax_per from bni.Org b1 " +
                        "JOIN bni.org_tax_code b2 ON b1.id_org_tax_code = b2.id_org_tax_code " +
                        "JOIN bni.tax_code_sub_elements b3 ON b2.inbound_id_tax_code = b3.id_tax_code " +
                        "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null;  ";
                String[][] taxPercentageLate_result = dbConnect.queryAndRetrieveRecords(taxPercentageLateFee_query);
                String qval_taxLate_orgid = taxPercentageLate_result[0][0];
                String qval_taxLate_orgname = taxPercentageLate_result[0][1];
                String qval_taxLate_percent = taxPercentageLate_result[0][2];
                String qval_tax_calLate_percent = taxPercentageLate_result[0][3];
                System.out.println(" Percentage of Tax is" + qval_taxLate_percent);
                System.out.println("Tax calculated percent" + qval_tax_calLate_percent);
                // id_sku =4 for late fee always
                String lateFeeFromSqlQuery = " select  sl.value, (Round(sl.value *('" + qval_tax_calLate_percent + "' )/ 100,2)) from pricing.scheme_line sl " +
                        " join pricing.scheme s on s.id = sl.id_scheme " +
                        " join pricing.org o on o.id = s.id_org " +
                        " where o.name in ('" + country + "', '" + region + "', '" + chapter + "') and id_sku='4'  " +
                        "  and  curdate() between  effective_from   and IFNULL(effective_to,curdate()) order by o.org_type desc limit 1; ";
                //select  sl.value, (Round(sl.value *'20.000'  )/ 100) from pricing.scheme_line sl  join pricing.scheme s on s.id = sl.id_scheme join pricing.org o on o.id = s.id_org where id_sku='4' and o.name in ('Bulgaria', 'Bankso', 'Chapter One');
                String[][] actualLateFeeFromDB1 = dbConnect.queryAndRetrieveRecords(lateFeeFromSqlQuery);
                String actualLateFeeFromDB = actualLateFeeFromDB1[0][0];
                String actualTaxForLateFeeFromDB = actualLateFeeFromDB1[0][1];
                System.out.println("Actual Late Fee from DB is " + actualLateFeeFromDB);
                System.out.println("Actual Tax for Late Fee from DB is " + actualTaxForLateFeeFromDB);
                assertEquals("Late Fee is not correct", actualLateFeeFromUI, actualLateFeeFromDB);
                double feevalue = Float.parseFloat(actualLateFeeFromDB1[0][0]);
                double taxpercent = Float.parseFloat(actualLateFeeFromDB1[0][1]);
                double taxcal = feevalue * taxpercent / 100;
                taxcal = Util.numberRound(taxcal, 2);
                System.out.println(Util.numberFormat(taxcal, 2));
                System.out.println("Actual Tax for Registration Fee from DB is " + (Util.numberFormat(taxcal, 2)));
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setLateTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 6, i - j, FlagTaxForLateFee);
                boolean setLatePercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 7, i - j, (Util.numberFormat(taxcal, 2)));
                boolean setLatePercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 8, i - j, actualTaxForLateFeeFromUI);
                boolean setLateFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 9, i - j, actualLateFeeFromDB);
                boolean setLateFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "SR", 10, i - j, actualLateFeeFromUI);
            }

            renewalApplication.clickCompanyPaidMembership();
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0, 250)", "");
            renewalApplication.enterPayerName(data.get("payerName"));
            TimeUnit.SECONDS.sleep(1);
            renewalApplication.clickReviewApplicationButton();
            TimeUnit.SECONDS.sleep(12);
            System.out.println(("Review button clicked"));
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
            System.out.println(("submit button clicked"));
            TimeUnit.SECONDS.sleep(17);
            renewalApplication.clickDownloadApplicationButton();
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "StreamLineRenewal");
            TimeUnit.SECONDS.sleep(12);
            System.out.println(("Download button clicked"));
            driver.close();
            TimeUnit.SECONDS.sleep(12);
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
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "StreamLineRenewal");
            reconcileApplications.clickAppStatusLink();
            renewalApproval = new RenewalApproval(driver);
            renewalApproval.clickAgreeCheckBox();
            renewalApproval.clickApproveButton();
            TimeUnit.SECONDS.sleep(2);
            renewalApproval.clickBackButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String language301[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber30 = Integer.parseInt(language301[1]);
            String transMainMenu2 = readWriteExcel.getCellData("translation", colNumber30, 3);
            String transSubMenu2 = readWriteExcel.getCellData("translation", colNumber30, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu2);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu2);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchText((data.get("firstName")), (data.get("lastName")));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(3);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickPaymentReceivedCheckBox();
            TimeUnit.SECONDS.sleep(5);
            Alert alert7 = driver.switchTo().alert();
            alert7.accept();
            TimeUnit.SECONDS.sleep(10);
            String name = data.get("firstName") + " " + data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '" + name + "' order by invoice_date desc;";
            String[][] invoiceAmountFromDB1 = dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceReferenceNumberDB = invoiceAmountFromDB1[0][0];
            String invoiceAmountFromDB2 = invoiceAmountFromDB1[0][1];
            System.out.println("Invoice Reference number from DB is " + invoiceReferenceNumberDB);
            System.out.println("Invoice Amount from DB is " + invoiceAmountFromDB2);
            reconcileApplications.clickRecncileButton();
            TimeUnit.SECONDS.sleep(5);
            Alert alert6 = driver.switchTo().alert();
            alert6.accept();
            TimeUnit.SECONDS.sleep(5);
            String reconcileApplicationStatus = "Pass";
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



}


