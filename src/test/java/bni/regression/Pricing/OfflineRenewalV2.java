

package bni.regression.Pricing;

        import bni.regression.libraries.common.CaptureScreenShot;
        import bni.regression.libraries.common.LaunchBrowser;
        import bni.regression.libraries.common.ReadWriteExcel;
        import bni.regression.libraries.common.ReadWritePropertyFile;
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

        import java.util.List;
        import java.util.Map;
        import java.util.concurrent.TimeUnit;

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
    private  RenewMember renewMember;
    DbConnect dbConnect = new DbConnect();
    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Given("^A member is added newly to the region following V2 pricing and member status is active now\\.$")
    public void a_member_is_added_newly_to_the_region_following_V2_pricing_and_member_status_is_active_now(DataTable loginDetails)  {

       List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I navigate to Operations ->Chapter ->Manage Memberships -> Manage Members\\. Enter firstname and last name and enter search members button following V2 pricing\\.$")
    public void step_2(DataTable offlineRenewal) throws Exception{
        Integer i = 2;
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

            String name1 =data.get("firstName")+" "+ data.get("lastName");
            String invoiceAmountSqlQuery = " select invoice_reference, total_amount  from bni.invoice where to_person_name = '"+name1+"'  and id_membership_application is null;" ;
            String[][] invoiceAmountFromDB1 =dbConnect.queryAndRetrieveRecords(invoiceAmountSqlQuery);
            String invoiceAmountFromDB= invoiceAmountFromDB1[0][0];
            System.out.println("Invoice amount from DB is "+invoiceAmountFromDB);
            reconcileApplications.clickRecncileButton();
            alert = LaunchBrowser.driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "OfflineRenewal");
              signOut.signOutBni();
            // To reconcile


          /*  driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            reconcileApplications = new ReconcileApplications(launchBrowser.driver);
            reconcileApplications.enterSearchCriteria(data.get("firstName"),data.get("lastName"));
            reconcileApplications.clickPaymentReceivedCheckBox();
            Alert alert = LaunchBrowser.driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
            reconcileApplications.clickRecncileButton();
            Alert alert2 = LaunchBrowser.driver.switchTo().alert();
            alert2.accept();
            TimeUnit.SECONDS.sleep(10);

            signOut.signOutBni();

          */
        }
    }


}





