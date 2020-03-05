package bni.regression.libraries.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchAndDeleteFile{

    public void searchFileAndDelete(String folderName, String startingString, String endingString){
        File[] listFiles = new File(folderName).listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                String fileName = listFiles[i].getName();
                if (fileName.startsWith(startingString)
                        && fileName.endsWith(endingString)) {
                    Path filePath = Paths.get(folderName + fileName);
                    try {
                        Files.delete(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}