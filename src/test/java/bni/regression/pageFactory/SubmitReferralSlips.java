package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubmitReferralSlips {
    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
    WebElement submitButton;

    @FindBy(css="#showHideSearch")
    WebElement searchCrossChapterButton;


    @FindBy(css="#referral")
    WebElement referralTextBox;

    @FindBy(css="#referralType1_0")
    WebElement referralType;

    @FindBy(css="#referralStatus1_0")
    WebElement referralStatus;

    @FindBy (css="#address")
    WebElement addressBox;

    @FindBy(css="#telephone")
    WebElement phoneNumber;

    @FindBy(css="#email")
    WebElement emailTextBox;

    @FindBy(css="#to")
    WebElement toMemberName;



    public SubmitReferralSlips(WebDriver driver) {
        bni.regression.pageFactory.SubmitReferralSlips.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickCrossChapterButton(){
        searchCrossChapterButton.click();
    }
    public void enterReferral(String referral)
    {
        referralTextBox.sendKeys(referral);
    }
    public void enterReferralType(String rType)
    {
        referralType.sendKeys(rType);
    }

    public void selectReferralStatus(String rStatus)
    {
       referralStatus.click();
    }

    public String selectToMember(String memberName)
    {
        Select memberNameSelect = new Select(toMemberName);
        memberNameSelect.selectByVisibleText(memberName);
        return memberName;
    }
    public void enterAddress(String address)
    {
        addressBox.sendKeys(address);
    }
    public void enterTelephoneNumber(String number)
    {
        phoneNumber.sendKeys(number);
    }
    public void enterEmail(String email)
    {
        emailTextBox.sendKeys(email);
    }
    public void clickSubmitButton()
    {
        submitButton.click();
    }
}
