package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CMSWithAbilityToViewEditRegionalWebsites {

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
    private FindAChapter findAChapter;
    private AdvanceChapterSearch advanceChapterSearch;
    private ChapterList chapterList;
    private RegionWebsiteList regionWebsiteList;
    private MultiRegArgSite multiRegArgSite;
    private EditPageFor editPageFor;
    private CMSPreviewUrl cmsPreviewUrl;

    @Before
    public void setup() throws Exception {
        //readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "addVisitor", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("I logged in as Admin user")
    public void User_logged_in_as_Admin(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I select Tools ->Manage Websites and select Manage your Websites and Select Regional website list. Select country as Argentina. Click settings under options. Scroll down and click publish")
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
            countryWebsiteList.clickRegionWebsiteListLink();
            TimeUnit.SECONDS.sleep(3);
            regionWebsiteList = new RegionWebsiteList(driver);
            regionWebsiteList.selectCountries(data.get("country"));
            TimeUnit.SECONDS.sleep(3);
            regionWebsiteList.enterSearchCriteria(data.get("region"));
            TimeUnit.SECONDS.sleep(2);
            regionWebsiteList.clickSettingsButton();
            TimeUnit.SECONDS.sleep(8);
            multiRegArgSite = new MultiRegArgSite(driver);
            multiRegArgSite.enterSearchCriteria(data.get("searchWebSiteString"));
            TimeUnit.SECONDS.sleep(2);
            multiRegArgSite.clickEditPageButton();
            editPageFor = new EditPageFor(driver);
            editPageFor.enterPageTitle(data.get("editWebSiteString"));
            TimeUnit.SECONDS.sleep(2);
            editPageFor.clickSaveButton();
            TimeUnit.SECONDS.sleep(5);
            editPageFor.clickBackButton();
            TimeUnit.SECONDS.sleep(3);
            multiRegArgSite = new MultiRegArgSite(driver);
            multiRegArgSite.clickPublishButton();
            TimeUnit.SECONDS.sleep(2);
            Alert alert = driver.switchTo().alert();
            alert.accept();
            TimeUnit.SECONDS.sleep(12);
            multiRegArgSite.clickPreviewUrl();
            TimeUnit.SECONDS.sleep(12);
            ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs1.get(2));
            //cmsPreviewUrl = new CMSPreviewUrl(driver);
            //cmsPreviewUrl.checkFindAPersonLink(data.get("editWebSiteString"));
            TimeUnit.SECONDS.sleep(2);
            driver.switchTo().window(tabs.get(0));
            signOut.signOutBni();
            i++;
        }
    }

    @Then("Enter the BNI app with edited or newly created regional website loaded as expected and active member is able to view button appears in the header of the published Regional Website")
    public void step_3() throws Exception {
        System.out.println("CMS with ability to view/edit regional websites script executed successfully");
    }
}