package bni.regression.pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RenewalApplication {


    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[2]/div/table/tr/td[1]/input")
    WebElement chapterSelection;

    @FindBy(css = ".sc-jbKcbu > div:nth-child(1) > button")
    WebElement nextButton;

    // @FindBy(css="#usage-navigation-next > div > button")
    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/button/span")
    WebElement applicationFormButton;

    @FindBy(xpath = "//*[@id='member-header']/div/span")
    WebElement textToGet;

 //@FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/div/button/span")

    //@FindBy(xpath="//*[@id='usage-navigation-next']/div/div[1]/button")
   // @FindBy(css="#usage-navigation-next > div > div > button > span")
            //*[@id="usage-navigation-next"]/div/div[1]/button/span
//@FindBy(xpath="//*[@id='usage-navigation-next']/div/div[1]/button/i")
  @FindBy(css="#usage-navigation-next > div > div.sc-kvZOFW.jTJgNu > button > span")


    WebElement appFormNextButton;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/div[1]/button")
    WebElement nextButtonSLPersoInfo;

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]")
    WebElement membershipFeeApplicationWidget;

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[3]")
    WebElement taxForMemFeeApplicationWidget;



    @FindBy(css="#header-menu > div:nth-child(1) > button:nth-child(1) > span")
    WebElement copyFormLink;



    //@FindBy(css="#application-language-content > div > table > tbody > tr")
    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div[3]/div/table/tbody/tr/td[1]/input")
    WebElement languageRadioButton;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[2]/div/div/div/div[2]/div/div[1]")
    WebElement companyPaidMembership;


    @FindBy(css="#application-language-content.sc-hMFtBS.eOacBy > div > table > tbody > tr.sc-Rmtcm:nth-child(3)  >td > input")
    WebElement applicationLanguage;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div/div[1]")
    WebElement membershipTermTextBox;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div/div[1]/div[2]/div")
    List<WebElement> membershipTermListBox;

    @FindBy(css=".sc-cMljjf > div:nth-child(1) >div")
    WebElement dropdown;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[3]/div/div/div/div[2]/div/div/input")
    WebElement payerNameTextBox;

    @FindBy(css=".sc-jbKcbu > > div:nth-child(1) > button")
    WebElement reviewButton;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/div/div/button/span")
    WebElement reviewApplicationButton;




    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/button/span")
    WebElement submitApplicationButton;
    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/input")
    WebElement paymentOption;

    @FindBy(css="i.dropdown")
    WebElement dropdownIcon2;

    //@FindBy(css="#member-payment-dropdown > div")
    @FindBy(css="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/div[2]/div/span")
    List<WebElement> paymentMethod2;

    @FindBy(css=".sc-hmzhuo > button")
    WebElement submitButton;

    @FindBy(css=".arrow")
    WebElement submitGBButton;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/button/span")
    WebElement submitButtonPaymentOption;

    @FindBy(css=".sc-jbKcbu > div:nth-child(1) > button")
    WebElement membershipTermNextButton;

    @FindBy(css="div.sc-ptRml:nth-child(2) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
    WebElement bniMember;

    @FindBy (css="div.sc-ptRml:nth-child(2) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    WebElement radioButton;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[4]/div/div/div/div[2]/div/div[2]/input")
    WebElement cofcRadioButton;

//@FindBy(css="#form-builder-tab-content > div.sc-ptRml.fDULPZ.tab-viewport-container > div > main > div > div > div > div > form > div > div > div:nth-child(1) > div > div > div > div.sc-fznZeY.gJlwEu.element-container > div > div.sc-pQEbo.dUrmQP.datepicker-wrapper > div > div > i")
//WebElement experienceDate;

    @FindBy(css=".field > div:nth-child(1) > input")
    WebElement experienceDate;


    //@FindBy(css="#form-builder-tab-content > div.sc-ptRml.fDULPZ.tab-viewport-container > div > main > div > div > div > div > form > div > div > div:nth-child(1) > div > div > div > div.sc-fznZeY.gJlwEu.element-container > div > div.sc-pQEbo.dUrmQP.datepicker-wrapper > div > div > input[type=text]")
    @FindBy(css=".center > tbody:nth-child(2) > tr > td")
    WebElement dateTextBox;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[1]/div/div[1]/div/div/div/section/div/button")
    WebElement editGBReviewPage;
    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement experienceMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement experienceYear;

    //@FindBy(css="button.ui:nth-child(3)")
    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/button/span")
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

    @FindBy(className = "dropdown icon")
    WebElement personalTitleTextBox;

    @FindBy(css="div.active:nth-child(5)")
    WebElement getPersonalTitleList;

    @FindBy(css=".firstname > div> div >  div.ui.input > input")
    WebElement personalFirstName;

    @FindBy(css=".lastname > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
    WebElement personalLastName;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[8]/div/div/div/div[2]/div/div[1]/input")
    WebElement languageTextBox;

    //  @FindBy(css="div.sc-fznKkj.fQkkzS.display.-simple-element-container.languages> div > div > div > div.visible.menu.transition")
    @FindBy(css="div > div > div > div > div.visible.menu.transition")

    WebElement scrollListBox;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div")
    List <WebElement> personalLanguageList;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[10]/div/div/div/div[2]/div/div/input")
    WebElement personalPhoneNumber;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[12]/div/div/div/div[2]/div/div/input")
    WebElement personalAddressLine1;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[15]/div/div/div/div[2]/div/div/input")
    WebElement personalCountryTextBox;

    @FindBy(className = "search")
    WebElement searchTextBox;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div")

    List<WebElement> personalCountryListBox;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[18]/div/div/div/div[2]/div/div/div[1]/input")
    WebElement personalIndustryTextBox;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div/div[1]/div[2]/div")
    List<WebElement> personalIndustryListBox;

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]")
    WebElement feeFetchedForMembershipTerm;

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[2]")
    WebElement registrationFee;

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[3]")
    WebElement taxFetchedForMembershipTerm;

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[3]")
    WebElement taxFetchedForRegistrationFee;



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

    @FindBy(css="/html/body/div[1]/div/header/div[3]/button/span")
    WebElement signOutButton;

    @FindBy(css=".sc-frDJqD > button")
    WebElement renewalApplicationSubmitButton;

    @FindBy(css=".fluid")
    WebElement countryDropdown;

    @FindBy(css="input.search")
    WebElement countrySelect;

    @FindBy(css=".input > input")
    WebElement emailTextBox;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[3]/div[2]/div/input")
    WebElement chapterTextBox;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[4]/div[2]/div/input")
    WebElement cityTextBox;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[5]/div[2]/div/div[1]")
    WebElement meetingDayComboBox;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[5]/div[2]/div/div[2]/div")
    List<WebElement> selectMeetingDay;


    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/button/span")
    WebElement searchButton;

    //@FindBy(css="tr.sc-Rmtcm > td > input ")
    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div[2]/table/tr/td[1]/input")
    WebElement chapterRadioButton;

    //test 2   @FindBy(css=".sc-frDJqD")
    // track
    @FindBy(css="tr.sc-Rmtcm:nth-child(2) > td:nth-child(1) > input")
    WebElement applicationButton;

    @FindBy (css="#chapter-search-link-button")
    WebElement differentChapter;

    @FindBy(css="div.sc-cpmLhU:nth-child(3) > div:nth-child(2) > div:nth-child(1) > input")
    WebElement differentChapterName;

    @FindBy(css="div.sc-cpmLhU:nth-child(4) > div:nth-child(2) > div:nth-child(1) > input")
    WebElement differentCity;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div/input")
    WebElement payNowMemTerm;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/div")
    List<WebElement> payNowMemTermList;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/form/div[2]/div/input")
    WebElement compMemberhsip;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/button/span")
    WebElement payNow2;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/input")
    WebElement paymentOptionTextBox;

    @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/div[2]/div")
    List<WebElement> paymentOptionList;

    @FindBy(xpath="//*[@id='member-header']/div/span")
    WebElement headerText;

    public RenewalApplication(WebDriver driver) {
        RenewalApplication.driver = driver;
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

    public void selectMembTerm(String membershipTerm)throws Exception
    {
        int counter =0;
        Actions action = new Actions(driver);
        payNowMemTerm.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : payNowMemTermList) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));

            String memTerm = a_collection.get(0).getText();
            // searchRegionTextbox.sendKeys(region);
            System.out.println("MemTerm is "+memTerm);
            if (membershipTerm.equals(memTerm)){
                action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/div"))));
                action.build().perform();
                a_collection.get(0).click();
                TimeUnit.SECONDS.sleep(8);
                counter++;
                break;
            }if (counter == 1) {
                break;
            }
        }

    }

    public void clickCompPaidMembership()
    {
        compMemberhsip.click();
    }

    public String getMembershipFeeFromApplication()
    {
       String memFee = membershipFeeApplicationWidget.getText();
        System.out.println("Membership Fee from Application widget for renewal is" +memFee);
        return memFee;
    }

    public String getMemFeeTaxFromApplication()
    {
        String memFeeTax = taxForMemFeeApplicationWidget.getText();
        System.out.println("Membership Fee from Application widget for renewal is" +memFeeTax);
        return memFeeTax;
    }


    public void selectPaymentOption2(String paymentMethod) throws Exception
    {
        int counter = 0;
        Actions action = new Actions(driver);
        paymentOptionTextBox.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : paymentOptionList) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String pay = a_collection.get(0).getText();
            System.out.println("Pay is "+pay);
            if (paymentMethod.equals(pay)){
                action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/div[2]/div"))));
                action.build().perform();
                a_collection.get(0).click();
                TimeUnit.SECONDS.sleep(8);
                counter++;
                break;
            }if (counter == 1) {
                break;
            }
        }

    }


    public String getFeeForMembershipTerm()
    {
        String feeFetched = feeFetchedForMembershipTerm.getText();
        System.out.println("Fee for the given membership term from UI is" + feeFetched);
        return  feeFetched;

    }

    public String registrationFeeFetched()
    {
        String registrationFeeFetched = registrationFee.getText();
        System.out.println("Registration Fee for membership from UI is" + registrationFeeFetched);
        return registrationFeeFetched;

    }


    public String getTaxForMembershipTerm()
    {
        String taxFetchForMem = taxFetchedForMembershipTerm.getText();
        System.out.println("Tax for the  membership term  from UI is " + taxFetchForMem);
        return  taxFetchForMem;

    }
    public String getTaxForRegistrationFee()
    {
        String taxFetchForRegistFee = taxFetchedForRegistrationFee.getText();
        System.out.println(" Tax for Registration Fee from UI is " + taxFetchForRegistFee);
        return taxFetchForRegistFee;

    }



    public void clickSearchButton()
    {
        searchButton.click();
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

    public void selectMembershipTerm(String membershipTerm) throws InterruptedException {
        int counter = 0;
        membershipTermTextBox.click();
        Actions action = new Actions(driver);
        for (WebElement divElement : membershipTermListBox) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String membershipName = a_collection.get(0).getText();
            if (membershipTerm.equals(membershipName)) {
                System.out.println("term selected is" +membershipName);
                action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div/div[1]/div[2]/div"))).click();
                TimeUnit.SECONDS.sleep(8);
                membershipTermTextBox.click();
                action.build().perform();
                a_collection.get(0).click();
                TimeUnit.SECONDS.sleep(8);
                counter++;
                break;
            }
            if (counter == 1) {
                break;
            }

        }
    }


public void getText()
{
    String TMT=textToGet.getText();
    System.out.println("text" +TMT);
}


    public void clickApplicationFormNextButton()
    {

      String hText = driver.getTitle();
       System.out.println("header text is" +hText);
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



    public void enterLengthProfessionalClassification(String lengthProfessionalExp)
    {
        lengthProfessionalClassification.sendKeys(lengthProfessionalExp);
    }



    public void clickReviewApplicationButton()
    {
        reviewApplicationButton.click();
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

    public void enterFirstName(String firstName)
    {
        personalFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName)
    {
        personalLastName.sendKeys(lastName);
    }


}


