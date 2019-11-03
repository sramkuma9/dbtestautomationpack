package bni.regression.libraries.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchAndReturnFileName {
    private CurrentDateTime currentDateTime = new CurrentDateTime();
    private String newName;

    public String searchFile(String folderName, String startingString, String endingString) throws IOException {
        String fileName = null;
        String returnFileName = null;
        File[] listFiles = new File(folderName).listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                fileName = listFiles[i].getName();
                if (fileName.startsWith(startingString)
                        && fileName.endsWith(endingString)) {
                    returnFileName = fileName;
                }
            }
        }
        return returnFileName;
    }
}