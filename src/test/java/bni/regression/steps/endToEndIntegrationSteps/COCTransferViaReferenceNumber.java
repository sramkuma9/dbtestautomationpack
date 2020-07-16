package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;




public class COCTransferViaReferenceNumber {

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
       private ManageMembers manageMembers;
    private TransferMembership transferMembership;
    private  CertificateOfCredit certificateOfCredit;
    DbConnect dbConnect = new DbConnect();
    private ActivateAPendingRecord activateAPendingRecord;
    private  ActivateAMembership activateAMembership;
    private EnterNewMember enterNewMember;



    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


        @Given("^I login to verify COC with Reference Number$")
        public void iLoginToVerifyCOCWithReferenceNumber(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }



            @When("^I select Admin -> Region , Certificate of Credit and enter the COC Reference Number$")
            public void iSelectAdminRegionCertificateOfCreditAndEnterTheCOCReferenceNumber(DataTable coc) throws Exception {

            Integer i = 2;
            for (Map<String, String> data : coc.asMaps(String.class, String.class)) {
                String lastName = data.get("lastName");
                String sqlQuery = "select id_certificate_of_credit from bni.certificate_of_credit where id_user like (select id_user from bni.user  where last_name ='" + lastName + "');";
                TimeUnit.SECONDS.sleep(4);
                String COCNumber = dbConnect.queryRecordCount(sqlQuery).toString();
                System.out.println("COCNumber is " + COCNumber);


                String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
                driver = launchBrowser.getDriver();
                launchBrowser.invokeBrowser();
                TimeUnit.SECONDS.sleep(2);
                login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
                TimeUnit.SECONDS.sleep(12);
                driver = launchBrowser.getDriver();
                bniConnect = new BNIConnect(driver);
                TimeUnit.SECONDS.sleep(4);
                bniConnect.navigateMenu("ADMIN,Region");
                TimeUnit.SECONDS.sleep(3);
                selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
                bniConnect = new BNIConnect(driver);
                TimeUnit.SECONDS.sleep(3);
                 String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
                int colNumber = Integer.parseInt(language[1]);
                readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
                String transMainMenu1 = readWriteExcel.getCellData("translation", colNumber, 34);
                String transSubMenu1 = readWriteExcel.getCellData("translation", colNumber, 35);
                bniConnect.selectItemFromMainListMenu(transMainMenu1);
                System.out.println("Admin -> Region clicked menu1");
                TimeUnit.SECONDS.sleep(2);
                bniConnect.selectItemFromSubListMenu(transSubMenu1);
                TimeUnit.SECONDS.sleep(6);
                System.out.println("Admin -> Region clicked menu2");
                certificateOfCredit = new CertificateOfCredit(driver);
                certificateOfCredit.enterCOCReferenceNumber(COCNumber);
                TimeUnit.SECONDS.sleep(2);
                certificateOfCredit.clickGetButton();
                TimeUnit.SECONDS.sleep(12);

                if ((data.get("cocCreditType")).equals("Transfer Months To An Active Member")) {
                    certificateOfCredit = new CertificateOfCredit(driver);
                    certificateOfCredit.clickTransferMonthsFromCoC();
                    TimeUnit.SECONDS.sleep(1);
                    manageMembers = new ManageMembers(driver);
                    manageMembers.enterFirstName(data.get("transferMemFirstName"));
                    TimeUnit.SECONDS.sleep(1);
                    manageMembers.enterLastName(data.get("transferMemLastName"));
                    TimeUnit.SECONDS.sleep(1);
                    manageMembers.clickSearchMembers();
                    TimeUnit.SECONDS.sleep(1);
                    manageMembers.clickArrow();
                    TimeUnit.SECONDS.sleep(1);
                }

                // 2nd scenario
                if ((data.get("cocCreditType")).equals("TransferMembership")) {
                    certificateOfCredit = new CertificateOfCredit(driver);
                    certificateOfCredit.clickTransferMembership();
                    TimeUnit.SECONDS.sleep(1);
                    transferMembership = new TransferMembership(driver);
                    transferMembership.enterFirstName(data.get("transferMemFirstName"));
                    TimeUnit.SECONDS.sleep(1);
                    transferMembership.enterSecondName(data.get("transferMemLastName"));
                    TimeUnit.SECONDS.sleep(1);
                    transferMembership.clickSearchVisitorsButton();
                    TimeUnit.SECONDS.sleep(1);
                    transferMembership.clickArrow();
                    TimeUnit.SECONDS.sleep(2);
                    transferMembership.clickSubmitButton();
                    TimeUnit.SECONDS.sleep(1);
                    Alert alert = driver.switchTo().alert();
                    alert.accept();
                    TimeUnit.SECONDS.sleep(1);
                    transferMembership.selectChapter(data.get("chapter"));
                    TimeUnit.SECONDS.sleep(1);
                    transferMembership.enterAddressLine1(data.get("addressLine"));
                    TimeUnit.SECONDS.sleep(1);
                    transferMembership.enterPayerName(data.get("payerName"));
                    TimeUnit.SECONDS.sleep(1);
                    transferMembership.clickSubmitButton();
                    TimeUnit.SECONDS.sleep(1);
                }


                //3rd scenario

                if ((data.get("cocCreditType")).equals("Activate a Pending Record")) {
                    certificateOfCredit = new CertificateOfCredit(driver);
                    certificateOfCredit.clickTransferMonthsFromCoCToPendingMember();
                    activateAPendingRecord = new ActivateAPendingRecord(driver);
                    activateAPendingRecord.enterFirstName(data.get("transferMemFirstName"));
                    TimeUnit.SECONDS.sleep(1);
                    activateAPendingRecord.enterSecondName(data.get("transferMemLastName"));
                    TimeUnit.SECONDS.sleep(1);
                    activateAPendingRecord.enterCompanyName(data.get("companyName"));
                    TimeUnit.SECONDS.sleep(1);
                    activateAPendingRecord.clickSearchButton();
                    TimeUnit.SECONDS.sleep(2);
                    activateAPendingRecord.enterSearchText(data.get("transferMemLastName"));
                    TimeUnit.SECONDS.sleep(2);
                    activateAPendingRecord.clickActivateButton();
                    TimeUnit.SECONDS.sleep(2);
                    activateAMembership = new ActivateAMembership(driver);
                    activateAMembership.clickSubmitButton();
                    Alert alert = driver.switchTo().alert();
                    alert.accept();
                }
                //4th scenario

                if ((data.get("cocCreditType")).equals("Redeem Credit For Same Person")) {
                    certificateOfCredit = new CertificateOfCredit(driver);
                    certificateOfCredit.clickRedeemCreditForSamePerson();
                    enterNewMember = new bni.regression.pageFactory.EnterNewMember(driver);
                    enterNewMember.clickApplicationDateField();
                    enterNewMember.selectVisitYear(data.get("year"));
                    TimeUnit.SECONDS.sleep(1);
                    enterNewMember.selectVisitMonth(data.get("month"));
                    TimeUnit.SECONDS.sleep(1);
                    enterNewMember.selectDateFromDatePicker(data.get("day"));
                    TimeUnit.SECONDS.sleep(1);
                    enterNewMember.selectRegion(data.get("region"));
                    TimeUnit.SECONDS.sleep(1);
                    enterNewMember.selectChapter(data.get("chapter"));
                    TimeUnit.SECONDS.sleep(1);
                    enterNewMember.selectProfession(data.get("profession"));
                    TimeUnit.SECONDS.sleep(1);
                    enterNewMember.selectSpeciality(data.get("speciality"));
                    TimeUnit.SECONDS.sleep(1);
                    enterNewMember.clickSubmitButton();
                    TimeUnit.SECONDS.sleep(1);
                }


                }
            }

    @Then("^COC transferred successfully$")
    public void COCtransferredsuccessfully() throws Exception {
        System.out.println("coc script executed.");
    }





}

