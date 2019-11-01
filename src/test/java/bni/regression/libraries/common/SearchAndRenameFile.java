package bni.regression.libraries.common;

import java.io.File;

public class SearchAndRenameFile {
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private String newName;

    public String searchFileAndRename(String folderName, String startingString, String endingString){
        File[] listFiles = new File(folderName).listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                String fileName = listFiles[i].getName();
                if (fileName.startsWith(startingString)
                        && fileName.endsWith(endingString)) {
                        File f1 = new File(fileName);
                        newName = "FindAPersonReport" + currentDateTime.dateTime().replaceAll("/","_").replaceAll(" ","-") + ".xls";
                        System.out.println(newName);
                        File f2 = new File(newName);
                        f1.renameTo(f2);
                }
            }
        }
        return newName;
    }
}
