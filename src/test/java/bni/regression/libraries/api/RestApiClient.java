package bni.regression.libraries.api;

import bni.regression.libraries.common.CurrentDateTime;
import bni.regression.libraries.common.ReadWritePropertyFile;
import org.apache.commons.io.FileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RestApiClient {
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CurrentDateTime currentDateTime = new CurrentDateTime();

    public void apiGetClient(String featureName) {

        try {

            URL url = new URL(readWritePropertyFile.loadAndReadPropertyFile("apiUrl", "properties/config.properties"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", readWritePropertyFile.loadAndReadPropertyFile("apiAuthorization", "properties/config.properties"));
            conn.setRequestProperty("Content-Type", readWritePropertyFile.loadAndReadPropertyFile("apiContentType", "properties/config.properties"));

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                try {

                    String[] dateSplit = currentDateTime.dateTime().split("/");
                    String timeSplit[] = dateSplit[2].split(" ");
                    String path = "src/test/resources/executionReports/apiOutput/" + dateSplit[0] + "/" + dateSplit[1] + "/" + timeSplit[0] + "/" + featureName + timeSplit[1] + ".json";
                    FileUtils.writeStringToFile(new File(path), output, StandardCharsets.UTF_8);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
