package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.ui.Login;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
import bni.regression.libraries.ui.SignOut;
import bni.regression.pageFactory.BNIConnect;
import bni.regression.pageFactory.CreateScheme;
import bni.regression.pageFactory.PricingPortalDashboard;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateSchemeForPricing {
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
    private CreateScheme createScheme;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();


    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("^user navigates to Pricing portal and create a new scheme$")
    public void user_navigates_to_Pricing_portal_and_create_a_new_scheme(DataTable loginDetails) throws Throwable {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }


    @When("^user login as admin and enters Pricing portal$")
    public void user_login_as_admin_and_enters_Pricing_portal(DataTable pricing) throws Throwable {
        for (Map<String, String> data : pricing.asMaps(String.class, String.class)) {
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
//            bniConnect.navigateMenu("TOOLS,System Admin");
//            TimeUnit.SECONDS.sleep(2);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
//            bniConnect.navigateMenu("TOOLS,System Admin");
//            TimeUnit.SECONDS.sleep(2);
            bniConnect = new BNIConnect(driver);
            TimeUnit.SECONDS.sleep(3);
//            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
//            int colNumber = Integer.parseInt(language[1]);
//            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
//            String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 36);
//            bniConnect.selectItemFromSubListMenu(transMainMenu);
            TimeUnit.SECONDS.sleep(5);
            bniConnect.navigateMenu("TOOLS,System Admin,Pricing Engine");
            TimeUnit.SECONDS.sleep(15);




            String chapter = data.get("chapter");
            String country= data.get("country");
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
                captureScreenShot.takeSnapShot(driver, "CreateScheme");
                TimeUnit.SECONDS.sleep(1);
                pricingPortalDashboard.clickCreateScheme();
                TimeUnit.SECONDS.sleep(1);
                createScheme = new CreateScheme(driver);
                createScheme.selectProductFrom(data.get("productFrom"));
                TimeUnit.SECONDS.sleep(1);
                createScheme.selectSKU(data.get("sku"));
               TimeUnit.SECONDS.sleep(1);
//               createScheme.selectTemplate(data.get("template"));
//               TimeUnit.SECONDS.sleep(1);
                createScheme.clickEffectiveDateField();
                TimeUnit.SECONDS.sleep(4);
                createScheme.selectMonth(data.get("month"));
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Month selected");
                //select year only when next year need to be added
//                createScheme.selectYear(data.get("year"));
//                TimeUnit.SECONDS.sleep(1);
                createScheme.selectDateFromDatePicker(data.get("day"));
                TimeUnit.SECONDS.sleep(5);
                createScheme.selectTaxableCheckBox(data.get("taxable"));
                TimeUnit.SECONDS.sleep(1);
                createScheme.enterMinimumFees(data.get("minimumFees"));
                TimeUnit.SECONDS.sleep(1);
                createScheme.enterMaximumFees(data.get("maximumFees"));
                createScheme.selectTemplate(data.get("template"));
                TimeUnit.SECONDS.sleep(1);
                if ((data.get("template")).equals("Individual Type")) {
                    System.out.println("loop started -IT");
                    String memberPrice = data.get("memberPrice");
                    System.out.println("MEmber price is" +memberPrice);
                    if (memberPrice.equals("") )
                    {
                        createScheme.clickDisabledCheckBox1();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterMemberPrice(data.get("memberPrice"));
                    }

                    String alumniPrice = data.get("alumniPrice");
                    if (alumniPrice.equals(""))
                    {
                        createScheme.clickDisabledCheckBox2();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterAlumniPrice(data.get("alumniPrice"));
                    }

                    String visitorPrice = data.get("visitorPrice");
                    if (visitorPrice.equals(""))
                    {
                        createScheme.clickDisabledCheckBox3();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterAlumniPrice(data.get("visitorPrice"));
                    }

                    TimeUnit.SECONDS.sleep(1);
                    createScheme.enterDefaultPrice(data.get("defaultPrice"));
                    TimeUnit.SECONDS.sleep(1);
                }

                if ((data.get("template")).equals("Organization")) {
                    createScheme.enterPriceForOrganizationTemplate(data.get("orgPrice"));
                }
                if ((data.get("template")).equals("Name and Individual")) {
                    System.out.println("loop started -IT And Chap");
                    String memberActive = data.get("memberActive");
                    if (memberActive.equals(""))
                    {
                        createScheme.clickDisabledCheckBox1();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterMemberActivePrice(data.get("memberActive"));
                    }
                    String memberCG = data.get("memberCG");
                    if (memberCG.equals(""))
                    {
                        createScheme.clickDisabledCheckBox2();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterMemberCGPrice(data.get("memberCG"));
                    }
                    String memberSuspended = data.get("memberSuspended");
                    if (memberSuspended.equals(""))
                    {
                        createScheme.clickDisabledCheckBox3();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterMemberSuspendedPrice(data.get("memberSuspended"));
                    }

                    String alumniActive = data.get("alumniActive");
                    if (alumniActive.equals(""))
                    {
                        createScheme.clickDisabledCheckBox4();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterAlumniActivePrice(data.get("alumniActive"));
                    }


                    String alumniCG = data.get("alumniCG");
                    if (alumniCG.equals(""))
                    {
                        createScheme.clickDisabledCheckBox5();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterAlumniCGPrice(data.get("alumniCG"));
                    }

                    String alumniSuspended = data.get("alumniSuspended");
                    if (alumniSuspended.equals(""))
                    {
                        createScheme.clickDisabledCheckBox6();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterAlumniSuspendedPrice(data.get("alumniSuspended"));
                    }
                    String visitorActive = data.get("visitorActive");
                    if (visitorActive.equals(""))
                    {
                        createScheme.clickDisabledCheckBox7();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterVisitorActivePrice(data.get("visitorActive"));
                    }

                    String visitorCG = data.get("visitorCG");
                    if (visitorCG.equals(""))
                    {
                        createScheme.clickDisabledCheckBox8();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterVisitorCGPrice(data.get("visitorCG"));
                    }


                    String visitorSuspended = data.get("visitorSuspended");
                    if (visitorSuspended.equals(""))
                    {
                        createScheme.clickDisabledCheckBox9();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterVisitorSuspendedPrice(data.get("visitorSuspended"));
                    }



                    createScheme.enterDefaultPriceForChapAndIndi(data.get("defaultPrice"));
                    TimeUnit.SECONDS.sleep(1);

                } else if ((data.get("template")).equals("Chapter Status Type")) {
                    System.out.println("loop started Chapter Based - Name");

                    String memberPrice = data.get("memberPrice");
                    if (memberPrice.equals(""))
                    {
                        createScheme.clickDisabledCheckBox1();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterChapterActivePrice(data.get("memberPrice"));
                    }

                  String alumniPrice = data.get("alumniPrice");
                    if (alumniPrice.equals(""))
                    {
                        createScheme.clickDisabledCheckBox2();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterChapterCoreGroupPrice(data.get("alumniPrice"));
                    }
                    TimeUnit.SECONDS.sleep(1);

                    String visitorPrice = data.get("visitorPrice");
                    if (visitorPrice.equals(""))
                    {
                        createScheme.clickDisabledCheckBox3();
                        TimeUnit.SECONDS.sleep(1);
                    }else {
                        createScheme.enterSuspendedChapterPrice(data.get("visitorPrice"));
                    }
                    TimeUnit.SECONDS.sleep(1);

                    createScheme.enterChapterDefaultPrice(data.get("defaultPrice"));
                    TimeUnit.SECONDS.sleep(1);
                }
            }
            createScheme.clickCreateSchemeButton();







           String newSchemeQuery = " select s1.id, s1.id_org, s1.id_sku, id_template, "+
                    "effective_from, effective_to, min_price, max_price, taxable_flag, " +
                    "grandfathering_threshold, s1.disabled_flag, s1.created, s1.last_modified " +
            "from pricing.scheme s1 JOIN pricing.org s2 ON s1.id_org = s2.id " +
            "JOIN pricing.scheme_line s3 ON s1.id = s3.id_scheme " +
             "LEFT JOIN pricing.scheme_line_option s4 ON s3.id = s4.id_scheme_line " +
            " LEFT JOIN pricing.variable_option s5 ON s4.id_variable_option = s5.id "+
            "where  " +
            "  s2.name in ('"+country+"', '"+region+"', '"+chapter+"') " +
                   "and id_template='" + tempType.get(templateType) +"' and id_sku ='" + memOption.get(memTerm) + "'  " +
             " order by s2.org_type desc limit 1;" ;
//s1.created = curdate() and " +

            String[][] newSchemeQueryResult = dbConnect.queryAndRetrieveRecords(newSchemeQuery);
            String schemeID = newSchemeQueryResult[0][0];
            System.out.println("schemeId is :" +schemeID);


            String idOrg = newSchemeQueryResult[0][1];
            System.out.println("idOrg is :" +idOrg);
          /*  String idSKU = newSchemeQuery_result[0][2];
            String idTemplate = newSchemeQuery_result[0][3];
            String effectiveFrom = newSchemeQuery_result[0][4];
            String effectiveTo = newSchemeQuery_result[0][5];
            String minPrice = newSchemeQuery_result[0][6];
            String maxPrice = newSchemeQuery_result[0][7];
            String taxableFlag = newSchemeQuery_result[0][8];
            String grandfathering_threshold = newSchemeQuery_result[0][9];
            String disabledFlag = newSchemeQuery_result[0][10];
            String created = newSchemeQuery_result[0][11];
            String lastModified = newSchemeQuery_result[0][12];



            readWriteExcel.setExcelFile("src/test/resources/executionReports/PricingResults/NMA.xlsx");
            boolean setschemeIDFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 0, i, schemeID);
            boolean setidOrgFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 1, i - j, idOrg);
            boolean setidSKUTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 2, i - j, idSKU);
            boolean setidTemplateTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 3, i - j, idTemplate);
            boolean seteffectiveFromFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 4, i - j, effectiveFrom);
            boolean seteffectiveToFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 5, i - j, effectiveTo);
            boolean setminPriceFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 6, i - j, minPrice);
            boolean setmaxPriceTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 7, i - j, maxPrice);
            boolean setTaxableFlagTax = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 8, i - j, taxableFlag);
            boolean setgrandfathering_thresholdFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 9, i - j, grandfathering_threshold);
            boolean setdisabledFlagFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 10, i - j, disabledFlag);
            boolean setcreatedFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 11, i - j, created);
            boolean setlastModifiedFlag = readWriteExcel.setCellData("src/test/resources/executionReports/PricingResults/NMA.xlsx", "Scheme", 12, i - j, lastModified);
 */
            //Tax for Registration Fee




        }
    }
}
