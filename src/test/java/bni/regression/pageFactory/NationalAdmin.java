package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NationalAdmin {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#droppedMemberEmail")
    WebElement emailTextBox;

    @FindBy(css = "#save")
    WebElement saveButton;

    @FindBy(css = "#assign")
    WebElement addPersonToRoleButton;

    @FindBy(css = "#datalist > tbody > tr:nth-child(1) > td:nth-child(3) > a > img")
    WebElement assignRoleImage;

    @FindBy(css = "#searchDroppedMember")
    WebElement searchButton;

    @FindBy(css = "#assignrolebtn")
    WebElement assignRoleButton;

    @FindBy(css = "#editNationRoles > tbody > tr")
    List<WebElement> roleAssignedToList;

    public NationalAdmin(WebDriver driver) {
        NationalAdmin.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }


    public void enterEmailId(String emailId) {
        emailTextBox.sendKeys(emailId);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickAssignRoleButton() {
        assignRoleButton.click();
    }

    public void clickAddPersonToRoleButton() {
        addPersonToRoleButton.click();
    }

    public String checkRoleAssigned(String expName) {
        Integer breaker = 2;
        String result = "Country level role is not assigned successfully...";
        for (WebElement trElement : roleAssignedToList) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("td"));
            String actualName = td_collection.get(0).getText();
            if (actualName.equals(expName)) {
                result = "Country level role assigned successfully...";
                breaker++;
                break;
            }
            if (breaker == 3) {
                break;
            }
        }
        return (result);
    }
}
