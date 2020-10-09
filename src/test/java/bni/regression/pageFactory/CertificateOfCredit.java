package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




public class CertificateOfCredit {

    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement submitButton;

    @FindBy(css="#btnTransferMembershipFromCoC")
    WebElement transferMembershipFromCoC;

    @FindBy(css="#btnTransferMonthsFromCoC")
    WebElement transferMonthsFromCoC;

    @FindBy(css="#btnTransferMonthsFromCoCToPendingMember")
    WebElement transferMonthsFromCoCToPendingMember;

    @FindBy(css="#btnEnterNewMemberFromCoC")
    WebElement enterNewMemberFromCoC;

    @FindBy(css="#certificateReference")
    WebElement certificateReferenceTextBox;

    @FindBy(css="#searchCertificate")
    WebElement getButton;

    public CertificateOfCredit(WebDriver driver) {
        CertificateOfCredit.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickTransferMembership()
    {
        transferMembershipFromCoC.click();
    }

    public void clickTransferMonthsFromCoC()
    {
        transferMonthsFromCoC.click();
    }

    public void clickTransferMonthsFromCoCToPendingMember()
    {
        transferMonthsFromCoCToPendingMember.click();
    }

    public void clickRedeemCreditForSamePerson()
    {
        enterNewMemberFromCoC.click();
    }
    public void clickSubmitButton(){
        submitButton.click();
    }

    public void enterCOCReferenceNumber(String cocNumber)
    {
        certificateReferenceTextBox.sendKeys(cocNumber);
    }

public void clickGetButton()
{
    getButton.click();
}
}
