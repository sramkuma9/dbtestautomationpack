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
import bni.regression.pageFactory.ChapterList;
import bni.regression.pageFactory.EnterOneToOnes;
import bni.regression.pageFactory.chapterOneToOneReport;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

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
    private chapterOneToOneReport ChapterOneToOneReport;
    DbConnect dbConnect = new DbConnect();

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
           String transMenu = readWriteExcel.getCellData("translation", colNumber, 26);
            //Test2
           //  String transMenu = readWriteExcel.getCellData("translation", colNumber, 8);
            bniConnect.selectItemFromSubListMenu(transMenu);
            TimeUnit.SECONDS.sleep(5);

            String metWith = data.get("metWith");
            String invitedBy = data.get("invitedBy");
            String location = data.get("location");
            String topicsOfConversation = data.get("topicsOfConversation");
            String year = data.get("year");
            String month = data.get("month");
            String day = data.get("day");
            enterOneToOnes = new EnterOneToOnes(driver);
            enterOneToOnes.selectMetWith(metWith);
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectInvitedBy(invitedBy);
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.enterLocation(location);
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.enterTopicsOfConversation(topicsOfConversation);
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.clickApplicationDateField();
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectVisitYear(year);
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectVisitMonth(month);
            TimeUnit.SECONDS.sleep(1);
            enterOneToOnes.selectDateFromDatePicker(day);
            TimeUnit.SECONDS.sleep(1);
            captureScreenShot = new CaptureScreenShot(driver);
            captureScreenShot.takeSnapShot(driver, "One-to-one Slips");
            enterOneToOnes.clickSaveButton();
            TimeUnit.SECONDS.sleep(20);
            //metWith
            String [] metName = metWith.split(" ");
            String  metFirst_name = metName[0];
            String  metLast_name =metName[1];
            //invitedBy
            String [] invitedName = invitedBy.split(" ");
            String  invitedFirst_name = invitedName[0];
            String  invitedLast_name =invitedName[1];
            //Date
            SimpleDateFormat inputMonthFormat = new SimpleDateFormat("MMM");
            Calendar cal = Calendar.getInstance();
            cal.setTime(inputMonthFormat.parse(month));
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            String expectedDate = year + "-" + monthFormat.format(cal.getTime()) + "-" +  day;
            System.out.println("Expected date  " + expectedDate);
            //Query
            String sqlQuery = "select count(*) " +
                    "from bni.member_one_to_one_slip m1 " +
                    "join bni.user u1 on u1.id_user = m1.id_user " +
                    "join bni.user u2 on u2.id_user = m1.met_with_id_user " +
                   " where u1.first_name = '"+invitedFirst_name+"' " +
                     "and u1.last_name ='"+invitedLast_name+"' " +
                    "and u2.first_name ='"+metFirst_name+"' " +
                    "and u2.last_name ='"+metLast_name+"' " +
                    "and m1.location= '"+location+"' " +
                    "and m1.comments ='"+topicsOfConversation+"' " +
                    "and m1.one_to_one_date ='"+expectedDate+"';";




           // String sqlQuery ="select count(*) from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user join bni.user u2 on u2.id_user = m1.met_with_id_user where u1.first_name = '"+invitedFirst_name+"' and u1.last_name ='"+invitedLast_name+"' and u2.first_name ='"+metFirst_name+"' and u2.last_name ='"+metLast_name+"' and m1.location= '"+location+"' and m1.comments ='"+topicsOfConversation+"' and m1.one_to_one_date ='"+expectedDate+"'; ";


                       System.out.println(sqlQuery);
                    Integer actCount=  dbConnect.queryRecordCount(sqlQuery);
System.out.println("Actual Count from Database is" +actCount);
Integer expCount=1;
assertEquals( "slips count mismatched", expCount,actCount);
            i++;
            signOut.signOutBni();
        }


        // Add database verification code.
    }


    @Then("a database entry is made")
    public void step_3() {
        System.out.println("View palms summary script executed.");
    }

}












   /*
              // working query
                  String sqlQuery = "select count(*) " +
                            "from bni.member_one_to_one_slip m1 " +
                            "join bni.user u1 on u1.id_user = m1.id_user " +
                            "join bni.user u2 on u2.id_user = m1.met_with_id_user " +

                            "where (u1.first_name||' '||u1.last_name) ='"+invitedBy+"' " +
                            "and (u2.first_name||' '||u2.last_name) ='"+metWith+"' " +
                            "and m1.location= '"+location+"' " +
                            "and m1.comments ='"+topicsOfConversation+"' " +
                            "and m1.one_to_one_date = str_to_date('"+year+month+day+"','%Y%b%d');";

              */


            /*
            try(
                    Connection conn = dbConnect.getConnection();


                    PreparedStatement pstmt = conn.prepareStatement("Select * from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user where u1.first_name  = ?")) {
              System.out.println("value of pstmt" +pstmt);
                pstmt.setString(1, String.valueOf((data.get("metWith"))));
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("metWith"));
                }
            }
            // Handle any errors that may have occurred.
            catch (SQLException e) {
                e.printStackTrace();
            }

             */

//            try {
//                Alert alert = driver.switchTo().alert();
//                alert.accept();
//            } catch (ElementNotVisibleException e) {
//                System.out.println("warning alert not found...");
//            }
            /*

          //  Integer actSlipsCount = 1;
            //  Integer expSlipsCount = dbConnect.queryRecordCount(readWritePropertyFile.loadAndReadPropertyFile("addSlipsViaMemberModule", "properties/sql.properties"));
              Integer expSlipsCount = dbConnect.queryRecordCount("select count(*) from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user join bni.user u2 on u2.id_user = m1.met_with_id_user where u1.first_name ='& and u1.last_name =\"Bni+v20200127164111\" and u2.first_name =\"Selenium\" and u2.last_name =\"Bni2+v20200127121659\" and m1.location= \"testing\" and m1.comments =\"meet 1-1\" and m1.one_to_one_date = \"2020-02-28\";");

              // assertEquals("Slips count is not correct", expSlipsCount, actSlipsCount);
            //   System.out.println("Db count is" + expSlipsCount);
            //               TimeUnit.SECONDS.sleep(12);
            Connection conn = null;
            Statement totalRowCount = null;
            Integer rowCount = null;

            try {
                Class.forName(readWritePropertyFile.loadAndReadPropertyFile("driver", "properties/config.properties"));
                System.out.println(" Connecting to MYSQL for getting rowcount in " + (readWritePropertyFile.loadAndReadPropertyFile("envName", "properties/config.properties")) + " database...");
                String url = (readWritePropertyFile.loadAndReadPropertyFile("url", "properties/config.properties"));
                String userName = (readWritePropertyFile.loadAndReadPropertyFile("userName", "properties/config.properties"));
                String password = (readWritePropertyFile.loadAndReadPropertyFile("password", "properties/config.properties"));

                // connecting to db
                conn = DriverManager.getConnection(url, userName, password);
                totalRowCount = conn.createStatement();

                // getting total rowcount
                ResultSet rs = totalRowCount.executeQuery("select count(*) from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user join bni.user u2 on u2.id_user = m1.met_with_id_user where u1.first_name =\"Selenium\" and u1.last_name =\"Bni+v20200127164111\" and u2.first_name =\"Selenium\" and u2.last_name =\"Bni2+v20200127121659\" and m1.location= \"testing\" and m1.comments =\"meet 1-1\" and m1.one_to_one_date = \"2020-02-28\";");
System.out.println("Result Rs value is " +rs);
                rs.next();
                rowCount = rs.getInt(1);

                // closing the result set.
                rs.close();

                // closing db connection
                conn.close();

            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //  return rowCount;

             */