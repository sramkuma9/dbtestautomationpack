package bni.regression.Pricing;

import bni.regression.libraries.common.*;
import bni.regression.libraries.common.email.GmailClient;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.Reconcile;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class CreateAMemberV2 {
    public static WebDriver driver;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    DbConnect dbConnect = new DbConnect();
    private AddAVisitor addAVisitor;
    private CaptureScreenShot captureScreenShot;
    private ViewEditVisitorsList viewEditVisitorsList;
    private EnterNewApplication enterNewApplication;
    public static String fixedDateTime;
    public static String visitorDateTime;
    private Add add;
    public List<List<String>> loginSubList;
    public String[] convertToMemberDetails = new String[8];
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    private bni.regression.libraries.ui.Reconcile reconcile = new Reconcile();
    private GmailClient gmailClient;
    private EnterNewMember enterNewMember;
    private ReconcileApplications reconcileApplications;


    @Before
    public void setup() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


     @Given("^Enter new Email ID and enter the details of the individual and add a new member following V2 pricing$")
        public void enterNewEmailIDAndEnterTheDetailsOfTheIndividualAndAddANewMemberfollowingV2pricing(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I Create a member via Enter New application, and add a member to a region following Vpricing$")
    public void iCreateAMemberViaEnterNewApplicationAndAddAMemberToARegionFollowingVPricing(DataTable createAMember) throws Exception{


        Integer i = 1;
        Integer j = 2;
        for (Map<String, String> data : createAMember.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(j - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
           TimeUnit.SECONDS.sleep(2);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);

           bniConnect.navigateMenu("OPERATIONS,Region");
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 5);
            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(5);
            enterNewApplication = new EnterNewApplication(driver);
            TimeUnit.SECONDS.sleep(24);
            String dateTimeStamp = currentDateTime.dateTime();
            visitorDateTime = (dateTimeStamp.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
            String hours = visitorDateTime.substring(11, 14);
            String lastName = data.get("lastName") + hours;
            enterNewApplication.enterEmail(data.get("firstName") + lastName + "@gmail.com");
            TimeUnit.SECONDS.sleep(2);
            enterNewApplication.clickSearchButton();
            TimeUnit.SECONDS.sleep(2);
            enterNewApplication.clickSearchByNameButton();
            TimeUnit.SECONDS.sleep(2);
            enterNewApplication.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(2);
            enterNewApplication.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            enterNewApplication.clickSearchMemberButton();
            TimeUnit.SECONDS.sleep(2);
            enterNewApplication.clickEnterNewMemberButton();
            TimeUnit.SECONDS.sleep(2);
            enterNewMember = new EnterNewMember(driver);
            enterNewMember.clickApplicationDateField();
            TimeUnit.SECONDS.sleep(2);
            enterNewMember.selectVisitYear(data.get("year"));
            TimeUnit.SECONDS.sleep(2);
            enterNewMember.selectVisitMonth(data.get("month"));
            TimeUnit.SECONDS.sleep(2);
            enterNewMember.selectDateFromDatePicker(data.get("day"));
            TimeUnit.SECONDS.sleep(2);
            enterNewMember.selectChapter(data.get("chapter"));
            TimeUnit.SECONDS.sleep(1);
            enterNewMember.selectProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(1);
            enterNewMember.selectSpeciality(data.get("speciality"));
            TimeUnit.SECONDS.sleep(1);
            enterNewMember.selectLanguage(data.get("language"));
            TimeUnit.SECONDS.sleep(4);
            enterNewMember.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(1);
            enterNewMember.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(1);
            enterNewMember.enterAddressLine1(data.get("addressLine1"));
            TimeUnit.SECONDS.sleep(1);
            enterNewMember.enterPhone(data.get("phone"));
            TimeUnit.SECONDS.sleep(1);
            enterNewMember.selectPaymentOption(data.get("paymentOption"));
            TimeUnit.SECONDS.sleep(1);
            enterNewMember.selectMemberShipOption(data.get("membershipOption"));
            TimeUnit.SECONDS.sleep(1);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "CreateAMember");
            enterNewMember.clickSubmitButton();
            TimeUnit.SECONDS.sleep(15);
            HashMap<String, String> memOption = new HashMap<String, String>();
            memOption.put("12 Month", "2");
            memOption.put("24 Month", "3");
            memOption.put("6 Month", "7");
            memOption.put("12 Month BNI#", "2");
            memOption.put("24 Month BNI#", "3");
            memOption.put("6 Month BNI#", "7");
            memOption.put("12 Month S", "2");
            memOption.put("24 Month S", "3");
            memOption.put("6 Month S", "7");
            memOption.put("12 Months", "2");
            memOption.put("24 Months", "3");
            memOption.put("6 Months", "7");
            memOption.put("12 Month Sgb", "2");
            memOption.put("24 Month Sgb", "3");
            memOption.put("6 Month Sgb", "7");
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


            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(2);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);

            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(2);
//            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
//            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
//            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String MainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
            String SubMenu = readWriteExcel.getCellData("translation", colNumber, 6);
            bniConnect.selectItemFromMainListMenu(MainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(SubMenu);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchCriteria(data.get("firstName"),data.get("lastName"));
            TimeUnit.SECONDS.sleep(5);
            reconcileApplications.clickCGCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickSuspendedChaptersCheckBox();
            TimeUnit.SECONDS.sleep(1);
            reconcileApplications.clickPaymentReceivedCheckBox();
            Alert alert =  driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
            String name = data.get("firstName") + " " + data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '" + name + "'  ;";
            String[][] invoiceAmountFromDB1 = dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceReferenceNumberDB= invoiceAmountFromDB1[0][0];
            String invoiceAmountFromDB2 = invoiceAmountFromDB1[0][1];
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "CreateAMember");
            reconcileApplications.clickRecncileButton();
            Alert alert2 = driver.switchTo().alert();
            alert2.accept();
            TimeUnit.SECONDS.sleep(10);

            String country=data.get("country");
            System.out.println("Country is" +country);
            String region =data.get("region");
            System.out.println("Region is" +region);
            String chapter = data.get("chapter");
            String firstName=data.get("firstName");
            String lastname1 = data.get("lastName");
            String memTerm = data.get("membershipOption");
            String appDate = data.get("applicationDate");
            String membershipFeeFromSqlQuery =   "select  m3.email_address, m3.first_name,m3.last_name," +
                    "m4.membership_fee, m4.registration_fee,m4.add_on_fee,m4.late_fee,m4.application_date," +
                    " m4.term_months, m4.id_application_status ,m4.service_start_date, m4.reconciled_on " +
                    "from bni.membership_application m4 " +
                    "join bni.user m3 on m3.id_membership= m4.id_membership" +
                    " where m3.first_name ='"+firstName+"' and m3.last_name ='"+lastname1+"'; " ;
            String[][] actualMembershipFeeFromDB1 = dbConnect.queryAndRetrieveRecords(membershipFeeFromSqlQuery);
           System.out.println(actualMembershipFeeFromDB1.toString());
           String memFeeQuery =" select  term_months,membership_fee, registration_fee, invoice_reference,total_amount  from bni.membership_application m4 " +
             "  join bni.user m3 on m3.id_membership= m4.id_membership " +
             " join bni.invoice m5 on m5.id_user =m3.id_user " +
             "  where m3.first_name ='Selenium' and m3.last_name ='Bni+v5816'; ";
            String[][] actualMembershipFeeFromDB = dbConnect.queryAndRetrieveRecords(memFeeQuery);
            String memFeeFromDB = actualMembershipFeeFromDB[0][1];
            String regFeeFromDB = actualMembershipFeeFromDB[0][2];
            String renewalDateQuery2 = "select CONVERT(renewal_due_date, char) from bni.membership b1 join " +
                    " bni.user b2 on b1.id_membership = b2.id_membership where last_name ='"+lastname1+"' ; " ;
            String[][] renewalDateStatusResult2 = dbConnect.queryAndRetrieveRecords(renewalDateQuery2);
            String renewalDate2 = renewalDateStatusResult2[0][0];
            System.out.println("Renewal Date is " +renewalDate2);
            String[] date12 = renewalDate2.split(" ");
            String exactRenewalDate2 = date12[0];
            System.out.println("date for renewal is " + exactRenewalDate2);
            SimpleDateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("Query Date : "+formatter2.format(formatter2.parse(exactRenewalDate2)));
            String taxFlag_query = "select s2.id,s2.name,s1.taxable_flag " +
                    "from pricing.scheme s1 " +
                    "JOIN pricing.org s2 ON s1.id_org = s2.id " +
                    "where '"+appDate+"' between s1.effective_from and IFNULL(s1.effective_to,'"+appDate+"') " +
                    "and s1.id_sku = '"+memOption.get(memTerm)+"' " +
                    "and s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "order by s2.org_type desc limit 1";
            String[][] taxFlag_result = dbConnect.queryAndRetrieveRecords(taxFlag_query);
            String qval_taxflag_orgid = taxFlag_result[0][0];
            String qval_taxflag_orgname = taxFlag_result[0][1];
            String memFeeTaxableFlag = taxFlag_result[0][2];
            System.out.println("Taxable Flag from DB is " +memFeeTaxableFlag);
            String taxPercentage_query = "select b1.id_org,b1.org_name,b3.percent,CASE WHEN '"+memFeeTaxableFlag+"'='N' THEN 0 ELSE b3.percent END cal_tax_per from bni.Org b1 " +
                    "JOIN bni.org_tax_code b2 ON b1.id_org_tax_code = b2.id_org_tax_code " +
                    "JOIN bni.tax_code_sub_elements b3 ON b2.inbound_id_tax_code = b3.id_tax_code " +
                    "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null;  ";
            String[][] taxPercentage_result = dbConnect.queryAndRetrieveRecords(taxPercentage_query);
            String qval_tax_orgid = taxPercentage_result[0][0];
            String qval_tax_orgname = taxPercentage_result[0][1];
            String taxPercentage = taxPercentage_result[0][2];
            String qval_tax_cal_percent =taxPercentage_result [0][3];
            System.out.println(" Percentage of Tax is" +taxPercentage);
            System.out.println("Tax calculated percent" +qval_tax_cal_percent);
            String taxCalculation_query = "select s2.id,s2.name,ifnull(s5.option,'DEFAULT') variable_option,s3.value,(Round(s3.value*("+qval_tax_cal_percent+")/100,2)) cal_tax_value " +
                    "from pricing.scheme s1 " +
                    "JOIN pricing.org s2 ON s1.id_org = s2.id " +
                    "JOIN pricing.scheme_line s3 ON s1.id = s3.id_scheme " +
                    "LEFT JOIN pricing.scheme_line_option s4 ON s3.id = s4.id_scheme_line " +
                    "LEFT JOIN pricing.variable_option s5 ON s4.id_variable_option = s5.id " +
                    "where '"+appDate+"' between s1.effective_from and IFNULL(s1.effective_to,'"+appDate+"') " +
                    "and s1.id_sku = '"+memOption.get(memTerm)+"' " +
                    //   "and s2.id="+qval_taxflag_orgid+" " +
                    "  and id_template ='"+tempType.get(templateType)+"' "  +
                    "and s2.name in ('" + country + "', '" + region + "', '" + chapter + "')" +
                    "and ifnull(s5.option,'DEFAULT') in ('"+individualOption+"','"+chapterOption+"') " +
                    "order by s2.org_type desc limit 1; ";

            String[][] taxCalculation_result = dbConnect.queryAndRetrieveRecords(taxCalculation_query);
            String memFeeVarOption = taxCalculation_result[0][2];
            String memFeeFromDBValue = taxCalculation_result[0][3];
            String memFeeTaxValueFromDB = taxCalculation_result[0][4];
            System.out.println("Membership fee from DB   is " +memFeeFromDBValue);
            System.out.println("Membership Tax value from DB   is " +memFeeTaxValueFromDB);
            String taxFlagForRegFee = " select IFNULL(taxable_flag,'null') from pricing.scheme s1 " +
                    " join  pricing.org s2 on s2.id = s1.id_org  " +
                    " where s2.name in ('" + country + "', '" + region + "', '" + chapter + "') " +
                    "  and id_sku ='1' " +
                    " and id_template ='" + tempType.get(templateType) + "' " +
                    " and '"+appDate+"' between  effective_from   and IFNULL(effective_to,'"+appDate+"') order by s2.org_type desc limit 1;";
            TimeUnit.SECONDS.sleep(5);
            String[][] taxFlagResultForRegFee = dbConnect.queryAndRetrieveRecords(taxFlagForRegFee);
            String FlagTaxForRegFee = taxFlagResultForRegFee[0][0];
            String taxPercentageRegFee_query = "select b1.id_org,b1.org_name,b3.percent,CASE WHEN '"+FlagTaxForRegFee+"'='N' THEN 0 ELSE b3.percent END cal_tax_per from bni.Org b1 " +
                    "JOIN bni.org_tax_code b2 ON b1.id_org_tax_code = b2.id_org_tax_code " +
                    "JOIN bni.tax_code_sub_elements b3 ON b2.inbound_id_tax_code = b3.id_tax_code " +
                    "where b1.org_name in ('" + region + "') and b1.id_org_type= '3' and b3.percent_effective_to is null;  ";
            String[][] taxPercentageReg_result = dbConnect.queryAndRetrieveRecords(taxPercentageRegFee_query);
            String qval_taxReg_orgid = taxPercentageReg_result[0][0];
            String qval_taxReg_orgname = taxPercentageReg_result[0][1];
            String qval_taxReg_percent = taxPercentageReg_result[0][2];
            String qval_tax_calReg_percent =taxPercentageReg_result [0][3];
            System.out.println(" Percentage of Tax is" +qval_taxReg_percent);
            System.out.println("Tax calculated percent" +qval_tax_calReg_percent);
            // id_sku =1 for registration fee always
            String registrationFeeFromSqlQuery = " select  sl.value, (Round(sl.value *('" +qval_tax_calReg_percent+ "'  )/ 100,2)) from pricing.scheme_line sl " +
                    " join pricing.scheme s on s.id = sl.id_scheme " +
                    " join pricing.org o on o.id = s.id_org " +
                    " where o.name in ('" + country + "', '" + region + "', '" + chapter + "') and id_sku='1'  " +
                    "  and  '"+appDate+"' between  effective_from   and IFNULL(effective_to,'"+appDate+"') order by o.org_type desc limit 1; ";
            String[][] actualRegistrationFeeFromDB1 = dbConnect.queryAndRetrieveRecords(registrationFeeFromSqlQuery);
            String actualRegistrationFeeFromDB = actualRegistrationFeeFromDB1[0][0];
            String actualTaxForRegistrationFeeFromDB = actualRegistrationFeeFromDB1[0][1];
            System.out.println("Actual Registration Fee from DB is " + actualRegistrationFeeFromDB);
            double feevalue = Float.parseFloat(actualRegistrationFeeFromDB1[0][0]);
            double taxpercent = Float.parseFloat(actualRegistrationFeeFromDB1[0][1]);
            double taxcal = feevalue*taxpercent/100;
            taxcal = Util.numberRound(taxcal,2);
            System.out.println(Util.numberFormat(taxcal,2));
            System.out.println("Actual Tax for Registration Fee from DB is " +(Util.numberFormat(taxcal,2)));
            String reconcileApplicationStatus = "Pass";
            String idMembershipQuery = " select b1.id_membership from bni.membership b1 join  bni.user b2 on b1.id_membership = b2.id_membership where last_name ='"+lastname1+"' ; " ;
            String[][] MembershipIDFromDB = dbConnect.queryAndRetrieveRecords(idMembershipQuery);
            String actualMembershipIDFromDB = MembershipIDFromDB[0][0];
            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setMemOptionFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 0, j, memTerm);
            boolean setMemTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 1, j-i, memFeeTaxableFlag);
            boolean setMemPercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 2, j-i, memFeeTaxValueFromDB);
            boolean setMemFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 3, j-i, memFeeFromDBValue);
            boolean setRegTaxFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 4, j-i, FlagTaxForRegFee);
            boolean setRegPercentDBTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 5, j-i, (Util.numberFormat(taxcal,2)));
            boolean setRegFeeDBFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 6, j-i, actualRegistrationFeeFromDB);
            boolean setMemFeeInvoiceNumberFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 7, j-i, invoiceReferenceNumberDB);
            boolean setMemFeeInvoiceFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 8, j-i, invoiceAmountFromDB2);
            boolean setRenewalDateFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 9, j-i, exactRenewalDate2);
            boolean setReconcileAppliFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 10, j-i, reconcileApplicationStatus);
            boolean setIDMembershipFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "ENA", 11, j-i, actualMembershipIDFromDB);
            i++;
            signOut.signOutBni();



        }
    }



}