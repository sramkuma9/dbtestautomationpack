package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;

public class SlipsAuditReport {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#heading_1")
    WebElement slipsAuditReportHeading;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
    WebElement closeButton;

    public SlipsAuditReport(WebDriver driver) {
        SlipsAuditReport.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickCloseButton() throws InterruptedException{
        closeButton.click();
    }

    public void checkSlipsAuditReport() throws InterruptedException{
        boolean actualResult = slipsAuditReportHeading.getText().contains("Slips Audit Report for");
        assertEquals("Slips Audit Report is Displayed.", true,actualResult);
    }

}
