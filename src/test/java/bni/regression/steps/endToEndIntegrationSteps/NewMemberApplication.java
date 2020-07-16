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
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class NewMemberApplication {


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

    @Given("^A member has to register the application via New Member application process$")
    public void aMemberHasToRegisterTheApplicationViaNewMemberApplicationProcess(DataTable loginDetails) {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }




    @When("^I login  BNI and add a  Member using NMA process  and enter the below details click Proceed to payment button\\.$")
    public void iLoginBNIAndAddAMemberUsingNMAProcessAndEnterTheBelowDetailsClickProceedToPaymentButton(DataTable addNMA) throws Exception {


        //To add a visitor
        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : addNMA.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            /*
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
           // String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("NMARegistrationLinkTest2", "properties/sql.properties"));
           // String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("NMARegistrationLinkTrain", "properties/sql.properties"));
            // String subject1 = (dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("NMARegistrationLink", "properties/sql.properties")).toString());
            // global
              String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("AddAndSearchBrandNewVisitorGlobal", "properties/sql.properties"));

            String subject = queryResult[0][0];
            System.out.println("Subject retrieved from sql query is " + subject);
            //   String emailSubject = "Thank you for visting BNI";
            gmailClient.checkEmail(emailIdUserName, subject, visitorEmailId, "applicant", 1);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String url = readWriteExcel.getCellData("applicant", 0, 0);
            System.out.println("URL is " + url);
            driver.manage().window().maximize();
            driver.navigate().to(url);
            TimeUnit.SECONDS.sleep(2);
/*
            Registration registration = new Registration(driver);
            registration.enterNewPassword(data.get("NewPassword"));
            registration.enterConfirmPassword(data.get("ConfirmPassword"));
            TimeUnit.SECONDS.sleep(5);
            registration.clickNextButton();
            TimeUnit.SECONDS.sleep(2);


 */
            /*

            //NMA Form
            BniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
            TimeUnit.SECONDS.sleep(8);
//            BniConnectApplicationPortal.clickApplicationFormButton();
//            BniConnectApplicationPortal.selectLanguage(data.get("language"));
           BniConnectApplicationPortal.clickApplicationFormButton();
            TimeUnit.SECONDS.sleep(3);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(13);
            BniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(3);
            BniConnectApplicationPortal.selectMembershipTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(18);
           //Membership Fee calculation   '695' is CountryID

            BniConnectApplicationPortal.clickCompanyPaidMembership();
            BniConnectApplicationPortal.enterPayerName(data.get("payerName"));
            TimeUnit.SECONDS.sleep(3);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NMAMembershipTerm");
            TimeUnit.SECONDS.sleep(1);

            BniConnectApplicationPortal.clickCOCButton();
            TimeUnit.SECONDS.sleep(1);

            //////////////////////////////////
/*
            BniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.enterExperienceProfessionalClassification(data.get("professionalExp"));
            TimeUnit.SECONDS.sleep(1);
         //  BniConnectApplicationPortal.clickCalendarIcon();
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.getDate(data.get("date"));
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.selectLicenceStatus(data.get("licenceOption"));
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js3 = (JavascriptExecutor) driver;
            js3.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(5);
            BniConnectApplicationPortal.enterCommitment(data.get("commitmentOption"));
            TimeUnit.SECONDS.sleep(1);
            BniConnectApplicationPortal.enterSubstitute(data.get("substituteOption"));
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js4 = (JavascriptExecutor) driver;
            js4.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.clickMembershipTermNextButton();
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js5 = (JavascriptExecutor) driver;
            js5.executeScript("window.scrollBy(0, 250)", "");
            BniConnectApplicationPortal.clickAcceptCheckBox();
            TimeUnit.SECONDS.sleep(3);


 */


            //////////////////////////////////
            /*
            BniConnectApplicationPortal.clickReviewApplicationButton();
            TimeUnit.SECONDS.sleep(12);
            System.out.println(("Review button clicked"));
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
            System.out.println(("submit button clicked"));
            TimeUnit.SECONDS.sleep(17);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NewMemberApplication");
            TimeUnit.SECONDS.sleep(12);
            BniConnectApplicationPortal.clickDownloadApplicationButton();
            System.out.println(("Download button clicked"));
            driver.close();



             */


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
            //   String lastName=data.get("lastName");
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
            // String lastName = readWriteExcel.getCellData("addBrandNewVisitor",1,1);
            // reconcileApplications.enterSearchText((data.get("firstName")), lastName);
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
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            //  String lastName=data.get("lastName");
            reconcileApplications = new ReconcileApplications(driver);
            reconcileApplications.enterSearchText((data.get("firstName")), lastName);
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
            String name = data.get("firstName")+data.get("lastName");
//            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '"+name+"'  and id_membership_application is null;" ;
//            String[][] invoiceAmountFromDB1 =dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
//            String invoiceAmountFromDB= invoiceAmountFromDB1[0][0];
//            System.out.println("Invoice amount from DB is "+invoiceAmountFromDB);
//
//
//            assertEquals("MembershipFee From Application widget and invoice  is not correct", actualMembershipFeeFromDB,invoiceAmountFromDB);
            reconcileApplications.enterSearchText((data.get("firstName")), lastName);
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickRecncileButton();
            TimeUnit.SECONDS.sleep(5);
            reconcile = new Reconcile(driver);
            TimeUnit.SECONDS.sleep(5);
            reconcile.clickSubmitButton();
            TimeUnit.SECONDS.sleep(5);
            Alert alert6 = driver.switchTo().alert();
            alert6.accept();


            TimeUnit.SECONDS.sleep(10);




        }
    }


}




  /*
            String expectedMembershipFeeFromUI= BniConnectApplicationPortal.getFeeForMembershipTerm();
            System.out.println("Membership fee from Application widget is" +expectedMembershipFeeFromUI);
            TimeUnit.SECONDS.sleep(3);

            String country=data.get("country");
            System.out.println("Country is" +country);
            String region =data.get("region");
            System.out.println("Region is" +region);
            String chapter = data.get("chapter");
            String memTerm = data.get("membershipTerm");
            HashMap <String , String> memOption = new HashMap<String , String>();
            memOption.put("12 Month", "2");
            memOption.put("24 Month","3");
            memOption.put("12 Month BNI#", "2");
            memOption.put("24 Month BNI#","3");
            memOption.put("12 Month S", "2");
            HashMap <String, String> templateType = new HashMap<>();
            templateType.put("Organization","1");
            templateType.put("Individual Type","2");
            templateType.put("Chapter Status Type","3");
            templateType.put("Individual and Chapter Status Type","4");

            HashMap<String, String> option = new HashMap<>();
            option.put("MEMBER","1");
            option.put("ALUMNI","2");
            option.put("VISITOR","3");
            HashMap<String, String> option2 = new HashMap<>();
            option2.put("ACTIVE","4");
            option2.put("COREGROUP", "5");
            option2.put("SUSPENDED","6");



            String taxPercentage ="select b1.percent from bni.tax_code_sub_elements b1 " +
                    " join  bni.org_tax_code b2 on b2.inbound_id_tax_code = b1.id_tax_code " +
                    " join bni.Org b3 on b3.id_org_tax_code = b2.id_org_tax_code where b3.org_name ='"+region+"' ; ";
            TimeUnit.SECONDS.sleep(5);
//            String[][] taxCalculatedPercent = dbConnect.queryAndRetrieveRecords(taxPercentage);
//            String taxPercent = taxCalculatedPercent[0][0];
            String[][] taxCalculatedPercent = dbConnect.queryAndRetrieveRecords(taxPercentage);
            String taxPercent1 = taxCalculatedPercent[0][0];
            String taxPercent2 = taxCalculatedPercent[0][1];
            System.out.println("Tax percentage from SQL query is " +taxPercent1);
            System.out.println("Tax percentage from SQL query is" +taxPercent2);
            String membershipFeeFromSqlQuery =   "select  sl.value , (Round(sl.value *'"+taxPercent1+"' )/ 100) from pricing.scheme_line sl " +
            " join pricing.scheme s on s.id = sl.id_scheme " +
            " join pricing.org o on o.id = s.id_org " +
            " join pricing.sku s2 on s2.id = s.id_sku " +
            " join pricing.scheme_line_option s4 on s4.id_scheme_line =sl.id " +
            " join pricing.variable_option s5 on s5.id = s4.id_variable_option" +
            " where o.name in ('"+country+"', '"+region+"', '"+chapter+"') and id_sku='"+memOption.get(memTerm)+"' " +
                    "and s5.option in ('"+option+"', '"+option2+"') and effective_to is null order by o.org_type desc limit 1; " ;

            String[][] actualMembershipFeeFromDB1 =dbConnect.queryAndRetrieveRecords(membershipFeeFromSqlQuery);
            String actualMembershipFeeFromDB= actualMembershipFeeFromDB1[0][0];

            System.out.println("Actual Membership Fee from DB is "+actualMembershipFeeFromDB);
           // assertEquals("MembershipFee is not correct", expectedMembershipFeeFromUI,actualMembershipFeeFromDB);
            //Tax for Membership Fee
            String expectedTaxForMembershipTermFromUI = BniConnectApplicationPortal.getTaxForMembershipTerm();
            TimeUnit.SECONDS.sleep(3);

           /* String taxPercentage ="select * from bni.tax_code_sub_elements b1" +
                    "join  bni.org_tax_code b2 on b2.inbound_id_tax_code = b1.id_tax_code" +
                    "join bni.Org b3 on b3.id_org_tax_code = b2.id_org_tax_code where b3.org_name ='"+region+"' ; ";


            System.out.println("Actual Tax for Membership Fee from DB is "+actualTaxFromMembershipTerm);

            */
//  assertEquals("Actual Tax for MembershipFee is not correct", expectedTaxForMembershipTermFromUI,actualTaxFromMembershipTerm);


            /*
            //Registration Fee calculation
            String expectedRegistrationFeeFromUI = BniConnectApplicationPortal.registrationFeeFetched();
            System.out.println("Registration fee from application widget" +expectedRegistrationFeeFromUI);
            TimeUnit.SECONDS.sleep(3);
            String registrationFeeFromSqlQuery = " select  sl.value, (Round(sl.value *'"+taxPercent1+"'  )/ 100) from pricing.scheme_line sl " +
           " join pricing.scheme s on s.id = sl.id_scheme " +
           " join pricing.org o on o.id = s.id_org " +
           " join pricing.sku s2 on s2.id = s.id_sku " +
           " join pricing.scheme_line_option s4 on s4.id_scheme_line =sl.id " +
           " join pricing.variable_option s5 on s5.id = s4.id_variable_option "+
                    " where o.name in ('"+country+"', '"+region+"', '"+chapter+"') and id_sku='"+memOption.get(memTerm)+"' " +
                    "and s5.option in ('"+option+"', '"+option2+"') and effective_to is null order by o.org_type desc limit 1; " ;

            String[][] actualRegistrationFeeFromDB1 =dbConnect.queryAndRetrieveRecords(registrationFeeFromSqlQuery);
            String actualRegistrationFeeFromDB = actualRegistrationFeeFromDB1[0][0];
            String actualTaxForRegistrationFeeFromDB =  actualRegistrationFeeFromDB1[0][1];
            System.out.println("Actual Registration Fee from DB is "+actualRegistrationFeeFromDB);

         //   assertEquals("MembershipFee is not correct", expectedRegistrationFeeFromUI,actualRegistrationFeeFromDB);

            //Tax for Registration Fee
            String expectedTaxForRegistrationFeeFromUI= BniConnectApplicationPortal.getTaxForRegistrationFee();
            TimeUnit.SECONDS.sleep(3);
          //  assertEquals("Actual Tax for Registration Fee is not correct", expectedTaxForRegistrationFeeFromUI,actualTaxForRegistrationFeeFromDB);
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0, 250)", "");

             */










         /*  BniConnectApplicationPortal.clickSelectDifferentChapter();
                    TimeUnit.SECONDS.sleep(2);
                    BniConnectApplicationPortal.enterChapterName(data.get("chapter"));
                    TimeUnit.SECONDS.sleep(1);
                    BniConnectApplicationPortal.enterCityName(data.get("city"));
                    TimeUnit.SECONDS.sleep(1);
                    BniConnectApplicationPortal.selectMeetingDay(data.get("meetingDay"));
                    TimeUnit.SECONDS.sleep(1);
                    BniConnectApplicationPortal.clickSearchButton();
                    TimeUnit.SECONDS.sleep(1);

                   */



           /*  //Not necessary
           BniConnectApplicationPortal.clickMembershipTermNextButton();
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


           /*
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
             String visitorEmailId = readWriteExcel.getCellData("addBrandNewVisitor",0,1);
              System.out.println("EmailID" +visitorEmailId);
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
             System.out.println("EmailID user name is" +emailIdUserName);
            TimeUnit.SECONDS.sleep(10);
            String[][] queryResult2 = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("PaymentLink", "properties/sql.properties"));
            String subject2 = queryResult2[0][0];
            System.out.println("Subject retrieved from sql query is " + subject2);   //   String emailSubject = "Welcome to Stage2";
            TimeUnit.SECONDS.sleep(10);
            gmailClient.checkEmail(emailIdUserName, subject2, visitorEmailId, "term", 1);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String hyperlinkTwo = readWriteExcel.getCellData("payment", 0, 0);
            System.out.println("URL is " + hyperlinkTwo);
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                    driver.manage().window().maximize();
            driver.navigate().to(hyperlinkTwo);
            TimeUnit.SECONDS.sleep(2);
          BniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
            TimeUnit.SECONDS.sleep(8);
           BniConnectApplicationPortal.selectMembTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(18);
            JavascriptExecutor js72 = (JavascriptExecutor) driver;
            js72.executeScript("window.scrollBy(0, 250)", "");
              BniConnectApplicationPortal.clickPayNow2();
              TimeUnit.SECONDS.sleep(5);
            BniConnectApplicationPortal.selectPaymentOption2(data.get("paymentMethod"));
            TimeUnit.SECONDS.sleep(5);
            BniConnectApplicationPortal.clickSubmitButton2();
            TimeUnit.SECONDS.sleep(5);
            */

            /*

            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String language31[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber31 = Integer.parseInt(language31[1]);
            String transMainMenu31 = readWriteExcel.getCellData("translation", colNumber31, 3);
            String transSubMenu31 = readWriteExcel.getCellData("translation", colNumber31, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu31);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu31);
            TimeUnit.SECONDS.sleep(6);

         */

             /*       //not necessary
     driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String language31[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber31 = Integer.parseInt(language31[1]);
            String transMainMenu31 = readWriteExcel.getCellData("translation", colNumber31, 3);
            String transSubMenu31 = readWriteExcel.getCellData("translation", colNumber31, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu31);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu31);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);

    */



//            //Tax for Membership Fee
//            String expectedTaxForMembershipTermFromUI = BniConnectApplicationPortal.getTaxForMembershipTerm();
//            TimeUnit.SECONDS.sleep(3);
//            // select (sl.value *8 ) / 100 from pricing.scheme_line sl  join pricing.scheme s on s.id = sl.id_scheme  join pricing.org o on o.id = s.id_org where  sl.id like'3338'  and o.id like'695';
//          /*  String sqlQueryForTaxFromMembershipTerm ="select (Round(sl.value *8 )/ 100) " +
//                    "from pricing.scheme_line sl  join pricing.scheme s on s.id = sl.id_scheme " +
//                    "join pricing.org o on o.id = s.id_org where  sl.id like'3338'  and o.id like'695';";
//
//           */
//
//            String sqlQueryForTaxFromMembershipTerm ="select  (Round(sl.value *8 )/ 100)   from pricing.scheme_line sl " +
//                    " join pricing.scheme s on s.id = sl.id_scheme " +
//                    " join pricing.org o on o.id = s.id_org " +
//                    " join pricing.sku s2 on s2.id = s.id_sku " +
//                    " join pricing.scheme_line_option s4 on s4.id_scheme_line =sl.id " +
//                    " join pricing.variable_option s5 on s5.id = s4.id_variable_option" +
//                    " where o.name in ('CC Switzerland') and id_sku='2' and s5.option='VISITOR' and effective_to is null; " ;


//            String[][] actualTaxFromMembershipTerm1 = dbConnect.queryAndRetrieveRecords(sqlQueryForTaxFromMembershipTerm);
//            String actualTaxFromMembershipTerm = actualTaxFromMembershipTerm1[0][0];
//
//            System.out.println("Actual Tax for Membership Fee from DB is "+actualTaxFromMembershipTerm);
//           // assertEquals("Actual Tax for MembershipFee is not correct", expectedTaxForMembershipTermFromUI,actualTaxFromMembershipTerm);


//            String registrationFeeFromSqlQuery = "select sl.value , from pricing.scheme_line sl " +
//                    " join pricing.scheme s on s.id = sl.id_scheme "+
//                    " join pricing.org o on o.id = s.id_org where  sl.id like'3687' and o.id like'866'; ";


    /* String sqlQueryForTaxFromRegistrationFee ="select (Round(sl.value *8 )/ 100) " +
                       " from pricing.scheme_line sl " +
                        " join pricing.scheme s on s.id = sl.id_scheme "+
                        " join pricing.org o on o.id = s.id_org where  sl.id like'3687' and o.id like'866'; ";
            String[][] actualTaxFromRegFeeTerm1 = dbConnect.queryAndRetrieveRecords(sqlQueryForTaxFromRegistrationFee);
            String actualTaxFromRegFee = actualTaxFromRegFeeTerm1[0][0];
            System.out.println("Actual Tax for Registration Fee from DB is "+actualTaxForRegistrationFeeFromDB);

            */