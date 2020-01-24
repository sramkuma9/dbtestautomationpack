package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindAnInvoice  {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#invoiceReference")
    WebElement invoiceReferenceTextBox;

    @FindBy(css = "#endDateDisplay")
    WebElement endDateDisplayListBox;

    @FindBy(css = "#startDateDisplay")
    WebElement startDateDisplayListBox;

    @FindBy(css = "#idCompany")
    WebElement idCompanyListBox;

    @FindBy(css = "#fromIdOrg")
    WebElement fromIdOrgListBox;

    @FindBy(css = "#toIdOrg")
    WebElement toIdOrgListBox;

    @FindBy(css = "#onBehalfIdOrg")
    WebElement onBehalfIdOrgListBox;

    @FindBy(css = "#idInvoiceType")
    WebElement idInvoiceTypeListBox;

    @FindBy(css = "#idInvoiceStatus")
    WebElement idInvoiceStatusListBox;

    @FindBy(css = "#idPaymentType")
    WebElement idPaymentTypeListBox;

    @FindBy(css = "#idPaymentStatus")
    WebElement idPaymentStatusListBox;

    @FindBy(css = "#numberPerPage")
    WebElement numberPerPageTextBox;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr")
    List<WebElement> datePicker;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-month")
    WebElement periodMonth;

    @FindBy(css = "#ui-datepicker-div > div > div > select.ui-datepicker-year")
    WebElement periodYear;

    @FindBy(css="#peopleSearch")
    WebElement searchButton;

    @FindBy(css="#reset")
    WebElement resetButton;



    public FindAnInvoice(WebDriver driver) {
        FindAnInvoice.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickEndDateField() throws InterruptedException {
        endDateDisplayListBox.click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void clickStartDateField() throws InterruptedException {
        startDateDisplayListBox.click();
        TimeUnit.SECONDS.sleep(2);
    }



    public void selectDatePicker(String date) throws Exception{
        for(WebElement trElement : datePicker)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            for (int row = 0; row < 7; row++) {
                String dayItem = td_collection.get(row).getText();
                if (date.equals(dayItem)) {
                    td_collection.get(row).findElement(By.tagName("a")).click();
                    TimeUnit.SECONDS.sleep(3);
                    break;
                }
            }
        }
    }


    public void clickSearchButton(){
        searchButton.click();
    }

    public void selectYear(String year) {
        Select visitYearSelect = new Select(periodYear);
        visitYearSelect.selectByVisibleText(year);
    }

    public void selectMonth(String month) {
        Select visitYearSelect = new Select(periodMonth);
        visitYearSelect.selectByVisibleText(month);
    }
    public void clickInvoiceReference(){
        invoiceReferenceTextBox.click();
    }
    public void enterInvoiceReference(String invoiceReference){
        invoiceReferenceTextBox.sendKeys(invoiceReference);
    }
    public void clickFromIdOrg(){
        fromIdOrgListBox.click();
    }

    public void selectFromIdOrg(String fromIdOrg){
        Select fromIdOrgSelect = new Select (fromIdOrgListBox) ;
        fromIdOrgSelect.selectByVisibleText(fromIdOrg);
    }
    public void clickToIdOrg(){
        toIdOrgListBox.click();
    }
    public void selectToIdOrg(String toIdOrg){
        Select toIdOrgSelect = new Select (toIdOrgListBox) ;
        toIdOrgSelect.selectByVisibleText(toIdOrg);
    }
    public void clickIdCompany(){
        idCompanyListBox.click();
    }
    public void selectIdCompany(String idCompany){
        Select idCompanySelect = new Select(idCompanyListBox);
        idCompanySelect.selectByVisibleText(idCompany);
    }
    public void clickOnBehalfIdOrg(){
        onBehalfIdOrgListBox.click();
    }
    public void selectOnBehalfIdOrg(String onBehalfIdOrg)
    {

        Select onBehalfIdOrgSelect = new Select (onBehalfIdOrgListBox);
        onBehalfIdOrgSelect.selectByVisibleText(onBehalfIdOrg);
    }
    public void clickIdInvoiceType(){
        idInvoiceTypeListBox.click();
    }
    public void selectIdInvoiceType(String idInvoiceType)
    {

        Select idInvoiceTypeSelect = new Select (idInvoiceTypeListBox);
        idInvoiceTypeSelect.selectByVisibleText(idInvoiceType);
    }
    public void clickIdInvoiceStatus(){
        idInvoiceStatusListBox.click();
    }
    public void selectIdInvoiceStatus(String idInvoiceStatus)
    {

        Select idInvoiceStatusSelect = new Select (idInvoiceStatusListBox);
        idInvoiceStatusSelect.selectByVisibleText(idInvoiceStatus);
    }
    public void clickIdPaymentType(){
        idPaymentTypeListBox.click();
    }
    public void selectIdPaymentType(String idPaymentType)
    {

        Select idPaymentTypeSelect = new Select (idPaymentTypeListBox);
        idPaymentTypeSelect.selectByVisibleText(idPaymentType);
    }
    public void clickIdPaymentStatus(){
        idPaymentStatusListBox.click();
    }
    public void selectIdPaymentStatus(String idPaymentStatus)
    {

        Select idPaymentStatusSelect = new Select (idPaymentStatusListBox);
        idPaymentStatusSelect.selectByVisibleText(idPaymentStatus);
    }

    public void enterNumberPerPage(String numberPerPage){
        numberPerPageTextBox.clear();
        numberPerPageTextBox.sendKeys(numberPerPage);
    }


}
