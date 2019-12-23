package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.BNIConnectSupport;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ZenDeskIntegration {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public List<List<String>> loginSubList;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private CaptureScreenShot captureScreenShot;
    private BNIConnectSupport bniConnectSupport;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("I log in as admin to check zen desk integration")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("I select help icon  in the Home page and the page is redirected to Track and enter the track server using below login details")
    public void step_2(DataTable zenDesk) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : zenDesk.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.clickHelpIcon();
            TimeUnit.SECONDS.sleep(10);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            login.loginToBni(data.get("userName"), data.get("password"));
            TimeUnit.SECONDS.sleep(14);
            bniConnectSupport = new BNIConnectSupport(driver);
            String actualTrackUrl = driver.getCurrentUrl();
            assertEquals("track URL is not correct", data.get("trackUrl"), actualTrackUrl);
            bniConnectSupport.checkBniSupportLink();
            TimeUnit.SECONDS.sleep(2);
            bniConnectSupport.signOutBniSupport();
        }
    }

    @Then("verify your are successfully logged into track and landed in the above link  and verify BNI Connect Support SANDBOX hyperlink is displayed at the bottom of the page")
    public void step_3() throws Exception {
        System.out.println("Zen desk integration script executed.");
    }
}