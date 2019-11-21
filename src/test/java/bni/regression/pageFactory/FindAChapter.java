package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindAChapter {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#mm-0 > section:nth-child(8) > div > div > p.legend > a")
    WebElement advanceSearch;

    @FindBy(css = "#cookieconsent:desc > div > a")
    WebElement iUnderstand;

    public FindAChapter(WebDriver driver) {
        FindAChapter.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickAdvanceSearchButton() {
        advanceSearch.click();
    }

    public void clickIUnderstandButton() {
        iUnderstand.click();
    }

}
