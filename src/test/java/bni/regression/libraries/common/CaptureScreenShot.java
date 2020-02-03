package bni.regression.libraries.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import java.io.File;

public class CaptureScreenShot {

    public static WebDriver driver;
    private CurrentDateTime currentDateTime = new CurrentDateTime();

    public CaptureScreenShot(WebDriver driver) {
        CaptureScreenShot.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        PageFactory.initElements(factory, this);
    }

    public void takeSnapShot(WebDriver webdriver, String featureName) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        String[] dateSplit = currentDateTime.dateTime().split("/");
        String timeSplit[] = dateSplit[2].split(" ");
        File DestFile = new File("src/test/resources/executionReports/screenShots/" + dateSplit[0] + "/" + dateSplit[1] + "/" + timeSplit[0] + "/" + featureName + timeSplit[1] + ".jpg");
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
