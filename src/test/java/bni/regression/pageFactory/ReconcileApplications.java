package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class ReconcileApplications {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#datalist_filter > input[type=text]")
    WebElement searchTextBox;

    @FindBy(css = "#datalist > tbody > tr:nth-child(1) > td.center_align > input[type=checkbox]")
    WebElement paymentReceivedCheckBox;

    @FindBy(css = "#datalist > tbody > tr:nth-child(1) > td:nth-child(9) > a")
    WebElement reconcileButton;

    public ReconcileApplications(WebDriver driver) {
        ReconcileApplications.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterSearchCriteria(String firstName, String lastName)
    {
        searchTextBox.sendKeys(firstName + " " + lastName);
    }

    public void clickPaymentReceivedCheckBox()
    {
        paymentReceivedCheckBox.click();
    }

    public void clickRecncileButton()
    {
        reconcileButton.click();
    }

}
