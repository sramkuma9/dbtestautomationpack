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
        //  boolean setFlag = readWriteExcel.deleteRow("src/test/resources/inputFiles/testInput.xlsx", "addBrandNewVisitor", 0);
        // boolean Flag = readWriteExcel.deleteRow("src/test/resources/inputFiles/testInput.xlsx", "addBrandNewVisitor", 1);
    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
//    @Given("User navigates to BNI homepage using the below login credentials following V2 pricing")
//    public void User_navigates_to_BNI_homepage_using_the_below_login_credentials(DataTable loginDetails) throws Exception {
        @Given("follow V2 pricing")
            public void follow_V2_pricing(DataTable loginDetails)throws Exception{

        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

//    @When("I enter a valid new email id and click search and create new button and I enter the below details and click the save button and search the added visitor following V2 pricing")
//    public void When_I_enter_a_valid_existing_email_id_and_click_search_and_Add_button_and_I_enter_the_below_details_and_click_the_save_button_and_search_the_added_visitor_following_V2_pricing(DataTable addPVVisitor) throws Exception {

//        @When("^I enter a valid new email id and click search and click create new button and I enter the below details and check Version Terms click the save button and search the added visitor following V pricing$")
//        public void iEnterAValidNewEmailIdAndClickSearchAndClickCreateNewButtonAndIEnterTheBelowDetailsAndCheckVersionTermsClickTheSaveButtonAndSearchTheAddedVisitorFollowingVPricing(DataTable addPVVisitor) throws Exception {

    @When("^Add a visitor for V pricing$")
    public void addAVisitorForVPricing(DataTable addPVVisitor) throws  Exception{


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
            addAVisitor = new AddAVisitor(driver);
            TimeUnit.SECONDS.sleep(24);
            String dateTimeStamp = currentDateTime.dateTime();
            visitorDateTime = (dateTimeStamp.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", ""));
            System.out.println("Timestamp" + visitorDateTime);
            String hours = visitorDateTime.substring(11, 14);
            String lastName = data.get("lastName") + hours;
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
            addAVisitor.selectVisitorCountry(data.get("country"));
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
            //Global
            //  String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("AddAndSearchBrandNewVisitorGlobal", "properties/sql.properties"));
            //Track
            //  String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("AddAndSearchBrandNewVisitorTrack", "properties/sql.properties"));
            //Test2
            String[][] queryResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("AddAndSearchBrandNewVisitorTest2", "properties/sql.properties"));

            //String subject1 = (dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("NMARegistrationLink", "properties/sql.properties")).toString());
            String subject = queryResult[0][0];
            System.out.println("Subject retrieved from sql query is " + subject);   //   String emailSubject = "New Individual Registration link";
            gmailClient.checkEmail(emailIdUserName, subject, visitorEmailId, " ", 1);
            // not necessary for global

    /*   readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String url= readWriteExcel.getCellData( "applicant", 0,0);
            System.out.println("URL is " +url);  // System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.navigate().to(url);
           TimeUnit.SECONDS.sleep(2);
           Registration registration = new Registration(driver);
          registration.enterNewPassword(data.get("NewPassword"));
            registration.enterConfirmPassword(data.get("ConfirmPassword"));
            TimeUnit.SECONDS.sleep(5);
            registration.clickNextButton();
            TimeUnit.SECONDS.sleep(2);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)", "");
            TimeUnit.SECONDS.sleep(13);
            registration.clickOffersArrow();
            TimeUnit.SECONDS.sleep(2);
           // registration.clickOffersOption();
            registration.selectOffersOption();
            TimeUnit.SECONDS.sleep(2);
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(250,0)", "");
            registration.clickNextArrow();
            TimeUnit.SECONDS.sleep(2);
            registration.clickLoginButton();
            TimeUnit.SECONDS.sleep(2);

     */


            i++;
            j++;
            signOut.signOutBni();
        }

}



}












/*
  bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromSubListMenu(transMenu);
            addAVisitor = new AddAVisitor(driver);
            TimeUnit.SECONDS.sleep(14);
            addAVisitor = new AddAVisitor(driver);
            addAVisitor.enterEmail(data.get("firstName") + data.get("lastName") + hours + "@gmail.com");
            TimeUnit.SECONDS.sleep(2);
            addAVisitor.clickSearchButton();
            TimeUnit.SECONDS.sleep(3);
            String day = data.get(("visitDay"));
            String year = data.get(("visitYear"));
            String month = data.get(("visitMonth"));
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM");
            Calendar cal = Calendar.getInstance();
            cal.setTime(inputFormat.parse(month));
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM");
            String expectedDate = day + "/" + outputFormat.format(cal.getTime()) + "/" + year;
            addAVisitor = new AddAVisitor(driver);
            addAVisitorDetails = addAVisitor.getSearchResults();
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "searchAndAddBrandNewVisitor");
//            assertEquals("Visit date is not correct", expectedDate, addAVisitorDetails[0]);
            assertEquals("First Name is not correct", data.get("firstName"), addAVisitorDetails[1]);
            assertEquals("Last Name is not correct", lastName, addAVisitorDetails[2]);
            assertEquals("Region is not correct", data.get("region"), addAVisitorDetails[3]);
            assertEquals("Chapter is not correct", data.get("chapter"), addAVisitorDetails[4]);
            assertEquals("Company Name is not correct", data.get("companyName"), addAVisitorDetails[5]);
            addAVisitor = new AddAVisitor(driver);
            addAVisitor.clickCloseButton();
            TimeUnit.SECONDS.sleep(2);
            Alert alert = driver.switchTo().alert();
            alert.accept();





 */


//  gmailClient.checkEmail("dbselenium@gmail.com", "Thank you for visiting BNI-" + data.get("firstName") + " "  + data.get("lastName") + visitorDateTime, data.get("firstName") + data.get("lastName") + visitorDateTime + "@gmail.com", "applicant", i);
//  gmailClient.checkEmail("SeleniumBni+vTwo712@gmail.com", "Thanks for Visiting Selenium Bni+vTwo712- Apply Now" , data.get("firstName") + data.get("lastName") + hours + "@gmail.com", "applicant", i);
        /*   String toEmailId = data.get("firstName") + data.get("lastName") + hours + "@gmail.com" ;
String subject ="Thank you for viisting BNIAnt - Two - Stage 2-" +data.get("firstName") + data.get("lastName") + hours ;
           System.out.println("Subject is " +subject);
            gmailClient.checkEmail("SeleniumBni@gmail.com", subject , toEmailId, "applicant", i);
          //  gmailClient.checkEmail("SeleniumBni@gmail.com", "HQ - New Individual Registration link edited" , "SeleniumBni+vTwo126@gmail.com", " ", i);


            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            String url= readWriteExcel.getCellData( "applicant", 0,0);

            System.out.println("URL is " +url);
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.navigate().to(url);


            TimeUnit.SECONDS.sleep(2);
            bniConnect = new BNIConnect(driver);
           Registration registration = new Registration(driver);
           registration.enterNewPassword(data.get("NewPassword"));
           registration.enterConfirmPassword(data.get("ConfirmPassword"));

         */