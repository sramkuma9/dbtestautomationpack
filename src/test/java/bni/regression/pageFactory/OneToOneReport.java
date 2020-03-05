package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OneToOneReport {

    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#__bookmark_3")
    WebElement OnesCount;

    public OneToOneReport(WebDriver driver) {
        ChapterList.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public Integer getOneToOneReportCount() {
        String oneToOneTotalCount = OnesCount.getText();
        String[] chapterCountSplit = oneToOneTotalCount.split(" ");
        return Integer.valueOf(chapterCountSplit[5]);
    }

}

