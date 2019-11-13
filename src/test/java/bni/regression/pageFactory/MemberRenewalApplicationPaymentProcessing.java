package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;

public class MemberRenewalApplicationPaymentProcessing {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#payerName")
    WebElement payerNameTextBox;

    @FindBy(css = "#payMethod")
    WebElement payMethodListBox;

    @FindBy(css = "#paymentSubmit")
    WebElement submitButton;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.bniStyle.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button")
    WebElement okButton;

    @FindBy(css = "#\\31 573641006085")
    WebElement confirmationMessage;


    public MemberRenewalApplicationPaymentProcessing(WebDriver driver) {
        MemberRenewalApplicationPaymentProcessing.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterPayerName(String payerName) {
        payerNameTextBox.sendKeys(payerName);
    }

    public void selectPayMethod() {
        Select payMethodSelect = new Select(payMethodListBox);
        payMethodSelect.selectByValue("1");
    }

    public void clickOkButton() {
        okButton.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void checkConfirmationMessage() {
        String actualmessage = confirmationMessage.getText();
        String expMessage = "Thank you for applying to renew your BNI membership. Your Membership Committee have been notified and will contact you when a decision has been made.";
        assertEquals("Confirmation message is not correct", expMessage, actualmessage);
    }

}
