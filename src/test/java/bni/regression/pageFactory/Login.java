package bni.regression.pageFactory;

import bni.regression.libraries.common.ReadWritePropertyFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

//    @FindBy(css =  "#app > div > div > div.styles__AppContent-cDnHLr.gXPOuk > div > div.styles__ContentContainer-kcVUrs.DjEAQ > section.styles__Container-ieicpK.iVPZSq > div.styles__Container-jGsLTA.fXXdOL > form.styles__StyledForm-feCSaU.hJcysH > div:nth-child(1) > div > div > div > input")
//    WebElement bniUserName;

  //@FindBy(css="  .bTcCMz > div:nth-child(1) > div")
    @FindBy(xpath ="//input[@name='username']")
  WebElement bniUserName;

@FindBy(xpath="//input[@name='password']")
WebElement bniPassword;


//    @FindBy(css =  "#app > div > div > div.styles__AppContent-cDnHLr.gXPOuk > div > div.styles__ContentContainer-kcVUrs.DjEAQ > section.styles__Container-ieicpK.iVPZSq > div.styles__Container-jGsLTA.fXXdOL > form.styles__StyledForm-feCSaU.hJcysH > div:nth-child(2) > div > div > div > input")
//    WebElement bniPassword;

//    @FindBy(css =  "#app > div > div > div.styles__AppContent-cDnHLr.gXPOuk > div > div.styles__ContentContainer-kcVUrs.DjEAQ > section.styles__Container-ieicpK.iVPZSq > div.styles__Container-jGsLTA.fXXdOL > form.styles__StyledForm-feCSaU.hJcysH > div.styles__ButtonContainer-iUsGiN.gqPErL > div > button > span.jss29")
//    WebElement bniSignIn;

  //  @FindBy(xpath="(//button[@class='jss52 jss28 jss30 jss36 styles__SignInButton-dSRDTk igetQs styles__PrimaryButton-gAZLqB blfDTc styles__StyledButton-fPIFiv fhBuoo'])")

    @FindBy(xpath="/html/body/div[1]/div/div/div[1]/div/div[1]/section[2]/div[2]/form[1]/div[3]/div/button")
    WebElement bniSignIn;

    public Login(WebDriver driver) {
        Login.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterBniUserName(String userName){
        //bniUserName.sendKeys(readWritePropertyFile.loadAndReadPropertyFile("bniUsername", "properties/config.properties"));
        bniUserName.sendKeys(userName);
        //bniUserName.sendKeys(Keys.TAB);
    }
    public void enterBniPassword(String password){

        //bniPassword.sendKeys(readWritePropertyFile.loadAndReadPropertyFile("bniPassword", "properties/config.properties"));
        bniPassword.sendKeys(password);
    }
    public void clickBniSignInButton(){

        bniSignIn.click();
    }
}
