package bni.regression.pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BNIConnectApplicationPortal {
    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[2]/div/table/tr/td[1]/input")
    WebElement chapterSelection;

    @FindBy(css = ".sc-jbKcbu > div:nth-child(1) > button")
    WebElement nextButton;

    // @FindBy(css="#usage-navigation-next > div > button")
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/button/span")
    WebElement applicationFormButton;


    // @FindBy(xpath="/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/div[1]/button/span")
    @FindBy(xpath = "//*[@id='usage-navigation-next']/div/div[1]/button/span")
    WebElement appFormNextButton;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/div[1]/button")
    WebElement nextButtonSLPersoInfo;

    @FindBy(css = "#header-menu > div:nth-child(1) > button:nth-child(1) > span")
    WebElement copyFormLink;

    //@FindBy(css="#application-language-content > div > table > tbody > tr")
   // @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div[3]/div/table/tbody/tr/td[1]/input")
     @FindBy(xpath="//*[@id='application-language-content']/div/table/tbody/tr/td/input")
    WebElement languageRadioButton;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[2]/div/div/div/div[2]/div/div[1]")
    WebElement companyPaidMembership;


    @FindBy(css = "#application-language-content.sc-hMFtBS.eOacBy > div > table > tbody > tr.sc-Rmtcm:nth-child(3)  >td > input")
    WebElement applicationLanguage;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div/div[1]")
    WebElement membershipTermTextBox;


    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div/div[1]/div[2]/div")
    List<WebElement> membershipTermListBox;

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[2]")
    WebElement feeFetchedForMembershipTerm;

    //*[@id="form-builder-tab-content"]/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[2]
    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]")
    WebElement registrationFee;

    //*[@id="form-builder-tab-content"]/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[3]")
    WebElement taxFetchedForMembershipTerm;

    @FindBy(xpath="//*[@id='form-builder-tab-content']/div[2]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[3]")
    WebElement taxFetchedForRegistrationFee;

    @FindBy(css = ".sc-cMljjf > div:nth-child(1) >div")
    WebElement dropdown;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[3]/div/div/div/div[2]/div/div/input")
    WebElement payerNameTextBox;

    @FindBy(css = ".sc-jbKcbu > > div:nth-child(1) > button")
    WebElement reviewButton;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/div/div/button/span")
    WebElement reviewApplicationButton;

    @FindBy(css = "#div.selected:nth-child(2)")
    WebElement membershipSelect;


    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/button/span")
    WebElement submitApplicationButton;
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/input")
    WebElement paymentOption;

    @FindBy(css = "i.dropdown")
    WebElement dropdownIcon2;

    //@FindBy(css="#member-payment-dropdown > div")
    @FindBy(css = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/div[2]/div/span")
    List<WebElement> paymentMethod2;

    @FindBy(css = ".sc-hmzhuo > button")
    WebElement submitButton;

    @FindBy(css = ".arrow")
    WebElement submitGBButton;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/button/span")
    WebElement submitButtonPaymentOption;

    @FindBy(xpath = "//*[@id='usage-navigation-next']/div/div[1]/button/span")
    WebElement membershipTermNextButton;

    @FindBy(css = "div.sc-ptRml:nth-child(2) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
    WebElement bniMember;

    @FindBy(css = "div.sc-ptRml:nth-child(2) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    WebElement radioButton;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/main/div/div/div/div/form/div/div/div[4]/div/div/div/div[2]/div/div[2]/input")
    WebElement cofcRadioButton;

//@FindBy(css="#form-builder-tab-content > div.sc-ptRml.fDULPZ.tab-viewport-container > div > main > div > div > div > div > form > div > div > div:nth-child(1) > div > div > div > div.sc-fznZeY.gJlwEu.element-container > div > div.sc-pQEbo.dUrmQP.datepicker-wrapper > div > div > i")
//WebElement experienceDate;

    @FindBy(css = ".field > div:nth-child(1) > input")
    WebElement experienceDate;

    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[3]/div/main/div/div/div/div/form/div/div/div[2]/div/div/div/div[2]/div/div[1]/div/div/i")
    WebElement calendarIcon;


    //@FindBy(css="#form-builder-tab-content > div.sc-ptRml.fDULPZ.tab-viewport-container > div > main > div > div > div > div > form > div > div > div:nth-child(1) > div > div > div > div.sc-fznZeY.gJlwEu.element-container > div > div.sc-pQEbo.dUrmQP.datepicker-wrapper > div > div > input[type=text]")
    @FindBy(css = ".center > tbody:nth-child(2) > tr > td")
    WebElement dateTextBox;

    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[3]/div/main/div/div/div/div/form/div/div/div[2]/div/div/div/div[2]/div/div[1]/div/div/input")
    WebElement getDateTextBox;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[1]/div/div[1]/div/div/div/section/div/button")
    WebElement editGBReviewPage;
    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement experienceMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement experienceYear;

    //@FindBy(css="button.ui:nth-child(3)")
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/button/span")
    WebElement downloadApplicationButton;

    //@FindBy(css="div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[3]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div/input")
    WebElement expProfessionalClassification;

    // @FindBy(css="div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")
    @FindBy(xpath = "")
    WebElement lengthProfessionalClassification;

    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[3]/div/main/div/div/div/div/form/div/div/div[3]/div/div/div/div[2]/div/div[1]/input")
    WebElement licenceOption1;

    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[3]/div/main/div/div/div/div/form/div/div/div[3]/div/div/div/div[2]/div/div[2]/input")
    WebElement licenceOption2;


    @FindBy(css = "div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input")


    WebElement professionalClassification;

    @FindBy(css = "div.sc-ptRml:nth-child(3) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
    WebElement background;

    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[4]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[1]/label")
    WebElement commitmentYes;

    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[4]/div/main/div/div/div/div/form/div/div/div[1]/div/div/div/div[2]/div/div[2]/input")
    WebElement commitmentNo;

    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[4]/div/main/div/div/div/div/form/div/div/div[2]/div/div/div/div[2]/div/div[1]/input")
    WebElement substituteYes;

    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[4]/div/main/div/div/div/div/form/div/div/div[2]/div/div/div/div[2]/div/div[1]/input")
    WebElement substituteNo;

    @FindBy(css = "div.sc-ptRml:nth-child(4) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) ")
    WebElement alreadyMember;

    @FindBy(className = "dropdown icon")
    WebElement personalTitleTextBox;

    @FindBy(css = "div.active:nth-child(5)")
    WebElement getPersonalTitleList;

    @FindBy(css = ".firstname > div> div >  div.ui.input > input")
    WebElement personalFirstName;

    @FindBy(css = ".lastname > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
    WebElement personalLastName;


    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[8]/div/div/div/div[2]/div/div[1]/input")
    WebElement languageTextBox;

    //  @FindBy(css="div.sc-fznKkj.fQkkzS.display.-simple-element-container.languages> div > div > div > div.visible.menu.transition")
    @FindBy(css = "div > div > div > div > div.visible.menu.transition")

    WebElement scrollListBox;


    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div")
    List<WebElement> personalLanguageList;


    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[10]/div/div/div/div[2]/div/div/input")
    WebElement personalPhoneNumber;


    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[12]/div/div/div/div[2]/div/div/input")
    WebElement personalAddressLine1;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[15]/div/div/div/div[2]/div/div/input")
    WebElement personalCountryTextBox;

    @FindBy(className = "search")
    WebElement searchTextBox;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div")

    List<WebElement> personalCountryListBox;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div[18]/div/div/div/div[2]/div/div/div[1]/input")
    WebElement personalIndustryTextBox;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div/div[1]/div[2]/div")
    List<WebElement> personalIndustryListBox;


    @FindBy(xpath = "//*[@id='form-builder-tab-content']/div[5]/div/main/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div[2]/div/div")
    WebElement acceptCheckBox;

    @FindBy(css = "div.sc-ptRml:nth-child(6) > div:nth-child(2) > main:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    WebElement acceptcheck1;

    @FindBy(css = "section.sc-pjstK:nth-child(1)")
    WebElement check2;
    @FindBy(css = "section.sc-pjstK:nth-child(1) > div:nth-child(1) > input")
    WebElement check3;

    @FindBy(css = ".companyname > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input")
    WebElement companyName;

    @FindBy(css = "input.search")
    WebElement dropdownIcon;

    @FindBy(css = "#usage-navigation-next > div > button")
    WebElement paynowButton;

    @FindBy(css = "/html/body/div[1]/div/header/div[3]/button/span")
    WebElement signOutButton;

    @FindBy(css = ".sc-frDJqD > button")
    WebElement renewalApplicationSubmitButton;

    @FindBy(css = ".fluid")
    WebElement countryDropdown;

    @FindBy(css = "input.search")
    WebElement countrySelect;

    @FindBy(css = ".input > input")
    WebElement emailTextBox;

    //@FindBy(css="#chapter-search-container > div > div > div:nth-child(3) > div.sc-dymIpo.field-element.edQgwG > div > input[type=text]")
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[3]/div[2]/div/input")

    WebElement chapterTextBox;

    //@FindBy(css="div.sc-cpmLhU:nth-child(4) > div:nth-child(2) > div:nth-child(1) > input")
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[4]/div[2]/div/input")
    WebElement cityTextBox;

    //@FindBy(css="div.sc-cpmLhU:nth-child(5) > div:nth-child(2) > div:nth-child(1) > input")
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[5]/div[2]/div/div[1]")
    WebElement meetingDayComboBox;

    //@FindBy(css="div.visible:nth-child(4) > div:nth-child(4)")
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[5]/div[2]/div/div[2]/div")
    List<WebElement> selectMeetingDay;

    //@FindBy(css="#usage-navigation-next > button")
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/button/span")
    WebElement searchButton;

    //@FindBy(css="tr.sc-Rmtcm > td > input ")
    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div[2]/table/tr/td[1]/input")
    WebElement chapterRadioButton;

    //test 2   @FindBy(css=".sc-frDJqD")
    // track
    @FindBy(css = "tr.sc-Rmtcm:nth-child(2) > td:nth-child(1) > input")
    WebElement applicationButton;

    @FindBy(css = "#chapter-search-link-button")
    WebElement differentChapter;

    @FindBy(css = "div.sc-cpmLhU:nth-child(3) > div:nth-child(2) > div:nth-child(1) > input")
    WebElement differentChapterName;

    @FindBy(css = "div.sc-cpmLhU:nth-child(4) > div:nth-child(2) > div:nth-child(1) > input")
    WebElement differentCity;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div/input")
    WebElement payNowMemTerm;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/div")
    List<WebElement> payNowMemTermList;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/form/div[2]/div/input")
    WebElement compMemberhsip;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[3]/div[2]/div/button/span")
    WebElement payNow2;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/input")
    WebElement paymentOptionTextBox;

    @FindBy(xpath = "/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/div[2]/div")
    List<WebElement> paymentOptionList;

    public BNIConnectApplicationPortal(WebDriver driver) {
        BNIConnectApplicationPortal.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void clickCOCButton() {
        cofcRadioButton.click();
    }

    public void selectMonth(String month) {
        Select monthSelect = new Select(experienceMonth);
        monthSelect.selectByVisibleText(month);
    }

    public void selectMembTerm(String membershipTerm) throws Exception {
        int counter = 0;
        Actions action = new Actions(driver);
        payNowMemTerm.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : payNowMemTermList) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));

            String memTerm = a_collection.get(0).getText();
            // searchRegionTextbox.sendKeys(region);
            System.out.println("MemTerm is " + memTerm);
            if (membershipTerm.equals(memTerm)) {
                action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/div"))));
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

    public void clickCompPaidMembership() {
        compMemberhsip.click();
    }

    public void selectPaymentOption2(String paymentMethod) throws Exception {
        int counter = 0;
        Actions action = new Actions(driver);
        paymentOptionTextBox.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : paymentOptionList) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String pay = a_collection.get(0).getText();
            System.out.println("Pay is " + pay);
            if (paymentMethod.equals(pay)) {
                action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/div[2]/div"))));
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

    public String getFeeForMembershipTerm()
    {
        String feeFetched = feeFetchedForMembershipTerm.getText();
        System.out.println("Fee for the given membership term from Application widget is" + feeFetched);
        return  feeFetched;

    }

    public String registrationFeeFetched()
    {
        String registrationFeeFetched = registrationFee.getText();
       System.out.println("Registration Fee for membership from Application widget is" + registrationFeeFetched);
        return registrationFeeFetched;

    }


    public String getTaxForMembershipTerm()
    {
        String taxFetchForMem = taxFetchedForMembershipTerm.getText();
        System.out.println("Tax for the  membership term  from Application widget is " + taxFetchForMem);
        return  taxFetchForMem;

    }
    public String getTaxForRegistrationFee()
    {
        String taxFetchForRegistFee = taxFetchedForRegistrationFee.getText();
        System.out.println(" Tax for Registration Fee from Application Widget is " + taxFetchForRegistFee);
        return taxFetchForRegistFee;

    }

    public void getDate(String date) {
        getDateTextBox.sendKeys(date);
        getDateTextBox.sendKeys(Keys.ENTER);

    }

    /*    public void selectDateFromDatePicker(String day) throws Exception {
            for (WebElement dayElement : datePicker) {
                String calendarDay = dayElement.getText();
                System.out.println("Calendar Day selected is " + calendarDay + ":" + calendarDay.contains(day));
                TimeUnit.SECONDS.sleep(1);
                if (calendarDay.contains(day)) {
                    dayElement.click();
                    break;

                }
            }
        }


        public void selectMonth(String month) throws Exception {
            currentMonth.click();
            for (WebElement monthElement : effectiveMonth) {
                String calendarMonth = monthElement.getText();
                System.out.println("Calendar Month selected is " + calendarMonth + ":" + calendarMonth.contains(month));
                TimeUnit.SECONDS.sleep(1);
                if (calendarMonth.contains(month)) {
                    monthElement.click();
                    break;

                }
            }
        }

        public void selectYear(String year) throws Exception {
            currentYear.click();
            for (WebElement yearElement : effectiveYear) {
                String calendarYear = yearElement.getText();
                System.out.println("Calendar Year selected is " + calendarYear + ":" + calendarYear.contains(year));
                TimeUnit.SECONDS.sleep(1);
                if (calendarYear.contains(year)) {
                    yearElement.click();
                    break;

                }
            }
        }


     */
    public void clickSubmitButton2() {
        submitButtonPaymentOption.click();
    }

    public void clickPayNow2() {
        payNow2.click();
    }


    public void enterEmailAddress(String email) {
        emailTextBox.sendKeys(email);
    }

    public void enterChapter(String chapter) {
        chapterTextBox.sendKeys(chapter);
    }

    public void clickChapterRadioButton() {
        chapterRadioButton.click();
    }

    public void enterChapterName(String chapter) {
        differentChapterName.sendKeys(chapter);
    }

    public void enterCityName(String city) {
        differentCity.sendKeys(city);
    }

    public void selectMeetingDay(String meetingDay) throws Exception {

        int counter = 0;
        Actions action = new Actions(driver);
        meetingDayComboBox.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : selectMeetingDay) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String day = a_collection.get(0).getText();
                if (meetingDay.equals(day)) {
                action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div/div/div/div/div[5]/div[2]/div/div[2]/div"))));
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


    public void clickSelectDifferentChapter() {
        differentChapter.click();
    }

    public void enterCity(String city) {
        cityTextBox.sendKeys(city);
    }

    public void clickSearchButton() {
        searchButton.click();
    }


    public void selectYear(String year) {
        Select yearSelect = new Select(experienceYear);
        yearSelect.selectByVisibleText(year);
    }

    public void selectDateFromDatePicker(String day) throws Exception {
        Integer breaker = 2;
        for (WebElement trElement : datePicker) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("td"));
            for (int row = 0; row < 7; row++) {
                String dayItem = td_collection.get(row).getText();
                if (day.equals(dayItem)) {
                    td_collection.get(row).findElement(By.tagName("a")).click();
                    TimeUnit.SECONDS.sleep(3);
                    breaker++;
                    break;
                }
            }
            if (breaker == 3) {
                break;
            }
        }
    }


    public void enterDate(String date1) throws InterruptedException {
        dateTextBox.sendKeys(date1);
        TimeUnit.SECONDS.sleep(3);
    }

    public void clickDropdown() {
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
                System.out.println("term selected is" + membershipName);
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


    public void clickChapterSelection() {
        chapterSelection.click();
    }

    public void clickApplicationFormButton() {
        applicationFormButton.click();
    }

    public void clickApplication() {
        applicationButton.click();
    }

    public void clickApplicationFormNextButton() throws Exception {
        appFormNextButton.click();
    }

    public void selectLanguage(String language) {
        languageRadioButton.sendKeys(language);
    }

    public void selectApplicationLanguage(String language) {
        applicationLanguage.click();
    }

    public void clickCompanyPaidMembership() {
        companyPaidMembership.click();
    }

    public void enterPayerName(String payerName) {
        payerNameTextBox.sendKeys(payerName);

    }

    public void clickReviewButton() {
        reviewButton.click();
    }

    public void clickSubmitApplicationbutton() {
        submitApplicationButton.click();
    }


    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickDownloadApplicationButton() {
        downloadApplicationButton.click();
    }

    public void enterBniMember(String member) {
        bniMember.sendKeys(member);
    }

    public void clickRadioButton() {
        radioButton.click();
    }

    public void clickMembershipTermNextButton() {
        membershipTermNextButton.click();
    }

    public void clickCalendarIcon() {
        calendarIcon.click();
    }

    public void enterExperienceProfessionalClassification(String expProfessionalExp) {
        expProfessionalClassification.sendKeys(expProfessionalExp);
    }

    public void enterLengthProfessionalClassification(String lengthProfessionalExp) {
        lengthProfessionalClassification.sendKeys(lengthProfessionalExp);
    }

    public void selectLicenceStatus(String licenceOption) {
        if (licenceOption == "Yes") {
            licenceOption1.click();
        } else {
            licenceOption2.click();
        }
    }

    public void selectProfessionalClassification() {
        professionalClassification.click();
    }

    public void clickRenewalApplicationSubmitButton() {
        renewalApplicationSubmitButton.click();
    }

    public void enterBackground(String backgroundClassification) {
        background.sendKeys(backgroundClassification);
    }

    public void enterCommitment(String commitmentOption) {
        if (commitmentOption == "Yes") {
            commitmentYes.click();
        } else {
            commitmentNo.click();
        }
    }

    public void enterSubstitute(String substituteOption) {
        if (substituteOption == "Yes") {
            substituteYes.click();
        } else {
            substituteNo.click();
        }
    }



    public void enterAlreadyMember() {
        alreadyMember.click();
    }

    public void clickAcceptCheckBox() {
        acceptCheckBox.click();
    }

    public void clickReviewApplicationButton() {
        reviewApplicationButton.click();
    }


    public void clickCheck1() {
        acceptcheck1.click();
    }

    public void clickCheck2() {
        check2.click();
    }

    public void clickCheck3() {
        check3.click();
    }

    public void clickEditGB() {
        editGBReviewPage.click();
    }

    public void editPersonalInfo(String test) {
        //companyName.clear();
        companyName.sendKeys(test);
    }

    public void selectPaymentMethod2(String paymentMethod) throws InterruptedException {
        paymentOption.click();
        int counter = 0;
        Actions action = new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : paymentMethod2) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String pm = a_collection.get(0).getText();
            System.out.println("payment method is " + pm);
            if (paymentMethod.equals(pm)) {
                action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/header/header/header/header/header/div/div[2]/div/span"))));
                action.build().perform();
                paymentOption.sendKeys(Keys.ARROW_DOWN);
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


    public void clickSubmitGBButton() {
        submitGBButton.click();

    }

    public void clickPaynow() throws InterruptedException {

        TimeUnit.SECONDS.sleep(2);
        paynowButton.click();
    }

    public void selectTitle(String title) throws InterruptedException {
        personalTitleTextBox.click();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("title box clicked");
        getPersonalTitleList.sendKeys(title);
    }

    public void enterFirstName(String firstName) {
        personalFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        personalLastName.sendKeys(lastName);
    }

    public void enterPersonalLanguage(String language) throws Exception {
        languageTextBox.click();
        int counter = 0;
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : personalLanguageList) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String lang = a_collection.get(0).getText();
               if (language.equals(lang)) {
                Robot robot = new Robot();  // Robot class throws AWT Exception
                Thread.sleep(4000); // Thread.sleep throws InterruptedException
                robot.keyPress(KeyEvent.VK_DOWN);  // press arrow down key of keyboard to navigate
                robot.mouseWheel(400);
                EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
                eventFiringWebDriver.executeScript("scroll(0,4000)", language);

                languageTextBox.sendKeys(Keys.ARROW_DOWN);
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div"))));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 1500)", "");
                languageTextBox.sendKeys(Keys.ARROW_DOWN);
                a_collection.get(0).click();
                TimeUnit.SECONDS.sleep(20);
                counter++;
                break;
            }
            if (counter == 1) {
                break;
            }
        }


    }


    public void enterPersonalPhoneNumber(String phoneNumber) {
        personalPhoneNumber.sendKeys(phoneNumber);
    }

    public void enterPersonalAddressLine(String addressLine) {
        personalAddressLine1.sendKeys(addressLine);
    }

    public void selectPersonalCountryList(String country) throws InterruptedException, AWTException {


        personalCountryTextBox.click();
        int counter = 0;
        //  Actions action = new Actions(driver);

        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : personalCountryListBox) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String county = a_collection.get(0).getText();
            System.out.println("Country from Dropdown in Application form is " + county);
            if (country.equals(county)) {

                Robot robot = new Robot();  // Robot class throws AWT Exception
                Thread.sleep(2000); // Thread.sleep throws InterruptedException
                robot.keyPress(KeyEvent.VK_DOWN);  // press arrow down key of keyboard to navigate
                robot.mouseWheel(596);
                personalCountryTextBox.sendKeys(Keys.ARROW_DOWN);
                // action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                WebDriverWait wait = new WebDriverWait(driver, 25);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/div"))));
                // action.build().perform();
                personalCountryTextBox.sendKeys(Keys.ARROW_DOWN);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 250)", "");
                a_collection.get(0).click();
                TimeUnit.SECONDS.sleep(18);
                counter++;
                break;
            }
            if (counter == 1) {
                break;
            }
        }


    }

    public void enterIndustryList(String industry) throws Exception {

        personalIndustryTextBox.click();
        int counter = 0;
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : personalIndustryListBox) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String indus = a_collection.get(0).getText();
                if (industry.equals(indus)) {

                Robot robot = new Robot();  // Robot class throws AWT Exception
                Thread.sleep(2000); // Thread.sleep throws InterruptedException
                robot.keyPress(KeyEvent.VK_DOWN);  // press arrow down key of keyboard to navigate
                robot.mouseWheel(300);

                EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
                eventFiringWebDriver.executeScript("scroll(0,4000)", industry);
                // action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(18);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 250)", "");
                WebDriverWait wait = new WebDriverWait(driver, 35);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/main/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/div/div/div[1]/div[2]/div"))));
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
}


