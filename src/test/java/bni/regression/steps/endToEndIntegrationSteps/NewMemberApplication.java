package bni.regression.steps.endToEndIntegrationSteps;
import bni.regression.libraries.common.*;
import bni.regression.libraries.common.email.GmailClient;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NewMemberApplication  {


        public static WebDriver driver;
        private Login login = new Login();
        private SignOut signOut = new SignOut();
        private GmailClient gmailClient = new GmailClient();
        private BNIConnect bniConnect;
        private CurrentDateTime currentDateTime = new CurrentDateTime();
        private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
        public List<List<String>> loginSubList;
        private CaptureScreenShot captureScreenShot;
        private AddAVisitor addAVisitor;
        private LaunchBrowser launchBrowser = new LaunchBrowser();
        private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
        ReadWriteExcel readWriteExcel = new ReadWriteExcel();
        public static String fixedDateTime;
        public static String visitorDateTime;
        private ManageMembers manageMembers;
        private MemberRenewalApplication memberRenewalApplication;
        private MemberRenewalApplicationPaymentProcessing memberRenewalApplicationPaymentProcessing;
        private ReconcileOnlineRenewals reconcileOnlineRenewals;
        private EditProfile editProfile;
        private BNIConnectApplicationPortal BniConnectApplicationPortal;
        private AddPaymentTypeForOnlineApplication addPaymentTypeForOnlineApplication;
        private ReconcileApplications reconcileApplications;
        private NewMemberApproval newMemberApproval;
        private  Reconcile reconcile;
        private ChangePaymentType changePaymentType;

        @Before
        public void setup() throws Exception {

        }

        @After
        public void tearDown() throws Exception {

        }


        @Given("A visitor is added newly to the region")
        public void aVisitorIsAddedNewlyToTheRegion(DataTable loginDetails) {

            List<List<String>> login = loginDetails.raw();
            loginSubList = login.subList(1, login.size());
        }

        @When("^I login BNI app with Member login details and accept TOS, check latest TOS version is displayed,then click Renew Now button in the home page and enter the below details click Proceed to payment button\\.$")
        public void iLoginBNIAppWithMemberLoginDetailsAndAcceptTOSCheckLatestTOSVersionIsDisplayedThenClickRenewNowButtonInTheHomePageAndEnterTheBelowDetailsClickProceedToPaymentButton(DataTable addNMA) throws Exception {

            {
                //To add a visitor
               Integer i = 2;
                Integer j = 1;

                for (Map<String, String> data : addNMA.asMaps(String.class, String.class)) {
                    String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
                        /*      gmailClient = new GmailClient();
                    //verify applynow link from Gmail account
                              TimeUnit.SECONDS.sleep(20);
                    readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
                    String visitorEmailId = readWriteExcel.getCellData("addBrandNewVisitor",0,1);
                    System.out.println("EmailID" +visitorEmailId);
                    String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
                    System.out.println("EmailID user name is" +emailIdUserName);
                    gmailClient.checkEmail(emailIdUserName, "Apply now" , visitorEmailId, "applicant", 1);
                    readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
                    String url= readWriteExcel.getCellData( "applicant", 0,0);
                    System.out.println("URL is " +url);
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                    WebDriver driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.navigate().to(url);
                    TimeUnit.SECONDS.sleep(2);
                    readWriteExcel.deleteRow("src/test/resources/inputFiles/testInput.xlsx","applicant", 0);
                    TimeUnit.SECONDS.sleep(2);
                     /*
                    bniConnect = new BNIConnect(driver);
                    Registration registration = new Registration(driver);
                   registration.enterNewPassword(data.get("NewPassword"));
                    registration.enterConfirmPassword(data.get("ConfirmPassword"));
                    TimeUnit.SECONDS.sleep(5);
                    registration.clickNextButton();
                    TimeUnit.SECONDS.sleep(2);
                    //NMA Form
                    BniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
                    TimeUnit.SECONDS.sleep(8);
                    BniConnectApplicationPortal.clickChapterSelection();
                    TimeUnit.SECONDS.sleep(3);
                    BniConnectApplicationPortal.clickApplicationFormButton();
                    TimeUnit.SECONDS.sleep(3);
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.scrollBy(0, 250)", "");
                    TimeUnit.SECONDS.sleep(13);
                    BniConnectApplicationPortal.clickApplicationFormNextButton();
                    TimeUnit.SECONDS.sleep(3);
                    BniConnectApplicationPortal.clickDropdown();
                    TimeUnit.SECONDS.sleep(3);
                    BniConnectApplicationPortal.selectMembershipTerm(data.get("membershipTerm"));
                    TimeUnit.SECONDS.sleep(18);
                    BniConnectApplicationPortal.clickCompanyPaidMembership();
                    JavascriptExecutor js2 = (JavascriptExecutor) driver;
                    js2.executeScript("window.scrollBy(0, 250)", "");
                    BniConnectApplicationPortal.enterPayerName(data.get("payerName"));
                    TimeUnit.SECONDS.sleep(1);
                    BniConnectApplicationPortal.clickCOCButton();
                    TimeUnit.SECONDS.sleep(1);
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
                    readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
                    String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
                    int colNumber = Integer.parseInt(language[1]);
                    readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
                    String transMenu2 = readWriteExcel.getCellData("translation", colNumber, 1);
                    bniConnect.selectItemFromSubListMenu(transMenu2);
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
                    BniConnectApplicationPortal.clickSignOutButton();
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(("Download button clicked"));
                    driver.close();
//Reconcile the application

                    launchBrowser.invokeBrowser();
                    TimeUnit.SECONDS.sleep(2);
                    login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
                    TimeUnit.SECONDS.sleep(12);
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
                    String lastName = readWriteExcel.getCellData("addBrandNewVisitor",1,1);
                    System.out.println("LAst name is " +lastName);
                    reconcileApplications.enterSearchText(lastName);
                    captureScreenShot = new CaptureScreenShot(driver);
                    captureScreenShot.takeSnapShot(driver, "NewMemberApplication");
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
                    reconcileApplications.enterSearchText(lastName);
                    TimeUnit.SECONDS.sleep(2);


                      //To click Send payment Link
                    reconcileApplications.clickSendPaymentLink();
//                    Alert alert2 = driver.switchTo().alert();
//                    alert2.accept();
                    TimeUnit.SECONDS.sleep(10);

                    */

                    readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
                    String visitorEmailId = readWriteExcel.getCellData("addBrandNewVisitor",0,1);
                    System.out.println("EmailID" +visitorEmailId);
                    String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
                    System.out.println("EmailID user name is" +emailIdUserName);
                    gmailClient.checkEmail(emailIdUserName, "Payment link" , visitorEmailId, "term", 1);
                    readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
                    String hyperlinkTwo= readWriteExcel.getCellData( "payment", 0,0);
                    System.out.println("URL is " +hyperlinkTwo);
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                    WebDriver driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.navigate().to(hyperlinkTwo);
                    TimeUnit.SECONDS.sleep(2);

//                  bniConnect = new BNIConnect(driver);
                    BniConnectApplicationPortal = new BNIConnectApplicationPortal(driver);
                    TimeUnit.SECONDS.sleep(8);
                  //  BniConnectApplicationPortal.clickPaynow();
                  //  TimeUnit.SECONDS.sleep(5);

                    BniConnectApplicationPortal.selectPaymentMethod2(data.get("paymentMethod"));
                    TimeUnit.SECONDS.sleep(5);
                    BniConnectApplicationPortal.clickSubmitGBButton();
                    TimeUnit.SECONDS.sleep(5);
                    driver = launchBrowser.getDriver();
                    launchBrowser.invokeBrowser();
                    TimeUnit.SECONDS.sleep(2);
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
                    String lastName = readWriteExcel.getCellData("addBrandNewVisitor",1,1);
                    reconcileApplications.enterSearchText(lastName);
                    captureScreenShot = new CaptureScreenShot(driver);
                    captureScreenShot.takeSnapShot(driver, "NewMemberApplication");
                   reconcileApplications.clickPaymentTypeSymbol();
                    TimeUnit.SECONDS.sleep(2);
                    reconcileApplications.clickChangePaymentType();
                    TimeUnit.SECONDS.sleep(2);
                    changePaymentType = new ChangePaymentType(driver);
                    changePaymentType.selectPaymentOption(data.get("paymentOption"));
                    TimeUnit.SECONDS.sleep(2);
                    BniConnectApplicationPortal.clickSignOutButton();
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
                    reconcileApplications.enterSearchText(lastName);
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


                }
            }


        }


    }


/*       addPaymentTypeForOnlineApplication = new  AddPaymentTypeForOnlineApplication(driver) ;
                    addPaymentTypeForOnlineApplication.selectPaymentOption(data.get("paymentMethod"));
                    TimeUnit.SECONDS.sleep(2);
                    addPaymentTypeForOnlineApplication.clickSubmitButton();
                    TimeUnit.SECONDS.sleep(2);
                    reconcileApplications = new ReconcileApplications(driver);
                    reconcileApplications.enterSearchText(lastName);
                    TimeUnit.SECONDS.sleep(2);

                    reconcileApplications.clickPaymentCheckBox();
                    TimeUnit.SECONDS.sleep(2);
                    Alert alert3 = driver.switchTo().alert();
                    alert3.accept();
                    TimeUnit.SECONDS.sleep(10);
                    reconcileApplications.clickRecncileButton();
                    TimeUnit.SECONDS.sleep(2);
                    Alert alert4 = driver.switchTo().alert();
                    alert4.accept();
                    TimeUnit.SECONDS.sleep(10);

              */