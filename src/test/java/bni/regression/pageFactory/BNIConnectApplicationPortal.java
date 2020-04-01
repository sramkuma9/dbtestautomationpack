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

    @FindBy(css="#chapter-selection-application > div > div > div.sc-hzDkRC.jXIDFV > div.sc-jhAzac.gICtMW > table > tr > td.one.wide.checkbox-cell > input[type=RADIO]")
    WebElement chapterSelection;

    @FindBy(css = ".sc-jbKcbu > div:nth-child(1) > button")
    WebElement nextButton;

    @FindBy(css="#usage-navigation-next > div > button")
    WebElement applicationFormButton;

    //@FindBy(css="#usage-navigation-next > div > div.sc-frDJqD.LsNMh > button")
            @FindBy(css=".sc-jbKcbu > div:nth-child(1) > button:nth-child(1) > span")
    WebElement appFormNextButton;



   @FindBy(css="#application-language-content > div > table > tbody > tr")
    WebElement languageRadioButton;

    @FindBy(css = "div.sc-qapaw:nth-child(2) > input")
    WebElement companyPaidMembership;


   // @FindBy (css="div.active:nth-child(1) > input")
   @FindBy(css="div.visible:nth-child(4)")
 //  @FindBy(css="div.visible:nth-child(4) > div")
    WebElement membershipTermListBox;

    @FindBy(css=".sc-cMljjf > div:nth-child(1) >div")
    WebElement dropdown;

    @FindBy(css=".payername > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
    WebElement payerNameTextBox;

    @FindBy(css=".sc-jbKcbu > > div:nth-child(1) > button")
    WebElement reviewButton;

    @FindBy(css=".sc-dNLxif > div")
    WebElement reviewApplicationButton;

  @FindBy(css="#div.selected:nth-child(2)")
WebElement membershipSelect;

    @FindBy(css=".sc-frDJqD > button:nth-child(1) > span")
    WebElement submitApplicationButton;
@FindBy(css="input.search")
WebElement paymentOption;

@FindBy(css="i.dropdown")
WebElement dropdownIcon2;

//@FindBy(css="#member-payment-dropdown > div")
@FindBy(css=".selected")
WebElement paymentMethod2;

@FindBy(css=".sc-hmzhuo > button")
WebElement submitButton;

@FindBy(css=".arrow")
WebElement submitGBButton;

@FindBy(css=".sc-jbKcbu > div:nth-child(1) > button")
WebElement membershipTermNextButton;

@FindBy(css="div.sc-ptRml:nth-child(2) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
WebElement bniMember;

@FindBy (css="div.sc-ptRml:nth-child(2) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
WebElement radioButton;

@FindBy(css=".cofc > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input")
WebElement cofcRadioButton;

//@FindBy(css="#form-builder-tab-content > div.sc-ptRml.fDULPZ.tab-viewport-container > div > main > div > div > div > div > form > div > div > div:nth-child(1) > div > div > div > div.sc-fznZeY.gJlwEu.element-container > div > div.sc-pQEbo.dUrmQP.datepicker-wrapper > div > div > i")
//WebElement experienceDate;

@FindBy(css=".field > div:nth-child(1) > input")
WebElement experienceDate;


//@FindBy(css="#form-builder-tab-content > div.sc-ptRml.fDULPZ.tab-viewport-container > div > main > div > div > div > div > form > div > div > div:nth-child(1) > div > div > div > div.sc-fznZeY.gJlwEu.element-container > div > div.sc-pQEbo.dUrmQP.datepicker-wrapper > div > div > input[type=text]")
@FindBy(css=".center > tbody:nth-child(2) > tr > td")
WebElement dateTextBox;

@FindBy(css="div.sc-fznyAO:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > section:nth-child(1) > div:nth-child(1) > button")
WebElement editGBReviewPage;
    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement experienceMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement experienceYear;

//@FindBy(css="button.ui:nth-child(3)")
  @FindBy(css=".download")
WebElement downloadApplicationButton;

  @FindBy(css="div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
WebElement expProfessionalClassification;

  @FindBy(css="div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
  WebElement lengthProfessionalClassification;

  @FindBy(css="div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
   WebElement licence;

  @FindBy(css="div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input")


  WebElement professionalClassification;

  @FindBy(css="div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
WebElement background;

  @FindBy(css="div.sc-ptRml:nth-child(4) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
   WebElement commitment;

  @FindBy(css="div.sc-ptRml:nth-child(4) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
  WebElement substitute;

  @FindBy(css="div.sc-ptRml:nth-child(4) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) ")
  WebElement alreadyMember;

//  @FindBy(css=".invalid > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
//  WebElement acceptCheckBox;

   @FindBy(css="div.sc-ptRml:nth-child(5) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    WebElement acceptCheckBox;

   @FindBy(css="div.sc-ptRml:nth-child(6) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
   WebElement acceptcheck1;

    @FindBy(css="section.sc-pjstK:nth-child(1)")
    WebElement check2;
    @FindBy(css="section.sc-pjstK:nth-child(1) > div:nth-child(1) > input")
    WebElement check3;

    @FindBy(css=".companyname > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
    WebElement companyName;

    @FindBy(css="input.search")
    WebElement dropdownIcon;

    @FindBy(css="#usage-navigation-next > div > button")
    WebElement paynowButton;

@FindBy(css="#header > div.sc-VigVT.sign-out-container.fLFfNN > button")
WebElement signOutButton;

@FindBy(css=".sc-frDJqD > button")
WebElement renewalApplicationSubmitButton;

    public BNIConnectApplicationPortal(WebDriver driver) {
        BNIConnectApplicationPortal.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickNextButton() {
        nextButton.click();    }

        public void clickCOCButton(){
        cofcRadioButton.click();
        }

    public void selectMonth(String month) {
        Select monthSelect = new Select(experienceMonth);
        monthSelect.selectByVisibleText(month);
    }

    public void selectYear(String year) {
        Select yearSelect = new Select(experienceYear);
        yearSelect.selectByVisibleText(year);
    }

    public void selectDateFromDatePicker(String day) throws Exception{
        Integer breaker = 2;
        for(WebElement trElement : datePicker)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            for (int row = 0; row < 7; row++) {
                String dayItem = td_collection.get(row).getText();
                if (day.equals(dayItem)) {
                    td_collection.get(row).findElement(By.tagName("a")).click();
                    TimeUnit.SECONDS.sleep(3);
                    breaker++;
                    break;
                }
            }
            if (breaker==3) {
                break;
            }
        }
    }

    public void enterDate(String date1) throws InterruptedException {
        dateTextBox.sendKeys(date1);
        TimeUnit.SECONDS.sleep(3);
    }

public void clickDropdown()
{
    dropdown.click();
}

//    public void selectMembershipTerm(String membershipTerm) throws InterruptedException {
//        int counter = 0;
//        Actions action = new Actions(driver);
//          for (WebElement divElement : membershipTermListBox) {
//             List<WebElement> a_collection = divElement.findElements(By.tagName("div"));
//            String membershipName = a_collection.get(0).getText();
//             if (membershipTerm.equals(membershipName)) {
//                 action.moveToElement(a_collection.get(0));
//                 TimeUnit.SECONDS.sleep(18);
//                  new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.selected:nth-child(2) > span"))).click();
//                 TimeUnit.SECONDS.sleep(8);
//                 a_collection.get(0).click();
//                 counter++;
//                 break;
//             }
//             if (counter == 1) {
//                 break;
//             }
//
//         }
//    }
//
    public void selectMembershipTerm(String membershipTerm)
    {
        membershipTermListBox.click();
      //  membershipTermListBox.sendKeys(membershipTerm);
    }


    public void clickChapterSelection()
    {
    chapterSelection.click();
    }

    public void clickApplicationFormButton()
    {
        applicationFormButton.click();
    }

    public void clickApplicationFormNextButton()
    {
        appFormNextButton.click();
    }

    public void selectLanguage(String language)
    {
        languageRadioButton.sendKeys(language);
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

    public void clickMembershipTermNextButton()
    {
        membershipTermNextButton.click();
    }
    public void clickCalendarIcon()
    {
        experienceDate.click();
    }

    public void enterExperienceProfessionalClassification(String expProfessionalExp)
    {
        expProfessionalClassification.sendKeys(expProfessionalExp);
    }

    public void enterLengthProfessionalClassification(String lengthProfessionalExp)
    {
        lengthProfessionalClassification.sendKeys(lengthProfessionalExp);
    }

    public void selectLicenceStatus()
    {
        licence.click();
    }

    public void selectProfessionalClassification()
    {
        professionalClassification.click();
    }

    public void clickRenewalApplicationSubmitButton()
    {
        renewalApplicationSubmitButton.click();
    }
    public void enterBackground(String backgroundClassification)
    {
        background.sendKeys(backgroundClassification);
    }

    public void enterCommitment()
    {
        commitment.click();
    }

   public void enterSubstitute()
   {
       substitute.click();
   }

   public void enterAlreadyMember()
   {
       alreadyMember.click();
   }

   public void clickAcceptCheckBox()
   {
       acceptCheckBox.click();
   }

   public void clickReviewApplicationButton()
   {
       reviewApplicationButton.click();
   }

   public void clickSignOutButton()
   {
       signOutButton.click();
   }

   public void clickCheck1()
       {
       acceptcheck1.click();
    }
    public void clickCheck2()
    {
        check2.click();
    }
    public void clickCheck3()
    {
        check3.click();
    }
    public void clickEditGB()
    {
        editGBReviewPage.click();
    }
    public void editPersonalInfo(String test)
    {
        //companyName.clear();
        companyName.sendKeys(test);
    }

    public void selectPaymentMethod2(String paymentMethod) throws InterruptedException
    {
        paymentOption.click();
        TimeUnit.SECONDS.sleep(3);
        paymentMethod2.click();
        TimeUnit.SECONDS.sleep(3);

//        Select  paymentMethodSelect = new Select (paymentMethod2);
//        paymentMethodSelect.selectByVisibleText(paymentMethod);
        }


    public void clickSubmitGBButton()
    {
        submitGBButton.click();

    }
    public void clickPaynow() throws InterruptedException
    {

        TimeUnit.SECONDS.sleep(2);
        paynowButton.click();
    }


}


