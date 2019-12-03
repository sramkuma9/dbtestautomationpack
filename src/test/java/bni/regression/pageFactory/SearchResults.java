package bni.regression.pageFactory;

import bni.regression.libraries.common.ReadWritePropertyFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchResults {
    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    @FindBy(css =  "#buttons_1 > a:nth-child(1)")
    //@FindBy(css = "#links_1")
    WebElement exportIndiaButton;

    @FindBy(css =  "#params_2")
    WebElement runAtDateTime;


    public SearchResults(WebDriver driver) {
        SearchResults.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickExportIndiaButton(){
        exportIndiaButton.click();
    }

    public String getDateTime() throws ParseException {
        String dateTime = runAtDateTime.getText();
        String splitDate = dateTime.substring(0,11);
        System.out.println(splitDate);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format1.parse(splitDate);
        System.out.println(format2.format(date));
        return ("find-person-report_" + format2.format(date) + " " + dateTime.substring(12)).replaceAll(" ","_").replaceAll(":","-") + ".xls";
    }
}
