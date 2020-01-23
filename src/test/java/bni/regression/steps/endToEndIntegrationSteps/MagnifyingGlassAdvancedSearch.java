package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.*;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.AddToMyConnectionWindow;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.MagnifyingGlass;
import bni.regression.pageFactory.SearchResults;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MagnifyingGlassAdvancedSearch {

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


    @Given("User login details and members already added")
    public void user_login_details_and_members_already_added(DataTable searchMagnifyingGlassDetails) throws Exception {
        // public void search2(DataTable searchMagnifyingGlassDetails) throws Exception {
        List<List<String>> login = searchMagnifyingGlassDetails.raw();
        loginSubList = login.subList(1, login.size());
    }
    @When("^I login BNI app and click magnifying glass and click Advanced Search button and enter keyword First name and click search members$")
    public void search3(DataTable search) throws Exception {
        //public void I_login_BNI_app_and_click_magnifying_glass_and_click_Advanced_Search_button_and_enter_keyword_First_name_and_click_search_members(DataTable search) throws Exception {
        Integer i = 2;
        for (Map<String, String> data : search.asMaps(String.class, String.class)) {
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
            TimeUnit.SECONDS.sleep(15);
            magnifyingGlass = new MagnifyingGlass(driver);
            magnifyingGlass.clickAdvanceSearch();
            TimeUnit.SECONDS.sleep(8);
            magnifyingGlass.advanceKeyword(data.get("memberKeywords"));
            TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.enterFirstName(data.get("memberFirstName"));
            TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.enterLastNameAdvSearch(data.get("memberLastName"));
            TimeUnit.SECONDS.sleep(1);
            /*
            magnifyingGlass.enterCompanyName(data.get("companyName"));
            TimeUnit.SECONDS.sleep(1);
       magnifyingGlass.selectCountry(data.get("country"));
           TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.enterCity(data.get("city"));
            TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.enterState(data.get("state"));
            TimeUnit.SECONDS.sleep(1);

             */
            magnifyingGlass.clickAdvanceSearchMembersButton();
            TimeUnit.SECONDS.sleep(15);
            magnifyingGlass.enterSearchText(data.get("searchField"));
            TimeUnit.SECONDS.sleep(5);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 350)", "");
            TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.clickAddMyConnection();
            TimeUnit.SECONDS.sleep(5);
            addToMyConnectionWindow = new AddToMyConnectionWindow(driver);
            TimeUnit.SECONDS.sleep(1);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "magnifyingGlassBasicSearch");
            addToMyConnectionWindow.clickSubmitButton();
            TimeUnit.SECONDS.sleep(2);
            //verification
            bniConnect.clickMagnifyingGlass();
            TimeUnit.SECONDS.sleep(5);
            magnifyingGlass = new MagnifyingGlass(driver);
            magnifyingGlass.clickAdvanceSearch();
            TimeUnit.SECONDS.sleep(5);
            magnifyingGlass.advanceKeyword(data.get("memberKeywords"));
            TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.enterFirstName(data.get("memberFirstName"));
            TimeUnit.SECONDS.sleep(1);
            magnifyingGlass.clickAdvanceSearchMembersButton();
            TimeUnit.SECONDS.sleep(5);
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(0, 350)", "");
            TimeUnit.SECONDS.sleep(1);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "MagnifyingGlassAdvancedSearch");
            i++;
        }
    }
    @Then("^Relevant searched member details displayed successfully in Results\\. Click \\(“Add to my connections”\\) \\+ Button\\. Click submit button on the Add to my connection message box$")
    // public void relevantSearchedMemberDetailsDisplayedSuccessfullyInResultsClickAddToMyConnectionsButtonClickSubmitButtonOnTheAddToMyConnectionMessageBox()

    //  @Then("Relevant searched member details displayed successfully in Results. Click (“Add to my connections”) + Button. Click submit button on the Add to my connection message box")
    public void step4()throws  Exception
    {
        System.out.println("Advance search Details");
        signOut.signOutBni();
    }

}



