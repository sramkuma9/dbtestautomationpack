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
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.TestCase.assertEquals;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NewMemberApplicationviaGenericLinkV2 {
    public List<List<String>> loginSubList;
    public static WebDriver driver;
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    private CaptureScreenShot captureScreenShot;
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private ViewRegionBusinessRules viewRegionBusinessRules;
    private EditBusinessRules editBusinessRules;
    private BNIConnectApplicationPortal bniConnectApplicationPortal;
    private ReconcileApplications reconcileApplications;
    private GmailClient gmailClient = new GmailClient();
    private NewMemberApproval newMemberApproval;
    private Reconcile reconcile;
    private ChangePaymentType changePaymentType;
    DbConnect dbConnect = new DbConnect();

    private AddPaymentTypeForOnlineApplication addPaymentTypeForOnlineApplication;

    @Before
    public void setup() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Given("^New Member Application via Generic link via VPricing$")
    public void newMemberApplicationViaGenericLinkViaVPricing(DataTable loginDetails) throws Throwable {


        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());

    }



    @When("^I Copy the Form link and Register the member via VPricing$")
    public void iCopyTheFormLinkAndRegisterTheMemberViaVPricing(DataTable genericLink) throws Exception {


        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : genericLink.asMaps(String.class, String.class)) {
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String url = readWriteExcel.getCellData("GenericApplyNow", 0, 0);
            System.out.println("URL is " + url);
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.navigate().to(url);
            //Registration
            bniConnect = new BNIConnect(driver);
           Registration registration = new Registration(driver);
            registration.enterNewPassword(data.get("NewPassword"));
            registration.enterConfirmPassword(data.get("ConfirmPassword"));
            TimeUnit.SECONDS.sleep(5);
            registration.clickNextButton();
            TimeUnit.SECONDS.sleep(12);
            //Form Section
            BNIConnectApplicationPortal bniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
            bniConnectApplicationPortal.enterChapter(data.get("chapter"));
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.enterCity(data.get("city"));
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.selectMeetingDay(data.get("meetingDay"));
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.clickSearchButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.clickChapterRadioButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.clickApplicationFormButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0, 450)", "");
            bniConnectApplicationPortal.enterPersonalLanguage(data.get("language"));
            TimeUnit.SECONDS.sleep(3);
            bniConnectApplicationPortal.enterPersonalPhoneNumber(data.get("phoneNumber"));
            TimeUnit.SECONDS.sleep(2);
            JavascriptExecutor js4 = (JavascriptExecutor) driver;
            js4.executeScript("window.scrollBy(0, 450)", "");
            bniConnectApplicationPortal.enterPersonalAddressLine(data.get("addressLine1"));
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js31 = (JavascriptExecutor) driver;
            js31.executeScript("window.scrollBy(0, 250)", "");
            JavascriptExecutor js3 = (JavascriptExecutor) driver;
            js3.executeScript("window.scrollBy(0, 250)", "");
            bniConnectApplicationPortal.enterIndustryList(data.get("industry"));
            TimeUnit.SECONDS.sleep(1);
            bniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(3);
            bniConnectApplicationPortal.selectMembershipTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(18);
            String country = data.get("country");
            String region = data.get("region");
            String chapter = data.get("chapter");
            String memTerm = data.get("membershipTerm");
            HashMap<String, String> memOption = new HashMap<String, String>();
            memOption.put("12 Month", "2");
            memOption.put("24 Month", "3");
            memOption.put("12 Months", "2");
            memOption.put("24 Months", "3");
            memOption.put("12 Month BNI#", "2");
            memOption.put("24 Month BNI#", "3");
            memOption.put("12 Month S", "2");
            memOption.put("24 Month S", "3");
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
            String memFeeFromDBValue = (taxCalculation_result[0][3]);
            String memFeeTaxValueFromDB = taxCalculation_result[0][4];
            System.out.println("Membership fee from DB   is " + memFeeFromDBValue);
            System.out.println("Tax for Membership fee from DB   is " + memFeeTaxValueFromDB);
            double taxcal = Double.parseDouble(memFeeFromDBValue) * Double.parseDouble(qval_tax_cal_percent) / 100;
            taxcal = Util.numberRound(taxcal, 2);
            System.out.println(Util.numberFormat(taxcal, 2));
            System.out.println("Actual Tax for Registration Fee from DB is " + (Util.numberFormat(taxcal, 2)));
            String expectedMembershipFeeFromUI = bniConnectApplicationPortal.getFeeForMembershipTerm();
            String expectedTaxForMembershipTermFromUI = bniConnectApplicationPortal.getTaxForMembershipTerm();
            TimeUnit.SECONDS.sleep(3);
//            assertEquals("Actual Tax for MembershipFee is not correct", expectedTaxForMembershipTermFromUI, (Util.numberFormat(taxcal, 2)));
//            assertEquals("Actual  MembershipFee is not correct", expectedMembershipFeeFromUI, memFeeFromDBValue);
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
            String registrationFeeFromSqlQuery = " select  sl.value, (Round(sl.value *('" + qval_tax_calReg_percent + "')/ 100,2)) from pricing.scheme_line sl " +
                    " join pricing.scheme s on s.id = sl.id_scheme " +
                    " join pricing.org o on o.id = s.id_org " +
                    " where o.name in ('" + country + "', '" + region + "', '" + chapter + "') and id_sku='1'  " +
                    "  and  curdate() between  effective_from   and IFNULL(effective_to,curdate()) order by o.org_type desc limit 1; ";
            String[][] actualRegistrationFeeFromDB1 = dbConnect.queryAndRetrieveRecords(registrationFeeFromSqlQuery);
            String actualRegistrationFeeFromDB = actualRegistrationFeeFromDB1[0][0];
            String actualTaxForRegistrationFeeFromDB = actualRegistrationFeeFromDB1[0][1];
            System.out.println("Actual Registration Fee from DB is " + actualRegistrationFeeFromDB);
            System.out.println("Actual Tax for Registration Fee from DB is " + actualTaxForRegistrationFeeFromDB);
            double feevalue = Float.parseFloat(actualRegistrationFeeFromDB1[0][0]);
            double taxpercent = Float.parseFloat(actualRegistrationFeeFromDB1[0][1]);
            double taxcal2 = feevalue * taxpercent / 100;
            taxcal2 = Util.numberRound(taxcal2, 2);
            System.out.println(Util.numberFormat(taxcal2, 2));
            System.out.println("Actual Tax for Registration Fee from DB is " + (Util.numberFormat(taxcal2, 2)));
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 0, i, memTerm);
            boolean setMemTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 1, i - j, memFeeTaxableFlag);
            boolean setMemPercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 2, i - j, (Util.numberFormat(taxcal, 2)));
            boolean setMemPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 3, i - j, expectedTaxForMembershipTermFromUI);
            boolean setMemFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 4, i - j, (memFeeFromDBValue));
            boolean setMemFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 5, i - j, expectedMembershipFeeFromUI);
            boolean setRegTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 6, i - j, FlagTaxForRegFee);
            //Tax for Registration Fee
            String expectedRegistrationFeeFromUI = bniConnectApplicationPortal.registrationFeeFetched();
            String expectedTaxForRegistrationFeeFromUI = bniConnectApplicationPortal.getTaxForRegistrationFee();
            TimeUnit.SECONDS.sleep(3);
//            assertEquals("MembershipFee is not correct", expectedRegistrationFeeFromUI, actualRegistrationFeeFromDB);
//            assertEquals("Actual Tax for Registration Fee is not correct", expectedTaxForRegistrationFeeFromUI, (Util.numberFormat(taxcal2, 2)));
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");//
            boolean setRegPercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 7, i - j, actualTaxForRegistrationFeeFromDB);
            boolean setRegPercentUITax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 8, i - j, (Util.numberFormat(taxcal2, 2)));
            boolean setRegFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 9, i - j, actualRegistrationFeeFromDB);
            boolean setRegFeeUIFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 10, i - j, expectedRegistrationFeeFromUI);
            JavascriptExecutor js21 = (JavascriptExecutor) driver;
            js21.executeScript("window.scrollBy(0, 250)", "");
            bniConnectApplicationPortal.clickCompanyPaidMembership();
            JavascriptExecutor js5 = (JavascriptExecutor) driver;
            js5.executeScript("window.scrollBy(0, 250)", "");
            bniConnectApplicationPortal.enterPayerName(data.get("payerName"));
            TimeUnit.SECONDS.sleep(1);
            bniConnectApplicationPortal.clickCOCButton();
            TimeUnit.SECONDS.sleep(1);
            bniConnectApplicationPortal.clickReviewApplicationButton();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js6 = (JavascriptExecutor) driver;
            js6.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(8);
            bniConnectApplicationPortal.clickSubmitApplicationbutton();
            TimeUnit.SECONDS.sleep(10);
            bniConnectApplicationPortal.clickDownloadApplicationButton();
            TimeUnit.SECONDS.sleep(10);
            //Reconcile
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
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
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String language3[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber3 = Integer.parseInt(language3[1]);
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber3, 3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber3, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            reconcileApplications.enterSearchText((data.get("firstName")), (data.get("lastName")));
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NewMemberApplicationGenericLink");
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
            newMemberApproval.clickBackButton();
            reconcileApplications = new ReconcileApplications(driver);
            reconcileApplications.enterSearchText((data.get("firstName")), data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            //To click Send payment Link
            reconcileApplications.clickSendPaymentLink();
            Alert alert2 = driver.switchTo().alert();
            alert2.accept();
            TimeUnit.SECONDS.sleep(10);
            reconcileApplications.clickPaymentTypeSymbol();
            TimeUnit.SECONDS.sleep(2);
            addPaymentTypeForOnlineApplication = new AddPaymentTypeForOnlineApplication(driver);
            TimeUnit.SECONDS.sleep(2);
            addPaymentTypeForOnlineApplication.selectPaymentOption(data.get("paymentMethod"));
            TimeUnit.SECONDS.sleep(2);
            addPaymentTypeForOnlineApplication.clickSubmitButton();
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications = new ReconcileApplications(driver);
            reconcileApplications.enterSearchText((data.get("firstName")), data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickPaymentReceivedCheckBox();
            TimeUnit.SECONDS.sleep(5);
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
            boolean setMemFeeInvoiceNumberFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 11, i - j, invoiceAmountFromDB);
            boolean setMemFeeInvoiceFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 12, i - j, invoiceAmountFromDB2);
            TimeUnit.SECONDS.sleep(4);
            String firstName = data.get("firstName");
            String lastName = data.get("lastName");
            String idMembershipApplication = "select id_membership_application from bni.membership_application b1 join  bni.user b2 on b1.id_membership = b2.id_membership where first_name ='" + firstName + "'  and last_name ='" + lastName + "'  order by id_membership_application desc ;";
            reconcileApplications.enterSearchText((data.get("firstName")), (data.get("lastName")));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickRecncileButton();
            TimeUnit.SECONDS.sleep(5);
            reconcile = new Reconcile(driver);
            TimeUnit.SECONDS.sleep(5);
            reconcile.clickSubmitButton();
            TimeUnit.SECONDS.sleep(5);
            Alert alert6 = driver.switchTo().alert();
            alert6.accept();
            String reconcileApplicationStatus = "Pass";
            String emailAddress = data.get("email ");
            String renewalDateQuery = "select CONVERT(renewal_due_date, char) from bni.membership b1 join " +
                    " bni.user b2 on b1.id_membership = b2.id_membership where email_address ='" + emailAddress + "'; ";
            String[][] renewalDateStatusResult = dbConnect.queryAndRetrieveRecords(renewalDateQuery);
            String renewalDate = renewalDateStatusResult[0][0];
            System.out.println("Renewal Date is " + renewalDate);
            String[] date1 = renewalDate.split(" ");
            String exactRenewalDate = date1[0];
            System.out.println("date for renewal is " + exactRenewalDate);
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemRenewalDateFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 13, i - j, exactRenewalDate);
            boolean setReconcileAppliFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 14, i - j, reconcileApplicationStatus);
            boolean setIdMembershipApplication = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "GenericNMA", 15, i - j, idMembershipApplication);
            TimeUnit.SECONDS.sleep(10);
            System.out.println("Member created successfully");
            driver.close();


        }
    }


}







































