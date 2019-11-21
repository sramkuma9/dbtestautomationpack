package bni.regression.pageFactory;

import bni.regression.libraries.common.ReadWritePropertyFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BNIConnect {
    public static WebDriver driver;
    public WebDriverWait wait;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    @FindBy(css = "#accountNav_2 > p > a")
    WebElement options;

    @FindBy(css = "#accountNav_2 > ul > li:nth-child(3) > a")
    WebElement signOut;

    @FindBy(css = "#footer > div:nth-child(2) > a:nth-child(3)")
    WebElement signOutFooter;

    @FindBy(css = "#bniCountry")
    WebElement countryListBox;

    @FindBy(css = "#commonFilterListCountries > div > a")
    List<WebElement> countrySelect;

    @FindBy(css = "#bniRegion")
    WebElement regionListBox;

    @FindBy(css = "#commonFilterListRegions > div > a")
    List<WebElement> regionSelect;

    @FindBy(css = "#bniChapter")
    WebElement chapterListBox;


    @FindBy(css = "#commonFilterListChapters > div > a")
    List<WebElement> chapterSelect;

    @FindBy(css = "#textnav > li")
    List<WebElement> menuNavigate;

    @FindBy(css = "#textnav > li > ul > li")
    List<WebElement> subMenuNavigate;

    @FindBy(css = "#help")
    List<WebElement> subListMenu;

    @FindBy(css = "#help")
    List<WebElement> mainListMenu;

    @FindBy(css = "#droppedMemberEmail")
    WebElement emailTextBox;

    @FindBy(css = "#searchDroppedMember")
    WebElement searchButton;

    //@FindBy(css = "#convertToMemberHref")
    @FindBy(css = "#convertToMemberHrefCofC")
    WebElement addButton;

    @FindBy(css = "#columnlinks > a:nth-child(5)")
    WebElement renewNowLink;

    public BNIConnect(WebDriver driver) {
        BNIConnect.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void hoverOnOptions() {
        Actions action = new Actions(driver);
        action.moveToElement(options);
        action.build().perform();
    }

    public void selectCountry(String country) throws InterruptedException {
        int counter = 0;
        Actions action = new Actions(driver);
        countryListBox.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : countrySelect) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String countryName = a_collection.get(0).getText();
            if (country.equals(countryName)){
                action.moveToElement(a_collection.get(0));
                action.build().perform();
                a_collection.get(0).click();
                counter++;
                break;
            }if (counter == 1) {
                break;
            }
        }
    }

    public void selectRegion(String region) throws InterruptedException {
        int counter = 0;
        Actions action = new Actions(driver);
        regionListBox.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : regionSelect) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String regionName = a_collection.get(0).getText();
            if (region.equals(regionName)){
                action.moveToElement(a_collection.get(0));
                action.build().perform();
                a_collection.get(0).click();
                counter++;
                break;
            }if (counter == 1) {
                break;
            }
        }
    }

    public void selectChapter(String chapter) throws InterruptedException {
        int counter = 0;
        Actions action = new Actions(driver);
        chapterListBox.click();
        TimeUnit.SECONDS.sleep(2);
        for (WebElement divElement : chapterSelect) {
            List<WebElement> a_collection = divElement.findElements(By.tagName("span"));
            String chapterName = a_collection.get(0).getText();
            if (chapter.equals(chapterName)){
                action.moveToElement(a_collection.get(0));
                action.build().perform();
                a_collection.get(0).click();
                counter++;
                break;
            }if (counter == 1) {
                break;
            }
        }
    }

    public void navigateMenu(String menuItem) throws Exception {
        String[] menusplit = menuItem.split(",");
        int size = menusplit.length;
        Actions action = new Actions(driver);
        int counter = 0;
        for (WebElement trElement : menuNavigate) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("a"));
            String menuName = td_collection.get(0).getText();
            if (menusplit[0].equals(menuName)) {
                switch (size) {
                    case 1:
                        TimeUnit.SECONDS.sleep(3);
                        td_collection.get(0).click();
                        break;
                    case 2:
                        TimeUnit.SECONDS.sleep(3);
                        action.moveToElement(td_collection.get(0));
                        action.build().perform();
                        TimeUnit.SECONDS.sleep(3);
                        //td_collection.findElement(By.tagName("a"));
                        for (WebElement subElement : subMenuNavigate) {
                            List<WebElement> sub_collection = subElement.findElements(By.tagName("a"));
                            String subMenuName = sub_collection.get(0).getText();
                            if (menusplit[1].equals(subMenuName)) {
                                sub_collection.get(0).click();
                                counter = counter + 1;
                                break;
                            }
                        }
                        break;
                    case 3:
                        TimeUnit.SECONDS.sleep(3);
                        action.moveToElement(td_collection.get(0));
                        action.build().perform();
                        TimeUnit.SECONDS.sleep(3);
                        for (WebElement subElement : subMenuNavigate) {
                            List<WebElement> sub_collection = subElement.findElements(By.tagName("a"));
                            String subMenuName = sub_collection.get(0).getText();
                            if (menusplit[1].equals(subMenuName)) {
                                sub_collection.get(0).click();
                                counter = counter + 1;
                                break;
                            }
                        }
                        TimeUnit.SECONDS.sleep(4);
                        this.selectItemFromMainListMenu(menusplit[1]);
                        TimeUnit.SECONDS.sleep(5);
                        this.selectItemFromSubListMenu(menusplit[2]);
                        break;
                }
            }
            if (counter == 1) {
                break;
            }
        }
    }

    public void selectItemFromSubListMenu(String item) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        for (WebElement trElement : subListMenu) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("tbody"));
            String menuItem = td_collection.get(0).findElement(By.tagName("tr")).getText();
            if (item.equals(menuItem)) {
                td_collection.get(0).findElement(By.tagName("tr")).findElement(By.tagName("a")).click();
                TimeUnit.SECONDS.sleep(3);
                break;
            }
        }
    }

    public void selectItemFromMainListMenu(String item) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        for (WebElement trElement : mainListMenu) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("tbody"));
            String menuItem = td_collection.get(0).findElement(By.tagName("tr")).getText();
            if (item.equals(menuItem)) {
                td_collection.get(0).findElement(By.tagName("tr")).findElement(By.tagName("a")).click();
                TimeUnit.SECONDS.sleep(3);
                break;
            }
        }
    }

    public void enterEmailId(String emailId){
        emailTextBox.sendKeys(emailId);
    }

    public void clickSearchButton(){
        searchButton.click();
    }

    public  void clickAddButton(){
        addButton.click();
    }

    public void clickSignOut() throws Exception {
        signOut.click();
        TimeUnit.SECONDS.sleep(5);
    }

    public void clickSignOutFooter() throws Exception {
        signOutFooter.click();
        TimeUnit.SECONDS.sleep(5);
    }

    public  void clickRenewNowLink(){
        renewNowLink.click();
    }
}
