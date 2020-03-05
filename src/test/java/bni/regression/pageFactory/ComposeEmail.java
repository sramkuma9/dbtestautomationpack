package bni.regression.pageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ComposeEmail {
    public static WebDriver driver;
    public WebDriverWait wait;

   @FindBy(css = "#messageSubject")
    WebElement subjectTextBox;

   @FindBy (css=".richeditor")
   WebElement bodyTextBox;

    @FindBy(css="fieldmailto")
    WebElement recipientsTextBox;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement sendButton;

    public ComposeEmail(WebDriver driver) {
        ComposeEmail.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterSubject(String subject) throws InterruptedException {
        subjectTextBox.click();
        TimeUnit.SECONDS.sleep(2);
        String js = "arguments[0].setAttribute('value','" + subject + "')";
        ((JavascriptExecutor) driver).executeScript(js, subjectTextBox);
    }

    public void clickSendButton(){
        sendButton.click();
    }
    public void enterBody()
    {
        bodyTextBox.sendKeys("Body");
    }

    public void setRecipientsList(String emailIds)
    {
        recipientsTextBox.clear();
        recipientsTextBox.sendKeys(emailIds);
    }

}
