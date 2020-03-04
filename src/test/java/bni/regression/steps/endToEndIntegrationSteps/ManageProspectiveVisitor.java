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

public class ManageProspectiveVisitor {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private LaunchBrowser launchBrowser = new LaunchBrowser();
    private Login login = new Login();
    private SignOut signOut = new SignOut();
    private BNIConnect bniConnect;
    private AddAVisitor addAVisitor;
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    public static String fixedDateTime;
    public static String visitorDateTime;
    public String name;
    public String[] addAVisitorDetails = new String[8];
    public String firstName;
    public String lastName;
    public List<List<String>> loginSubList;
    private CaptureScreenShot captureScreenShot;
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();

    @Before
    public void setup() throws Exception {
        fixedDateTime = currentDateTime.dateTime();
        // readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        // boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "manageAProspectiveVisitor", 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Given("^I am on the manage Visitor Registration page with details below and PV details already added$")
    public void i_Am_On_The_Manage_Visitor_Registration_Page_With_Details_Below_AndPVDetailsAlreadyAdded(DataTable loginDetails) {

        List<List<String>> login = loginDetails.raw();
        loginSubList = login.subList(1, login.size());
    }

    @When("^I click Manage Prospective Visitors and search already added PV user, click edit button , check visitor attended checkbox, enter Invited By and Visited Date details and click the submit button$")
    public void iClickManageProspectiveVisitorsAndSearchAlreadyAddedPVUserClickEditButtonCheckVisitorAttendedCheckboxEnterInvitedByAndVisitedDateDetailsAndClickTheSubmitButton(DataTable table4) throws Exception {

        {
            Integer j = 2;
            Integer i = 1;
            for (Map<String, String> data : table4.asMaps(String.class, String.class)) {
                String[] splitCredentials = loginSubList.get(j - 2).toString().replace("[", "").replace("]", "").split(",");
                driver = launchBrowser.getDriver();
                launchBrowser.invokeBrowser();
                TimeUnit.SECONDS.sleep(2);
                login.loginToBni(splitCredentials[0].replaceAll(" ", ""), splitCredentials[1].replaceAll(" ", ""));
                TimeUnit.SECONDS.sleep(12);
                driver = launchBrowser.getDriver();
                bniConnect = new BNIConnect(driver);
                captureScreenShot = new CaptureScreenShot(driver);
                bniConnect.navigateMenu("Operations,Chapter");
                TimeUnit.SECONDS.sleep(3);
                selectCountryRegionChapter.selectCountryRegChap(splitCredentials[2].trim(), splitCredentials[3].trim(), splitCredentials[4].trim());
                TimeUnit.SECONDS.sleep(3);
                bniConnect = new BNIConnect(driver);
                TimeUnit.SECONDS.sleep(3);
                String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
                int colNumber = Integer.parseInt(language[1]);
                readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
                String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 17);
               // String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 19);
                //track
                 String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 25);
                bniConnect.selectItemFromSubListMenu(transMainMenu);
                TimeUnit.SECONDS.sleep(10);
                bniConnect.selectItemFromSubListMenu(transSubMenu);
                TimeUnit.SECONDS.sleep(10);
                // To click Manage a Registered Visitor & Mark Attendance
                readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
                String lastName = readWriteExcel.getCellData("registerAProspectiveVisitor", 1, i);
                ManageARegisteredVisitor manageARegisteredVisitor = new ManageARegisteredVisitor(driver);
                TimeUnit.SECONDS.sleep(24);
                manageARegisteredVisitor.enterSearch(lastName);
                TimeUnit.SECONDS.sleep(2);
                manageARegisteredVisitor.clickEditAProspectiveVisitorImage();
                TimeUnit.SECONDS.sleep(3);
                manageARegisteredVisitor.clickVisitorAttended();
                TimeUnit.SECONDS.sleep(8);
                manageARegisteredVisitor.selectVisitorInvitedBy(data.get("visitorInvitedBy"));
                TimeUnit.SECONDS.sleep(1);
                manageARegisteredVisitor.clickVisitDate();
                TimeUnit.SECONDS.sleep(1);
                if (!(data.get("visitYear")).equals("")) {
                    manageARegisteredVisitor.clickVisitDate();
                    TimeUnit.SECONDS.sleep(1);
                    manageARegisteredVisitor.selectYear(data.get("visitYear"));
                    TimeUnit.SECONDS.sleep(2);
                    manageARegisteredVisitor.selectMonth(data.get("visitMonth"));
                    TimeUnit.SECONDS.sleep(2);
                    manageARegisteredVisitor.selectDateFromDatePicker(data.get("visitDate"));
                    TimeUnit.SECONDS.sleep(2);
                }
                captureScreenShot = new CaptureScreenShot(driver);
                captureScreenShot.takeSnapShot(driver, "ManageAProspectiveVisitor");
                TimeUnit.SECONDS.sleep(1);
                manageARegisteredVisitor.clickSubmitButton();
                TimeUnit.SECONDS.sleep(5);
                manageARegisteredVisitor.selectIndustry(data.get("industry"));
                TimeUnit.SECONDS.sleep(1);
                manageARegisteredVisitor.selectClassification(data.get("classification"));
                TimeUnit.SECONDS.sleep(1);
                captureScreenShot = new CaptureScreenShot(driver);
                captureScreenShot.takeSnapShot(driver, "ManageAProspectiveVisitor");
                manageARegisteredVisitor.clickSubmitButton();
                TimeUnit.SECONDS.sleep(5);
                i++;
                j++;
                signOut.signOutBni();
            }


        }


    }

    @Then("^PV visitor is added as a visitor successfully$")
    public void pvVisitorIsAddedAsAVisitorSuccessfully() {
        System.out.println("PV visitor is added as a visitor successfully");
    }

    // @And("^sign out from BNI$")
    //public void sign_out_from_BNI() throws Exception {
    //  TimeUnit.SECONDS.sleep(2);
    //signOut.signOutBni();
}





