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

public class RegionReport {
    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    // @FindBy(css =  "#buttons_1 > a:nth-child(1)")
    @FindBy(css = "#links_1")
    WebElement exportButton;

    @FindBy(css = "#buttons_1")
    List<WebElement> exportButtonList;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button")
    WebElement closeButton;


    public RegionReport(WebDriver driver) {
        RegionReport.driver = driver;
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
