package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class EnterNewApplication {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#droppedMemberEmail")
    WebElement emailTextBox;

    @FindBy(css = "#searchByNameBtn > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3) > div:nth-child(1) > button")
    WebElement searchMemberButton;

    @FindBy(css="#searchBtnArea > div:nth-child(1) > button")
    WebElement searchButton;

    @FindBy(css = "#convertToMemberHref")
    WebElement addButton;

    @FindBy(css="#enter_btn")
    WebElement enterNewMemberButton;

    @FindBy(css="#showSearchByFirstAndLastName")
    WebElement searchByNameButton;

    @FindBy(css = "#datalist1 > tbody > tr")
    List<WebElement> searchResults;

    @FindBy(css = "#reconcile_member_btn")
    WebElement reconcileApplicationButton;

    @FindBy(css="#droppedMemberFirstName")
    WebElement firstNameTextBox;

    @FindBy(css="#droppedMemberLastName")
    WebElement lastNameTextBox;

    public EnterNewApplication(WebDriver driver) {
        EnterNewApplication.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickSearchMemberButton() throws InterruptedException {
        searchMemberButton.click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void clickSearchButton() throws InterruptedException {
        searchButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickAddButton() throws InterruptedException {
        addButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickReconcileApplicationButton() throws InterruptedException {
        reconcileApplicationButton.click();
        TimeUnit.SECONDS.sleep(4);
    }

    public void enterEmail(String emailId) throws InterruptedException {
        emailTextBox.clear();
        emailTextBox.sendKeys(emailId);
        TimeUnit.SECONDS.sleep(1);
    }

    public void clickEnterNewMemberButton()
    {
        enterNewMemberButton.click();
    }
    public void clickSearchByNameButton()
    {
        searchByNameButton.click();
    }

    public void enterFirstName(String firstName)
    {
        firstNameTextBox.sendKeys(firstName);
    }

    public void enterLastName(String lastName)
    {
        lastNameTextBox.sendKeys(lastName);
    }

    public String[] getSearchResults() throws Exception{
        Integer recordCount = 0;
        String [] addAVisitorDetails = new String [8];
        for(WebElement trElement : searchResults)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            String type = td_collection.get(1).getText();
            if (type.equals("Pending Membership")) {
                addAVisitorDetails[0] = td_collection.get(0).getText();
                addAVisitorDetails[1] = td_collection.get(2).getText();
                addAVisitorDetails[2] = td_collection.get(3).getText();
                addAVisitorDetails[3] = td_collection.get(5).getText();
                addAVisitorDetails[4] = td_collection.get(6).getText();
                addAVisitorDetails[5] = td_collection.get(4).getText();
            }
            recordCount++;
        }
        Integer expRecordCount = 4;
        assertEquals("Search All individuals result does not have 4 records", expRecordCount, recordCount );
        return addAVisitorDetails;
    }
}
