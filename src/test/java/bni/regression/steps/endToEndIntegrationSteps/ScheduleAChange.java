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
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class ScheduleAChange {


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
    private ViewSchemeWindow viewSchemeWindow;
    private ViewScheme viewScheme;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private ViewAttributes viewAttributes;
    private ScheduleChange scheduleChange;


    @Before
    public void setup() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("^user navigates to Pricing portal and Schedule a change for a Scheme from available schemes$")
    public void user_navigates_to_Pricing_portal_and_Schedule_a_change_for_a_Scheme_from_available_schemes(DataTable loginDetails) throws Throwable {

        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^a user login as admin and schedule a change for the scheme in the available Schemes$")
    public void a_user_login_as_admin_and_schedule_a_change_for_the_scheme_in_the_available_Schemes(DataTable scheduleAChange) throws Throwable {

        for (Map<String, String> data : scheduleAChange.asMaps(String.class, String.class)) {
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
            selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
            TimeUnit.SECONDS.sleep(3);
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
                captureScreenShot.takeSnapShot(driver, "ScheduleAChangeScheme");
                TimeUnit.SECONDS.sleep(1);
                viewScheme = new ViewScheme(driver);
                viewScheme.selectSchemeToView(data.get("product"), data.get("sku"), data.get("template"), data.get("status"));
                TimeUnit.SECONDS.sleep(13);
                viewSchemeWindow = new ViewSchemeWindow(driver);
                viewSchemeWindow.clickScheduleAChange();
                TimeUnit.SECONDS.sleep(3);
                scheduleChange = new ScheduleChange(driver);
                scheduleChange.clickEffectiveDateField();
                TimeUnit.SECONDS.sleep(1);
                scheduleChange.selectMonth(data.get("month"));
                TimeUnit.SECONDS.sleep(1);
                scheduleChange.selectDateFromDatePicker(data.get("day"));
                TimeUnit.SECONDS.sleep(1);
                scheduleChange.clickChangeTemplateHyperlink();
                TimeUnit.SECONDS.sleep(1);
                TimeUnit.SECONDS.sleep(5);
                scheduleChange.selectTaxableCheckBox(data.get("taxable"));
                TimeUnit.SECONDS.sleep(1);
                scheduleChange.enterMinimumFees(data.get("minimumFees"));
                TimeUnit.SECONDS.sleep(1);
                scheduleChange.enterMaximumFees(data.get("maximumFees"));
                scheduleChange.clickScheduleChangeButton();

                scheduleChange.selectTemplate(data.get("templateToChange"));
                TimeUnit.SECONDS.sleep(1);
                if ((data.get("templateToChange")).equals("Individual Type")) {
                    System.out.println("loop started -IT");
                    String memberPrice = data.get("memberPrice");
                    System.out.println("MEmber price is" + memberPrice);
                    if (memberPrice.equals("")) {
                        scheduleChange.clickDisabledCheckBox1();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterMemberPrice(data.get("memberPrice"));
                    }

                    String alumniPrice = data.get("alumniPrice");
                    if (alumniPrice.equals("")) {
                        scheduleChange.clickDisabledCheckBox2();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterAlumniPrice(data.get("alumniPrice"));
                    }

                    String visitorPrice = data.get("visitorPrice");
                    if (visitorPrice.equals("")) {
                        scheduleChange.clickDisabledCheckBox3();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterAlumniPrice(data.get("visitorPrice"));
                    }

                    TimeUnit.SECONDS.sleep(1);
                    scheduleChange.enterDefaultPrice(data.get("defaultPrice"));
                    TimeUnit.SECONDS.sleep(1);
                }

                if ((data.get("templateToChange")).equals("Organization")) {
                    scheduleChange.enterPriceForOrganizationTemplate(data.get("orgPrice"));
                }
                if ((data.get("template")).equals("Name and Individual")) {
                    System.out.println("loop started -IT And Chap");
                    String memberActive = data.get("memberActive");
                    if (memberActive.equals("")) {
                        scheduleChange.clickDisabledCheckBox1();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterMemberActivePrice(data.get("memberActive"));
                    }
                    String memberCG = data.get("memberCG");
                    if (memberCG.equals("")) {
                        scheduleChange.clickDisabledCheckBox2();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterMemberCGPrice(data.get("memberCG"));
                    }
                    String memberSuspended = data.get("memberSuspended");
                    if (memberSuspended.equals("")) {
                        scheduleChange.clickDisabledCheckBox3();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterMemberSuspendedPrice(data.get("memberSuspended"));
                    }

                    String alumniActive = data.get("alumniActive");
                    if (alumniActive.equals("")) {
                        scheduleChange.clickDisabledCheckBox4();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterAlumniActivePrice(data.get("alumniActive"));
                    }


                    String alumniCG = data.get("alumniCG");
                    if (alumniCG.equals("")) {
                        scheduleChange.clickDisabledCheckBox5();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterAlumniCGPrice(data.get("alumniCG"));
                    }

                    String alumniSuspended = data.get("alumniSuspended");
                    if (alumniSuspended.equals("")) {
                        scheduleChange.clickDisabledCheckBox6();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterAlumniSuspendedPrice(data.get("alumniSuspended"));
                    }
                    String visitorActive = data.get("visitorActive");
                    if (visitorActive.equals("")) {
                        scheduleChange.clickDisabledCheckBox7();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterVisitorActivePrice(data.get("visitorActive"));
                    }

                    String visitorCG = data.get("visitorCG");
                    if (visitorCG.equals("")) {
                        scheduleChange.clickDisabledCheckBox8();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterVisitorCGPrice(data.get("visitorCG"));
                    }


                    String visitorSuspended = data.get("visitorSuspended");
                    if (visitorSuspended.equals("")) {
                        scheduleChange.clickDisabledCheckBox9();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterVisitorSuspendedPrice(data.get("visitorSuspended"));
                    }


                    scheduleChange.enterDefaultPriceForChapAndIndi(data.get("defaultPrice"));
                    TimeUnit.SECONDS.sleep(1);

                } else if ((data.get("templateToChange")).equals("Chapter Status Type")) {
                    System.out.println("loop started Chapter Based - Name");

                    String memberPrice = data.get("memberPrice");
                    if (memberPrice.equals("")) {
                        scheduleChange.clickDisabledCheckBox1();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterChapterActivePrice(data.get("memberPrice"));
                    }

                    String alumniPrice = data.get("alumniPrice");
                    if (alumniPrice.equals("")) {
                        scheduleChange.clickDisabledCheckBox2();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterChapterCoreGroupPrice(data.get("alumniPrice"));
                    }
                    TimeUnit.SECONDS.sleep(1);

                    String visitorPrice = data.get("visitorPrice");
                    if (visitorPrice.equals("")) {
                        scheduleChange.clickDisabledCheckBox3();
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        scheduleChange.enterSuspendedChapterPrice(data.get("visitorPrice"));
                    }
                    TimeUnit.SECONDS.sleep(1);

                    scheduleChange.enterChapterDefaultPrice(data.get("defaultPrice"));
                    TimeUnit.SECONDS.sleep(1);
                }
                scheduleChange = new ScheduleChange(driver);
                scheduleChange.clickScheduleChangeButton();
                TimeUnit.SECONDS.sleep(1);
                scheduleChange.clickConfirmButton();
                TimeUnit.SECONDS.sleep(1);
            }


        }
    }
}

