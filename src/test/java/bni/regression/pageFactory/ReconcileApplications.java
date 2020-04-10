package bni.regression.pageFactory;

import org.junit.Assert;
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

    @FindBy(css="tr> td:nth-child(8) > input")
    WebElement checkBoxPayment;

    @FindBy(css = "#datalist > tbody > tr:nth-child(1) > td:nth-child(9) > a")
    WebElement reconcileButton;

    @FindBy(css = "#datalist > tbody > tr:nth-child(1) > td:nth-child(5) > a")
    WebElement appStatusLink;


    @FindBy(css=".approveonlinenewapplication")
    WebElement vpApprovalLink;

    @FindBy(css=".fa-plus")
    WebElement paymentTypeLink;

    @FindBy(css="#sendPaymentLink")
    WebElement sendPaymentLink;

    @FindBy(css="#tr> td:nth-child(3) > a")
    WebElement appType;

    @FindBy(css=".fa-plus")
    WebElement paymentType;

    @FindBy(css=".odd > td:nth-child(7) > a")
    WebElement getPaymentType;

    @FindBy(css="#initPaymentLink")
    WebElement payNowButton;

    @FindBy(css="#changepaymenttype")
    WebElement changepaymenttype;

    public ReconcileApplications(WebDriver driver) {
        ReconcileApplications.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickPaymentType()
    {
        paymentTypeLink.click();
    }
    public void enterSearchCriteria(String firstName, String lastName)
    {
        searchTextBox.sendKeys(firstName + " " + lastName);
    }

    public void enterSearchText( String lastName)
    {
        searchTextBox.sendKeys(lastName);
    }

    public void clickPaymentReceivedCheckBox()
    {
        paymentReceivedCheckBox.click();
    }

    public void clickPaymentCheckBox()
    {
        checkBoxPayment.click();
    }

    public void clickRecncileButton()
    {
        reconcileButton.click();
    }

    public void clickSendPaymentLink()
    {
        sendPaymentLink.click();
    }
    public void clickAppStatusLink()
    {
        appStatusLink.click();
    }

    public void clickPaymentype()
    {
        getPaymentType.click();
    }
    public void clickVPApprovalLink()
    {
        vpApprovalLink.click();
    }

    public void checkAppType(String expAppType){
        String actualAppType = appType.getText();
        Assert.assertEquals("App Type is not correct", expAppType, actualAppType);
    }

    public void clickPaymentTypeSymbol()
    {
        paymentType.click();

    }
    public void clickPayNow()
    {
        payNowButton.click();
    }
public void clickChangePaymentType()
{
    changepaymenttype.click();
}
}
