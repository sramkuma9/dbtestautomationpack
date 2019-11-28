package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewVisitor {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css =  "#finishReviewButton")
    WebElement continueWithoutApprovingVisitorsButton;

    public ReviewVisitor(WebDriver driver) {
        ReviewVisitor.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickContinueWithoutApprovingVisitorsButton() throws InterruptedException{
        continueWithoutApprovingVisitorsButton.click();
    }
}