package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ViewScheme {

    public static WebDriver driver;
    public WebDriverWait wait;







    @FindBy(css = "#div.euiFlexGrid.euiFlexGrid--gutterLarge.euiFlexGrid--halves.euiFlexGrid--responsive.sc-fzoYHE.GXatJ >div:nth-child(1) > div > div > dl > dd > div ")
    WebElement productValueInScheme;

    @FindBy(css = "#div.euiFlexGrid.euiFlexGrid--gutterLarge.euiFlexGrid--halves.euiFlexGrid--responsive.sc-fzoYHE.GXatJ > div:nth-child(2) > div > div > dl > dd > div")
    WebElement skuValueInScheme;


    @FindBy(css = "#div.euiFlexGrid.euiFlexGrid--gutterLarge.euiFlexGrid--halves.euiFlexGrid--responsive.sc-fzoYHE.GXatJ > div:nth-child(7) > div > div > dl > dd > div ")

    //@FindBy(xpath ="//*[@id='iaf0c23a0-f454-11ea-ad9b-63977bdafeef_9d9a0e80-f458-11ea-ad9b-63977bdafeef']/div/div[1]/div[7]/div/div/dl/dd/div")
            WebElement effectiveDateInScheme;

    @FindBy(xpath = "//*[@id='root']/div[1]/main/div[3]/div/div/div[3]/div/div[3]/div/div[1]/button/span/span")
    WebElement deactivateSchemeButton;



    @FindBy(xpath = "//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div/div[1]/div/div/div/button")
   WebElement eyeIcon;


    public ViewScheme(WebDriver driver) {
        ViewScheme.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }



    public void selectSchemeToView(String product, String sku, String template, String status) {

        List<WebElement> body = driver.findElements(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div/div[2]/div/div/div/div"));
        int count = body.size();
        System.out.println("count is" + count);
        for (int a = 1; a <= count; a++) {
            String productValueInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[2]/div/div/div/div/div[1]/div/div")).getText();
            System.out.println("Product" + productValueInViewScheme);

            String skuValueInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[" + (a + 1) + " ]/div[3]/div/div/div/div/div[1]/div/div")).getText();
            System.out.println("SKU" + skuValueInViewScheme);
            String templateInViewScheme = driver.findElement((By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[4]/div/div/div/div/div[1]/div/div"))).getText();
            System.out.println("templateToDeactivate" + templateInViewScheme);
            String statusInViewScheme = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[" + (a + 1) + " ]/div[5]/div/div/div/div/span/span/span")).getText();
            System.out.println("statusToDeactivate" + statusInViewScheme);
            if ((productValueInViewScheme.equals(product) && skuValueInViewScheme.equals(sku) && templateInViewScheme.equals(template) && statusInViewScheme.equals(status))) {
                System.out.println("Data matched with feature input");
                WebElement eyeIcon = driver.findElement(By.xpath("//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[ " + (a + 1) + " ]/div[1]/div/div/div/button"));
                eyeIcon.click();


            }

        }

//    public void clickDeactivateScheme(){
//       deactivateSchemeButton.click();
//    }

    }
}
