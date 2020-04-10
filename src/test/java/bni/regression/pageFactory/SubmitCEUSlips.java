package bni.regression.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubmitCEUSlips {


        public static WebDriver driver;
        public WebDriverWait wait;

        @FindBy(css="#editform > h1:nth-child(7) > strong")
        WebElement memberName;

       // @FindBy(css="#ceuTable > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(3) >#numberAttended_1.smallField")
       @FindBy(css="#ceuTable >  tbody > tr > td > input#numberAttended_1.smallField")
        WebElement BNICDQTY;

        @FindBy(css = "#ceuTable >  tbody > tr > td > input#numberAttended_2.smallField")
        WebElement podcastQty;

        @FindBy(css = "body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > button:nth-child(2)")
        WebElement submitButton;

        @FindBy(css="#ceuTable >  tbody > tr > td > input#numberAttended_3.smallField")
        WebElement MSPQty;

        @FindBy(css="#ceuTable >  tbody > tr > td > input#numberAttended_4.smallField")
        WebElement LTTQty;

        @FindBy(css="#ceuTable >  tbody > tr > td > input#numberAttended_5.smallField")
        WebElement advTrainingQty ;

        @FindBy(css="#ceuTable >  tbody > tr > td > input#numberAttended_6.smallField")
        WebElement otherQty;

        @FindBy(css="#ceuTable >  tbody > tr > td > input#numberAttended_7.smallField")
        WebElement bookQty;

        @FindBy(css="#ceuTable tfoot > tr > th.totalValue > span")
        WebElement total;

        @FindBy(css="#slipHeader")
        WebElement header;

        public SubmitCEUSlips(WebDriver driver) {
            bni.regression.pageFactory.SubmitCEUSlips.driver = driver;
            AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
            //This initElements method will create all WebElements
            PageFactory.initElements(factory, this);
            wait = new WebDriverWait(driver, 5);
        }

        public void enterBNICDQty(String CDQty) {
           BNICDQTY.sendKeys(CDQty);
        }

        public void enterPodcastQty(String podcast)
        {
            podcastQty.sendKeys(podcast);
        }

        public void enterMSPQty(String msp)
        {
            MSPQty.sendKeys(msp);
        }

        public void enterLTTQty(String ltt)
        {
            LTTQty.sendKeys(ltt);
        }
         public void enterTrainingQty(String training)
        {
            advTrainingQty.sendKeys(training);
          }

         public void enterOtherQty(String other)
         {
        otherQty.sendKeys(other);
         }

          public void enterBookQty(String book)
       {
        bookQty.sendKeys(book);
      }

         public void clickSubmitButton()
        {
            submitButton.click();
        }

        public Integer getTotalCount() {
            String totalCount =total.getText();
            System.out.println("Total count is" +totalCount);
            return Integer.valueOf(totalCount);

        }

        public String getMemberName()
        {
            String memName =memberName.getText();
            String[] memNameSplit = memName.split(" ");
            System.out.println("member name is" +memName);
            return  memName;

        }
         public void clickHeader()
            {
                header.click();
            }
    }





