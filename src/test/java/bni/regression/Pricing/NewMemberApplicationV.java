package bni.regression.Pricing;


import bni.regression.libraries.common.*;
import bni.regression.libraries.common.email.GmailClient;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.ReconcileOnlineRenewals;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class NewMemberApplicationV {


    public static WebDriver driver;
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private GmailClient gmailClient = new GmailClient();
    private BNIConnect bniConnect;
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    public static String fixedDateTime;
    public static String visitorDateTime;
    private BNIConnectApplicationPortal BniConnectApplicationPortal;
    private AddPaymentTypeForOnlineApplication addPaymentTypeForOnlineApplication;
    private ReconcileApplications reconcileApplications;
    private NewMemberApproval newMemberApproval;
    private Reconcile reconcile;
    DbConnect dbConnect = new DbConnect();

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


//       @Given("^A member has to register the application via New Member application process following V pricing$")
//    public void a_member_has_to_register_the_application_via_New_Member_application_process_following_V_pricing(DataTable loginDetails) throws Throwable {

    //           @Given("^A member has to register the application via New Member application process following V pricing$")
//           public void a_member_has_to_register_the_application_via_New_Member_application_process_following_V_pricing(DataTable loginDetails) throws Throwable {
    @Given("A new Pricing version")
    public void a_new_Pricing_version(DataTable loginDetails) throws Throwable {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }


    @When("I login  BNI and add a  Member using NMA process  and enter the below details click Proceed to payment button following new pricing")
    public void i_login_BNI_and_add_a_Member_using_NMA_process_and_enter_the_below_details_click_Proceed_to_payment_button_following_new_pricing(DataTable addNMA) throws Throwable {

        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : addNMA.asMaps(String.class, String.class)) {

            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String visitorEmailId = readWriteExcel.getCellData("addBrandNewVisitor", 0, 1);
            System.out.println("EmailID" + visitorEmailId);
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
            System.out.println("EmailID user name is" + emailIdUserName);
            String country = data.get("country");
            String region = data.get("region");
            String registrationSubject = "select subject from bni_mailer.message_template b1  join " +
                    " bni_mailer.mail_event_template_org b2 on b1.id_message_template =b2.message_template_id_message_template " +
                    " join bni.org b3 on b2.id_org = b3.id_org " +
                    "where b3.org_name in ('HQ', '" + country + "', '" + region + "') " +
                    " and b2.mail_event_id_mail_event ='1' order by  id_org_type desc limit 1 ; ";
            String[][] registrationSubjectResult = dbConnect.queryAndRetrieveRecords(registrationSubject);
            String subject = registrationSubjectResult[0][0];
            gmailClient.checkEmail(emailIdUserName, subject, visitorEmailId, "applicant", 1);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String url = readWriteExcel.getCellData("applicant", 0, 0);
            System.out.println("URL is " + url);
            TimeUnit.SECONDS.sleep(2);
            driver.manage().window().maximize();
            driver.navigate().to(url);
            TimeUnit.SECONDS.sleep(2);
        Registration registration = new Registration(driver);
            registration.enterNewPassword(data.get("NewPassword"));
            registration.enterConfirmPassword(data.get("ConfirmPassword"));
            TimeUnit.SECONDS.sleep(5);
            registration.clickNextButton();
            TimeUnit.SECONDS.sleep(2);




           String baseEnvURl = readWritePropertyFile.loadAndReadPropertyFile("baseUrl", "properties/config.properties");
            //NMA Form
            BniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
            TimeUnit.SECONDS.sleep(8);
//            if (baseEnvURl.equals("https://www.trackbniconnect.com/")) {
//                BniConnectApplicationPortal.clickApplicationFormButton();
//                BniConnectApplicationPortal.selectLanguage(data.get("language"));
//            }
         BniConnectApplicationPortal.clickApplicationFormButton();
            TimeUnit.SECONDS.sleep(3);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(13);
            BniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(3);
            BniConnectApplicationPortal.selectMembershipTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(10);
            String chapter = data.get("chapter");
            String memTerm = data.get("membershipTerm");
            HashMap<String, String> memOption = new HashMap<String, String>();
            memOption.put("12 Month", "2");
            memOption.put("24 Month", "3");
            memOption.put("12 Months", "2");
            memOption.put("24 Months", "3");
            memOption.put("18 Months", "8");
            memOption.put("18 Month", "8");
            memOption.put("12 Month BNI#", "2");
            memOption.put("24 Month BNI#", "3");
            memOption.put("12 Month S", "2");
            memOption.put("24 Month S", "3");
            memOption.put("12 Months", "2");
            memOption.put("24 Month Sgb", "3");
            memOption.put("12 Months GB path1#מי", "2");
            String templateType = data.get("templateType");
            HashMap<String, String> tempType = new HashMap<>();
            tempType.put("Organization", "1");
            tempType.put("Individual Type", "2");
            tempType.put("Chapter Status Type", "3");
            tempType.put("Individual and Chapter Status Type", "4");
            String individualOption = data.get("option");
            String chapterOption = data.get("option2");
            HashMap<String, String> option = new HashMap<String, String>();
            option.put("MEMBER", "1");
            option.put("ALUMNI", "2");
            option.put("VISITOR", "3");
            HashMap<String, String> option2 = new HashMap<String, String>();
            option2.put("ACTIVE", "4");
            option2.put("COREGROUP", "5");
            option2.put("SUSPENDED", "6");
            String taxFlag_query = "select s2.id,s2.name,s1.taxable_flag " +
                    "from pricing.scheme s1 " +
                    "JOIN pricing.org s2 ON s1.id_org = s2.id " +
                    "where curdate() between s1.effective_from and IFNULL(s1.effective_to,curdate()) " +
                    "and s1.id_sku = '" + memOption.get(memTerm) + "' " +
                    "and s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "order by s2.org_type desc limit 1";
            String[][] taxFlag_result = dbConnect.queryAndRetrieveRecords(taxFlag_query);
            String qval_taxflag_orgid = taxFlag_result[0][0];
            String qval_taxflag_orgname = taxFlag_result[0][1];
            String memFeeTaxableFlag = taxFlag_result[0][2];
            System.out.println("Taxable Flag from DB is " + memFeeTaxableFlag);
            String taxPercentage_query = "select b1.id_org,b1.org_name,b3.percent,CASE WHEN '" + memFeeTaxableFlag + "'='N' THEN 0 ELSE b3.percent END cal_tax_per from bni.Org b1 " +
                    "JOIN bni.org_tax_code b2 ON b1.id_org_tax_code = b2.id_org_tax_code " +
                    "JOIN bni.tax_code_sub_elements b3 ON b2.inbound_id_tax_code = b3.id_tax_code " +
                    "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null;  ";
            String[][] taxPercentage_result = dbConnect.queryAndRetrieveRecords(taxPercentage_query);
            String qval_tax_orgid = taxPercentage_result[0][0];
            String qval_tax_orgname = taxPercentage_result[0][1];
            String taxPercentage = taxPercentage_result[0][2];
            String qval_tax_cal_percent = taxPercentage_result[0][3];
            System.out.println(" Percentage of Tax is" + taxPercentage);
            System.out.println("Tax calculated percent" + qval_tax_cal_percent);
            String taxCalculation_query = "select s2.id,s2.name,ifnull(s5.option,'DEFAULT') variable_option,s3.value,(Round(s3.value*('" + qval_tax_cal_percent + "')/100,2)) cal_tax_value " +
                    "from pricing.scheme s1 " +
                    "JOIN pricing.org s2 ON s1.id_org = s2.id " +
                    "JOIN pricing.scheme_line s3 ON s1.id = s3.id_scheme " +
                    "LEFT JOIN pricing.scheme_line_option s4 ON s3.id = s4.id_scheme_line " +
                    "LEFT JOIN pricing.variable_option s5 ON s4.id_variable_option = s5.id " +
                    "where curdate() between s1.effective_from and IFNULL(s1.effective_to,curdate()) " +
                    "and s1.id_sku = '" + memOption.get(memTerm) + "' " +
                    //   "and s2.id="+qval_taxflag_orgid+" " +
                    "  and id_template ='" + tempType.get(templateType) + "' " +
                    "and s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "and ifnull(s5.option,'DEFAULT') in ('" + individualOption + "','" + chapterOption + "') " +
                    "order by s2.org_type desc limit 1; ";
            String[][] taxCalculation_result = dbConnect.queryAndRetrieveRecords(taxCalculation_query);
            String memFeeVarOption = taxCalculation_result[0][2];
            String memFeeFromDBValue = taxCalculation_result[0][3];
            String memFeeTaxValueFromDB = taxCalculation_result[0][4];
            System.out.println("Membership fee from DB   is " + memFeeFromDBValue);
            System.out.println("Membership Tax value from DB   is " + memFeeTaxValueFromDB);
            String expectedMembershipFeeFromUI = BniConnectApplicationPortal.getFeeForMembershipTerm();
            String expectedTaxForMembershipTermFromUI = BniConnectApplicationPortal.getTaxForMembershipTerm();
            TimeUnit.SECONDS.sleep(3);
//                  assertEquals("Actual Tax for MembershipFee is not correct", expectedTaxForMembershipTermFromUI,memFeeTaxValueFromDB);
            assertEquals("Actual  MembershipFee is not correct", expectedMembershipFeeFromUI, memFeeFromDBValue);
            String taxFlagForRegFee = " select IFNULL(taxable_flag,'null') from pricing.scheme s1 " +
                    " join  pricing.org s2 on s2.id = s1.id_org  " +
                    " where s2.name in ('" + country + "', '" + region + "', '" + chapter + "') " +
                    "  and id_sku ='1' " +
                    " and id_template ='" + tempType.get(templateType) + "' " +
                    " and curdate() between  effective_from   and IFNULL(effective_to,curdate()) order by s2.org_type desc limit 1;";
            TimeUnit.SECONDS.sleep(5);
            String[][] taxFlagResultForRegFee = dbConnect.queryAndRetrieveRecords(taxFlagForRegFee);
            String FlagTaxForRegFee = taxFlagResultForRegFee[0][0];
            String taxPercentageRegFee_query = "select b1.id_org,b1.org_name,b3.percent,CASE WHEN '" + FlagTaxForRegFee + "'='N' THEN 0 ELSE b3.percent END cal_tax_per from bni.Org b1 " +
                    "JOIN bni.org_tax_code b2 ON b1.id_org_tax_code = b2.id_org_tax_code " +
                    "JOIN bni.tax_code_sub_elements b3 ON b2.inbound_id_tax_code = b3.id_tax_code " +
                    "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null;  ";

            String[][] taxPercentageReg_result = dbConnect.queryAndRetrieveRecords(taxPercentageRegFee_query);

            String qval_taxReg_orgid = taxPercentageReg_result[0][0];
            String qval_taxReg_orgname = taxPercentageReg_result[0][1];
            String qval_taxReg_percent = taxPercentageReg_result[0][2];
            String qval_tax_calReg_percent = taxPercentageReg_result[0][3];
            System.out.println(" Percentage of Tax is" + qval_taxReg_percent);
            System.out.println("Tax calculated percent" + qval_tax_calReg_percent);


            // id_sku =1 for registration fee always
            String registrationFeeFromSqlQuery = " select  sl.value from pricing.scheme_line sl " +
                    " join pricing.scheme s on s.id = sl.id_scheme " +
                    " join pricing.org o on o.id = s.id_org " +
                    " where o.name in ('" + country + "', '" + region + "', '" + chapter + "') and id_sku='1'  " +
                    "  and  curdate() between  effective_from   and IFNULL(effective_to,curdate()) order by o.org_type desc limit 1; ";
            String[][] actualRegistrationFeeFromDB1 = dbConnect.queryAndRetrieveRecords(registrationFeeFromSqlQuery);
            double feevalue = Double.parseDouble(actualRegistrationFeeFromDB1[0][0]);
            double taxcal = feevalue * Double.parseDouble(qval_tax_calReg_percent) / 100;
            taxcal = Util.numberRound(taxcal, 2);
            System.out.println(Util.numberFormat(taxcal, 2));
            System.out.println("Actual Tax for Registration Fee from DB is " + (Util.numberFormat(taxcal, 2)));
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 0, i, memTerm);
            boolean setMemTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 1, i - j, memFeeTaxableFlag);
            boolean setMemPercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 2, i - j, memFeeTaxValueFromDB);
            boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 3, i - j, expectedTaxForMembershipTermFromUI);
            boolean setMemFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 4, i - j, memFeeFromDBValue);
            boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 5, i - j, expectedMembershipFeeFromUI);
            boolean setRegTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 6, i - j, FlagTaxForRegFee);
            //Tax for Registration Fee
            String expectedRegistrationFeeFromUI = BniConnectApplicationPortal.registrationFeeFetched();
            String expectedTaxForRegistrationFeeFromUI = BniConnectApplicationPortal.getTaxForRegistrationFee();
            TimeUnit.SECONDS.sleep(3);
            // assertEquals("Actual Tax for Registration Fee is not correct", expectedTaxForRegistrationFeeFromUI, (Util.numberFormat(taxcal, 2)));
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");//
            boolean setRegPercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 7, i - j, (Util.numberFormat(taxcal, 2)));
            boolean setRegPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 8, i - j, expectedTaxForRegistrationFeeFromUI);
            boolean setRegFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 9, i - j, Double.toString(feevalue));
            boolean setRegFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 10, i - j, expectedRegistrationFeeFromUI);
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.clickCompanyPaidMembership();
            BniConnectApplicationPortal.enterPayerName(data.get("payerName"));
            TimeUnit.SECONDS.sleep(3);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NMAMembershipTerm");
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.clickCOCButton();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.clickReviewApplicationButton();
            TimeUnit.SECONDS.sleep(12);
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
            TimeUnit.SECONDS.sleep(17);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NewMemberApplication");
            TimeUnit.SECONDS.sleep(12);
            BniConnectApplicationPortal.clickDownloadApplicationButton();
            System.out.println(("Download button clicked"));
            driver.close();



           // String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");

            //Reconcile the application
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            // String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);

            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String language3[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber3 = Integer.parseInt(language3[1]);
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber3, 3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber3, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);

            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String lastName = readWriteExcel.getCellData("addBrandNewVisitor", 1, 1);
            System.out.println("LAst name is " + lastName);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchText((data.get("firstName")), lastName);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NewMemberApplication");
            TimeUnit.SECONDS.sleep(5);
            reconcileApplications.clickAppStatusLink();
            TimeUnit.SECONDS.sleep(5);
            newMemberApproval = new NewMemberApproval(driver);
            newMemberApproval.clickApproveButton();
            TimeUnit.SECONDS.sleep(2);
            Alert alert = driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
            newMemberApproval.clickPDF();
            TimeUnit.SECONDS.sleep(10);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(2);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String language4[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber4 = Integer.parseInt(language4[1]);
            String transMainMenu2 = readWriteExcel.getCellData("translation", colNumber4, 3);
            String transSubMenu2 = readWriteExcel.getCellData("translation", colNumber4, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu2);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu2);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(driver);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            reconcileApplications.enterSearchText((data.get("firstName")), lastName);
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);
            //To click Send payment Link
            reconcileApplications.clickSendPaymentLink();
            Alert alert2 = driver.switchTo().alert();
            alert2.accept();
            TimeUnit.SECONDS.sleep(10);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NewMemberApplication");
            reconcileApplications.clickPaymentTypeSymbol();
            TimeUnit.SECONDS.sleep(2);
            addPaymentTypeForOnlineApplication = new AddPaymentTypeForOnlineApplication(driver);
            TimeUnit.SECONDS.sleep(2);
            addPaymentTypeForOnlineApplication.selectPaymentOption(data.get("paymentMethod"));
            TimeUnit.SECONDS.sleep(2);
            addPaymentTypeForOnlineApplication.clickSubmitButton();
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchText((data.get("firstName")), lastName);
            TimeUnit.SECONDS.sleep(2);
            //String lastName=data.get("lastName");
            reconcileApplications = new ReconcileApplications(driver);
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.enterSearchText((data.get("firstName")), lastName);
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickPaymentReceivedCheckBox();
            TimeUnit.SECONDS.sleep(2);
            Alert alert7 = driver.switchTo().alert();
            alert7.accept();
            TimeUnit.SECONDS.sleep(10);
            String name1 = data.get("firstName") + " " + data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '" + name1 + "' ;";
            String[][] invoiceAmountFromDB1 = dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceAmountFromDB = invoiceAmountFromDB1[0][0];
            String invoiceAmountFromDB2 = invoiceAmountFromDB1[0][1];
            System.out.println("Invoice Reference number from DB is " + invoiceAmountFromDB);
            System.out.println("Invoice Amount from DB is " + invoiceAmountFromDB2);
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemFeeInvoiceNumberFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 11, i - j, invoiceAmountFromDB);
            boolean setMemFeeInvoiceFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 12, i - j, invoiceAmountFromDB2);
            TimeUnit.SECONDS.sleep(4);
            reconcileApplications.clickRecncileButton();
            TimeUnit.SECONDS.sleep(5);
            reconcile = new Reconcile(driver);
            TimeUnit.SECONDS.sleep(5);
            reconcile.clickSubmitButton();
            TimeUnit.SECONDS.sleep(5);
            Alert alert6 = driver.switchTo().alert();
            alert6.accept();
            String reconcileApplicationStatus = "Pass";
            String firstName = data.get("firstName");
            //  String lastName = data.get("lastName");
            String renewalDateQuery = "select CONVERT(renewal_due_date, char) from bni.membership b1 join " +
                    " bni.user b2 on b1.id_membership = b2.id_membership where first_name ='" + firstName + "' and  last_name = '" + lastName + "' ; ";
            String[][] renewalDateStatusResult = dbConnect.queryAndRetrieveRecords(renewalDateQuery);
            String renewalDate = renewalDateStatusResult[0][0];
            System.out.println("Renewal Date is " + renewalDate);
            String[] date1 = renewalDate.split(" ");
            String exactRenewalDate = date1[0];
            System.out.println("date for renewal is " + exactRenewalDate);
            String idMembershipQuery = " select b1.id_membership from bni.membership b1 join  bni.user b2 on b1.id_membership = b2.id_membership where last_name ='" + lastName + "' ; ";
            String idMembershipApplication = "select id_membership_application from bni.membership_application b1 join  bni.user b2 on b1.id_membership = b2.id_membership where first_name ='" + firstName + "'  and last_name ='" + lastName + "'  order by id_membership_application desc ;";
            String[][] MembershipIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipQuery);
            String actualMembershipIDFromDB = MembershipIDFromDB[0][0];
            String[][] MembershipApplicationIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipApplication);
            String actualMembershipApplicationIDFromDB = MembershipApplicationIDFromDB[0][0];
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemRenewalDateFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 13, i - j, exactRenewalDate);
            boolean setReconcileAppliFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 14, i - j, reconcileApplicationStatus);
            boolean setLastNameFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 15, i - j, lastName);
            boolean setIDMembershipFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 16, i - j, actualMembershipIDFromDB);
            boolean setIdMembershipApplication = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "NMA", 17, i - j, actualMembershipApplicationIDFromDB);
            TimeUnit.SECONDS.sleep(10);
            driver.close();


        }
    }


}



























