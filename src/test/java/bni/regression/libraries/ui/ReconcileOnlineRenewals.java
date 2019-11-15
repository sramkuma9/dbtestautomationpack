package bni.regression.libraries.ui;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.pageFactory.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ReconcileOnlineRenewals {
    private EnterNewApplication enterNewApplication;
    private CaptureScreenShot captureScreenShot = new CaptureScreenShot(LaunchBrowser.driver);
    private ReconcileApplications reconcileApplications;
    private BNIConnect bniConnect;
    private LaunchBrowser launchBrowser;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    private RenewalApproval renewalApproval;
    private ViewRenewalApproval viewRenewalApproval;

    public void reconcileApp(String firstName, String lastName, String cred2, String cred3, String cred4) throws Exception {
        LaunchBrowser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //WebDriver driver = launchBrowser.getDriver();
        bniConnect = new BNIConnect(launchBrowser.driver);
        bniConnect.navigateMenu("Operations,Region");
        TimeUnit.SECONDS.sleep(3);
        selectCountryRegionChapter.selectCountryRegChap(cred2.trim(), cred3.trim(), cred4.trim());
        bniConnect = new BNIConnect(launchBrowser.driver);
        TimeUnit.SECONDS.sleep(3);
        String language[] = readWritePropertyFile.loadAndReadPropertyFile("language", "properties/config.properties").split(",");
        int colNumber = Integer.parseInt(language[1]);
        readWriteExcel.setExcelFile("src/test/resources/inputFiles/translation.xlsx");
        String transMainMenu = readWriteExcel.getCellData("translation", colNumber, 3);
        bniConnect.selectItemFromMainListMenu(transMainMenu);
        TimeUnit.SECONDS.sleep(3);
        String transSubMenu = readWriteExcel.getCellData("translation", colNumber, 6);
        bniConnect.selectItemFromSubListMenu(transSubMenu);
        TimeUnit.SECONDS.sleep(5);
        enterNewApplication = new EnterNewApplication(launchBrowser.driver);
        TimeUnit.SECONDS.sleep(8);
        reconcileApplications = new ReconcileApplications(launchBrowser.driver);
        reconcileApplications.enterSearchCriteria(firstName, lastName);
        TimeUnit.SECONDS.sleep(5);
        reconcileApplications.clickAppStatusLink();
        TimeUnit.SECONDS.sleep(4);
        renewalApproval = new RenewalApproval(launchBrowser.driver);
        renewalApproval.clickAgreeCheckBox();
        renewalApproval.clickApproveButton();
        TimeUnit.SECONDS.sleep(10);
        viewRenewalApproval = new ViewRenewalApproval(launchBrowser.driver);
        viewRenewalApproval.checkApprovalStatus();
        viewRenewalApproval.clickBackButton();
        TimeUnit.SECONDS.sleep(8);
        reconcileApplications = new ReconcileApplications(launchBrowser.driver);
        reconcileApplications.enterSearchCriteria(firstName, lastName);
        TimeUnit.SECONDS.sleep(5);
        reconcileApplications.clickPaymentReceivedCheckBox();
        Alert alert = LaunchBrowser.driver.switchTo().alert();
        alert.accept();
        TimeUnit.SECONDS.sleep(10);
    }
}