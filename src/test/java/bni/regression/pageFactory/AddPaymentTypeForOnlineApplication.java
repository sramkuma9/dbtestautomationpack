package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AddPaymentTypeForOnlineApplication {


    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement submitButton;

    @FindBy(css="#paymentOption")
    WebElement paymentOption;

    public AddPaymentTypeForOnlineApplication(WebDriver driver){

        AddPaymentTypeForOnlineApplication.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectPaymentOption(String payment) throws InterruptedException{

        Select paymentSelect = new Select(paymentOption);
        paymentSelect.selectByVisibleText(payment);
    }

    public void clickSubmitButton()
    {
        submitButton.click();
    }
}

