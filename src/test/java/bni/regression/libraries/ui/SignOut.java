package bni.regression.libraries.ui;

import bni.regression.pageFactory.BNIConnect;
import bni.regression.libraries.common.LaunchBrowser;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

import static bni.regression.pageFactory.BNIConnect.driver;

public class SignOut {
    private BNIConnect bniConnect;

    public void signOutBni() throws Exception{
        LaunchBrowser.driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250)", "");
        TimeUnit.SECONDS.sleep(1);
        bniConnect = new BNIConnect(LaunchBrowser.driver);
       // bniConnect.hoverOnOptions();
        TimeUnit.SECONDS.sleep(5);
        bniConnect.clickSignOutFooter();
        TimeUnit.SECONDS.sleep(3);
        LaunchBrowser.driver.quit();
    }
}