package bni.regression.pageFactory;

import bni.regression.libraries.common.CaptureScreenShot;
import bni.regression.libraries.common.ReadWritePropertyFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;

public class ManageMembers {
    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    private CaptureScreenShot captureScreenShot;

    @FindBy(css =  "#firstName")
    WebElement firstNameTextBox;

    @FindBy(css =  "#secondName")
    WebElement lastNameTextBox;

    @FindBy(css =  "#searchMembers")
    WebElement searchMembersButton;

    @FindBy(css =  "#datalist > tbody > tr > td:nth-child(3)")
    WebElement checkCompany;

    @FindBy(css =  "#datalist > tbody > tr > td:nth-child(5)")
    WebElement checkStatus;

    @FindBy(css =  "#datalist > tbody > tr > td:nth-child(7) > a > img")
    WebElement editMemberButton;

    public ManageMembers(WebDriver driver) {
        ManageMembers.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterFirstName(String firstName){
        firstNameTextBox.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }

    public void clickSearchMembers(){
        searchMembersButton.click();
    }

    public void clickEditMember(){
        editMemberButton.click();
    }

    public void checkCompanyNameAndStatus(String expectedCompanyName, String expectedStatus) throws Exception {
        String actualCompanyName = checkCompany.getText();
        String actualStatus = checkStatus.getText();
        captureScreenShot = new CaptureScreenShot(driver);
        captureScreenShot.takeSnapShot(driver, "viewEditMemberDetails");
        assertEquals("companyName is not correct", expectedCompanyName, actualCompanyName);
        assertEquals("Status is not correct", expectedStatus, actualStatus);
    }
}
