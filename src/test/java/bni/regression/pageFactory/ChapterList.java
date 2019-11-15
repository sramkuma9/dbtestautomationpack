package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChapterList {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#chapterListTable_info")
    WebElement chapterCount;

    public ChapterList(WebDriver driver) {
        ChapterList.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public String getChapterCount() {
        String chapterTotalCount = chapterCount.getText();
        String[] chapterCountSplit = chapterTotalCount.split(" ");
        return chapterCountSplit[5];
    }

}
