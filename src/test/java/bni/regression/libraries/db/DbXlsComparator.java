package bni.regression.libraries.db;

import bni.regression.libraries.common.ReadWriteExcel;

import static org.junit.Assert.assertEquals;

public class DbXlsComparator {
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();

    public void resultComparator(String[][] sqlResult, String[][] xlsData, Integer xlsRowCount) throws Exception {
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        String dbRowCount = readWriteExcel.getCellData("sqlCount", 0, 1);
        String dbColCount = readWriteExcel.getCellData("sqlCount", 1, 1);

        assertEquals("Total records in DB and generated xls report does not match", dbRowCount, xlsRowCount);

        for (int i = 0; i < Integer.parseInt(dbRowCount); i++) {
            for (int j = 0; j <= Integer.parseInt(dbColCount); j++) {
                assertEquals("The value '" + xlsData[i][j] + "' in XLS file & DB does not match", sqlResult[i][j], xlsData[i][j]);
            }
        }
    }
}

