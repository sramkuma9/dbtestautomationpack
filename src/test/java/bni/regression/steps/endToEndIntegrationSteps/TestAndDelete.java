package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.api.RestApiClient;
import bni.regression.libraries.common.*;
import bni.regression.libraries.common.email.GmailClient;
import bni.regression.libraries.db.DbConnect;
import bni.regression.libraries.db.DbXlsComparator;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

import java.sql.*;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class TestAndDelete {
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    DbConnect dbConnect = new DbConnect();
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadPDFReader readPDFReader = new ReadPDFReader();
    SearchAndReturnFileName searchAndReturnFileName = new SearchAndReturnFileName();
    SearchAndDeleteFile searchAndDeleteFile = new SearchAndDeleteFile();
    GmailClient gmailClient = new GmailClient();
    RestApiClient restApiClient = new RestApiClient();
    DbXlsComparator dbXlsComparator = new DbXlsComparator();
    DeleteAndCopyConfigFile deleteAndCopyConfigFile = new DeleteAndCopyConfigFile();

    @Given("test and delete")
    public void test_and_delete(String userName, String subject, String toEmailId, String type, int i) throws Exception {


        gmailClient.checkEmail("seleniumbni@gmail.com", "Business Enhancement Meet" , "seleniumbni+89@gmail.com", "applicant", i);

            /*
              @Given("test and delete")
    public void test_and_delete(DataTable db) throws Exception {
        Integer i=2;
                   // for (Map<String, String> data : db.asMaps(String.class, String.class)) {
          //  Integer expSlipsCount = dbConnect.queryRecordCount("select count(*) from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user join bni.user u2 on u2.id_user = m1.met_with_id_user where u1.first_name =\"Selenium\" and u1.last_name =\"Bni+v20200127164111\" and u2.first_name =\"Selenium\" and u2.last_name =\"Bni2+v20200127121659\" and m1.location= \"testing\" and m1.comments =\"meet 1-1\" and m1.one_to_one_date = \"2020-02-28\";");
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
                ResultSet rs = totalRowCount.executeQuery("select count(*) from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user join bni.user u2 on u2.id_user = m1.met_with_id_user " +
                        "where u1.first_name =\"Selenium\" and u1.last_name =\"Bni+v20200127164111\" and u2.first_name =\"Selenium\" and u2.last_name =\"Bni2+v20200127121659\" and m1.location= \"testing\" and m1.comments =\"meet 1-1\" and m1.one_to_one_date = \"2020-02-28\";");


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

        }

             */
    }
}



























































    /*




        //    readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //       boolean setFlag = readWriteExcel.deleteRow("src/test/resources/inputFiles/testInput.xlsx", "addBrandNewVisitor", 0);
        //       boolean setFlag1 = readWriteExcel.deleteRow("src/test/resources/inputFiles/testInput.xlsx", "addBrandNewVisitor", 1);
//      String[][] sqlResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("addAVisitor1", "properties/sql.properties"));
        //   readWriteExcel.setExcelFile("src/test/resources/inputFiles/testAndDelete.xlsx");
//        Integer xlsRowCount = readWriteExcel.getRowCount("addBrandNewVisitor");
//        String[][] xlsData = readWriteExcel.returnDataForDbXlsComparatorClass("addBrandNewVisitor");
//        dbXlsComparator.resultComparator(sqlResult, xlsData,xlsRowCount);
        //searchAndRenameFile.searchFileAndRename("/home/ajay/Downloads/test","del",".xls");
        //searchAndDeleteFile.searchFileAndDelete("/home/ajay/Downloads/","FindAPersonReport",".xls");
        //gmailClient.checkEmail("shanthibni@gmail.com","BNI- Your registration is successful for TestAutomation20191204134548","shanthibni+32@gmail.com");
        // gmailClient.checkEmail("shanthibni@gmail.com","email verification","shanthibni+45@gmail.com");
        //gmailClient.checkEmail("dbselenium@gmail.com","Fwd: You've visited.....would you now like to join us?","dbselenium@gmail.com","applicant");
        // restApiClient.apiGetClient("addAndsearchBrandNewVisitor");
        //readWriteExcel.setExcelFile("src/test/resources/inputFiles/testAndDelete.xlsx");
        //String[][] xlsData = readWriteExcel.returnDataForDbXlsComparatorClass("addBrandNewVisitor");

        //Find An invoice
//        Integer i = 2;
//        Integer j = 1;
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testAndDelete.xlsx");
//        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
//        String invoiceReference = null;
//        boolean setInvoiceReferenceFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnInvoiceSheet", 0, i, invoiceReference);
//        String personName=null;
//        boolean setLastNameFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnInvoiceSheet", 1, i - j, personName);
//
////        Integer j = 1;
////        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testAndDelete.xlsx");
////        boolean setInvoiceReferenceFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnInvoiceSheet", 0, i, invoiceReference);
////        boolean setLastNameFlag = readWriteExcel.setCellData("src/test/resources/inputFiles/testInput.xlsx", "findAnInvoiceSheet", 1, i - j, personName);
//
//       // readWriteExcel.setCellData("testAndDelete,findAnInvoiceSheet,1,0,1");
//       String[][] sqlResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("findAnInvoice", "properties/sql.properties"));
//


//        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testAndDelete.xlsx");
//        Integer xlsRowCount = readWriteExcel.getRowCount("findAnInvoiceSheet");
//        System.out.println("xlsRowCount is" + xlsRowCount);
//        String[][] xlsData = readWriteExcel.returnDataForDbXlsComparatorClass("findAnInvoiceSheet");
//             dbXlsComparator.resultComparator(sqlResult, xlsData, xlsRowCount);

        //  Integer expSlipsCount = dbConnect.queryRecordCount("select count(*) from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user join bni.user u2 on u2.id_user = m1.met_with_id_user where u1.first_name =\"Selenium\" and u1.last_name =\"Bni+v20200127164111\" and u2.first_name =\"Selenium\" and u2.last_name =\"Bni2+v20200127121659\" and m1.location= \"testing\" and m1.comments =\"meet 1-1\" and m1.one_to_one_date = \"2020-02-28\";");

//        set @monthNo = 2
//        set @sql = N'
//        select SalesPerson
//        from ' + @tableName + '
//        where mon = @monthNo'
//        exec sp_executesql @sql, N'@monthNo int', @monthNo


//        String firstname = "Selenium",
//        String lastname = "Bni6\n",

//        Integer expSlipsCount = dbConnect.queryRecordCount( "   String firstname = \"Selenium\",\n" +
//                "        String lastname = \"Bni6\\n\",select * from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user   join bni.user u2 on u2.id_user = m1.met_with_id_user where u1.first_name =\"firstname\"  and u1.last_name =\"lastname\" )");


        //  join bni.user u2 on u2.id_user = m1.met_with_id_user  where u1.first_name =@firstname  and u1.last_name =@lastname

        //  u2.first_name ="Rizwana" and u2.last_name ="begum" and m1.location= "location" );

//System.out.println("Expected slips count" +expSlipsCount);
//        System.out.println("success");

        //  Set firstName= getRequestString("fname"),lastName = getRequestString("lastName"), select * from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user where u1.first_name ='@fname';

//      Set first_name= Collections.singleton("fname"), lastName = Collections.singleton("lastName");
//      // Select * from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user where u1.first_name ='@fname';
//        Connection con = null;
//        CallableStatement statement = (CallableStatement) con.prepareStatement("Select * from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user where u1.first_name =?");
//
//
//        //statement =con.prepareStatement("SELECT * from employee WHERE  userID = :userId");
//        int fname = 0;
//        statement.setString(fname, String.valueOf(first_name));
//        ResultSet rs = statement.executeQuery();


        /*
//
            try(
                    Connection conn = dbConnect.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement("Select count (*) from bni.member_one_to_one_slip m1 join bni.user u1 on u1.id_user = m1.id_user where u1.first_name  = ?");) {
                    pstmt.setString(1, "Selenium");
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getString("first_name"));
                    }
            }
            // Handle any errors that may have occurred.
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

         */



