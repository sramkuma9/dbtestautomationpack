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

        String[] apiPropSplit = readWritePropertyFile.loadAndReadPropertyFile("apiProperty", "properties/config.properties").split(";");
        int propSize = apiPropSplit.length;
        try {

            URL url = new URL(readWritePropertyFile.loadAndReadPropertyFile("apiUrl", "properties/config.properties"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            switch (propSize) {
                case 1:
                    String[] connProp1Split = apiPropSplit[0].split(",");
                    conn.setRequestProperty(connProp1Split[0], connProp1Split[1]);
                    break;
                case 2:
                    String[] connProp2Split1 = apiPropSplit[0].split(",");
                    conn.setRequestProperty(connProp2Split1[0], connProp2Split1[1]);
                    String[] connProp2Split2 = apiPropSplit[1].split(",");
                    conn.setRequestProperty(connProp2Split2[0], connProp2Split2[1]);
                    break;
                case 3:
                    String[] connProp3Split1 = apiPropSplit[0].split(",");
                    conn.setRequestProperty(connProp3Split1[0], connProp3Split1[1]);
                    String[] connProp3Split2 = apiPropSplit[1].split(",");
                    conn.setRequestProperty(connProp3Split2[0], connProp3Split2[1]);
                    String[] connProp3Split3 = apiPropSplit[2].split(",");
                    conn.setRequestProperty(connProp3Split3[0], connProp3Split3[1]);
                    break;
                case 4:
                    String[] connProp4Split1 = apiPropSplit[0].split(",");
                    conn.setRequestProperty(connProp4Split1[0], connProp4Split1[1]);
                    String[] connProp4Split2 = apiPropSplit[1].split(",");
                    conn.setRequestProperty(connProp4Split2[0], connProp4Split2[1]);
                    String[] connProp4Split3 = apiPropSplit[2].split(",");
                    conn.setRequestProperty(connProp4Split3[0], connProp4Split3[1]);
                    String[] connProp4Split4 = apiPropSplit[3].split(",");
                    conn.setRequestProperty(connProp4Split4[0], connProp4Split4[1]);
                    break;
                case 5:
                    String[] connProp5Split1 = apiPropSplit[0].split(",");
                    conn.setRequestProperty(connProp5Split1[0], connProp5Split1[1]);
                    String[] connProp5Split2 = apiPropSplit[1].split(",");
                    conn.setRequestProperty(connProp5Split2[0], connProp5Split2[1]);
                    String[] connProp5Split3 = apiPropSplit[2].split(",");
                    conn.setRequestProperty(connProp5Split3[0], connProp5Split3[1]);
                    String[] connProp5Split4 = apiPropSplit[3].split(",");
                    conn.setRequestProperty(connProp5Split4[0], connProp5Split4[1]);
                    String[] connProp5Split5 = apiPropSplit[4].split(",");
                    conn.setRequestProperty(connProp5Split5[0], connProp5Split5[1]);
                    break;
            }


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
                    String path = readWritePropertyFile.loadAndReadPropertyFile("apiDownloadFilePath", "properties/config.properties")+ dateSplit[0] + "/" + dateSplit[1] + "/" + timeSplit[0] + "/" + featureName + timeSplit[1] + ".json";
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