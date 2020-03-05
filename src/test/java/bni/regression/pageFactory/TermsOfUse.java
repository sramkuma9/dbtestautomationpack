package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TermsOfUse {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#toschecked")
    WebElement checkBox;

    @FindBy(css = "#submit")
    WebElement acceptButton;

    @FindBy(css = "#toscontent > embed")
    WebElement pdfText;

    public TermsOfUse(WebDriver driver) {
        TermsOfUse.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickCheckBox() {
        checkBox.click();
    }

    public void clickAcceptButton(){
        acceptButton.click();
    }

    public void checkLastUpdatedDate(){
        System.out.println("print is: " + pdfText.getText());
    }
}
