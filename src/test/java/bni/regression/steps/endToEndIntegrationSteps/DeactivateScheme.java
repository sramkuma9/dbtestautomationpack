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
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DeactivateScheme {


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
    private ViewScheme viewScheme;
    private ViewSchemeWindow viewSchemeWindow;private DeactivateSchemeWindow deactivateSchemeWindow;


    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("^user navigates to Pricing portal and Deactivate a created scheme$")
    public void user_navigates_to_Pricing_portal_and_create_a_new_scheme(DataTable loginDetails) throws Throwable {
        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }


    @When("^user login as admin and enters Pricing portal to Deactivate a scheme$")
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
            TimeUnit.SECONDS.sleep(4);
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());

         //  bniConnect.navigateMenu("TOOLS, System Admin");

             bniConnect.navigateMenu("TOOLS,System Admin,Pricing Engine");
            TimeUnit.SECONDS.sleep(5);
            bniConnect = new BNIConnect(driver);
//            bniConnect.selectItemFromMainListMenu("System Admin");
//            TimeUnit.SECONDS.sleep(5);
////            bniConnect.selectItemFromSystemAdminList("Pricing Engine");
//            TimeUnit.SECONDS.sleep(5);
//            String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
//            int colNumber = Integer.parseInt(language[1]);
//            readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
////         //   String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 43);
//            String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 36);
////         //   bniConnect.selectItemFromSubListMenu(transMainMenu);
////            TimeUnit.SECONDS.sleep(5);
//            bniConnect.selectItemFromSubListMenu(transSubMenu);
//            TimeUnit.SECONDS.sleep(5);


            pricingPortalDashboard = new PricingPortalDashboard(driver);
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
            String country = data.get("country");
            String region = data.get("region");
            System.out.println("Region name is" + region);
            if (region == "") {
                pricingPortalDashboard.clickSelectOrganisationButton2();
                TimeUnit.SECONDS.sleep(3);
            } else {
                pricingPortalDashboard.selectRegion(data.get("region"));
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Region selected");
                String chapter = data.get("chapter");
                if (chapter == "") {
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
            }

            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "DeactivateScheme");
            TimeUnit.SECONDS.sleep(1);
            String product = data.get("product");
            String SKU = data.get("SKU");
            String sku = data.get("sku");
            String template = data.get("template");
            String status = data.get("status");

            List<WebElement> body = driver.findElements(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div/div[2]/div/div/div/div"));
            int count = body.size();
            System.out.println("count is" + count);
            for (int a = 1; a <= count; a++) {
                String productValueInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[2]/div/div/div/div/div[1]/div/div")).getText();
                System.out.println("Product" + productValueInViewScheme);

                String skuValueInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[" + (a + 1) + " ]/div[3]/div/div/div/div/div[1]/div/div")).getText();
                System.out.println("SKU" + skuValueInViewScheme);
                String templateInViewScheme = driver.findElement((By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[4]/div/div/div/div/div[1]/div/div"))).getText();
                System.out.println("templateToDeactivate" + templateInViewScheme);
                String statusInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[" + (a + 1) + " ]/div[5]/div/div/div/div/span/span/span")).getText();
                System.out.println("statusToDeactivate" + statusInViewScheme);
                if ((productValueInViewScheme.equals(product) && skuValueInViewScheme.equals(sku) && templateInViewScheme.equals(template) && statusInViewScheme.equals(status))) {
                    System.out.println("Data matched with feature input");
                    WebElement eyeIcon = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[1]/div/div/div/button"));
                    eyeIcon.click();


                }

            }

            TimeUnit.SECONDS.sleep(13);
            viewSchemeWindow = new ViewSchemeWindow(driver);
            viewSchemeWindow.clickDeactivateScheme();
            TimeUnit.SECONDS.sleep(3);
            deactivateSchemeWindow = new DeactivateSchemeWindow(driver);
            // derived scheme or discontinue scheme
            String derivedorDiscontinueScheme = data.get("derived Scheme or discontinueSKU");
            if (derivedorDiscontinueScheme.equals("Use Derived Scheme")) {
                System.out.println("Derived scheme is selected");
                deactivateSchemeWindow.clickSelectDerivedScheme();
                TimeUnit.SECONDS.sleep(2);
                deactivateSchemeWindow.clickEffectiveDate();
                TimeUnit.SECONDS.sleep(2);
                deactivateSchemeWindow.selectMonth(data.get("month"));
                TimeUnit.SECONDS.sleep(2);
                deactivateSchemeWindow.selectDateFromDatePicker(data.get("day"));
                TimeUnit.SECONDS.sleep(2);
                deactivateSchemeWindow.clickDeactivate();
                deactivateSchemeWindow.clickYesButtonToDeactivate();
                TimeUnit.SECONDS.sleep(2);

            } else {

                deactivateSchemeWindow.clickSelectDiscontinueSKU();
                TimeUnit.SECONDS.sleep(2);
                        deactivateSchemeWindow.clickEffectiveDate();
            TimeUnit.SECONDS.sleep(2);
            deactivateSchemeWindow.selectMonth(data.get("month"));
            TimeUnit.SECONDS.sleep(2);
            deactivateSchemeWindow.selectDateFromDatePicker(data.get("day"));
            TimeUnit.SECONDS.sleep(2);
            deactivateSchemeWindow.clickDeactivate();
            deactivateSchemeWindow.clickYesButtonToDeactivate();
            TimeUnit.SECONDS.sleep(2);
            //deactivateSchemeWindow.clickDeactivate();
        }

            String chapter = data.get("chapter");
            String memTerm = data.get("SKU");
            HashMap<String, String> memOption = new HashMap<String, String>();
            memOption.put("12 Month", "5");
            memOption.put("services.pricing.skus.membershipfee6months", "8");
            memOption.put("24 Month", "6");
            memOption.put("12 Months", "5");
            memOption.put("24 Months", "6");
            memOption.put("18 Months", "10");
            memOption.put("18 Month", "10");
            memOption.put("12 Month BNI#", "5");
            memOption.put("24 Month BNI#", "6");
            memOption.put("12 Month S", "5");
            memOption.put("24 Month S", "6");
            memOption.put("12 Months", "5");
            memOption.put("24 Month Sgb", "6");
            memOption.put("12 Months GB path1#מי", "5");
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
            String effective_to = data.get("effectiveTo");
            System.out.println("Effe date " + effective_to);
//Effective to date verification
           String sqlQueryForEffectiveDateCheck = " select effective_to "+
            " from pricing.scheme s1 JOIN pricing.org s2 ON s1.id_org = s2.id "+
             " JOIN pricing.scheme_line s3 ON s1.id = s3.id_scheme "+
             " LEFT JOIN pricing.scheme_line_option s4 ON s3.id = s4.id_scheme_line "+
             "LEFT JOIN pricing.variable_option s5 ON s4.id_variable_option = s5.id "+
             " where   s2.name in ('"+country+"', '"+region+"', '"+chapter+"') " +
             " and id_template ='" + tempType.get(templateType) +"' and id_sku ='" +memTerm + "'  and effective_to = '" + effective_to + "' ; ";


            System.out.println("Sql Query printyed " +sqlQueryForEffectiveDateCheck);
String [][] effectiveToDateFromQuery = dbConnect.queryAndRetrieveRecords(sqlQueryForEffectiveDateCheck);
System.out.println("Effective to date of scheme is" +effectiveToDateFromQuery[0][0]);
// check whether we have future scheme..
            List <WebElement> body2 = driver.findElements(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div/div[2]/div/div/div/div"));
            int count2 = body2.size();
            System.out.println("count2 is" +count2);
            for (int a = 1; a <=count2; a++) {
                String productValueInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[2]/div/div/div/div/div[1]/div/div")).getText();
                System.out.println("Product" + productValueInViewScheme);

                String skuValueInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[" + (a + 1) + " ]/div[3]/div/div/div/div/div[1]/div/div")).getText();
                System.out.println("SKU" + skuValueInViewScheme);
                String templateInViewScheme = driver.findElement((By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[4]/div/div/div/div/div[1]/div/div"))).getText();
                System.out.println("templateToDeactivate" + templateInViewScheme);
                String statusInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[" + (a + 1) + " ]/div[5]/div/div/div/div/span/span/span")).getText();
                System.out.println("statusToDeactivate" + statusInViewScheme);
                if ((productValueInViewScheme.equals(product) && skuValueInViewScheme.equals(sku) && templateInViewScheme.equals(template) && statusInViewScheme.equals("Future"))) {

//                    WebElement eyeIcon = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[1]/div/div/div/button"));
//                    eyeIcon.click();
                    String sqlQueryForFutureSchemeDisabledFlagCheck = " select s1.disabled_flag "+
                            " from pricing.scheme s1 JOIN pricing.org s2 ON s1.id_org = s2.id "+
                            " JOIN pricing.scheme_line s3 ON s1.id = s3.id_scheme "+
                            " LEFT JOIN pricing.scheme_line_option s4 ON s3.id = s4.id_scheme_line "+
                            "LEFT JOIN pricing.variable_option s5 ON s4.id_variable_option = s5.id "+
                            " where   s2.name in ('"+country+"', '"+region+"', '"+chapter+"') " +
                            " and id_template = '" + tempType.get(templateType) +"' and id_sku ='" +memTerm + "' and s1.disabled_flag ='Y'  ; ";
                    String [][]futureSchemeDisabledFlagQuery = dbConnect.queryAndRetrieveRecords(sqlQueryForFutureSchemeDisabledFlagCheck);
                    System.out.println("Future scheme disabled flag is" +futureSchemeDisabledFlagQuery[0][0]);

                }
                else{
                    System.out.println("Scheme does not have any future scheme");
                }


            }
            String sqlQueryForCheckNewSchemeWithDisabledFlagCheck = " select effective_from, s1.disabled_flag"+
                    " from pricing.scheme s1 JOIN pricing.org s2 ON s1.id_org = s2.id "+
                    " JOIN pricing.scheme_line s3 ON s1.id = s3.id_scheme "+
                    " LEFT JOIN pricing.scheme_line_option s4 ON s3.id = s4.id_scheme_line "+
                    "LEFT JOIN pricing.variable_option s5 ON s4.id_variable_option = s5.id "+
                    " where   s2.name in ('"+country+"', '"+region+"', '"+chapter+"') " +
                    " and id_template ='" + tempType.get(templateType) +"' and id_sku ='" + memTerm + "' and s1.disabled_flag ='Y'  and effective_from = '"+effective_to+"' ; ";

            String [][] newSchemeDisabledFlagQuery = dbConnect.queryAndRetrieveRecords(sqlQueryForCheckNewSchemeWithDisabledFlagCheck);
            System.out.println("New scheme effective from date is" +newSchemeDisabledFlagQuery[0][0]);
            System.out.println("New scheme disabled flag is" +newSchemeDisabledFlagQuery[0][1]);


        }




        }
    }


















































/*
                    if (productGiven=="New Membership" && SKU=="Registration Fee") {
                        id_sku.equals("1");
                    }
                    else if (productGiven=="New Membership" && SKU=="12 Months"){
                        id_sku = "2";}

                    else if (productGiven=="New Membership" && SKU=="24 Months"){
                         id_sku = "3";}

                    else if (productGiven=="Renewal Membership" && SKU == "Late Fee"){
                         id_sku = "4";}

                    else if (productGiven=="Renewal Membership" && SKU == "12 Months"){
                         id_sku.equals("5");}

                    else if(productGiven== "Renewal Membership" && SKU == "24 Months"){
                         id_sku = "6";}
                                        else if (productGiven=="New Membership" && SKU == "6 Months") {
                        id_sku = "7";
                    }
                    else if (productGiven=="Renewal Membership" && SKU=="6 Months"){
                         id_sku = "8";}

                    else if (productGiven=="New Membership" && SKU == "18 Months"){
                         id_sku = "9";}

                    else if (productGiven=="Renewal Membership" && SKU == "18 Months"){
                         id_sku = "10";}
                    else {System.out.println("SKU doesn't match");}

 */





              /*  pricingPortalDashboard = new PricingPortalDashboard(driver);
                pricingPortalDashboard.viewScheme1();
                viewScheme = new ViewScheme(driver);
                viewScheme.selectSchemeToDeactivate(data.get("product"),data.get("SKU"), data.get("effectiveDate"));
                pricingPortalDashboard = new PricingPortalDashboard(driver);
                pricingPortalDashboard.viewScheme2();
                viewScheme = new ViewScheme(driver);
                viewScheme.selectSchemeToDeactivate(data.get("product"),data.get("SKU"), data.get("effectiveDate"));
                pricingPortalDashboard = new PricingPortalDashboard(driver);
                pricingPortalDashboard.viewScheme3();
                viewScheme = new ViewScheme(driver);
                viewScheme.selectSchemeToDeactivate(data.get("product"),data.get("SKU"), data.get("effectiveDate"));



               */


/*
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

 */




/*
 String SQLQueryForSchemeID = "select s1.id_scheme from pricing.scheme_line s1 " +
                        " join pricing.scheme s2 on s2.id = s1.id_scheme " +
                        " join pricing.org s3 on s3.id =s2.id_org " +
                        "  join    pricing.variable_option s5 " +
                        "  where  s3.name in ('" + country + "', '" + region + "', '" + chapter + "')    and  id_sku ='" + id_sku + "' and  effective_from ='" + effective_from + "'; ";

                String[][] schemeIDArray = dbConnect.queryAndRetrieveRecords(SQLQueryForSchemeID);
                String schemeID = schemeIDArray[0][0];
                String updateQueryToDeactivateScheme = "update pricing.scheme  SET disabled_flag = 'Y'  where id = '" + schemeID + "'; ";
                dbConnect.queryUpdate(updateQueryToDeactivateScheme);
                String updatedQuery = "Select * from pricing.scheme   where id = '" + schemeID + "'; ";
                String[][] updatedQueryResult = dbConnect.queryAndRetrieveRecords(updatedQuery);
 */