package bni.regression.steps.endToEndIntegrationSteps;

import bni.regression.libraries.common.CurrentDateTime;
import bni.regression.libraries.common.ReadWritePropertyFile;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class SettingTrackConfigFile {

    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();

    @Given("Delete config file and copy track config file")
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

    @And("Copy testInput file")
    public void copyTestInputFile() throws IOException {
        String[] dateSplit = currentDateTime.dateTime().split("/");
        String timeSplit[] = dateSplit[2].split(" ");
        String filePath = readWritePropertyFile.loadAndReadPropertyFile("testInputFilePath", "properties/config.properties");
        Path sourceFilePath = Paths.get("src/test/resources/inputFiles/testInput.xlsx");
        System.out.println("sourceFilePath is "   + sourceFilePath);
        Path targetFilePath = Paths.get( filePath + dateSplit[0] + "/" + dateSplit[1] + "/" + timeSplit[0] + "/Track" + timeSplit[1].replaceAll(":","-")  + ".xlsx");
        System.out.println("targetFilePath is "   + targetFilePath);
        Files.copy(sourceFilePath,targetFilePath);
    }
}