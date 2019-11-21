package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MemberRenewalApplication {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#\\32  > input[type=RADIO]")
    WebElement membershipPeriodCheckBox;

    @FindBy(css = "#\\32 55 > input[type=RADIO]")
    WebElement networkingOrgCheckBox;

    @FindBy(css = "#\\32 58 > input[type=RADIO]")
    //@FindBy(css="#\32 58 > input:nth-child(1)")#\32 58 > input[type=RADIO]
    WebElement invitePeopleCheckBox;

    @FindBy(css = "#\\32 53 > input[type=RADIO]")
    WebElement referralCheckBox;

    @FindBy(css = "#\\32 57 > input[type=RADIO]")
    WebElement convictedCheckBox;

    @FindBy(css = "#\\32 68 > input[type=RADIO]")
    WebElement recommendCheckBox;

    @FindBy(css = "#\\32 51 > input[type=CHECKBOX]")
    WebElement refferalChapterCheckBox;

    @FindBy(css = "#questions > ul > li:nth-child(9) > div > span > textarea")
    WebElement describeBNITextBox;

    @FindBy(css = "#\\32 62 > input[type=CHECKBOX]")
    WebElement leadershipTeamCheckBox;

    @FindBy(css = "#agreeToTermsAndConditions")
    WebElement termsAndCOnditionsCheckBox;

    @FindBy(css = "#submitProceedPayment")
    WebElement proceedToPayment;

    public MemberRenewalApplication(WebDriver driver) {
        MemberRenewalApplication.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickMembershipPeriodCheckBox() {
        membershipPeriodCheckBox.click();
    }

    public void clickNetworkingOrgCheckBox() {
        networkingOrgCheckBox.click();
    }

    public void clickInvitePeopleCheckBox() {
        invitePeopleCheckBox.click();
    }

    public void clickReferralCheckBox() {
        referralCheckBox.click();
    }

    public void clickConvictedCheckBox() {
        convictedCheckBox.click();
    }

    public void clickRecommendCheckBox() {
        recommendCheckBox.click();
    }

    public void clickrefferalChapterCheckBox() {
        refferalChapterCheckBox.click();
    }

    public void clickleadershipTeamCheckBox() {
        leadershipTeamCheckBox.click();
    }

    public void clickTermsAndConditionsCheckBox() {
        termsAndCOnditionsCheckBox.click();
    }

    public void clickProceedToPayment() {
        proceedToPayment.click();
    }

    public void enterDescribeBNI(){
        describeBNITextBox.sendKeys("おはようございます");
    }

}
