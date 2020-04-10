package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.common.email.GmailClient;
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
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NewMemberApplicationviaGenericLink {
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
    private  Reconcile reconcile;
    private ChangePaymentType changePaymentType;

    @Before
    public void setup() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Given("^New Member Application via Generic link$")
    public void new_Member_Application_via_Generic_link(DataTable loginDetails) throws Throwable {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());

    }

    @When("^I Copy the Form link and Register the member$")
    public void iCopyTheFormLinkAndRegisterTheMember(DataTable genericLink) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : genericLink.asMaps(String.class, String.class)) {
         /*   readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String url = readWriteExcel.getCellData("GenericApplyNow", 0, 0);
            System.out.println("URL is " + url);
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.navigate().to(url);
            //bniConnect = new BNIConnect(driver);
     Registration registration = new Registration(driver);
           registration.enterNewPassword(data.get("NewPassword"));
            registration.enterConfirmPassword(data.get("ConfirmPassword"));
            TimeUnit.SECONDS.sleep(5);
            registration.clickNextButton();
            TimeUnit.SECONDS.sleep(2);    TimeUnit.SECONDS.sleep(12);
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
            bniConnectApplicationPortal.clickRenewalApplicationSubmitButton();
            TimeUnit.SECONDS.sleep(2);
            TimeUnit.SECONDS.sleep(12);
         // bniConnectApplicationPortal.selectApplicationLanguage(data.get("applicationLanguage"));
          //  TimeUnit.SECONDS.sleep(2);
        bniConnectApplicationPortal.clickSubmitApplicationbutton();
            bniConnectApplicationPortal.enterFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.enterLastName(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0, 450)", "");

           bniConnectApplicationPortal.enterPersonalLanguage(data.get("language"));
            TimeUnit.SECONDS.sleep(1);
            bniConnectApplicationPortal.enterPersonalPhoneNumber(data.get("phoneNumber"));
            TimeUnit.SECONDS.sleep(2);
            JavascriptExecutor js4 = (JavascriptExecutor) driver;
            js4.executeScript("window.scrollBy(0, 450)", "");
            bniConnectApplicationPortal.enterPersonalAddressLine(data.get("addressLine1"));
            TimeUnit.SECONDS.sleep(1);
          bniConnectApplicationPortal.selectPersonalCountryList(data.get("country"));
            TimeUnit.SECONDS.sleep(1);
            JavascriptExecutor js3 = (JavascriptExecutor) driver;
            js3.executeScript("window.scrollBy(0, 250)", "");
            bniConnectApplicationPortal.enterIndustryList(data.get("industry"));
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.clickApplicationFormNextButton();
            TimeUnit.SECONDS.sleep(1);

            bniConnectApplicationPortal.clickDropdown();
            TimeUnit.SECONDS.sleep(3);
            bniConnectApplicationPortal.selectMembershipTerm(data.get("membershipTerm"));
            TimeUnit.SECONDS.sleep(18);
            bniConnectApplicationPortal.clickCompanyPaidMembership();
            JavascriptExecutor js5 = (JavascriptExecutor) driver;
            js5.executeScript("window.scrollBy(0, 250)", "");
            bniConnectApplicationPortal.enterPayerName(data.get("payerName"));
            TimeUnit.SECONDS.sleep(1);
            bniConnectApplicationPortal.clickCOCButton();
            TimeUnit.SECONDS.sleep(1);
            bniConnectApplicationPortal.clickReviewApplicationButton();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Review application button clicked");

            bniConnectApplicationPortal.clickSubmitApplicationbutton();
            TimeUnit.SECONDS.sleep(10);
            System.out.println("Submit application button clicked");
            bniConnectApplicationPortal.clickDownloadApplicationButton();
            System.out.println("Download application button clicked");
            TimeUnit.SECONDS.sleep(10);

*/

            //Reconcile
/*
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));   TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
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
//            String lastName = readWriteExcel.getCellData("addBrandNewVisitor",1,1);
//            System.out.println("LAst name is " +lastName);
            reconcileApplications.enterSearchText(data.get("lastName"));
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NewMemberApplicationGenericLinkFeature");
            TimeUnit.SECONDS.sleep(5);
            reconcileApplications.clickAppStatusLink();
            TimeUnit.SECONDS.sleep(5);
            newMemberApproval  = new NewMemberApproval(driver);
            newMemberApproval.clickApproveButton();
            TimeUnit.SECONDS.sleep(2);
            Alert alert = driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(10);
            newMemberApproval.clickPDF();
            TimeUnit.SECONDS.sleep(10);
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Operations,Region");
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
            reconcileApplications.enterSearchText(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);


 */
//-------------------------------------------------------------------------------------------------------------------------------------------------------//
            //To click Send payment Link
            reconcileApplications.clickSendPaymentLink();
//                    Alert alert2 = driver.switchTo().alert();
//                    alert2.accept();
            TimeUnit.SECONDS.sleep(10);

            gmailClient = new GmailClient();

            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
              String visitorEmailId = data.get("email");
            System.out.println("EmailID" +visitorEmailId);
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
            System.out.println("EmailID user name is" +emailIdUserName);
            gmailClient.checkEmail(emailIdUserName, "705" , visitorEmailId, "term", 1);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String hyperlinkTwo= readWriteExcel.getCellData( "payment", 0,0);
            System.out.println("URL is " +hyperlinkTwo);
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
//                    driver.manage().window().maximize();
            driver.navigate().to(hyperlinkTwo);
            TimeUnit.SECONDS.sleep(2);

//                  bniConnect = new BNIConnect(driver);
            bniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
            TimeUnit.SECONDS.sleep(8);
            //  BniConnectApplicationPortal.clickPaynow();
            //  TimeUnit.SECONDS.sleep(5);

            bniConnectApplicationPortal.selectPaymentMethod2(data.get("paymentMethod"));
            TimeUnit.SECONDS.sleep(5);
            bniConnectApplicationPortal.clickSubmitGBButton();
            TimeUnit.SECONDS.sleep(5);
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");

            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            String language31[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber31 = Integer.parseInt(language31[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu31 = readWriteExcel.getCellData("translation", colNumber31, 3);
            String transSubMenu31 = readWriteExcel.getCellData("translation", colNumber31, 6);
            bniConnect.selectItemFromMainListMenu(transMainMenu31);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu31);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            //   String lastName = readWriteExcel.getCellData("addBrandNewVisitor",1,1);
            reconcileApplications.enterSearchText(data.get("lastName"));
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "NewMemberApplication");
            reconcileApplications.clickPaymentTypeSymbol();
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickChangePaymentType();
            TimeUnit.SECONDS.sleep(2);
            changePaymentType = new ChangePaymentType(driver);
            changePaymentType.selectPaymentOption(data.get("paymentOption"));
            TimeUnit.SECONDS.sleep(2);
            bniConnectApplicationPortal.clickSignOutButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(3);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            bniConnect.selectItemFromMainListMenu(transMainMenu31);
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectItemFromSubListMenu(transSubMenu31);
            TimeUnit.SECONDS.sleep(6);
            reconcileApplications = new ReconcileApplications(LaunchBrowser.driver);
            reconcileApplications.enterSearchText(data.get("lastName"));
            TimeUnit.SECONDS.sleep(2);
            reconcileApplications.clickPaymentReceivedCheckBox();
            TimeUnit.SECONDS.sleep(5);
            Alert alert7 = driver.switchTo().alert();
            alert7.accept();
            TimeUnit.SECONDS.sleep(10);
            reconcileApplications.clickRecncileButton();
            TimeUnit.SECONDS.sleep(5);
            reconcile = new Reconcile(driver);
            TimeUnit.SECONDS.sleep(5);
            reconcile.clickSubmitButton();
            TimeUnit.SECONDS.sleep(5);

            Alert alert6 = driver.switchTo().alert();
            alert6.accept();
            TimeUnit.SECONDS.sleep(10);















/*bniConnectApplicationPortal.clickCountryDropdown("Antarctica");
        TimeUnit.SECONDS.sleep(2);
        bniConnectApplicationPortal.enterEmailAddress("SeleniumBni+v010@gmail.com");
        TimeUnit.SECONDS.sleep(2);

 */

      /*

          String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
        driver = launchBrowser.getDriver();
        launchBrowser.invokeBrowser();
        TimeUnit.SECONDS.sleep(2);
        login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
        TimeUnit.SECONDS.sleep(12);
        driver = launchBrowser.getDriver();
        bniConnect = new BNIConnect(driver);
       bniConnect.navigateMenu("Admin,Country");

        TimeUnit.SECONDS.sleep(3);
        selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
        bniConnect = new BNIConnect(driver);
        TimeUnit.SECONDS.sleep(3);





        /*
        String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
         launchBrowser.invokeBrowser();
        login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
        TimeUnit.SECONDS.sleep(5);
        bniConnect = new BNIConnect(driver);
        selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
        TimeUnit.SECONDS.sleep(5);
        bniConnect.navigateMenu("Admin,Country");
        TimeUnit.SECONDS.sleep(3);

         */
      /*
        String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
        int colNumber = Integer.parseInt(language[1]);
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
        String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 32);
        bniConnect.selectItemFromSubListMenu(transMainMenu);
        TimeUnit.SECONDS.sleep(5);
        String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 33);
        bniConnect.selectItemFromSubListMenu(transSubMenu);
        TimeUnit.SECONDS.sleep(6);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
         TimeUnit.SECONDS.sleep(5);
         BNIConnectApplicationPortal bniConnectApplicationPortal= new BNIConnectApplicationPortal(driver);
        String copiedUrl =  bniConnectApplicationPortal.clickCopyFormLink(String link);
        TimeUnit.SECONDS.sleep(3);

        driver.getCurrentUrl()

       */


        }
    }
}
