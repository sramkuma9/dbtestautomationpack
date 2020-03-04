package bni.regression.pageFactory;

import bni.regression.libraries.common.LaunchBrowser;
import bni.regression.libraries.common.ReadWriteExcel;
import bni.regression.libraries.common.ReadWritePropertyFile;
import bni.regression.libraries.ui.SelectCountryRegionChapter;
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

public class EnterOneToOnes {
    public static WebDriver driver;
    public WebDriverWait wait;
    private BNIConnect bniConnect;
    private LaunchBrowser launchBrowser;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();
    ReadWriteExcel readWriteExcel = new ReadWriteExcel();
    private SelectCountryRegionChapter selectCountryRegionChapter = new SelectCountryRegionChapter();
    private OneToOneReport oneToOneReport;

    @FindBy(css = "#metWith")
    WebElement metWithListBox;

    @FindBy(css = "#invitedBy")
    WebElement invitedByListBox;

    @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
    WebElement saveButton;

    @FindBy(css = "#location")
    WebElement locationTextBox;

    @FindBy(css = "#topicsOfConversation")
    WebElement topicsOfConversationTextBox;

    @FindBy(css = "#oneToOneDate")
    WebElement dateTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement applicationMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement applicationYear;

    @FindBy(css = ".linksList > table:nth-child(8) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(1)")
    WebElement slipsCount;

    @FindBy(css = "#memberModuleOneToOneStartDateDisplay")
    WebElement oneToOneStartDate;

    @FindBy(css = "#memberModuleOneToOneEndDateDisplay")
    WebElement oneToOneendDate;

    @FindBy(css = "#__bookmark_3")
    List<WebElement> getSearchResults;

    @FindBy(css = "#button")
    WebElement goButton;

    public EnterOneToOnes(WebDriver driver) {
        EnterOneToOnes.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterLocation(String location) {
        locationTextBox.sendKeys(location);
    }

    public void enterTopicsOfConversation(String topicsOfConversation) {
        topicsOfConversationTextBox.sendKeys(topicsOfConversation);
    }

    public void selectMetWith(String metWith) {
        Select metWithSelect = new Select(metWithListBox);
        metWithSelect.selectByVisibleText(metWith);
    }

    public void selectInvitedBy(String invitedBy) {
        Select invitedBySelect = new Select(invitedByListBox);
        invitedBySelect.selectByVisibleText(invitedBy);
    }

    public void selectVisitMonth(String month) {
        Select visitMonthSelect = new Select(applicationMonth);
        visitMonthSelect.selectByVisibleText(month);
    }

    public void selectVisitYear(String year) {
        Select visitYearSelect = new Select(applicationYear);
        visitYearSelect.selectByVisibleText(year);
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

    public void clickApplicationDateField() throws InterruptedException {
        dateTextBox.click();
        TimeUnit.SECONDS.sleep(1);
    }

    public void clickSlipsCount() throws Exception {
        bniConnect = new BNIConnect(launchBrowser.driver);
        bniConnect.clickOnetoOneRecord();
        TimeUnit.SECONDS.sleep(3);
        bniConnect.clickOneToOneStartDate();
        {
            bniConnect.selectYear("2019");
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectMonth("Sep");
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectDateFromDatePicker("19");
            TimeUnit.SECONDS.sleep(2);
        }
        bniConnect.clickOneToOneEndDate();
        {
            bniConnect.selectYear("2020");
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectMonth("Apr");
            TimeUnit.SECONDS.sleep(2);
            bniConnect.selectDateFromDatePicker("29");
            TimeUnit.SECONDS.sleep(2);
        }
        bniConnect.clickGoButton();

//        driver.switchTo().frame(1);
//        List<WebElement> rows = driver.findElements(By.cssSelector("#__bookmark_3 > tbody:nth-child(2) > tr"));
//        System.out.println("Rows count" +rows);


//        return null;
    }


    public void clickSaveButton() throws InterruptedException {
        saveButton.click();
        TimeUnit.SECONDS.sleep(2);


    }

    public void getSearchResults() throws Exception {
        Integer recordCount = 0;
        oneToOneReport = new OneToOneReport(driver);
        WebElement table = driver.findElement(By.xpath("//table[@class='gf-table']"));
        List<WebElement> row = table.findElements(By.tagName("tr"));
        System.out.println("Total Number of Rows = " + row.size());

    }
}
//        String [] addSlipDetails = new String [8];
//        for(WebElement trElement : getSearchResults)
//
//        {
//            List<WebElement> td_collection=trElement.findElements(By.tagName("tr"));
//            String type = td_collection.get(1).getText();
//            if (type.equals("Pending Membership")) {
//                addSlipDetails[0] = td_collection.get(0).getText();
//                addSlipDetails[1] = td_collection.get(2).getText();
//                addSlipDetails[2] = td_collection.get(3).getText();
//                addSlipDetails[3] = td_collection.get(5).getText();
//                addSlipDetails[4] = td_collection.get(6).getText();
//                addSlipDetails[5] = td_collection.get(4).getText();
//            }
//            recordCount++;
//        }
//      //  Integer expRecordCount = 4;
//      //  assertEquals("Search All individuals result does not have 4 records", expRecordCount, recordCount );
//        return addSlipDetails;
        //}
