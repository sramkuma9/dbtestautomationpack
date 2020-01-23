package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
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

public class MagnifyingGlassBasicSearch {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public String firstName;
    public String lastName;
    public List<List<String>> loginSubList;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private SearchResults searchResults;
    private MagnifyingGlass magnifyingGlass;
    public static String fixedDateTime;
    private CaptureScreenShot captureScreenShot;
    private AddToMyConnectionWindow addToMyConnectionWindow;

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        //readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "magnifyingGlass", 0);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Given("^User login details$")
    public void user_login_details(DataTable basicSearch) throws Exception {
        List<List<String>> login = basicSearch.raw();
        loginSubList = login.subList(1, login.size());
    }


    @When("^I login BNI app and click magnifying glass and enter keyword and click search members, Click \\(“Add to my connections”\\) \\+ Button$")
    //   @When(" I login BNI app and click magnifying glass and enter keyword and click search members")
    public void I_login_BNI_app_and_click_magnifying_glass_and_enter_keyword_and_click_search_members__Click_Add_to_my_connections_Button(DataTable basicSearch) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : basicSearch.asMaps(String.class, String.class)) {
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            captureScreenShot = new CaptureScreenShot(driver);
            bniConnect.clickMagnifyingGlass();
            TimeUnit.SECONDS.sleep(5);
            magnifyingGlass = new MagnifyingGlass(driver);
            magnifyingGlass.enterKeyword(data.get("memberKeyword"));
            TimeUnit.SECONDS.sleep(1);

            magnifyingGlass.clickSearchMembersButton();
            TimeUnit.SECONDS.sleep(12);
            magnifyingGlass.enterSearchText(data.get("searchField"));
            TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.clickAddMyConnection();
            TimeUnit.SECONDS.sleep(5);
           // magnifyingGlass.clickSearchMembersButton();
           // TimeUnit.SECONDS.sleep(2);
            //magnifyingGlass.clickAddToMyConnectButton();
           // TimeUnit.SECONDS.sleep(2);
            addToMyConnectionWindow = new AddToMyConnectionWindow(driver);
            TimeUnit.SECONDS.sleep(1);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "magnifyingGlassBasicSearch");
            addToMyConnectionWindow.clickSubmitButton();
            TimeUnit.SECONDS.sleep(2);
            bniConnect.clickMagnifyingGlass();
            TimeUnit.SECONDS.sleep(5);
            magnifyingGlass = new MagnifyingGlass(driver);
            magnifyingGlass.enterKeyword(data.get("memberKeyword"));
            TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.clickSearchMembersButton();
            TimeUnit.SECONDS.sleep(5);
            magnifyingGlass.enterSearchText(data.get("searchField"));
            TimeUnit.SECONDS.sleep(1);

            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "MagnifyingGlassBasicSearch");
            i++;
            signOut.signOutBni();



        }
    }


    @Then("^Click submit button on the Add to my connection message box\\.$")
    public void clickSubmitButtonOnTheAddToMyConnectionMessageBox() {
System.out.println("Message submitted successfully");
    }
}
