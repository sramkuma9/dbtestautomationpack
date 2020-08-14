package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubmitTYFCBSlips {

    public static WebDriver driver;
    public WebDriverWait wait;

 @FindBy(css="#thankYouTo")
    WebElement thankYouToListBox;

    @FindBy(css = "body > div.wrapper > div.content-wrapper > section.content > div:nth-child(2) > div.box-body > form > div.box-footer > div:nth-child(2) > button")
    WebElement saveButton;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
    WebElement submitButton;

    @FindBy(css="#forReferralAmountInDollar")
    WebElement referralAmountTextBox;

@FindBy(css="#modalFormDiv > div:nth-child(7) > div.enterTYFCBRadioOptions > label:nth-child(3)")
WebElement referralTypeOptionBox;

@FindBy(css="#businessType1_0")
WebElement businessTypeOptionBox;

    public SubmitTYFCBSlips(WebDriver driver) {
        SubmitTYFCBSlips.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectThankYouTo(String thankYouTo) {
        Select thankyouToSelect = new Select(thankYouToListBox);
        thankyouToSelect.selectByVisibleText(thankYouTo);
    }

    public Integer enterReferralAmount(String referralAmount)
    {
        referralAmountTextBox.sendKeys(referralAmount);
        return Integer.valueOf(referralAmount);
    }

    public void selectBusinessType(String businessType)
    {
     businessTypeOptionBox.click();
    }
 public void selectReferralType(String referralType)
 {
    referralTypeOptionBox.click();
 }

 public void clickSubmitButton()
 {
     submitButton.click();
 }


    }
