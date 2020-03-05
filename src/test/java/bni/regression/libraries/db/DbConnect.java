package bni.regression.libraries.db;

import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;

import java.sql.*;

public class DbConnect {

    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();

    public Connection getConnection()
    {
        Connection conn = null;
        try
        {
            // DB connection details
            Class.forName(readWritePropertyFile.loadAndReadPropertyFile("driver", "properties/config.properties"));
            //   System.out.println(" Connecting to MYSQL for getting rowcount in " + (readWritePropertyFile.loadAndReadPropertyFile("envName", "properties/config.properties")) + " database...");
            String url = (readWritePropertyFile.loadAndReadPropertyFile("url", "properties/config.properties"));
            System.out.println("Url is" +url);
            String userName = (readWritePropertyFile.loadAndReadPropertyFile("userName", "properties/config.properties"));
            String password = (readWritePropertyFile.loadAndReadPropertyFile("password", "properties/config.properties"));

            // connecting to db
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public Integer queryRecordCount(String sqlCountQuery) {

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
            ResultSet rs = totalRowCount.executeQuery(sqlCountQuery);
            rs.next();
            rowCount = rs.getInt(1);
System.out.println("Row count is " +rowCount);
            // closing the result set.
            rs.close();

            // closing db connection
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    public String[][] queryAndRetrieveRecords(String sqlQuery) {
        Connection conn = null;
        Statement actualResult = null;
        String[][] sqlData = new String[50000][50];
        ResultSet actualRS = null;
        Integer recordCount;
        String sqlCountQuery;
        if (sqlQuery.indexOf('*') > 1) {
            sqlCountQuery = sqlQuery.replace("select *", "select count(*)");
        } else {
            sqlCountQuery = sqlQuery.replace("select", "select count(*)" + ",");
        }
        recordCount = this.queryRecordCount(sqlCountQuery);
System.out.println("SQL query is " +sqlCountQuery);
        try {
            // DB connection details
            Class.forName(readWritePropertyFile.loadAndReadPropertyFile("driver", "properties/config.properties"));
         //   System.out.println(" Connecting to MYSQL for getting rowcount in " + (readWritePropertyFile.loadAndReadPropertyFile("envName", "properties/config.properties")) + " database...");
            String url = (readWritePropertyFile.loadAndReadPropertyFile("url", "properties/config.properties"));
            System.out.println("Url is" +url);
            String userName = (readWritePropertyFile.loadAndReadPropertyFile("userName", "properties/config.properties"));
            String password = (readWritePropertyFile.loadAndReadPropertyFile("password", "properties/config.properties"));

            // connecting to db
            conn = DriverManager.getConnection(url, userName, password);
            actualResult = conn.createStatement();
            actualRS = actualResult.executeQuery(sqlQuery);
            ResultSetMetaData rsmd = actualRS.getMetaData();
            Integer colCount = rsmd.getColumnCount()-1;

            readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
            readWriteExcel.setSqlCount("src/test/resources/inputFiles/testInput.xlsx","sqlCount",0,2,recordCount.toString());

            readWriteExcel.setSqlCount("src/test/resources/inputFiles/testInput.xlsx","sqlCount",1,2,colCount.toString());

            //getting the value of each record.
            for (Integer iteration = 0; iteration < recordCount; iteration++) {
                actualRS.next();
                switch (colCount) {
                    case 0:
                        sqlData[iteration][0] = actualRS.getString(1);
                        break;

                    case 1:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        break;

                    case 2:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        sqlData[iteration][2] = actualRS.getString(3);
                        break;

                    case 3:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        sqlData[iteration][2] = actualRS.getString(3);
                        sqlData[iteration][3] = actualRS.getString(4);
                        break;

                    case 4:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        sqlData[iteration][2] = actualRS.getString(3);
                        sqlData[iteration][3] = actualRS.getString(4);
                        sqlData[iteration][4] = actualRS.getString(5);
                        break;

                    case 5:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        sqlData[iteration][2] = actualRS.getString(3);
                        sqlData[iteration][3] = actualRS.getString(4);
                        sqlData[iteration][4] = actualRS.getString(5);
                        sqlData[iteration][5] = actualRS.getString(6);
                        break;

                    case 6:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        sqlData[iteration][2] = actualRS.getString(3);
                        sqlData[iteration][3] = actualRS.getString(4);
                        sqlData[iteration][4] = actualRS.getString(5);
                        sqlData[iteration][5] = actualRS.getString(6);
                        sqlData[iteration][6] = actualRS.getString(7);
                        break;

                    case 7:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        sqlData[iteration][2] = actualRS.getString(3);
                        sqlData[iteration][3] = actualRS.getString(4);
                        sqlData[iteration][4] = actualRS.getString(5);
                        sqlData[iteration][5] = actualRS.getString(6);
                        sqlData[iteration][6] = actualRS.getString(7);
                        sqlData[iteration][7] = actualRS.getString(8);
                        break;

                    case 8:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        sqlData[iteration][2] = actualRS.getString(3);
                        sqlData[iteration][3] = actualRS.getString(4);
                        sqlData[iteration][4] = actualRS.getString(5);
                        sqlData[iteration][5] = actualRS.getString(6);
                        sqlData[iteration][6] = actualRS.getString(7);
                        sqlData[iteration][7] = actualRS.getString(8);
                        sqlData[iteration][8] = actualRS.getString(9);
                        break;

                    case 9:
                        sqlData[iteration][0] = actualRS.getString(1);
                        sqlData[iteration][1] = actualRS.getString(2);
                        sqlData[iteration][2] = actualRS.getString(3);
                        sqlData[iteration][3] = actualRS.getString(4);
                        sqlData[iteration][4] = actualRS.getString(5);
                        sqlData[iteration][5] = actualRS.getString(6);
                        sqlData[iteration][6] = actualRS.getString(7);
                        sqlData[iteration][7] = actualRS.getString(8);
                        sqlData[iteration][8] = actualRS.getString(9);
                        sqlData[iteration][9] = actualRS.getString(10);
                        break;
                }
            }

            // closing the result set.
            actualRS.close();

            // closing db connection
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlData;
    }
}

