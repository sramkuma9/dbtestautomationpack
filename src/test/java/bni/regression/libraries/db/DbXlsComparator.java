package bni.regression.libraries.db;

import bni.regression.libraries.common.ReadWriteExcel;

import static org.junit.Assert.assertEquals;

public class DbXlsComparator {
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();

    public void resultComparator(String[][] sqlResult, String[][] xlsData, Integer xlsRowCount) throws Exception {
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/testInput.xlsx");
        Integer dbRowCount = Integer.parseInt(readWriteExcel.getCellData("sqlCount", 0, 1));
        Integer dbColCount = Integer.parseInt(readWriteExcel.getCellData("sqlCount", 1, 1));

        assertEquals("Total records in DB and generated xls report does not match", dbRowCount, xlsRowCount);

        for (int i = 0; i < dbRowCount; i++) {
            for (int j = 0; j <= dbColCount; j++) {
                assertEquals("row - " + (i+2) + " column - " + (j+1) + "' in XLS file & DB does not match", sqlResult[i][j], xlsData[j][i]);
            }
        }
    }
}

