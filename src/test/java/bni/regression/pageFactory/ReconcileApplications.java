package bni.regression.pageFactory;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;

public class ReconcileApplications {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#datalist_filter > input[type=text]")
    WebElement searchTextBox;

    @FindBy(xpath = "/html/body/div[2]/div/div[1]/div[4]/div/form/div/div/table/tbody/tr/td[8]/input")
    WebElement paymentReceivedCheckBox;

    @FindBy(css="tr> td:nth-child(8) > input")
    WebElement checkBoxPayment;

    @FindBy(css = "#datalist > tbody > tr > td:nth-child(9) > a")
    WebElement reconcileButton;

    @FindBy(css = "#datalist > tbody > tr > td:nth-child(5) > a")
    WebElement appStatusLink;


    @FindBy(css=".approveonlinenewapplication")
    WebElement vpApprovalLink;

    @FindBy(css=".fa-plus")
    WebElement paymentTypeLink;

    @FindBy(css="#sendPaymentLink")
    WebElement sendPaymentLink;

    @FindBy(css="#tr> td:nth-child(3) > a")
    WebElement appType;

    @FindBy(xpath="//*[@id='show_core_groups']")
    WebElement showCGCheckBox;

    @FindBy(xpath="//*[@id='show_suspended_chapters']")
    WebElement showSuspendedChaptersCheckBox;

    //@FindBy(xpath="/html/body/div[2]/div/div[1]/div[4]/div/form/div/div/table/tbody/tr/td[7]/a")
    @FindBy(xpath="//*[@id='datalist']/tbody/tr/td[7]/a")
    WebElement paymentType;

    @FindBy(css=".odd > td:nth-child(7) > a")
    WebElement getPaymentType;

    @FindBy(css="#initPaymentLink")
    WebElement payNowButton;

    @FindBy(css="#changepaymenttype")
    WebElement changepaymenttype;

    @FindBy(css="#show_unsubmitted")
    WebElement unSubmittedApplicationCheckbox;

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

    public void enterSearchText(String firstName, String lastName)
    {
        searchTextBox.sendKeys(firstName + " " + lastName);
    }

    public void clickPaymentReceivedCheckBox()
    {

        paymentReceivedCheckBox.isSelected();
       paymentReceivedCheckBox.click();
    }

    public void clickPaymentCheckBox()
    {
        checkBoxPayment.click();
    }

    public void clickRecncileButton()
    {


       boolean clickFlag = isClickable(reconcileButton);
        System.out.println("click statement" +clickFlag);
        reconcileButton.click();
    }

    public static boolean isClickable(WebElement webe) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 25);
            wait.until(ExpectedConditions.elementToBeClickable(webe));
            return true;
        } catch (Exception e) {
            return false;
        }

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

    public void clickUnsubmittedApplicationCheckbox()
    {
        unSubmittedApplicationCheckbox.click();
    }

    public void clickCGCheckBox()
    {
        showCGCheckBox.click();
    }

    public void clickSuspendedChaptersCheckBox()
    {
        showSuspendedChaptersCheckBox.click();
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
