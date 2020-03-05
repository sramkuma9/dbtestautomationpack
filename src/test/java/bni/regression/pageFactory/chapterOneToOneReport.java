package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

import java.util.List;

public class chapterOneToOneReport {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(css = "#__bookmark_3 > tbody:nth-child(2) > tr")
    List<WebElement> getSearchResults;


    public chapterOneToOneReport(WebDriver driver) {
        chapterOneToOneReport.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public Integer getSearchResults(int slipsCount)   {
                  slipsCount=0;
driver.switchTo().frame(0);
        WebElement table = driver.findElement(By.cssSelector("#__bookmark_3 > tbody:nth-child(2) > tr"));

        List<WebElement> row = table.findElements(By.tagName("tr"));

        System.out.println("Total Number of Rows = " + row.size());


        if (slipsCount == row.size())
            {
            System.out.println("Slips count ");
        }
        return slipsCount;
    }

}




        /*

        for (WebElement trElement : getSearchResults) {
            List<WebElement> td_collection = trElement.findElements(By.tagName("tr"));
            int c = td_collection.size();
            System.out.println("rowSize" + c);

        }
        return slipCount;
    }
}

         */
//}
//
//          public Integer getSearchResults() throws Exception {
//
//              for (WebElement trElement : getSearchResults) {
//                  List<WebElement> td_collection = trElement.findElements(By.tagName("tr"));
//                  int c = td_collection.size();
//                  System.out.println("rowSize" + c);
////                                                                                                    Integer recordCount = 0;
//            }                                                                                       WebElement table = driver.findElement(By.xpath("//table[@id='__bookmark_3]"));
//           return 0;                                                                                List<WebElement> row = table.findElements(By.tagName("tr"));
//        }                                                                                           System.out.println("Total Number of Rows = " + row.size());
//           return row.size();
      /*  String [] addSlipDetails = new String [8];
        for(WebElement trElement : getSearchResults)
        {
            List<WebElement> td_collection=trElement.findElements(By.tagName("td"));
            String type = td_collection.get(1).getText();
            if (type.equals("Pending Membership")) {
                addSlipDetails[0] = td_collection.get(0).getText();
                addSlipDetails[1] = td_collection.get(2).getText();
                addSlipDetails[2] = td_collection.get(3).getText();
                addSlipDetails[3] = td_collection.get(5).getText();
                addSlipDetails[4] = td_collection.get(6).getText();
                addSlipDetails[5] = td_collection.get(4).getText();
            }

       */


        //  Integer expRecordCount = 4;
        //  assertEquals("Search All individuals result does not have 4 records", expRecordCount, recordCount );
       //























































    //
    //
    // return addSlipDetails;




//    public Integer[] getSearchResults() throws Exception {
//
//
//        for (WebElement trElement : getSearchResults) {
//            List<WebElement> td_collection = trElement.findElements(By.tagName("tr"));
//            int c = td_collection.size();
//        }
//









































//        Integer recordCount = 0;
//        WebElement table = (WebElement) driver.findElements(By.cssSelector("#__bookmark_3"));
//      //  WebElement table = driver.findElement(By.cssSelector("#__bookmark_3 > tbody:nth-child(2) > tr"));
// html > body.connect > div >div > table > tbody > tr > td > table#__bookmark_3
//        List<WebElement> row = table.findElements(By.tagName("tr"));
//        System.out.println("Total Number of Rows = " + row.size());
        /*

     //   List<WebElement> allRows = driver.findElements(By.xpath("html/body.connect/div/div/table/tbody/tr/td/table/#__bookmark_3"));
        List<WebElement> allRows = driver.findElements(By.xpath(" html\\body\\div\\div[2]\\table\\tbody\\tr[2]\\td\\table[3]\\tbody\\tr[15]"));
// And iterate over them and get all the cells
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Print the contents of each cell
            for (WebElement cell : cells) {

                System.out.println(cell.getText());



            }
        }
    }

         */

