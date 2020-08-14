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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class OnlineRenewalOfflinePaymentV2 {

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


    @Before
    public void setup() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Given("^“Allow Online Renewals” as Post-Approval only following Vpricing$")
    public void allowOnlineRenewalsAsPostApprovalOnlyFollowingVPricing(DataTable loginDetails) {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }


    @When("^Click Renew Now button in the home page and enter the below details click Proceed to payment button following Vpricing\\. Enter card details and Proceed to payment$")
    public void clickRenewNowButtonInTheHomePageAndEnterTheBelowDetailsClickProceedToPaymentButtonFollowingVPricingEnterCardDetailsAndProceedToPayment(DataTable onlineRenewal) throws Exception {

        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : onlineRenewal.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            String country = data.get("country");
            System.out.println("Country is" + country);
            String region = data.get("region");
            System.out.println("Region is" + region);
            String chapter = data.get("chapter");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(12);
            bniConnect.clickRenewNowLink();
            TimeUnit.SECONDS.sleep(5);
            memberRenewalApplicationTest2 = new MemberRenewalApplicationTest2(driver);
            memberRenewalApplicationTest2.clickMembershipPeriodCheckBox(data.get("membershipPeriod"));
            TimeUnit.SECONDS.sleep(2);

            String memTerm = data.get("membershipPeriod");
            HashMap<String, String> memOption = new HashMap<String, String>();
            memOption.put("12 Month", "5");
            memOption.put("24 Month", "6");
            memOption.put("12 Months", "5");
            memOption.put("24 Months", "6");
            memOption.put("12 Month S", "5");
            memOption.put("24 Month S", "6");
            memOption.put("12 Month BNI#", "5");
            memOption.put("24 Month BNI#", "6");
            String templateType = data.get("templateType");
            String individualOption = data.get("option");
            String chapterOption = data.get("option2");
            HashMap<String, String> tempType = new HashMap<>();
            tempType.put("Organisation", "1");
            tempType.put("Individual Type", "2");
            tempType.put("Chapter Status Type", "3");
            tempType.put("Individual and Chapter Status Type", "4");
            String lastName = data.get("lastName");
            String firstName = data.get("firstName");
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
                String actualLateFeeFromUI = memberRenewalApplicationTest2.getMembershipLateFee();
                String actualTaxForLateFeeFromUI = memberRenewalApplicationTest2.getTaxForLateFee();
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
                String[][] actualLateFeeFromDB1 = dbConnect.queryAndRetrieveRecords(lateFeeFromSqlQuery);
                String actualLateFeeFromDB = actualLateFeeFromDB1[0][0];
                String actualTaxForLateFeeFromDB = actualLateFeeFromDB1[0][1];
                System.out.println("Actual Late Fee from DB is " + actualLateFeeFromDB);
                System.out.println("Actual Tax for Late Fee from DB is " + actualTaxForLateFeeFromDB);
                double feevalue = Float.parseFloat(actualLateFeeFromDB1[0][0]);
                double taxpercent = Float.parseFloat(actualLateFeeFromDB1[0][1]);
                double taxcal = feevalue * taxpercent / 100;
                taxcal = Util.numberRound(taxcal, 2);
                System.out.println(Util.numberFormat(taxcal, 2));
                System.out.println("Actual Tax for Late Fee from DB after conversion is " + (Util.numberFormat(taxcal, 2)));
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setLateTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 6, i - j, FlagTaxForLateFee);
                boolean setLatePercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 7, i - j, (Util.numberFormat(taxcal, 2)));
                boolean setLatePercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 8, i - j, actualTaxForLateFeeFromUI);
                boolean setLateFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 9, i - j, actualLateFeeFromDB);
                boolean setLateFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 10, i - j, actualLateFeeFromUI);
                memberRenewalApplicationTest2.getMembershipFeeIfMemIsLate();
                TimeUnit.SECONDS.sleep(2);
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
                        "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null;  ";
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
                TimeUnit.SECONDS.sleep(2);
              String expectedMembershipFeeFromUI = memberRenewalApplicationTest2.getMembershipFee();
            if(expectedMembershipFeeFromUI.equals("null")) {
                String expectedMembershipFeeFromUIForLateMem = memberRenewalApplicationTest2.getMembershipFeeIfMemIsLate();
            }
             String expectedTaxForMembershipTermFromUI = memberRenewalApplicationTest2.getTaxForMembershipFeeIfMemIsLate();
            if(expectedTaxForMembershipTermFromUI.equals("null"))
            {
                String expectedTaxForMembershipTermFromUIForLateMem = memberRenewalApplicationTest2.getMembershipFeeIfMemIsLate();
            }
                TimeUnit.SECONDS.sleep(3);
                String idMembershipApplicationQuery ="select id_membership_application from bni.membership_application b1 join  bni.user b2 on b1.id_membership = b2.id_membership where first_name ='"+firstName+"'  and last_name ='"+lastName+"'  order by id_membership_application desc ;" ;
                String[][] MembershipApplicationIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipApplicationQuery);
                String actualMembershipApplicationIDFromDB = MembershipApplicationIDFromDB[0][0];
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 0, i, memTerm);
                boolean setMemTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 1, i - j, qval_taxable_flag);
                boolean setMemPercentTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 2, i - j, qval_var_option_cal_tax_value);
                boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 3, i - j, expectedTaxForMembershipTermFromUI);
                boolean setMemFeeFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 4, i - j, qval_var_option_fee_value);
                boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 5, i - j, expectedMembershipFeeFromUI);
                boolean setIdMembershipApplication = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 6, i-j, actualMembershipApplicationIDFromDB);

            }
            else {
                memberRenewalApplicationTest2.getMembershipFee();
                memberRenewalApplicationTest2.getTaxForMembershipTerm();
                TimeUnit.SECONDS.sleep(2);
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
                        "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null;  ";
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
                TimeUnit.SECONDS.sleep(2);
             String expectedMembershipFeeFromUI = memberRenewalApplicationTest2.getMembershipFee();
             String expectedTaxForMembershipTermFromUI = memberRenewalApplicationTest2.getTaxForMembershipTerm();
                TimeUnit.SECONDS.sleep(3);
//                assertEquals("Actual Tax for MembershipFee is not correct", expectedTaxForMembershipTermFromUI, qval_var_option_cal_tax_value);
//                  assertEquals("Actual  MembershipFee is not correct", expectedMembershipFeeFromUI, qval_var_option_fee_value);
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 0, i, memTerm);
                boolean setMemTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 1, i - j, qval_taxable_flag);
                boolean setMemPercentTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 2, i - j, qval_var_option_cal_tax_value);
                boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 3, i - j, expectedTaxForMembershipTermFromUI);
                boolean setMemFeeFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 4, i - j, qval_var_option_fee_value);
                boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 5, i - j, expectedMembershipFeeFromUI);
            }
            memberRenewalApplicationTest2.clickNetworkingOrgCheckBox();
            System.out.println("Networking checked");
            memberRenewalApplicationTest2.clickInvitePeopleCheckBox();
            memberRenewalApplicationTest2.clickReferralCheckBox();
            memberRenewalApplicationTest2.clickConvictedCheckBox();
            memberRenewalApplicationTest2.clickLeadershipPosition();
            memberRenewalApplicationTest2.clickRecommendCheckBox();
            memberRenewalApplicationTest2.clickrefferalChapterCheckBox();
            memberRenewalApplicationTest2.clickleadershipTeamCheckBox();
           TimeUnit.SECONDS.sleep(1);
//            memberRenewalApplicationTest2.enterDescribeBNI();
//            TimeUnit.SECONDS.sleep(1);
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
            String renewalFormCopy = "select subject from bni_mailer.message_template b1  join " +
                    "  bni_mailer.mail_event_template_org b2 on b1.id_message_template =b2.message_template_id_message_template " +
                    "  join bni.org b3 on b2.id_org = b3.id_org " +
                    " where b3.org_name in ('HQ', 'Antarctica', 'Ant - Two') " +
                    "  and b2.mail_event_id_mail_event ='58' order by  id_org_type desc limit 1 ;" ;
            String[][] queryResult21 = dbConnect.queryAndRetrieveRecords(renewalFormCopy);
            String subject = queryResult21[0][0];
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
            memberRenewalApplicationPaymentProcessing.clickOkButton();
            TimeUnit.SECONDS.sleep(8);
            signOut.signOutBni();
            gmailClient = new GmailClient();
            //verify Renewal Approved - Member from Gmail account
            TimeUnit.SECONDS.sleep(20);
            String renewalApprovedReceived = "select subject from bni_mailer.message_template b1  join " +
                    "  bni_mailer.mail_event_template_org b2 on b1.id_message_template =b2.message_template_id_message_template " +
                    "  join bni.org b3 on b2.id_org = b3.id_org " +
                    " where b3.org_name in ('HQ', 'Antarctica', 'Ant - Two') " +
                    "  and b2.mail_event_id_mail_event ='65' order by  id_org_type desc limit 1 ;" ;
            String[][] queryResult2 = dbConnect.queryAndRetrieveRecords(renewalApprovedReceived);
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
            TimeUnit.SECONDS.sleep(4);
            reconcileApplications.enterSearchText((data.get("firstName")), data.get("lastName"));
            TimeUnit.SECONDS.sleep(3);
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
            String name1 = data.get("firstName") + " " + data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '" + name1 + "' order by invoice_reference desc ;";
            String[][] invoiceAmountFromDB1 = dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceReferenceNumberDB = invoiceAmountFromDB1[0][0];
            String invoiceAmountFromDB2 = invoiceAmountFromDB1[0][1];
            System.out.println("Invoice Reference number from DB is " + invoiceReferenceNumberDB);
            System.out.println("Invoice Amount from DB is " + invoiceAmountFromDB2);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "OnlineRenewalOfflinePayment");
            reconcileApplications.clickRecncileButton();
            Alert alert17 = driver.switchTo().alert();
            alert17.accept();
            TimeUnit.SECONDS.sleep(5);
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
            String idMembershipApplication ="select id_membership_application from bni.membership_application b1 join  bni.user b2 on b1.id_membership = b2.id_membership where first_name ='"+firstName+"'  and last_name ='"+lastName+"'  order by id_membership_application desc ;" ;
            String[][] MembershipApplicationIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipApplication);
            String actualMembershipApplicationIDFromDB = MembershipApplicationIDFromDB[0][0];
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemFeeInvoiceNumberFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 11, i - j, invoiceReferenceNumberDB);
            boolean setMemFeeInvoiceFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 12, i - j, invoiceAmountFromDB2);
            boolean setRenewalDateFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 13, i - j, exactRenewalDate2);
            boolean setReconcileAppliFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 14, i - j, reconcileApplicationStatus);
            boolean setLateMemFeeFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 14, i - j, reconcileApplicationStatus);
            boolean setMembershipApplicationIDFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OnlineRenewal", 15, i - j, actualMembershipApplicationIDFromDB);
            i++;
            signOut.signOutBni();


        }
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
 String taxFlagForMemFee ="  select taxable_flagIFNULL(s1.percent,'null')  from pricing.scheme s1 join " +
                    " pricing.org s2 on s2.id = s1.id_org where s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "     and id_template ='"+tempType.get(templateType)+"' " +
                    "     and id_sku ='" + memOption.get(memTerm) + "'  order by s2.org_type desc limit 1;";
            TimeUnit.SECONDS.sleep(5);
            String[][] taxFlagResultForMemFee = dbConnect.queryAndRetrieveRecords(taxFlagForMemFee);
            String FlagTaxForMemFee = taxFlagResultForMemFee[0][0];
            System.out.println("Taxable flag for Membership fee from sql query is" +FlagTaxForMemFee);




            String taxPercentage = "select b1.percent from bni.tax_code_sub_elements b1 " +
                    " join  bni.org_tax_code b2 on b2.inbound_id_tax_code = b1.id_tax_code " +
                    " join bni.Org b3 on b3.id_org_tax_code = b2.id_org_tax_code where b3.org_name ='" + country + "' and b1.percent_effective_to is null;  ";
            TimeUnit.SECONDS.sleep(5);

//            String[][] taxCalculatedPercent = dbConnect.queryAndRetrieveRecords(taxPercentage);
//            String taxPercent = taxCalculatedPercent[0][0];
            String[][] taxCalculatedPercent = dbConnect.queryAndRetrieveRecords(taxPercentage);
            String taxPercent1 = taxCalculatedPercent[0][0];
            System.out.println("Tax percentage from SQL query is " + taxPercent1);





//            String membershipFeeFromSqlQuery =   "select value, , (Round(s1.value *'" + taxPercent1 + "' )/ 100) from pricing.scheme_line  s1 " +
//                    " join pricing.scheme s2 on s2.id =s1.id_scheme "+
//                    " join pricing.sku s3 on s3.id =s2.id_sku " +
//                    " join pricing.org s4 on s4.id =s2.id_org and  s2.id_sku='"+memOption.get(memTerm)+"'  and  s4.name='"+country+"'; ";


//            String membershipFeeFromSqlQuery =   " select s1.value , (Round(s1.value *'" + taxPercent1 + "' )/ 100) from pricing.scheme_line s1 " +
//                    " join pricing.scheme s2 on s2.id = s1.id_scheme " +
//                    " join pricing.org s3 on s3.id =s2.id_org " +
//                    " join    pricing.variable_option s5   where id_template='"+tempType.get(templateType)+"' " +
//                    " and id_sku ='" + memOption.get(memTerm) + "' and  s3.name in ('" + country + "', '" + region + "', '" + chapter + "')"+
//                    " and  effective_to is null  order by s3.org_type desc limit 1 ;";

            String membershipFeeFromSqlQuery =" select s1.value , (Round(s1.value *'" + taxPercent1 + "' )/ 100) from pricing.scheme_line s1 " +
                    " join pricing.scheme s2 on s2.id = s1.id_scheme " +
                    " join pricing.org s3 on s3.id =s2.id_org " +
                    " join    pricing.variable_option s5  " +
                    " and id_sku ='" + memOption.get(memTerm) + "' and  s3.name in ('" + country + "', '" + region + "', '" + chapter + "')"+
                    "    and  curdate() between  effective_from   and IFNULL(effective_to,curdate()) order by  s3.org_type desc limit 1 ;";



            String[][] actualMembershipFeeFromDB1 =dbConnect.queryAndRetrieveRecords(membershipFeeFromSqlQuery);
            String actualMembershipFeeFromDB= actualMembershipFeeFromDB1[0][0];
            System.out.println("Actual Membership Fee from DB is "+actualMembershipFeeFromDB);

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