package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.common.email.GmailClient;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.ComposeEmail;
import bni.regression.pageFactory.RegionEmailMembers;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class verifyMassEmails {

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
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private RegionEmailMembers regionEmailMembers;
    private ComposeEmail composeEmail;
    private  GmailClient gmailClient= new GmailClient();

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("User logged in as admin login and select Operations, Region. Navigate to Create Email, Email Members")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("Enter the below details and click Find button. Select Region Email Members and click Next button. Enter Subject, select attachment and click Send button")
    public void step_2(DataTable massEmails) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : massEmails.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
            bniConnect.navigateMenu("Operations,Region");
            TimeUnit.SECONDS.sleep(8);
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 15);
            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 16);
            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(5);
            bniConnect.selectItemFromSubListMenu(transSubMenu);
            TimeUnit.SECONDS.sleep(10);
            regionEmailMembers = new RegionEmailMembers(driver);
            regionEmailMembers.selectRegion(data.get("listOfRegions"));
            TimeUnit.SECONDS.sleep(3);
            regionEmailMembers.selectChapter(data.get("membersInOneOrMoreChapters"));
            TimeUnit.SECONDS.sleep(1);
            regionEmailMembers.selectAssistantDirector(data.get("membersUnderThisAssistantDirector"));
            TimeUnit.SECONDS.sleep(1);
            regionEmailMembers.selectPosition(data.get("membersWhoHoldThisPosition"));
            TimeUnit.SECONDS.sleep(1);
            regionEmailMembers.selectAreaDirector(data.get("membersWhoAreUnderThisAreaDirector"));
            TimeUnit.SECONDS.sleep(1);
            regionEmailMembers.selectGeoArea(data.get("membersWhoAreInThisGeoArea"));
            TimeUnit.SECONDS.sleep(1);
            regionEmailMembers.selectStatus(data.get("membersWhoHaveThisStatus"));
            TimeUnit.SECONDS.sleep(2);
            regionEmailMembers.clickFindButton();
            TimeUnit.SECONDS.sleep(8);
            regionEmailMembers.enterSearchString("test");
            // String emailIds= data.get("emailIds");
            regionEmailMembers.clickSelectAllButton();
            TimeUnit.SECONDS.sleep(3);
            regionEmailMembers.clickNextButton();
            TimeUnit.SECONDS.sleep(10);
            composeEmail = new ComposeEmail(driver);
           // regionEmailMembers.selectMembersForEmail(data.get("emailIds"));
           // composeEmail.setRecipientsList("");
          //  TimeUnit.SECONDS.sleep(10);
            //composeEmail.setRecipientsList(data.get("emailIds"));


           // String emailIds= data.get("emailIds");
            //regionEmailMembers.clickSelectAllButton();
            TimeUnit.SECONDS.sleep(10);
            composeEmail.enterSubject(data.get("subject"));
            TimeUnit.SECONDS.sleep(2);
//            driver.switchTo().frame(1);


            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "verifyMassEmails");
            composeEmail.clickSendButton();

           gmailClient.checkEmail("seleniumbni@gmail.com", "subject" , "SeleniumBni+v20200127175908@gmail.com", "term", i);
                      TimeUnit.SECONDS.sleep(2);
            signOut.signOutBni();
            i++;
            // add email verification code
        }
    }

    @Then("Verify selected members only receive the Email")
    public void step_3() {
        System.out.println("verify mass email script executed.");
    }

}