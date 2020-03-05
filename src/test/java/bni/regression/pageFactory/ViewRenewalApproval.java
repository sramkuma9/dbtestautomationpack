package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;

public class ViewRenewalApproval {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#subsubnav > a > span")
    WebElement backButton;

    @FindBy(css = "#memberDeatils > dl > dd:nth-child(6)")
    WebElement approvalStatus;


    public ViewRenewalApproval(WebDriver driver) {
        ViewRenewalApproval.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickBackButton()
    {
        backButton.click();
    }

    public void checkApprovalStatus(){
        String appStatus = approvalStatus.getText();
        assertEquals("online renewal approval status is not correct", "Approved",appStatus);
    }

}
