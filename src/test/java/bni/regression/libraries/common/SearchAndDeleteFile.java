package bni.regression.libraries.common;

import java.io.File;

public class SearchAndDeleteFile{

    public void searchFileAndDelete(String folderName, String startingString, String endingString){
        File[] listFiles = new File(folderName).listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                String fileName = listFiles[i].getName();
                if (fileName.startsWith(startingString)
                        && fileName.endsWith(endingString)) {
                    File f1 = new File("oldname.txt");
                    File f2 = new File("newname.txt");
                    boolean b = f1.renameTo(f2);
                }
            }
        }
    }
}
