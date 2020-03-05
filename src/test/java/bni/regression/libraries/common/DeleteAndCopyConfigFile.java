package bni.regression.libraries.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class DeleteAndCopyConfigFile {

    public void searchFileAndDelete() {

        Path sourceFilePath = Paths.get("src/test/resources/properties/trackConfig.properties");
        Path targetFilePath = Paths.get("src/test/resources/properties/config.properties");
        try {
            Files.delete(targetFilePath);
            TimeUnit.SECONDS.sleep(2);
            Files.copy(sourceFilePath,targetFilePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}