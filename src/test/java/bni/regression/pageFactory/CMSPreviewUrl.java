package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class CMSPreviewUrl {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#mm-0 > section.widgetBanners > div > div:nth-child(1) > ul > li:nth-child(1) > a")
    WebElement findAPersonLink;

    public CMSPreviewUrl(WebDriver driver) {
        CMSPreviewUrl.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void checkFindAPersonLink(String expectedResult){
        String actualResult = findAPersonLink.getText();
        assertEquals("CMS content changed is not reflected in the preview URL.", expectedResult, actualResult);
    }


}
