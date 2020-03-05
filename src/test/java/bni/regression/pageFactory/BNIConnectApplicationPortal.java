package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BNIConnectApplicationPortal {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = ".sc-hqyNC > div:nth-child(1) > button:nth-child(1) > span")

    WebElement nextButton;

    @FindBy(css = "div.sc-qapaw:nth-child(2) > input")
    WebElement companyPaidMembership;

   // @FindBy(css = "div.item:nth-child(2)")
        //    @FindBy(css="input.search")
    @FindBy (css=".sc-fzpdbB")
    WebElement membershipTermListBox;

    @FindBy(css="i.dropdown")
    WebElement dropdown;
    @FindBy(css=".input > input:nth-child(1)")
    WebElement payerNameTextBox;

    @FindBy(css=".sc-jbKcbu > > div:nth-child(1) > button")
    WebElement reviewButton;

//@FindBy(css="div.active:nth-child(2)")
@FindBy (css=".selected")
List <WebElement> membershipSelect;

    @FindBy(css=".sc-hmzhuo > button")
    WebElement submitApplicationButton;
@FindBy(css="input.search")
WebElement paymentOption;

@FindBy(css=".sc-hmzhuo > button")
WebElement submitButton;

@FindBy(css="div.sc-ptRml:nth-child(2) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
WebElement bniMember;

@FindBy (css="div.sc-ptRml:nth-child(2) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
WebElement radioButton;

//@FindBy(css="button.ui:nth-child(3)")
  @FindBy(css="button.ui:nth-child(3) > span")
WebElement downloadApplicationButton;

    public BNIConnectApplicationPortal(WebDriver driver) {
        BNIConnectApplicationPortal.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickNextButton() {
        nextButton.click();    }
public void clickDropdown()
{
    dropdown.click();
}
    public void selectMembershipTerm(String membershipTerm) throws InterruptedException {
        int counter = 0;
        Actions action = new Actions(driver);
        membershipTermListBox.sendKeys("");
        TimeUnit.SECONDS.sleep(2);
        membershipTermListBox.click();
        TimeUnit.SECONDS.sleep(2);
   //    Select membershipSelect = new Select (membershipTermListBox);
//        membershipSelect.selectByVisibleText(membershipTerm);
//        System.out.println("List" + membershipTerm);

         for (WebElement divElement : membershipSelect) {
             List<WebElement> a_collection = divElement.findElements(By.tagName("div"));
             String membershipName = a_collection.get(0).getText();
             System.out.println("MEmbershipname" +membershipName);
             if (membershipTerm.equals(membershipName)) {
                 action.moveToElement(a_collection.get(0));
                 TimeUnit.SECONDS.sleep(18);
                 membershipTermListBox.sendKeys(membershipTerm);
                 new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.item:nth-child(2)"))).click();
                 TimeUnit.SECONDS.sleep(8);
                 a_collection.get(0).click();
                 counter++;
                 break;
             }
             if (counter == 1) {
                 break;
             }

         }
    }
public void clickCompanyPaidMembership()
{
    companyPaidMembership.click();
}

public void enterPayerName(String payerName)
{
    payerNameTextBox.sendKeys(payerName);

}
public void clickReviewButton()
{
    reviewButton.click();
}

public void clickSubmitApplicationbutton()
{
    submitApplicationButton.click();
}
public void selectPaymentMethod(String methodOfPayment)
{
 Select  paymentMethodSelect = new Select (paymentOption);
 paymentMethodSelect.selectByVisibleText(methodOfPayment);
}
public void clickSubmitButton()
{
    submitButton.click();
}
public void clickDownloadApplicationButton()
{
    downloadApplicationButton.click();
}
public void enterBniMember(String member)
{
    bniMember.sendKeys(member);
}
public void clickRadioButton()
{
    radioButton.click();
}
}

