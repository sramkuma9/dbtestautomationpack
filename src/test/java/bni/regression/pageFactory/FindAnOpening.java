package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindAnOpening {

    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#Category")
    WebElement categoryListBox;

    @FindBy(css = "#secCategory")
    WebElement secCategoryListBox;

    @FindBy(css = "#city")
    WebElement cityListBox;

    @FindBy(css = "#dayOfWeek")
    WebElement dayOfWeekListBox;

    @FindBy(css="#eventSearch")
    WebElement eventSearchButton;


    public FindAnOpening(WebDriver driver) {
        FindAnOpening.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }


    public void selectCategory(String category) {
        Select categorySelect = new Select(categoryListBox);
        categorySelect.selectByVisibleText(category);

    }
    public void clickSecCategory()
    {
        secCategoryListBox.click();
    }

    public void selectSecCategory(String secCategory) {
        Select secCategorySelect = new Select(secCategoryListBox);
        secCategorySelect.selectByVisibleText(secCategory);
    }
    public void clickCity()
    {
        cityListBox.click();
    }
    public void selectCity(String city) {
        Select citySelect = new Select(cityListBox);
        citySelect.selectByVisibleText(city);
    }
    public void clickDayOfWeek()
    {
        dayOfWeekListBox.click();
    }
    public void selectDayOfWeek(String dayOfWeek) {
        Select dayOfWeekSelect = new Select(dayOfWeekListBox);
        dayOfWeekSelect.selectByVisibleText(dayOfWeek);
    }

    public void clickSearchButton(){
        eventSearchButton.click();
    }
}
