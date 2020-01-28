package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.TranslationHome;
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

public class EditTranslationsAndEnsureThatTheyAppear {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public List<List<String>> loginSubList;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private CaptureScreenShot captureScreenShot;
    private TranslationHome translationHome;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("User logged in with below login details, select T icon in the Home page")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("Amend the Translate English to Japanese page, click Footer and update the below details and click Submit button")
    public void step_2(DataTable translation) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : translation.asMaps(String.class, String.class)) {
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
            bniConnect.clickTIcon();
            TimeUnit.SECONDS.sleep(8);
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            translationHome = new TranslationHome(driver);
            translationHome.clickFooterLink();
            TimeUnit.SECONDS.sleep(3);
            translationHome.enterBrowserPolicy(data.get("browserPolicy"));
            TimeUnit.SECONDS.sleep(1);
            translationHome.enterPrivacyPolicy(data.get("privacyPolicy"));
            TimeUnit.SECONDS.sleep(1);
            translationHome.clickSubmitButton();
            TimeUnit.SECONDS.sleep(3);
            driver.switchTo().window(tabs.get(0));
            driver.navigate().refresh();
            TimeUnit.SECONDS.sleep(10);
            bniConnect = new BNIConnect(driver);
            bniConnect.checkBrowserPolicyTranslation(data.get("browserPolicy"));
            TimeUnit.SECONDS.sleep(1);
            bniConnect.checkPrivacyPolicyTranslation(data.get("privacyPolicy"));
            TimeUnit.SECONDS.sleep(1);
            signOut.signOutBni();
            i++;
        }
    }

    @Then("the browser policy and privacy policy reflect the above changes")
    public void step_3() throws Exception {
        System.out.println("Edit Translations and ensure that they appear script executed.");
    }
}