package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegionEmailMembers {
    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(css = "#filterByRegions")
    List<WebElement> listOfRegions;

    @FindBy(css = "#filterByChapters")
    List<WebElement> oneOrMoreChapters;

    @FindBy(css = "#filterByAssistantDirectors")
    List<WebElement> membersUnderThisAssistantDirector;

    @FindBy(css = "#filterByChapterRoles")
    List<WebElement> membersWhoHoldPosition;

    @FindBy(css = "#filterByAreaDirectors")
    List<WebElement> membersUnderAreaDirector;

    @FindBy(css = "#filterByGeoAreas")
    List<WebElement> membersinGeoArea;

    @FindBy(css = "#filterByMembershipStatuses")
    List<WebElement> memberStatus;

    @FindBy(css = "#searchEmailBTN")
    WebElement findButton;

    @FindBy(css = "#selectAll")
    WebElement selectAllButton;

    @FindBy(css = "#datalist_filter > input[type=text]")
    //@FindBy(css = "#datalist_filter > input:nth-child(1)")
    WebElement searchTextBox;



    //@FindBy(css = "#member_6065")
    @FindBy(css = "#datalist > tbody > tr:nth-child(1) > td:nth-child(1)")
    WebElement memberCheckBox;

    @FindBy(css = "#proceedStep3")
    WebElement nextButton;

    public RegionEmailMembers(WebDriver driver) {
        RegionEmailMembers.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectRegion(String region) throws InterruptedException {
        for (WebElement trElement : listOfRegions) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("option"));
            Integer listSize = td_collection.size();
            for (int i = 0; i < listSize; i++) {
                String regionItem = td_collection.get(i).getText();
                if (region.equals(regionItem)) {
                    td_collection.get(i).click();
                    break;
                }
            }
        }
    }

    public void selectChapter(String chapter) throws InterruptedException {
        for (WebElement trElement : oneOrMoreChapters) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("option"));
            Integer listSize = td_collection.size();
            for (int i = 0; i < listSize; i++) {
                String Item = td_collection.get(i).getText();
                if (chapter.equals(Item)) {
                    td_collection.get(i).click();
                    TimeUnit.SECONDS.sleep(1);
                    td_collection.get(i).click();
                    break;
                }
            }
        }
    }

    public void selectAssistantDirector(String asstDir) throws InterruptedException {
        for (WebElement trElement : membersUnderThisAssistantDirector) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("option"));
            Integer listSize = td_collection.size();
            for (int i = 0; i < listSize; i++) {
                String Item = td_collection.get(i).getText();
                if (asstDir.equals(Item)) {
                    td_collection.get(i).click();
                    TimeUnit.SECONDS.sleep(1);
                    td_collection.get(i).click();
                    break;
                }
            }
        }
    }

    public void selectPosition(String position) throws InterruptedException {
        for (WebElement trElement : membersWhoHoldPosition) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("option"));
            Integer listSize = td_collection.size();
            for (int i = 0; i < listSize; i++) {
                String Item = td_collection.get(i).getText();
                if (position.equals(Item)) {
                    td_collection.get(i).click();
                    TimeUnit.SECONDS.sleep(1);
                    td_collection.get(i).click();
                    break;
                }
            }
        }
    }

    public void selectAreaDirector(String areaDir) throws InterruptedException {
        for (WebElement trElement : membersUnderAreaDirector) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("option"));
            Integer listSize = td_collection.size();
            for (int i = 0; i < listSize; i++) {
                String Item = td_collection.get(i).getText();
                if (areaDir.equals(Item)) {
                    td_collection.get(i).click();
                    TimeUnit.SECONDS.sleep(1);
                    td_collection.get(i).click();
                    break;
                }
            }
        }
    }

    public void selectGeoArea(String geoArea) throws InterruptedException {
        for (WebElement trElement : membersinGeoArea) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("option"));
            Integer listSize = td_collection.size();
            for (int i = 0; i < listSize; i++) {
                String Item = td_collection.get(i).getText();
                if (geoArea.equals(Item)) {
                    td_collection.get(i).click();
                    TimeUnit.SECONDS.sleep(1);
                    td_collection.get(i).click();
                    break;
                }
            }
        }
    }

    public void selectStatus(String status) throws InterruptedException {
        String[] memberStatusSplit = status.split(",");
        Integer size = memberStatusSplit.length;
        for (int j = 0; j < size; j++) {
            for (WebElement trElement : memberStatus) {
                List<WebElement> td_collection = trElement.findElements(By.tagName("option"));
                Integer listSize = td_collection.size();
                for (int i = 0; i < listSize; i++) {
                    String Item = td_collection.get(i).getText();
                    if (memberStatusSplit[j].equals(Item)) {
                        td_collection.get(i).click();
                        Actions actions = new Actions(driver);
                        actions.keyDown(Keys.LEFT_CONTROL).click(td_collection.get(i))
                                .build()
                                .perform();
                        break;
                    }
                }
            }
        }
    }

    public void clickFindButton() {
        findButton.click();
    }

    public void clickSelectAllButton() {
        selectAllButton.click();
    }

    public void selectMembersForEmail(String searchString) throws InterruptedException {
        String[] emailSplit = searchString.split(",");
        Integer size = emailSplit.length;
        for (int i = 0; i < size; i++) {
            searchTextBox.click();
            TimeUnit.SECONDS.sleep(1);
            searchTextBox.sendKeys(emailSplit[i]);
            TimeUnit.SECONDS.sleep(1);
            memberCheckBox.click();
            TimeUnit.SECONDS.sleep(2);
            searchTextBox.clear();
        }
    }

    public void enterSearchString(String searchString) {
        searchTextBox.sendKeys(searchString);
    }


    public void checkMemberCheckBox() {
        memberCheckBox.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }
}