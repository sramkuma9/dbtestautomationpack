package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TranslationHome {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#common.footer.browserpolicy")
    WebElement browserPolicyTextBox;

    @FindBy(css = "#browser > li.collapsable > ul > li:nth-child(5) > a")
    WebElement footerLink;

    @FindBy(css = "#common.footer.privacypolicy")
    WebElement privacyPolicyTextBox;

    @FindBy(css = "#submit")
    WebElement submitButton;

    public TranslationHome(WebDriver driver) {
        TranslationHome.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterBrowserPolicy(String browserPolicy){
        browserPolicyTextBox.clear();
        browserPolicyTextBox.sendKeys(browserPolicy);
    }

    public void enterPrivacyPolicy(String privacyPolicy){
        browserPolicyTextBox.clear();
        privacyPolicyTextBox.sendKeys(privacyPolicy);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public void clickFooterLink(){
        footerLink.click();
    }
}
