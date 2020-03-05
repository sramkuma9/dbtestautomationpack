package bni.regression.pageFactory;

import bni.regression.libraries.common.ReadWritePropertyFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CountryReport {
    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    //@FindBy(css =  "#buttons_1 > script:nth-child(1) > a:nth-child(2)")
    //@FindBy(css =  "#buttons_1 > a:nth-child(2)")//*[@id="links_1"]
//      @FindBy(css = "a[ref='/web/secure/WebReport?__params_key=eb0dbf4a-b92a-4ba2-82ba-c864304d37af&ReportType=XLS&encryptString=9fg78g9d04i4323i1kfdfovmn090r8e0ldkxnf9h7r9r0sdfgje928mn13fdgr4g5dg45r2rg1t8jh6df3sd2sd1d8b4g52j1j2t8r6sd6s54ht5k65u4k8ui68iouy65+9090459486409854469084gb9n80498gh40j9fgj09870dfy98h4f9d840yh65er1yaere41h5fgh9004y9er904sdfg8h7fgh132s1g321vb32m1n6gn44fguedy98jh4st464EW6R451AW6R4EZ32D4654C65ZX4D&firstTime=0&reportId=-1'][id='links_1']")
    //@FindBy(css =  "#reporttoolbar_1 > tbody > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1) > script:nth-child(1) > a:nth-child(2)")
    //@FindBy(css = "#links_1")
@FindBy(css= "a.links:nth-child(3)")
    WebElement exportButton;

    @FindBy(css = "#buttons_1")
    List<WebElement> exportButtonList;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button")
    WebElement closeButton;


    public CountryReport(WebDriver driver) {
        CountryReport.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickExportButton() {
        exportButton.click();
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public void clickExportButtonFromList(String buttonName) {
        for (WebElement trElement : exportButtonList) {
            List<WebElement> a_collection = trElement.findElements(By.tagName("a"));
            String Name = a_collection.get(0).getText();
            if (Name.equals(buttonName)) {
                a_collection.get(0).click();
                break;
            }
        }
    }
}
