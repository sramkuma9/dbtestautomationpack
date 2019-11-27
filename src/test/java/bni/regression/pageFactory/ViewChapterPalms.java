package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewChapterPalms {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#auditLinkt]")
    WebElement slipsAuditReportButton;

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

}
