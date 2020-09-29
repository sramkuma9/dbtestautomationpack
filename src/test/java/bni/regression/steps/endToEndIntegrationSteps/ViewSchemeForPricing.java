package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.*;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ViewSchemeForPricing {


    public static WebDriver driver;

    public List<List<String>> loginSubList;
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    private CaptureScreenShot captureScreenShot;
    DbConnect dbConnect = new DbConnect();
    private PricingPortalDashboard pricingPortalDashboard;
    private ViewScheme viewScheme;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private ViewAttributes viewAttributes;


    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Given("^user navigates to Pricing portal and view the available schemes$")
    public void userNavigatesToPricingPortalAndViewTheAvailableSchemes(DataTable loginDetails) throws Throwable {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^user with any role for access logins the BNI Connect will be able to view the available Schemes$")
    public void userWithAnyRoleForAccessLoginsTheBNIConnectWillBeAbleToViewTheAvailableSchemes(DataTable viewSchemeTable) throws Throwable {
        for (Map<String, String> data : viewSchemeTable.asMaps(String.class, String.class)) {
            int i = 2;
            int j = 1;
            String[] splitCredentials = loginSubList.get(i - 2).toString().replace("[", "").replace("]", "").split(",");
            driver = launchBrowser.getDriver();
            launchBrowser.invokeBrowser();
            TimeUnit.SECONDS.sleep(2);
            login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
            TimeUnit.SECONDS.sleep(12);
            driver = launchBrowser.getDriver();
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
         //   selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());

            String userRole = data.get("userRole");
         /*   String userName = data.get("userName");

            String roleCheckQuery =    "select roles from bni.user where user_name ='"+userName+"' ;";
            String [][] roleFetchedFromQuery = dbConnect.queryAndRetrieveRecords(roleCheckQuery);
            String roles = roleFetchedFromQuery[0][0];
            System.out.println("Role value is" +roles);
            String [] role = roleCheckQuery.split(",");
//            int roleValue  = Integer.parseInt(role);
//            System.out.println("Role value is" +roleValue);
            if(role.valueOf(role).equals('7'))

 */
if(userRole.equals("CountryLevel") ) {
    System.out.println("CountryLevel login");

    bniConnect.navigateMenu("TOOLS,System Admin,Pricing Engine");
    TimeUnit.SECONDS.sleep(2);
    String chapter = data.get("chapter");
    String country = data.get("country");
    String region = data.get("region");
    String memTerm = data.get("sku");
    HashMap<String, String> memOption = new HashMap<String, String>();
    memOption.put("12 Month", "2");
    memOption.put("services.pricing.skus.membershipfee6months", "7");
    memOption.put("24 Month", "3");
    memOption.put("12 Months", "2");
    memOption.put("24 Months", "3");
    memOption.put("18 Months", "8");
    memOption.put("18 Month", "8");
    memOption.put("12 Month BNI#", "2");
    memOption.put("24 Month BNI#", "3");
    memOption.put("12 Month S", "2");
    memOption.put("24 Month S", "3");
    memOption.put("12 Months", "2");
    memOption.put("24 Month Sgb", "3");
    memOption.put("12 Months GB path1#מי", "2");
    String templateType = data.get("template");
    HashMap<String, String> tempType = new HashMap<>();
    tempType.put("Organization", "1");
    tempType.put("Individual Type", "2");
    tempType.put("Chapter Status Type", "3");
    tempType.put("Individual and Chapter Status Type", "4");
    String individualOption = data.get("option");
    String chapterOption = data.get("option2");
    HashMap<String, String> option = new HashMap<String, String>();
    option.put("MEMBER", "1");
    option.put("ALUMNI", "2");
    option.put("VISITOR", "3");
    HashMap<String, String> option2 = new HashMap<String, String>();
    option2.put("ACTIVE", "4");
    option2.put("COREGROUP", "5");
    option2.put("SUSPENDED", "6");
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    TimeUnit.SECONDS.sleep(1);
    pricingPortalDashboard = new PricingPortalDashboard(driver);
    pricingPortalDashboard.clickSelectOrganisationButton();
    TimeUnit.SECONDS.sleep(1);
    pricingPortalDashboard.clickDropdown();
    TimeUnit.SECONDS.sleep(1);
    pricingPortalDashboard.selectCountry(data.get("country"));
    TimeUnit.SECONDS.sleep(1);
    String regionName = data.get("region");
    System.out.println("Region name is" + regionName);
    if (regionName == "") {
        pricingPortalDashboard.clickSelectOrganisationButton2();
        TimeUnit.SECONDS.sleep(3);
    } else {
        pricingPortalDashboard.selectRegion(data.get("region"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Region selected");
        String chapterName = data.get("chapter");
        if (chapterName == "") {
            pricingPortalDashboard.clickSelectOrganisationButton2();
            TimeUnit.SECONDS.sleep(3);
        } else
            TimeUnit.SECONDS.sleep(1);
        pricingPortalDashboard.selectChapter(data.get("chapter"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("chapter selected");
        TimeUnit.SECONDS.sleep(1);
        pricingPortalDashboard.clickSelectOrganisationButton2();
        TimeUnit.SECONDS.sleep(3);
        captureScreenShot = new CaptureScreenShot(driver);
        captureScreenShot.takeSnapShot(driver, "View Scheme");
        TimeUnit.SECONDS.sleep(1);
        viewScheme = new ViewScheme(driver);
        viewScheme.selectSchemeToView(data.get("product"), data.get("sku"), data.get("template"), data.get("status"));

    }
}

//else if(userRole.equals("CountryLevel"))
//{
//    bniConnect.navigateMenu("ADMIN,Country");
//    TimeUnit.SECONDS.sleep(2);
//    selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
//    bniConnect = new BNIConnect(driver);
//    TimeUnit.SECONDS.sleep(3);
//    String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
//    int colNumber = Integer.parseInt(language[1]);
//    readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
//    String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 39);
//    String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 40);
//    bniConnect.selectItemFromSubListMenu(transMainMenu);
//    TimeUnit.SECONDS.sleep(5);
//    bniConnect.selectItemFromSubListMenu(transSubMenu);
//    TimeUnit.SECONDS.sleep(5);
//    viewAttributes = new ViewAttributes(driver);
//    viewAttributes.clickviewPricingButton();
//    TimeUnit.SECONDS.sleep(5);
//}
else if(userRole.equals("RegionalLevel"))
{
    bniConnect.navigateMenu("ADMIN,Region");
    TimeUnit.SECONDS.sleep(2);
    selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
    bniConnect = new BNIConnect(driver);
    TimeUnit.SECONDS.sleep(3);
    bniConnect.navigateMenu("ADMIN,Region");
    TimeUnit.SECONDS.sleep(2);
    String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
    int colNumber = Integer.parseInt(language[1]);
    readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
    String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 37);
    String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 38);
    bniConnect.selectItemFromSubListMenu(transMainMenu);
    TimeUnit.SECONDS.sleep(5);
    bniConnect.selectItemFromSubListMenu(transSubMenu);
    TimeUnit.SECONDS.sleep(5);
    JavascriptExecutor js51 = (JavascriptExecutor) driver;
    js51.executeScript("window.scrollBy(0, 450)", "");
    viewAttributes = new ViewAttributes(driver);
    viewAttributes.clickViewPricingRegionButton();
    TimeUnit.SECONDS.sleep(5);
}
else
{
    bniConnect.navigateMenu("ADMIN,Chapter");
    TimeUnit.SECONDS.sleep(2);
    selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
    bniConnect = new BNIConnect(driver);
    TimeUnit.SECONDS.sleep(3);
    bniConnect.navigateMenu("ADMIN,Chapter");
    TimeUnit.SECONDS.sleep(2);
    String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
    int colNumber = Integer.parseInt(language[1]);
    readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
    String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 41);
    String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 42);
    bniConnect.selectItemFromSubListMenu(transMainMenu);
    TimeUnit.SECONDS.sleep(5);
    bniConnect.selectItemFromSubListMenu(transSubMenu);
    TimeUnit.SECONDS.sleep(5);
    JavascriptExecutor js51 = (JavascriptExecutor) driver;
    js51.executeScript("window.scrollBy(0, 450)", "");
    viewAttributes = new ViewAttributes(driver);
    viewAttributes.clickViewPricingChapterButton();
    TimeUnit.SECONDS.sleep(5);
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    TimeUnit.SECONDS.sleep(1);
    viewScheme = new ViewScheme(driver);
    viewScheme.selectSchemeToView(data.get("product"), data.get("sku"), data.get("template"),data.get("status"));
    TimeUnit.SECONDS.sleep(5);




}


            }
        }
    }





