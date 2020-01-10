package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class CMSWithDefaultAndCustomisedContents {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    public List<List<String>> loginSubList;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private CaptureScreenShot captureScreenShot;
    private CountryWebsiteList countryWebsiteList;
    DbConnect dbConnect = new DbConnect();
    private FindAChapter findAChapter;
    private AdvanceChapterSearch advanceChapterSearch;
    private ChapterList chapterList;

    @Before
    public void setup() throws Exception {
        //readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "addVisitor", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("User logged in as Admin")
    public void User_logged_in_as_Admin(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I select Tools option and navigate to CMS and select Manage your Websites. Select Country Website list ->On Argentina row, Click settings under options, click “preview ” in Find a chapter. Click Advanced Search, Enter region and click find button")
    public void step_2(DataTable cms) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : cms.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.navigateMenu("Tools,Manage Websites,Manage Your Websites (New CMS)");
            TimeUnit.SECONDS.sleep(8);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            countryWebsiteList = new CountryWebsiteList(driver);
            countryWebsiteList.enterCountrySearch(data.get("country"));
            TimeUnit.SECONDS.sleep(3);
            countryWebsiteList.clickSettingsButton();
            TimeUnit.SECONDS.sleep(8);
            countryWebsiteList.enterPagesSearch("Find A Chapter");
            TimeUnit.SECONDS.sleep(3);
            countryWebsiteList.clickPreviewButton();
            TimeUnit.SECONDS.sleep(15);
            ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(2));
            findAChapter = new FindAChapter(driver);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 1300)", "");
            TimeUnit.SECONDS.sleep(2);
            findAChapter.clickAdvanceSearchButton();
            TimeUnit.SECONDS.sleep(8);
            advanceChapterSearch = new AdvanceChapterSearch(driver);
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(0, 500)", "");
            TimeUnit.SECONDS.sleep(2);
            advanceChapterSearch.selectRegion(data.get("region"));
            TimeUnit.SECONDS.sleep(1);
            advanceChapterSearch.clickFindButton();
            chapterList = new ChapterList(driver);
            Integer actualChapterCount =  chapterList.getChapterCount();
            Integer expChapterCount = dbConnect.queryRecordCount(readWritePropertyFile.loadAndReadPropertyFile("CMSWithDefaultAndCustomisedContents", "properties/sql.properties"));
            assertEquals("Chapter count is not correct", expChapterCount, actualChapterCount);
            driver.switchTo().window(tabs1.get(0));
            signOut.signOutBni();
        }
    }

    @Then("Chapter List contents displayed successfully")
    public void step_3() throws Exception {
        System.out.println("CMS with default and customised contents script executed successfully");
    }
}