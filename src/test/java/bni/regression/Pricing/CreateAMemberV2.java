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

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @When("I click Operations-Region, Enter New application, and add a member to a region following V2 pricing")
    public void iclickoperationsregionenternewapplicationandaddamembertoaregionfollowingV2pricing(DataTable createAMember) throws Exception {
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
            System.out.println("Timestamp" +visitorDateTime);
            String hours = visitorDateTime.substring(11, 14);
            String lastName = data.get("lastName") + hours;
            enterNewApplication.enterEmail(data.get("firstName") + lastName + "@gmail.com");
          //  enterNewApplication.enterEmail(data.get("emailId"));
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
            System.out.println("application data field clicked");
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
            TimeUnit.SECONDS.sleep(4);
            TimeUnit.SECONDS.sleep(15);


            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(2);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(2);

            bniConnect.navigateMenu("OPERATIONS,Region");
            TimeUnit.SECONDS.sleep(2);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
//            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
//            int colNumber = Integer.parseInt(language[1]);
 //           readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
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
            Alert alert = LaunchBrowser.driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "CreateAMember");
            reconcileApplications.clickRecncileButton();
            alert = LaunchBrowser.driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);

            String country=data.get("country");
            System.out.println("Country is" +country);
            String region =data.get("region");
            System.out.println("Region is" +region);
            String chapter = data.get("chapter");
//            String memTerm = data.get("membershipTerm");
//            HashMap<String , String> memOption = new HashMap<String , String>();
//            memOption.put("12 Month", "2");
//            memOption.put("24 Month","3");
//            memOption.put("1 Year US", "2");
//            memOption.put("2 Year US","3");
            String firstName=data.get("firstName");
            String lastname1 = data.get("lastName");


            String membershipFeeFromSqlQuery =   "select  m3.email_address, m3.first_name,m3.last_name," +
                    "m4.membership_fee, m4.registration_fee,m4.add_on_fee,m4.late_fee,m4.application_date," +
                    " m4.term_months, m4.id_application_status ,m4.service_start_date, m4.reconciled_on " +
                    "from bni.membership_application m4 " +
                    "join bni.user m3 on m3.id_membership= m4.id_membership" +
                    " where m3.first_name ='"+firstName+"' and m3.last_name ='"+lastname1+"'; " ;

            String[][] actualMembershipFeeFromDB1 = dbConnect.queryAndRetrieveRecords(membershipFeeFromSqlQuery);
         //   System.out.println(Arrays.asList(actualMembershipFeeFromDB1));
            System.out.println(actualMembershipFeeFromDB1.toString());


/*
            String MemberEmailAddress= ((actualMembershipFeeFromDB1[0][1]));
            System.out.println("Member Email Address is" + MemberEmailAddress);
            String MemberFirstName =  actualMembershipFeeFromDB1[0][2];
            System.out.println("Member First Name is "+ MemberFirstName);
            String MembershipFee =  actualMembershipFeeFromDB1[0][4];
            System.out.println("Membership Fee is "+ MembershipFee);
            String MemberRegistrationFee =  actualMembershipFeeFromDB1[0][5];
            System.out.println("Member Registration Fee is "+MemberRegistrationFee);
            String MemberTermMonths =  actualMembershipFeeFromDB1[0][9];
            System.out.println("Member Term Months applied is "+MemberTermMonths);

 */




//            System.out.println("Actual Membership Fee from DB is "+actualMembershipFeeFromDB);
//            // assertEquals("MembershipFee is not correct", expectedMembershipFeeFromUI,actualMembershipFeeFromDB);
//            //Tax for Membership Fee
////            String expectedTaxForMembershipTermFromUI = BniConnectApplicationPortal.getTaxForMembershipTerm();
//            TimeUnit.SECONDS.sleep(3);
//            System.out.println("Actual Tax for Membership Fee from DB is "+actualTaxFromMembershipTerm);
//            //  assertEquals("Actual Tax for MembershipFee is not correct", expectedTaxForMembershipTermFromUI,actualTaxFromMembershipTerm);
//


            i++;
            signOut.signOutBni();



        }
    }


}