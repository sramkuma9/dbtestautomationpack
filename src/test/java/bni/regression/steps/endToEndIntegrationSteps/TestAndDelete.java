package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.ReadPDFReader;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.db.DbConnect;
import cucumber.api.java.en.Given;

public class TestAndDelete {
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    DbConnect dbConnect = new DbConnect();
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadPDFReader readPDFReader = new ReadPDFReader();
    @Given("test and delete")
    public void test_and_delete() throws Exception {
        //readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        //boolean setFlag = readWriteExcel.deleteCellData("src/test/resources/inputFiles/testInput.xlsx", "addVisitor", 0);
        String[][] sqlResult = dbConnect.queryAndRetrieveRecords(readWritePropertyFile.loadAndReadPropertyFile("addAVisitor", "properties/sql.properties"));
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        String rowCount = readWriteExcel.getCellData("sqlCount",0,1);
        String colCount = readWriteExcel.getCellData("sqlCount",1,1);
        for (int i = 0; i < Integer.parseInt(rowCount); i++) {
            for(int j = 0; j <= Integer.parseInt(colCount); j++) {
               System.out.println(sqlResult[i][j]);
            }
        }
    }
}
