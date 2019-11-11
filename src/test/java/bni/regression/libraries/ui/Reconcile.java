package bni.regression.libraries.ui;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.pageFactory.EnterNewApplication;
import bni.regression.pageFactory.ReconcileApplications;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Reconcile {
    private EnterNewApplication enterNewApplication;
    private CaptureScreenShot captureScreenShot = new CaptureScreenShot(LaunchBrowser.driver);
    private ReconcileApplications reconcileApplications;

    public void reconcileApp(String firstName, String LastName, WebDriver driver) throws Exception{
        LaunchBrowser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        enterNewApplication = new EnterNewApplication(driver);
        enterNewApplication.clickReconcileApplicationButton();
        TimeUnit.SECONDS.sleep(8);
        reconcileApplications = new ReconcileApplications(driver);
        reconcileApplications.clickPaymentReceivedCheckBox();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        TimeUnit.SECONDS.sleep(10);
        reconcileApplications.clickRecncileButton();
        alert = driver.switchTo().alert();
        alert.accept();
        TimeUnit.SECONDS.sleep(10);
    }
}