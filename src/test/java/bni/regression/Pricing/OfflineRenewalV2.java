

package bni.regression.Pricing;

import bni.regression.libraries.common.*;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.Reconcile;
import bni.regression.pageFactory.*;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class OfflineRenewalV2 {


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
    private ReconcileApplications reconcileApplications;

    private Reconcile reconcile = new Reconcile();
    private EditProfile editProfile;
    private ManageMembers manageMembers;
    private RenewMember renewMember;
    DbConnect dbConnect = new DbConnect();

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Given("^A member is added newly to the region following V2 pricing and member status is active now\\.$")
    public void a_member_is_added_newly_to_the_region_following_V2_pricing_and_member_status_is_active_now(DataTable loginDetails) {

        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I navigate to Operations ->Chapter ->Manage Memberships -> Manage Members\\. Enter firstname and last name and enter search members button following V2 pricing\\.$")
    public void step_2(DataTable offlineRenewal) throws Exception {
        Integer i = 2;
        Integer j = 1;

        for (Map<String, String> data : offlineRenewal.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(8);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 4);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(5);
            manageMembers = new ManageMembers(driver);
            manageMembers.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(2);
            manageMembers.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            manageMembers.clickSearchMembers();
            TimeUnit.SECONDS.sleep(5);
            manageMembers.clickEditMember();
            TimeUnit.SECONDS.sleep(8);
            //Edit profile and click renew button for offline renewal
            editProfile = new EditProfile(driver);
            editProfile.clickMemberShipDetailsButton();
            TimeUnit.SECONDS.sleep(5);
            editProfile.clickRenewButton();
            TimeUnit.SECONDS.sleep(8);
            renewMember = new RenewMember(driver);
            renewMember.selectProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(2);
            renewMember.selectSpeciality(data.get("speciality"));
            TimeUnit.SECONDS.sleep(2);
            renewMember.clickRenewalApplicationDate();
            TimeUnit.SECONDS.sleep(8);
            renewMember.selectYear(data.get("periodYear"));
            TimeUnit.SECONDS.sleep(2);
            renewMember.selectMonth(data.get("periodMonth"));
            TimeUnit.SECONDS.sleep(2);
            renewMember.selectDateFromDatePicker(data.get("day"));
            TimeUnit.SECONDS.sleep(2);
            String memTerm = data.get("membershipPeriod");
            HashMap<String, String> memOption = new HashMap<String, String>();
            memOption.put("12 Month", "5");
            memOption.put("24 Month", "6");
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
            String country = data.get("country");
            System.out.println("Country is" + country);
            String region = data.get("region");
            System.out.println("Region is" + region);
            String chapter = data.get("chapter");
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemOptionFlag2 = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 0, i, memTerm);
            renewMember.selectMembershipPeriod(data.get("membershipPeriod"));
            TimeUnit.SECONDS.sleep(2);
            renewMember.selectPaymentMode(data.get("paymentMode"));
            TimeUnit.SECONDS.sleep(2);
            renewMember.clickSubmitButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu2 = readWriteExcel.getCellData("translation", colNumber, 3);
            bniConnect.selectItemFromMainListMenu(transMainMenu2);
            TimeUnit.SECONDS.sleep(3);
            String transSubMenu2 = readWriteExcel.getCellData("translation", colNumber, 6);
            bniConnect.selectItemFromSubListMenu(transSubMenu2);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchCriteria(data.get("firstName"), data.get("lastName"));
            TimeUnit.SECONDS.sleep(5);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "OfflineRenewal");
            reconcileApplications.clickPaymentReceivedCheckBox();
            TimeUnit.SECONDS.sleep(3);
            Alert alert = LaunchBrowser.driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(20);
            String name1 = data.get("firstName") + " " + data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '" + name1 + "' order by invoice_date desc;";
            String[][] invoiceAmountFromDB1 = dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceAmountFromDB = invoiceAmountFromDB1[0][0];
            String invoiceAmountFromDB2 = invoiceAmountFromDB1[0][1];
            System.out.println("Invoice Reference number from DB is " + invoiceAmountFromDB);
            System.out.println("Invoice Amount from DB is " + invoiceAmountFromDB2);
            reconcileApplications.clickRecncileButton();
           Alert  alert2 = driver.switchTo().alert();
            alert2.accept();
            TimeUnit.SECONDS.sleep(10);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "OfflineRenewal");
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
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 1, i - j, qval_taxable_flag);
            boolean setMemPercentTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 2, i - j, qval_var_option_cal_tax_value);
            boolean setMemFeeFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 3, i - j, qval_var_option_fee_value);
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
                System.out.println("Actual Tax for Registration Fee from DB is " + (Util.numberFormat(taxcal, 2)));
                String idMembershipQuery = " select b1.id_membership from bni.membership b1 join  bni.user b2 on b1.id_membership = b2.id_membership where last_name ='" + lastName + "' ; ";
                String[][] MembershipIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipQuery);
                String actualMembershipIDFromDB = MembershipIDFromDB[0][0];
                String idMembershipApplication = "select id_membership_application from bni.membership_application b1 join  bni.user b2 on b1.id_membership = b2.id_membership where first_name ='" + firstName + "'  and last_name ='" + lastName + "'  order by id_membership_application desc ;";
                String[][] MembershipApplicationIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipApplication);
                String actualMembershipApplicationIDFromDB = MembershipApplicationIDFromDB[0][0];
                readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
                boolean setLateTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 4, i - j, FlagTaxForLateFee);
                boolean setLatePercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 5, i - j, (Util.numberFormat(taxcal, 2)));
                boolean setLateFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 6, i - j, actualLateFeeFromDB);
                boolean setIDMembershipFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 7, i - j, actualMembershipIDFromDB);
                boolean setIDMembershipApplicationFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 8, i - j, actualMembershipApplicationIDFromDB);
                boolean setInvoiceReferenceFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 9, i - j, invoiceAmountFromDB);
                boolean setInvoiceAmountFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "OFFLINERENEWAL", 10, i - j, invoiceAmountFromDB2);

            }

            signOut.signOutBni();



        }
    }


}





