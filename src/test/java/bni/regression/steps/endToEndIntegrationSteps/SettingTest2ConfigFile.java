package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CurrentDateTime;
import bni.regression.libraries.common.ReadWritePropertyFile;
import cucumber.api.java.en.Given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class SettingTest2ConfigFile {
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();

    @Given("Delete config file and copy test2 config file")
    public void searchFileAndDelete() {

        Path sourceFilePath = Paths.get("src/test/resources/properties/test2Config.properties");
        Path targetFilePath = Paths.get("src/test/resources/properties/config.properties");
        try {
            Files.delete(targetFilePath);
            TimeUnit.SECONDS.sleep(2);
            Files.copy(sourceFilePath, targetFilePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}