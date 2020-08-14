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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
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
    private MemberRenewalApplicationTest2 memberRenewalApplicationTest2;
    private MemberRenewalApplicationPaymentProcessing memberRenewalApplicationPaymentProcessing;
    private ReconcileOnlineRenewals reconcileOnlineRenewals;
    private PaymentCheckOut paymentCheckOut;
    private ReconcileApplications reconcileApplications;
    private DbConnect dbConnect;
    private GmailClient gmailClient;
    private RenewalApproval renewalApproval;

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
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(5);
            String country=data.get("country");
            System.out.println("Country is" +country);
            String region =data.get("region");
            System.out.println("Region is" +region);
            String chapter = data.get("chapter");
            bniConnect = new BNIConnect(driver);
            bniConnect.clickRenewNowLink();
            TimeUnit.SECONDS.sleep(5);
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
            memOption.put("12 Months", "5");
            memOption.put("24 Months","6");
            memOption.put("12 Month BNI#", "5");
            memOption.put("24 Month BNI#","6");
            memOption.put("12 Month S", "5");
            memOption.put("24 Month S","6");
            String templateType = data.get("templateType");
            HashMap<String, String> tempType = new HashMap<>();
            tempType.put("Organisation", "1");
            tempType.put("Individual Type", "2");
            tempType.put("Chapter Status Type", "3");
            tempType.put("Individual and Chapter Status Type", "4");
            String individualOption = data.get("option");
            String chapterOption = data.get("option2");



            String taxFlag_query = "select s2.id,s2.name,s1.taxable_flag " +
                    "from pricing.scheme s1 " +
                    "JOIN pricing.org s2 ON s1.id_org = s2.id " +
                    "where curdate() between s1.effective_from and IFNULL(s1.effective_to,curdate()) " +
                    "and s1.id_sku = '"+memOption.get(memTerm)+"' " +
                    "and s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "order by s2.org_type desc limit 1;";

            String[][] taxFlag_result = dbConnect.queryAndRetrieveRecords(taxFlag_query);


            String qval_taxflag_orgid = taxFlag_result[0][0];
            String qval_taxflag_orgname = taxFlag_result[0][1];
            String qval_taxable_flag = taxFlag_result[0][2];
            System.out.println("Taxable Flag from DB is " +qval_taxable_flag);

            String taxPercentage_query = "select b1.id_org,b1.org_name,b3.percent,CASE WHEN '"+qval_taxable_flag+"'='N' THEN 0 ELSE b3.percent END cal_tax_per from bni.Org b1 " +
                    "JOIN bni.org_tax_code b2 ON b1.id_org_tax_code = b2.id_org_tax_code " +
                    "JOIN bni.tax_code_sub_elements b3 ON b2.inbound_id_tax_code = b3.id_tax_code " +
                    "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null;  ";

            String[][] taxPercentage_result = dbConnect.queryAndRetrieveRecords(taxPercentage_query);

            String qval_tax_orgid = taxPercentage_result[0][0];
            String qval_tax_orgname = taxPercentage_result[0][1];
            String qval_tax_percent = taxPercentage_result[0][2];
            String qval_tax_cal_percent =taxPercentage_result [0][3];
            System.out.println(" Percentage of Tax is" +qval_tax_percent);
            System.out.println("Tax calculated percent" +qval_tax_cal_percent);

            String taxCalculation_query = "select s2.id,s2.name,ifnull(s5.option,'DEFAULT') variable_option,s3.value,(Round(s3.value*("+qval_tax_cal_percent+"))/100) cal_tax_value " +
                    "from pricing.scheme s1 " +
                    "JOIN pricing.org s2 ON s1.id_org = s2.id " +
                    "JOIN pricing.scheme_line s3 ON s1.id = s3.id_scheme " +
                    "LEFT JOIN pricing.scheme_line_option s4 ON s3.id = s4.id_scheme_line " +
                    "LEFT JOIN pricing.variable_option s5 ON s4.id_variable_option = s5.id " +
                    "where curdate() between s1.effective_from and IFNULL(s1.effective_to,curdate()) " +
                    "and s1.id_sku = '"+memOption.get(memTerm)+"' " +
                    //   "and s2.id="+qval_taxflag_orgid+" " +
                    "and s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "and ifnull(s5.option,'DEFAULT') in ('"+individualOption+"','"+chapterOption+"') " +
                    "order by s2.org_type desc limit 1;";

            String[][] taxCalculation_result = dbConnect.queryAndRetrieveRecords(taxCalculation_query);

            String qval_var_option = taxCalculation_result[0][2];
            String qval_var_option_fee_value = taxCalculation_result[0][3];
            String qval_var_option_cal_tax_value = taxCalculation_result[0][4];
            System.out.println("Membership fee from DB   is " +qval_var_option_fee_value);
            System.out.println("Tax for Membership fee from DB   is " +qval_var_option_cal_tax_value);

            memberRenewalApplicationTest2.getMembershipFee();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.getTaxForMembershipTerm();
            TimeUnit.SECONDS.sleep(1);
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
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickTermsAndConditionsCheckBox();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterProductDescribeBNI();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickProceedToPayment();
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
            paymentCheckOut.clickNextButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnect = new BNIConnect(driver);
            Alert alert2 = driver.switchTo().alert();
            alert2.accept();
            TimeUnit.SECONDS.sleep(1);
            driver.close();
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
            reconcileApplications.enterSearchText((data.get("firstName")),data.get("lastName"));
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


            String name = data.get("firstName")+" "+data.get("lastName");
           String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '"+name+"'  and id_membership_application is null;" ;
          String[][] invoiceAmountFromDB1 =dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
           String invoiceAmountFromDB= invoiceAmountFromDB1[0][0];
           System.out.println("Invoice amount from DB is "+invoiceAmountFromDB);


            reconcileApplications = new ReconcileApplications(driver);
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.enterSearchText((data.get("firstName")),data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickRecncileButton();
            TimeUnit.SECONDS.sleep(1);
            Alert alert21 = driver.switchTo().alert();
            alert21.accept();
            TimeUnit.SECONDS.sleep(1);
            driver.close();
            i++;
        }
    }


}































          /*  memberRenewalApplication = new MemberRenewalApplication(driver);
            memberRenewalApplicationTest2 = new MemberRenewalApplicationTest2(driver);
            memberRenewalApplicationTest2.clickMembershipPeriodCheckBox(data.get("membershipPeriod"));
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Membership checked");
            memberRenewalApplicationTest2.clickNetworkingOrgCheckBox();
            System.out.println("Networking checked");

            memberRenewalApplicationTest2.clickReferralCheckBox();
            memberRenewalApplicationTest2.clickConvictedCheckBox();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickLicence();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickLicenceStatus();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.enterDescribeBNI();
            TimeUnit.SECONDS.sleep(1);
            memberRenewalApplicationTest2.clickTermsAndConditionsCheckBox();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(1);

            memberRenewalApplicationTest2.clickProceedToPayment();

           */
//            memberRenewalApplicationTest2.enterProfession(data.get("profession"));
//            TimeUnit.SECONDS.sleep(1);
//            memberRenewalApplicationTest2.enterSpeciality(data.get("speciality"));
//            TimeUnit.SECONDS.sleep(1);

            /*
            memberRenewalApplication.clickMembershipPeriodCheckBox();
            System.out.println("Membership checked");
            memberRenewalApplication.clickNetworkingOrgCheckBox();
            System.out.println("Networking checked");
            memberRenewalApplication.clickInvitePeopleCheckBox();
            memberRenewalApplication.clickReferralCheckBox();
            memberRenewalApplication.clickConvictedCheckBox();
            //    memberRenewalApplication.clickLicence1Box();
            //  memberRenewalApplication.clickLicence2Box();


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

           /* memberRenewalApplication = new MemberRenewalApplication(driver);
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

            */