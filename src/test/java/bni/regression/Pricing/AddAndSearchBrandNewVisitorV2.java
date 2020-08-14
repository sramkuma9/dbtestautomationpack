package bni.regression.Pricing;

import bni.regression.libraries.common.*;
import bni.regression.libraries.common.email.GmailClient;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.pageFactory.AddAVisitor;
import bni.regression.pageFactory.BNIConnect;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SignOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class AddAndSearchBrandNewVisitorV2 {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private AddAVisitor addAVisitor;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public static String fixedDateTime;
    public static String visitorDateTime;
    public String name;
    public String[] addAVisitorDetails = new String[8];
    public String firstName;
    public String lastName;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private GmailClient gmailClient = new GmailClient();
    DbConnect dbConnect = new DbConnect();

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
    }

    @After
    public void tearDown() throws Exception {

    }


    @Given("follow V2 pricing")
    public void follow_V2_pricing(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }


    @When("^Add a visitor for V pricing$")
    public void addAVisitorForVPricing(DataTable addPVVisitor) throws Exception {


        Integer i = 2;
        Integer j = 1;
        for (Map<String, String> data : addPVVisitor.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(4);
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
            String transMenu = readWriteExcel.getCellData("translation", colNumber, 1);
            bniConnect.selectItemFromSubListMenu(transMenu);
            TimeUnit.SECONDS.sleep(3);
            addAVisitor = new AddAVisitor(driver);
            TimeUnit.SECONDS.sleep(24);
            String dateTimeStamp = currentDateTime.dateTime();
            visitorDateTime = (dateTimeStamp.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
            System.out.println("Timestamp" + visitorDateTime);
            String hours = visitorDateTime.substring(11, 14);
            String lastName = data.get("lastName") + hours;
            System.out.println("LAst Name" +lastName);
            addAVisitor.enterEmail(data.get("firstName") + lastName + "@gmail.com");
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickSearchButton();
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            boolean setEmailFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "addBrandNewVisitor", 0, i, data.get("firstName") + data.get("lastName") + hours + "@gmail.com");
            boolean setLastNameFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "addBrandNewVisitor", 1, i - j, lastName);
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickSearchByNameButton();
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterFirstName(data.get("firstName"));
            addAVisitor.enterLastName(lastName);
            TimeUnit.SECONDS.sleep(1);
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("submit"))));
            driver.findElement(By.name("submit")).click();
            addAVisitor.clickCreateNewButton();
            TimeUnit.SECONDS.sleep(2);
            //   addAVisitor.clickAddButton();
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectChapter(data.get("chapter"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectProfession(data.get("profession"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectSpeciality(data.get("speciality"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectLanguage(data.get("language"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectInvitedBy(data.get("person"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickVisitDateField();
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectVisitYear(data.get("visitYear"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectVisitMonth(data.get("visitMonth"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectDateFromDatePicker(data.get("visitDay"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectVisitorTitle(data.get("title"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterAddressLine1(data.get("address"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterVisitorFirstName(data.get("firstName"));
            TimeUnit.SECONDS.sleep(1);
            addAVisitor.enterVisitorLastName(lastName);
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterCompanyName(data.get("companyName"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.selectVisitorCountry(data.get("country2"));
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.enterVisitorPhoneNumber(data.get("phone"));
            TimeUnit.SECONDS.sleep(2);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "searchAndAddBrandNewVisitor");
            addAVisitor.clickSaveButton();
            TimeUnit.SECONDS.sleep(30);
            gmailClient = new GmailClient();
            //verify HQ - New Individual Registration link edited from Gmail account
            TimeUnit.SECONDS.sleep(20);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String visitorEmailId = readWriteExcel.getCellData("addBrandNewVisitor", 0, 1);
            System.out.println("EmailID" + visitorEmailId);
            String emailIdUserName = readWritePropertyFile.loadAndReadPropertyFile("emailUserName", "properties/config.properties");
            System.out.println("EmailID user name is" + emailIdUserName);
            String country = data.get("country");
            String region = data.get("region");
            String registrationSubject = "select subject from bni_mailer.message_template b1  join " +
                    " bni_mailer.mail_event_template_org b2 on b1.id_message_template =b2.message_template_id_message_template " +
                    " join bni.org b3 on b2.id_org = b3.id_org " +
                    "where b3.org_name in ('HQ', '" + country + "', '" + region + "') " +
                    " and b2.mail_event_id_mail_event ='1' order by  id_org_type desc limit 1 ; ";
            String[][] registrationSubjectResult = dbConnect.queryAndRetrieveRecords(registrationSubject);
            String subject = registrationSubjectResult[0][0];
            System.out.println("Subject retrieved from sql query is " + subject);
            gmailClient.checkEmail(emailIdUserName, subject, visitorEmailId, " ", 1);
            i++;
            j++;
            signOut.signOutBni();
        }

    }


}











