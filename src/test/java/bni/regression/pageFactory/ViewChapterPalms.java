package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ViewChapterPalms {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#auditLink")
    WebElement slipsAuditReportButton;

    @FindBy(css = "#datalist > tbody > tr > td:nth-child(9)")
    WebElement palmsEntry;

    public ViewChapterPalms(WebDriver driver) {
        ViewChapterPalms.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickSlipsAuditReportButton() throws InterruptedException{
        slipsAuditReportButton.click();
    }

    public void checkPalmsEntry(){
        System.out.println(palmsEntry.getText());
        assertEquals("Palms entry for 1-2-1 is not correct", "1", palmsEntry.getText());
    }

}
