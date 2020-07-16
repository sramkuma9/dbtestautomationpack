package bni.regression.Pricing;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private  Reconcile reconcile;
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

        @When("^I login BNI app with Member login details then click Renew Now button in the home page and enter the below details click Proceed to payment button following V2 pricing\\.$")
        public void iLoginBNIAppWithMemberLoginDetailsThenClickRenewNowButtonInTheHomePageAndEnterTheBelowDetailsClickProceedToPaymentButton(DataTable SLR) throws Exception {
                Integer i = 2;
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
//       Alert alert = driver.switchTo().alert();
//        alert.accept();
            TimeUnit.SECONDS.sleep(3);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            renewalApplication = new RenewalApplication(driver);
            TimeUnit.SECONDS.sleep(8);
            JavascriptExecutor js42 = (JavascriptExecutor) driver;
            js42.executeScript("window.scrollBy(0, 250)", "");
            System.out.println("Next button to be clicked56");
             TimeUnit.SECONDS.sleep(10);
            System.out.println("Next button to be clicked");
            renewalApplication.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(5);
            String country=data.get("country");
            String region = data.get("region");
            String chapter = data.get("chapter");
            System.out.println("Country is" +country);
            String memTerm = data.get("membershipTerm");
            HashMap<String , String> memOption = new HashMap<String , String>();
            memOption.put("12 Month", "5");
            memOption.put("24 Month","6");
            memOption.put("12 Month BNI#", "5");
            memOption.put("24 Month BNI#","6");
            memOption.put("12 Month S", "5");
            memOption.put("24 Month S","6");
            memOption.put("12 Month S", "5");
            memOption.put("24 Month S","6");
            String option = data.get("option");
            String option2 = data.get("option2");
            String templateType = data.get("templateType");
            HashMap<String, String> tempType = new HashMap<>();
            tempType.put("Organisation", "1");
            tempType.put("Individual Type", "2");
            tempType.put("Chapter Status Type", "3");
            tempType.put("Individual and Chapter Status Type", "4");


            renewalApplication.selectMembershipTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(18);

//            String taxPercentage ="select b1.percent from bni.tax_code_sub_elements b1 " +
//                    " join  bni.org_tax_code b2 on b2.inbound_id_tax_code = b1.id_tax_code " +
//                    " join bni.Org b3 on b3.id_org_tax_code = b2.id_org_tax_code where b3.org_name ='"+country+"' and percent_effective_to is null; ";
//
//            String[][] taxCalculatedPercent = dbConnect.queryAndRetrieveRecords(taxPercentage);
//            String taxPercent1 = taxCalculatedPercent[0][0];
//           // String taxPercent2 = taxCalculatedPercent[0][1];
//            System.out.println("Tax percentage from SQL query is " +taxPercent1);
           // System.out.println("Tax percentage from SQL query is" +taxPercent2);

            String taxFlagForMemFee ="  select taxable_flag from pricing.scheme s1 join " +
                    " pricing.org s2 on s2.id = s1.id_org where s2.name in ('" + country + "', '" + region + "', '" + chapter + "') " +
                    "   and id_template ='"+tempType.get(templateType)+"' " +
                    "   and id_sku ='" + memOption.get(memTerm) + "' order by s2.org_type desc limit 1;";
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

            if (FlagTaxForMemFee.equals("N"))
            {
                taxPercent1="null";
            }




//            String membershipFeeFromSqlQuery =   "select value, , (Round(s1.value *'" + taxPercent1 + "' )/ 100) from pricing.scheme_line  s1 " +
//                    " join pricing.scheme s2 on s2.id =s1.id_scheme "+
//                    " join pricing.sku s3 on s3.id =s2.id_sku " +
//                    " join pricing.org s4 on s4.id =s2.id_org and  s2.id_sku='"+memOption.get(memTerm)+"'  and  s4.name='"+country+"'; ";


            String membershipFeeFromSqlQuery =   " select s1.value , (Round(s1.value *'" + taxPercent1 + "' )/ 100) from pricing.scheme_line s1 " +
                    " join pricing.scheme s2 on s2.id = s1.id_scheme " +
                    " join pricing.org s3 on s3.id =s2.id_org " +
                    " join    pricing.variable_option s5   where id_template='"+tempType.get(templateType)+"' " +
                    " and id_sku ='" + memOption.get(memTerm) + "' and  s3.name in ('" + country + "', '" + region + "', '" + chapter + "')"+
                    " and  effective_to is null  order by s3.org_type desc limit 1 ;";

            String[][] actualMembershipFeeFromDB1 =dbConnect.queryAndRetrieveRecords(membershipFeeFromSqlQuery);
            String actualMembershipFeeFromDB= actualMembershipFeeFromDB1[0][0];
            System.out.println("Actual Membership Fee from DB is "+actualMembershipFeeFromDB);

            renewalApplication.getMembershipFeeFromApplication();
            TimeUnit.SECONDS.sleep(1);
            renewalApplication.getMemFeeTaxFromApplication();
            TimeUnit.SECONDS.sleep(1);
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

            String visitorEmailId=data.get("memberEmailID");
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
            System.out.println("EmailID user name is" +emailIdUserName);
            String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("OnlineRenewalOfflinePaymentRenewalForm", "properties/sql.properties"));
            String subject = queryResult[0][0];
            System.out.println("Subject retrieved from sql query is " +subject);   //   String emailSubject = "Copy of Renewal Form";
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
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "StreamLineRenewal");
            reconcileApplications.clickAppStatusLink();
             renewalApproval = new RenewalApproval(driver);
             renewalApproval.clickAgreeCheckBox();
             renewalApproval.clickApproveButton();
            TimeUnit.SECONDS.sleep(2);
            renewalApproval.clickBackButton();
            TimeUnit.SECONDS.sleep(2);
           /* bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
//            String language301[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
//            int colNumber30 = Integer.parseInt(language301[1]);
//            String transMainMenu = readWriteExcel.getCellData("translation", colNumber30, 3);
//            String transSubMenu = readWriteExcel.getCellData("translation", colNumber30, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);

            */
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchText((data.get("firstName")),data.get("lastName"));
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
            TimeUnit.SECONDS.sleep(2);
//            Alert alert71 = driver.switchTo().alert();
//            alert71.accept();
            TimeUnit.SECONDS.sleep(5);
            String name =data.get("firstName")+" "+ data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '"+name+"'  and id_membership_application is null;" ;
            String[][] invoiceAmountFromDB1 =dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceAmountFromDB= invoiceAmountFromDB1[0][0];
            System.out.println("Invoice amount from DB is "+invoiceAmountFromDB);

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
            Alert alert7 = driver.switchTo().alert();
            alert7.accept();
            TimeUnit.SECONDS.sleep(10);
            reconcileApplications.enterSearchText((data.get("firstName")), (data.get("lastName")));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickRecncileButton();
            TimeUnit.SECONDS.sleep(5);
            Alert alert6 = driver.switchTo().alert();
            alert6.accept();
            TimeUnit.SECONDS.sleep(10);





        }
    }



}

























//To verify BR



   /*
  login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(5);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Admin,Country");
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
            editBusinessRules.selectAllowOverride(data.get("allowOverride"));
            editBusinessRules.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();
//Edit Region BR
        /*    driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(5);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Admin,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transSubMenu2 = readWriteExcel.getCellData("translation", colNumber, 7);
            bniConnect.selectItemFromSubListMenu(transSubMenu2);
            TimeUnit.SECONDS.sleep(5);
            viewRegionBusinessRules = new ViewRegionBusinessRules(driver);
            viewRegionBusinessRules.clickEditRulesButton();
            TimeUnit.SECONDS.sleep(5);
            editBusinessRules = new EditBusinessRules(driver);
            editBusinessRules.selectAllowOnlineRenewals(data.get("allowRegionOnlineRenewals"));
            TimeUnit.SECONDS.sleep(1);
           // editBusinessRules.selectAllowOverride(data.get("allowOverride"));
            editBusinessRules.clickSubmitButton();
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();



           driver = launchBrowser.getDriver();


          */

/*
//            driver = launchBrowser.getDriver();
//            launchBrowser.invokeBrowser();
//            TimeUnit.SECONDS.sleep(2);
//            //    String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
//            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
//            TimeUnit.SECONDS.sleep(12);
//            driver=launchBrowser.getDriver();
//            bniConnect = new BNIConnect(driver);
//            TimeUnit.SECONDS.sleep(3);
//            bniConnect.navigateMenu("OPERATIONS,Region");
//            TimeUnit.SECONDS.sleep(3);
//            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
//            String language31[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
//            int colNumber3 = Integer.parseInt(language31[1]);
//            String transMainMenu = readWriteExcel.getCellData("translation", colNumber3, 3);
//            String transSubMenu = readWriteExcel.getCellData("translation", colNumber3, 6);
//            bniConnect.selectItemFromMainListMenu(transMainMenu);
//            TimeUnit.SECONDS.sleep(2);
//            bniConnect.selectItemFromSubListMenu(transSubMenu);
//            TimeUnit.SECONDS.sleep(6);
//            bniConnect = new BNIConnect(driver);

            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchText((data.get("firstName")), data.get("lastName"));
            reconcileApplications.clickUnsubmittedApplicationCheckbox();
            TimeUnit.SECONDS.sleep(2);

//            bniConnect = new BNIConnect(driver);
//            bniConnect.navigateMenu("OPERATIONS,Region");
//            TimeUnit.SECONDS.sleep(3);
//
//
//
//
//            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
//            String language32[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
//            int colNumber32 = Integer.parseInt(language32[1]);
//            String transMainMenu32 = readWriteExcel.getCellData("translation", colNumber32, 3);
//            String transSubMenu32 = readWriteExcel.getCellData("translation", colNumber32, 6);
//            bniConnect.selectItemFromMainListMenu(transMainMenu32);
//            TimeUnit.SECONDS.sleep(2);
//            bniConnect.selectItemFromSubListMenu(transSubMenu32);
//            TimeUnit.SECONDS.sleep(6);

  */



          /* BniConnectApplicationPortal.clickMembershipTermNextButton();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.enterExperienceProfessionalClassification(data.get("professionalExp"));
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.enterLengthProfessionalClassification(data.get("lengthProfExp"));
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.selectLicenceStatus();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js3 = (JavascriptExecutor) driver;
            js3.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.enterBackground(data.get("background"));
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(5);
            BniConnectApplicationPortal.enterCommitment();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.enterSubstitute();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js4 = (JavascriptExecutor) driver;
            js4.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.enterAlreadyMember();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.clickMembershipTermNextButton();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js5 = (JavascriptExecutor) driver;
            js5.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.clickAcceptCheckBox();
            TimeUnit.SECONDS.sleep(3);

            */

/*            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(12);
            bniConnect = new BNIConnect(driver);
            bniConnect.clickRenewNowLink();
           BniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
           BniConnectApplicationPortal.clickPaynow();

            TimeUnit.SECONDS.sleep(1);
           BniConnectApplicationPortal.selectPaymentMethod2(data.get("paymentMethod"));
            TimeUnit.SECONDS.sleep(2);
BniConnectApplicationPortal.clickRenewalApplicationSubmitButton();
            TimeUnit.SECONDS.sleep(2);

 */


/*
    BniConnectApplicationPortal.clickSignOutButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            bniConnect.selectItemFromMainListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(6);
 */
