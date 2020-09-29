package bni.regression.pageFactory;

import io.github.bonigarcia.wdm.SeleniumServerStandaloneManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class PricingPortalDashboard {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css=".euiButton__text")
    WebElement selectOrganisationButton;


  @FindBy(xpath="/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[3]/div/div[2]/button/span/span")
    WebElement selectOrganisationButton2;

    @FindBy(xpath = "//button[@class='euiSuperSelectControl']")
 WebElement countryDropDownIcon;

    @FindBy(xpath = "//div[@class='euiSuperSelect__listbox']")
   WebElement countryListBox;

    @FindBy(css=".euiSuperSelect__listbox > button > span  ")
    List<WebElement> countrySelect;

    @FindBy(xpath="//*[@class='euiIcon euiIcon--medium euiIcon-isLoaded euiContextMenu__icon']")
    WebElement countryIcon;

   @FindBy(xpath="/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/div/div/button")
   WebElement regionListBox;

  @FindBy(css=".euiSuperSelect__listbox > button > span ")
   List<WebElement> regionSelect;

    @FindBy(xpath="//*[@class='euiIcon euiIcon--medium euiIcon-isLoaded euiContextMenu__icon']")
    WebElement regionIcon;

  @FindBy(xpath="/html/body/div[1]/div[1]/main/div[3]/div/div/div[3]/div/div[2]/div/div/div/div/div[3]/div/div[2]/div/div/div/div/button")
    WebElement chapterListBox;


  @FindBy(css=".euiSuperSelect__listbox > button > span  ")
    List<WebElement> chapterSelect;

    @FindBy(xpath="//*[@class='euiIcon euiIcon--medium euiIcon-isLoaded euiContextMenu__icon']")
    WebElement chapterIcon;

    @FindBy(xpath="/html/body/div/div[1]/main/div[1]/div/div[2]/button/span/span")
    WebElement createSchemeButton;

    @FindBy(xpath="//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[2]/div[1]/div/div/div/button")
    WebElement s1;

    @FindBy(xpath="//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[3]/div[1]/div/div/div/button")
    WebElement s2;

    @FindBy(xpath="//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div/div[4]/div[1]/div/div/button")
    WebElement s3;

    @FindBy(xpath="//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div/div[5]/div[1]/div/div/button")
    WebElement s4;     ;

    @FindBy(xpath="//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[6]/div[1]/div/div/div/button")
    WebElement s5;

    @FindBy(xpath="//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[7]/div[1]/div/div/div/button")
    WebElement s6;

    @FindBy(xpath ="//*[@id='root']/div[1]/main/div[2]/div[2]/div/div[4]/div/div[2]/div/div/div[8]/div[1]/div/div/div/button")
    WebElement s7;

    @FindBy(xpath ="//*[@id='root']/div[1]/main/div[3]/div/div/div[3]/div/button")
    WebElement closeButton;



    @FindBy(xpath="//*[@id='iaf0c23a0-f454-11ea-ad9b-63977bdafeef_9d9a0e80-f458-11ea-ad9b-63977bdafeef']/div/div[1]/div[1]/div/div[1]/dl/dd/div")
    WebElement productValueInScheme;

    @FindBy(xpath ="//*[@id='iaf0c23a0-f454-11ea-ad9b-63977bdafeef_9d9a0e80-f458-11ea-ad9b-63977bdafeef']/div/div[1]/div[2]/div/div/dl/dd/div")
    WebElement skuValueInScheme;

    @FindBy(xpath ="//*[@id='iaf0c23a0-f454-11ea-ad9b-63977bdafeef_9d9a0e80-f458-11ea-ad9b-63977bdafeef']/div/div[1]/div[7]/div/div/dl/dd/div")
    WebElement effectiveDateInScheme;

    @FindBy(xpath="//*[@id='root']/div[1]/main/div[3]/div/div/div[3]/div/div[3]/div/div[1]/button/span/span")
    WebElement deactivateSchemeButton;



    public PricingPortalDashboard(WebDriver driver) {
        PricingPortalDashboard.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickSelectOrganisationButton() {
        selectOrganisationButton.click();
    }
    public void clickSelectOrganisationButton2() {
        selectOrganisationButton2.click();
    }
    public void clickDropdown()
    {
        countryDropDownIcon.click();
    }
    public void clickCreateScheme() { createSchemeButton.click();}

    public void viewScheme1()
    {
        s1.click();
    }
    public void viewScheme2()
    {
        s2.click();
    }
    public void viewScheme3()
    {
        s3.click();
    }


    public void selectCountry(String country) throws Exception {
    int counter = 0;
    Actions action = new Actions(driver);
    action.clickAndHold(countryListBox);
       // countryListBox.sendKeys(Keys.ARROW_DOWN);

    for (WebElement divElement : countrySelect)
    {
        List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
        TimeUnit.SECONDS.sleep(1);
        String countryName = a_collection.get(0).getText();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Country name is " +countryName+":"+country.equals(countryName)) ;
        System.out.println("Country given is " +country);
        if (country.equals(countryName)) {
            action.clickAndHold(a_collection.get(0));
             TimeUnit.SECONDS.sleep(8);
            WebDriverWait wait = new WebDriverWait(driver, 5);
           wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div[2]/div/button/span/span"))));
            countryIcon.isSelected();
            action.build().perform();

            a_collection.get(0).click();
            TimeUnit.SECONDS.sleep(2);
            counter++;
            break;
        }
        if (counter == 1) {
            break;
        }
    }


}
    public void selectRegion(String region) throws Exception {
        int counter = 0;
        Actions action = new Actions(driver);
        action.clickAndHold(regionListBox);
        regionListBox.sendKeys(Keys.ARROW_DOWN);
        System.out.println("listbox clicked");
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : regionSelect) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String regionName = a_collection.get(0).getText();
            System.out.println("Region name is" +regionName);
            if (region.equals(regionName)) {
                action.clickAndHold(a_collection.get(0));
                TimeUnit.SECONDS.sleep(8);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div[2]/div/button/span/span"))));
                regionIcon.isSelected();
                action.build().perform();
                a_collection.get(0).click();
                TimeUnit.SECONDS.sleep(2);
                counter++;
                break;
            }
            if (counter == 1) {
                break;
            }
        }
    }

    public void selectChapter(String chapter) throws Exception {

        int counter = 0;
        Actions action = new Actions(driver);
        action.clickAndHold(chapterListBox);
        TimeUnit.SECONDS.sleep(1);
        chapterListBox.sendKeys(Keys.ARROW_DOWN);
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : chapterSelect)
        {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            TimeUnit.SECONDS.sleep(1);
            String chapterName = a_collection.get(0).getText();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("chapter name is" +chapterName);
            if (chapter.equals(chapterName)) {
                action.moveToElement(a_collection.get(0));
                TimeUnit.SECONDS.sleep(8);
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div[2]/div/button/span/span"))));
                //    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("euiIcon euiIcon--medium euiIcon-isLoaded euiContextMenu__icon"))));
                chapterIcon.isSelected();
                action.build().perform();
                a_collection.get(0).click();
                TimeUnit.SECONDS.sleep(2);
                counter++;
                break;
            }
            if (counter == 1) {
                break;
            }
        }


    }



}