package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.EnterOneToOnes;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AddSlipsViaMemberModule {

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
    private EnterOneToOnes enterOneToOnes;

    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    // Scenario: Navigate to Add a Visitor page
    @Given("member login using below details")
    public void step_1(DataTable loginDetails) throws Exception {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("the member select Enter One to Ones from Member Module, Enter the below details and click save")
    public void step_2(DataTable viewPalms) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : viewPalms.asMaps(String.class, String.class)) {
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
            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
            int colNumber = Integer.parseInt(language[1]);
            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
            String transMenu = readWriteExcel.getCellData("translation", colNumber, 8);
            bniConnect.selectItemFromSubListMenu(transMenu);
            TimeUnit.SECONDS.sleep(5);
            enterOneToOnes = new EnterOneToOnes(driver);
            enterOneToOnes.selectMetWith(data.get("metWith"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectInvitedBy(data.get("invitedBy"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.enterLocation("location");
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.enterTopicsOfConversation("topicsOfConversation");
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.clickApplicationDateField();
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectVisitYear(data.get("year"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectVisitMonth(data.get("month"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectDateFromDatePicker(data.get("day"));
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.clickSaveButton();
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (ElementNotVisibleException e) {
                System.out.println("warning alert not found...");
            }
            TimeUnit.SECONDS.sleep(12);
            signOut.signOutBni();
        }
        i++;
        // Add database verification code.
    }

    @Then("a database entry is made")
    public void step_3() {
        System.out.println("View palms summary script executed.");
    }

}