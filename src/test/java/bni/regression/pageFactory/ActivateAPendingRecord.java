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

public class ActivateAPendingRecord {


    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(1)")
    WebElement submitButton;

    @FindBy(css="#firstName")
    WebElement firstNameTextbox;

    @FindBy(css="#secondName")
    WebElement secondNameTextbox;

    @FindBy(css="#searchPendingMember")
    WebElement searchButton;

    @FindBy(css="#companyName")
    WebElement companyName;

    @FindBy(css = "#memberSelectChapter")
    WebElement chapterListBox;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement applicationMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement applicationYear;

    // @FindBy(css = "#applicationDate")
    @FindBy(id="applicationDate")
    WebElement applicationDateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "tr> td:nth-child(5) > a")
    WebElement activateButton;

    @FindBy(css="#transfer_datalist_filter > input[type=text]")
    WebElement searchTextBox;



    public ActivateAPendingRecord(WebDriver driver) {
        ActivateAPendingRecord.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterFirstName(String firstName)
    {
        firstNameTextbox.sendKeys(firstName);
    }

    public void enterSecondName(String secondName)
    {
        secondNameTextbox.sendKeys(secondName);
    }
    public void clickSubmitButton(){
        submitButton.click();
    }

    public void enterCompanyName(String compName)
    {
        companyName.sendKeys(compName);
    }

    public void clickSearchButton()
    {
        searchButton.click();
    }

  public void enterSearchText(String text)
  {
    searchTextBox.sendKeys(text);
  }

    public void selectChapter(String chapter) {
        Select chapterSelect = new Select(chapterListBox);
        chapterSelect.selectByVisibleText(chapter);
    }

    public void clickActivateButton()
    {
        activateButton.click();
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

    public void selectMonth(String month) {
        Select visitMonthSelect = new Select(applicationMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectYear(String year) {
        Select visitYearSelect = new Select(applicationYear);
        visitYearSelect.selectByVisibleText(year);
    }




}

