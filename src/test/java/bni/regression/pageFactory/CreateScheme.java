package bni.regression.pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateScheme {


    public static WebDriver driver;
    public WebDriverWait wait;


    @FindBy(xpath = "//*[@class='euiIcon euiIcon--medium euiIcon-isLoaded euiContextMenu__icon']")
    WebElement checkIcon;

    @FindBy(xpath = "/html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[1]/dl/dd/div/div/div/div/button")
    WebElement productListBox;

    @FindBy(xpath = "//div[@class='euiSuperSelect__listbox']")
    WebElement listBox;

    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/div[2]/div/button")
    List<WebElement> productSelect;


    @FindBy(xpath = "/html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[4]/dl/dd/div/div/div/div/button")
    WebElement templateListBox;

    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/div[2]/div/button")
    List<WebElement> templateValuesFromList;

    @FindBy(xpath = "/html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[2]/dl/dd/div/div/div/div/button")
    WebElement SKUListBox;

    @FindBy(xpath = "/html/body/div[2]/div/div[3]/div/div[2]/div/button")
    List<WebElement> SKUValues;

    @FindBy(xpath = "//*[@id='root']/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[1]/div/input")
    WebElement effectiveDateField;

    @FindBy(xpath = "//span[@class='react-datepicker__month-read-view--selected-month']")
    WebElement currentMonth;

    @FindBy(xpath = "//span[@class='react-datepicker__year-read-view--selected-year']")
    WebElement currentYear;

    @FindBy(xpath = "/html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div[1]/div[1]/div/div")
    List<WebElement> effectiveMonth;

    @FindBy(xpath = " /html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[2]/div/div[2]/div/div[2]/div/div" )
     List<WebElement> datePicker;

    @FindBy(xpath = "/html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div")
    List<WebElement> effectiveYear;

    @FindBy(css = "div.euiFormControlLayout__childrenWrapper:nth-child(2) > input")
    WebElement minFeesTextBox;

    @FindBy(css = "div.euiFormControlLayout--group:nth-child(2) > div:nth-child(1) > input")
    WebElement maxFeesTextBox;

    @FindBy(xpath = "//*[@placeholder='Enter Price']")
    WebElement priceHolder;

    @FindBy(xpath = "/html/body/div/div[1]/main/div[2]/div/div[2]/div[2]/div[3]/div/div[2]/div/div/div[2]/div[2]/div/div")
    WebElement individualTypeElements;

    @FindBy(xpath="//*[@id='root']/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[1]/div/input")
    WebElement dateBox;

    //@FindBy(xpath = "/html/body/div/div[1]/main/div[2]/div/div[2]/div[2]/div[3]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/div/input")
    @FindBy(xpath ="//*[@id='range-0']")
    WebElement memberPriceTextBox;

    @FindBy(xpath = "//*[@id='disabled0']")
    WebElement memberPriceDisabledBox;

    @FindBy(xpath = "//*[@id='range-1']")
    WebElement alumniPriceTextBox;

    @FindBy(xpath = "//*[@id='range-2']")
    WebElement visitorPriceTextBox;

    @FindBy(xpath = "//*[@id='range-3']")
    WebElement defaultPriceTextBox;

    @FindBy(xpath ="//*[@id='range-0']")
    WebElement chapterActiveTextBox;

    @FindBy(xpath = "//*[@id='range-1']")
    WebElement chapterCoreGroupTextBox;

    @FindBy(xpath = "//*[@id='range-2']")
    WebElement chapterSuspendedTextBox;

    @FindBy(xpath = "//*[@id='range-3']")
    WebElement defaultChapterTextBox;

@FindBy(xpath ="//*[@id='root']/div[1]/main/div[2]/div/div[2]/div[1]/div/div[5]/dl/dd/div/label")
WebElement taxableCheckBox;

    @FindBy(xpath ="//*[@id='range-0']")
    WebElement memberActiveTextBox;

   @FindBy(xpath = "//*[@id='range-1']")
    WebElement memberCGTextBox;

    @FindBy(xpath = "//*[@id='range-2']")
    WebElement memberSusTextBox;

    @FindBy(xpath = "//*[@id='range-3']")
    WebElement alumniActiveTextBox;

    @FindBy(xpath = "//*[@id='range-4']")
    WebElement alumniCGTextBox;

    @FindBy(xpath = "//*[@id='range-5']")
    WebElement alumniSusTextBox;

    @FindBy(xpath = "//*[@id='range-6']")
    WebElement visitorActiveTextBox;

    @FindBy(xpath = "//*[@id='range-7']")
    WebElement visitorCGTextBox;

    @FindBy(xpath = "//*[@id='range-8']")
    WebElement visitorSusTextBox;

    @FindBy(xpath = "//*[@id='range-9']")
    WebElement defaultPriceForChapAndIndiTextBox;

    @FindBy(xpath="/html/body/div/div[1]/main/div[2]/div/div[2]/div[3]/div/div[2]/button/span/span")
    WebElement createSchemeButton;

    @FindBy(xpath ="//*[@id='range-0']")
    WebElement organizationPriceTextBox;
    @FindBy(xpath ="//*[@id='disabled0']")
    WebElement disabledCheckBox1;
    @FindBy(xpath ="//*[@id='disabled1']")
    WebElement disabledCheckBox2;
    @FindBy(xpath ="//*[@id='disabled2']")
    WebElement disabledCheckBox3;
    @FindBy(xpath ="//*[@id='disabled3']")
    WebElement disabledCheckBox4;
    @FindBy(xpath ="//*[@id='disabled4']")
    WebElement disabledCheckBox5;
    @FindBy(xpath ="//*[@id='disabled5']")
    WebElement disabledCheckBox6;
    @FindBy(xpath ="//*[@id='disabled6']")
    WebElement disabledCheckBox7;
    @FindBy(xpath ="//*[@id='disabled7']")
    WebElement disabledCheckBox8;
    @FindBy(xpath ="//*[@id='disabled8']")
    WebElement disabledCheckBox9;

    public CreateScheme(WebDriver driver) {
        CreateScheme.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }


    public void selectProductFrom(String productFrom) throws Exception {

        Actions action = new Actions(driver);
        action.clickAndHold(listBox);
        productListBox.sendKeys(Keys.ARROW_DOWN);
        for (WebElement buttonElement3 : productSelect) {
            String ProductValueFrom = buttonElement3.getText();
            System.out.println("ProductValueFrom is " + ProductValueFrom + ":" + ProductValueFrom.contains(productFrom));
            TimeUnit.SECONDS.sleep(1);
            if (ProductValueFrom.contains(productFrom)) {
                buttonElement3.click();
                break;

            }
        }
    }

    public void selectTemplate(String template) throws Exception {
        Actions action = new Actions(driver);
        action.clickAndHold(listBox);
        templateListBox.sendKeys(Keys.ARROW_DOWN);
        TimeUnit.SECONDS.sleep(2);
        System.out.println("template listbox clicked");
        for (WebElement buttonElement : templateValuesFromList) {
            String templateValueFrom = buttonElement.getText();
            System.out.println("templateValueFrom is " + templateValueFrom + ":" + templateValueFrom.equals(template));
            System.out.println("templateValueFrom is " + templateValueFrom);
            if (templateValueFrom.startsWith(template)) {
                buttonElement.click();

                TimeUnit.SECONDS.sleep(4);
                break;
            }
        }
    }

    public void selectSKU(String sku) throws Exception {
        Actions action = new Actions(driver);
        action.clickAndHold(listBox);
        SKUListBox.sendKeys(Keys.ARROW_DOWN);
        TimeUnit.SECONDS.sleep(2);
        for (WebElement buttonElement2 : SKUValues) {
            String SKUValueFrom = buttonElement2.getText();
            System.out.println("SKU VAlue is" + SKUValueFrom);
            System.out.println("SKUValueFrom Listbox is " + SKUValueFrom + ":" + SKUValueFrom.equals(sku));

            if (SKUValueFrom.contains(sku)) {
                buttonElement2.click();
                TimeUnit.SECONDS.sleep(2);
                break;
            }
        }
    }

    public void clickEffectiveDateField() {
        effectiveDateField.click();
    }




          public void selectDateFromDatePicker(String day) throws Exception {
              Actions action = new Actions(driver);
              action.clickAndHold(dateBox);
              for (WebElement dayElement : datePicker) {
                  String calendarDay = dayElement.getText();
                  System.out.println("Day element is" +dayElement);
                 // String calendarDayCSSValue =dayElement.getCssValue(day);
String classElement = dayElement.getAttribute("class");
//System.out.println("value of class name:" +classElement );
if (!classElement.contains("outside-month")){
   // System.out.println("Calendar Day selected is " + calendarDay + ":" + calendarDay.contains(day));
                  //System.out.println("Calendar Day CSS is " + calendarDay + ":" + calendarDayCSSValue.contains(day));
                  TimeUnit.SECONDS.sleep(1);

                      if (calendarDay.contains(day)) {
                          dateBox.sendKeys(Keys.ARROW_DOWN);
                          if (calendarDay.contains(day)) {
                              WebDriverWait wait = new WebDriverWait(driver, 5);
                              wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[2]/div/div[2]/div/div[2]/div/div"))));
                              dayElement.click();
                              System.out.println("Inside enabled loop");
                              TimeUnit.SECONDS.sleep(3);
                              break;
                          }
                      }
                  }
                  }
              }




        /*
         public void selectDateFromDatePicker(String day) throws Exception {

        for (WebElement dayElement : datePicker) {
            String calendarDay = dayElement.getText();
            System.out.println("Calendar Day selected is " + calendarDay + ":" + calendarDay.contains(day));
            TimeUnit.SECONDS.sleep(1);
            if (calendarDay.contains(day)) {
                System.out.println("enabled day is " + dayElement.isEnabled());
               if (calendarDay.contains(day)) {
                        WebDriverWait wait = new WebDriverWait(driver, 5);
                        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div/div[1]/main/div[2]/div/div[2]/div[1]/div/div[3]/dl/dd/div/span/span/div/div/div[2]/div/div[2]/div/div[2]/div/div"))));
                        dayElement.click();
                        System.out.println("Inside enabled loop");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    }


                }
            }

        }
         */


    public void selectMonth(String month) throws Exception {
        currentMonth.click();
        for (WebElement monthElement : effectiveMonth) {
            String calendarMonth = monthElement.getText();
            System.out.println("Calendar Month selected is " + calendarMonth + ":" + calendarMonth.contains(month));
            TimeUnit.SECONDS.sleep(1);
            if (calendarMonth.contains(month)) {
                monthElement.click();
                TimeUnit.SECONDS.sleep(3);
                break;

            }
        }
    }

    public void selectYear(String year) throws Exception {
        currentYear.click();
        for (WebElement yearElement : effectiveYear) {
            String calendarYear = yearElement.getText();
            System.out.println("Calendar Year selected is " + calendarYear + ":" + calendarYear.contains(year));
            TimeUnit.SECONDS.sleep(1);
            if (calendarYear.contains(year)) {
                yearElement.click();
                TimeUnit.SECONDS.sleep(3);
                break;

            }
        }
    }

    public void enterMinimumFees(String minimumFees) {
        minFeesTextBox.sendKeys(minimumFees);

    }

    public void enterMaximumFees(String maximumFees) {
        maxFeesTextBox.sendKeys(maximumFees);

    }

    public void  enterMemberPrice(String memberPrice) {
       Actions action = new Actions(driver);
        action.moveToElement(memberPriceTextBox).click().click().click().click().sendKeys(memberPrice).build().perform();

    }

    public void enterAlumniPrice(String alumniPrice) {
         Actions action = new Actions(driver);
        action.moveToElement(alumniPriceTextBox).click().click().click().click().sendKeys(alumniPrice).build().perform();

    }

    public void enterVisitorPrice(String visitorPrice) {
        Actions action = new Actions(driver);
        action.moveToElement(visitorPriceTextBox).click().click().click().click().sendKeys(visitorPrice).build().perform();


    }

    public void enterDefaultPrice(String defaultPrice) {

        Actions action = new Actions(driver);
        action.moveToElement(defaultPriceTextBox).click().click().click().click().sendKeys(defaultPrice).build().perform();

    }


    //////
    public void  enterChapterActivePrice(String activeChapterPrice) {
        Actions action = new Actions(driver);
        action.moveToElement(chapterActiveTextBox).click().click().click().click().sendKeys(activeChapterPrice).build().perform();

    }

    public void enterChapterCoreGroupPrice(String chapterCGPrice) {
        Actions action = new Actions(driver);
        action.moveToElement(chapterCoreGroupTextBox).click().click().click().click().sendKeys(chapterCGPrice).build().perform();

    }

    public void enterSuspendedChapterPrice(String suspenededChapPrice) {
        Actions action = new Actions(driver);
        action.moveToElement(chapterSuspendedTextBox).click().click().click().click().sendKeys(suspenededChapPrice).build().perform();


    }

    public void enterChapterDefaultPrice(String defaultChapterPrice) {

        Actions action = new Actions(driver);
        action.moveToElement(defaultChapterTextBox).click().click().click().click().sendKeys(defaultChapterPrice).build().perform();

    }


    ////

    public void  enterMemberActivePrice(String memberActive) {
        Actions action = new Actions(driver);
        action.moveToElement(memberActiveTextBox).click().click().click().click().sendKeys(memberActive).build().perform();

    }

    public void enterMemberCGPrice(String memberCG) {
        Actions action = new Actions(driver);
        action.moveToElement(memberCGTextBox).click().click().click().click().sendKeys(memberCG).build().perform();

    }

    public void enterMemberSuspendedPrice(String memberSuspended) {
        Actions action = new Actions(driver);
        action.moveToElement(memberSusTextBox).click().click().click().click().sendKeys(memberSuspended).build().perform();


    }

    public void enterAlumniActivePrice(String alumniActive) {

        Actions action = new Actions(driver);
        action.moveToElement(alumniActiveTextBox).click().click().click().click().sendKeys(alumniActive).build().perform();

    }
    public void  enterAlumniCGPrice(String alumniCG) {
        Actions action = new Actions(driver);
        action.moveToElement(alumniCGTextBox).click().click().click().click().sendKeys(alumniCG).build().perform();

    }

    public void enterAlumniSuspendedPrice(String alumniSuspended) {
        Actions action = new Actions(driver);
        action.moveToElement(alumniSusTextBox).click().click().click().click().sendKeys(alumniSuspended).build().perform();

    }

    public void enterVisitorActivePrice(String visitorActive) {
        Actions action = new Actions(driver);
        action.moveToElement(visitorActiveTextBox).click().click().click().click().sendKeys(visitorActive).build().perform();


    }
    public void selectTaxableCheckBox(String taxable)
    {
        if (taxable.equals("Yes"))
        {
            taxableCheckBox.click();
        }

    }

    public void enterVisitorCGPrice(String visitorCG) {

        Actions action = new Actions(driver);
        action.moveToElement(visitorCGTextBox).click().click().click().click().sendKeys(visitorCG).build().perform();

    }
    public void  enterVisitorSuspendedPrice(String visitorSuspended) {
        Actions action = new Actions(driver);
        action.moveToElement(visitorSusTextBox).click().click().click().click().sendKeys(visitorSuspended).build().perform();

    }
    public void  enterDefaultPriceForChapAndIndi(String defaultPrice) {
        Actions action = new Actions(driver);
        action.moveToElement(defaultPriceForChapAndIndiTextBox).click().click().click().click().sendKeys(defaultPrice).build().perform();

    }


    public void clickCreateSchemeButton()
    {
        createSchemeButton.click();
    }

    public void enterPriceForOrganizationTemplate(String orgPrice)
    {
        Actions action = new Actions(driver);
        action.moveToElement(organizationPriceTextBox).click().click().click().click().sendKeys(orgPrice).build().perform();

    }

    public void clickDisabledCheckBox1()
    {
        disabledCheckBox1.click();
    }
    public void clickDisabledCheckBox2()
    {
        disabledCheckBox2.click();
    }
    public void clickDisabledCheckBox3()
    {
        disabledCheckBox3.click();
    }
    public void clickDisabledCheckBox4()
    {
        disabledCheckBox4.click();
    }
    public void clickDisabledCheckBox5()
    {
        disabledCheckBox5.click();
    }
    public void clickDisabledCheckBox6()
    {
        disabledCheckBox6.click();
    }
    public void clickDisabledCheckBox7()
    {
        disabledCheckBox7.click();
    }
    public void clickDisabledCheckBox8()
    {
        disabledCheckBox8.click();
    }
    public void clickDisabledCheckBox9()
    {
        disabledCheckBox9.click();
    }
}
