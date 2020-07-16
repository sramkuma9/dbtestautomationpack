package bni.regression.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MemberRenewalApplicationTest2 {
    public static WebDriver driver;
    public WebDriverWait wait;

    @FindBy(xpath="//*[@id='12']/span")
    WebElement membershipPeriodCheckBox1;

    @FindBy(xpath="//*[@id='24']/span")
    WebElement membershipPeriodCheckBox2;

    @FindBy(xpath="//*[@id='membershipCosts']/tbody/tr[3]/td[2]")
    WebElement membershipFees;

    @FindBy(xpath="//*[@id='membershipCosts']/tbody/tr[3]/td[3]")
    WebElement membershipTaxes;

    @FindBy(xpath="/html/body/div[2]/div[1]/div[4]/div[2]/form/div[1]/div[4]/ul/li[1]/div/span/label[2]/input")
     WebElement networkingOrgCheckBox;


     @FindBy(xpath="/html/body/div[2]/div[1]/div[4]/div[2]/form/div[1]/div[4]/ul/li[3]/div/span/label[1]/input")
    WebElement invitePeopleCheckBox;


    @FindBy(xpath="/html/body/div[2]/div[1]/div[4]/div[2]/form/div[1]/div[4]/ul/li[4]/div/span/label[2]/input")
    WebElement referralCheckBox;


    @FindBy(xpath="/html/body/div[2]/div[1]/div[4]/div[2]/form/div[1]/div[4]/ul/li[5]/div/span/label[2]/input")
     WebElement convictedCheckBox;

    @FindBy(css="li.cf:nth-child(7) > div:nth-child(2) > span:nth-child(1) > input")
    WebElement typeQuestion;

             @FindBy(css="#\\32 79 > input[type=CHECKBOX]")

             WebElement licenceCheckBox;

             @FindBy(css="#\\32 82 > input[type=CHECKBOX]")
             WebElement licenceStatus;


       @FindBy(xpath="/html/body/div[2]/div[1]/div[4]/div[2]/form/div[1]/div[4]/ul/li[6]/div/span/label[4]/input")
     WebElement leadershipPositionCheckBox;


    @FindBy(xpath="/html/body/div[2]/div[1]/div[4]/div[2]/form/div[1]/div[4]/ul/li[7]/div/span/label[1]/input")
    WebElement recommendCheckBox;


    @FindBy(xpath ="/html/body/div[2]/div[1]/div[4]/div[2]/form/div[1]/div[4]/ul/li[8]/div/span/label[1]/input")
    WebElement referralChapterCheckBox;

    @FindBy(css = "#productservicedescription")
    WebElement describeBNITextBox;

    @FindBy(css="li.cf:nth-child(9) > div:nth-child(2) > span:nth-child(1) > textarea")
    WebElement describeTextBox;

    @FindBy(css = "#\\35 95 > span")
    WebElement leadershipTeamCheckBox;

    @FindBy(css = "#agreeToTermsAndConditions")
    WebElement termsAndCOnditionsCheckBox;

    @FindBy(css = "#submitProceedPayment")
    WebElement proceedToPayment;

    @FindBy(css=".ui-dialog-buttonpane > button:nth-child(2)")
    WebElement clickExtraCloseButton;

@FindBy(css="#profession")
WebElement professionListBox;

@FindBy(css="#speciality")
WebElement  specialityListBox;

    public MemberRenewalApplicationTest2(WebDriver driver) {
        MemberRenewalApplication.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 100);
        //This initElements method will create all WebElements
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void clickMembershipPeriodCheckBox(String membershipPeriod) {

       if (membershipPeriod=="12 Month")
        {
            membershipPeriodCheckBox1.click();
        }
        if (membershipPeriod=="One Year Membership ")
        {
            membershipPeriodCheckBox1.click();
        }
        if(membershipPeriod=="Two Year Membership ") {
            membershipPeriodCheckBox2.click();
        }
        else {
            membershipPeriodCheckBox2.click();
        }
    }


    public void getMembershipFee() {

        String membershipFeeForTheGivenTerm2 = membershipFees.getText();
        System.out.println("membership term  Fee from UI is " + membershipFeeForTheGivenTerm2);
//
//        WebElement table =      driver.findElement(By.xpath(“#membershipCosts > tbody > tr.period> td:nth-child(2)”));
//                List<WebElement> rowsList = table.findElements(By.tagName(“td”));
//
//        List<WebElement> columnsList = null;
//
//        for (WebElement row : rowsList) {
//            System.out.println();
//            columnsList = row.findElements(By.tagName(“td”));
//
//            for (WebElement column : columnsList) {
//                System.out.print(column.getText() + “, “);
//            }

    }
    public String getTaxForMembershipTerm()
    {
        String taxFetchForMem = membershipTaxes.getText();
        System.out.println("Tax for the  membership term  from UI is " +taxFetchForMem);
        return  taxFetchForMem;

    }
    public void clickNetworkingOrgCheckBox() {
        networkingOrgCheckBox.click();
    }

    public void clickInvitePeopleCheckBox() {
        invitePeopleCheckBox.click();
    }

    public void clickReferralCheckBox() {
        referralCheckBox.click();
    }
    public void setTypeQuestion(String question)
    {
        typeQuestion.sendKeys(question);
    }

    public void clickConvictedCheckBox() {
        convictedCheckBox.click();
    }
    public void clickLicence()
    {
        licenceCheckBox.click();
    }

    public void clickLicenceStatus()
    {
        licenceStatus.click();
    }
    public void clickLeadershipPosition() { leadershipPositionCheckBox.click();}
    public void clickRecommendCheckBox() {
        recommendCheckBox.click();
    }

    public void clickrefferalChapterCheckBox() {
        referralChapterCheckBox.click();
    }



    public void enterProfession(String profession)
    {
      Select professionSelect = new Select(professionListBox);
      professionSelect.selectByVisibleText(profession);
    }

    public void enterSpeciality(String speciality)
    {

        Select specialitySelect = new Select (specialityListBox);
        specialitySelect.selectByVisibleText(speciality);
    }
    public void clickleadershipTeamCheckBox() {
        leadershipTeamCheckBox.click();
    }

    public void clickTermsAndConditionsCheckBox() {
        termsAndCOnditionsCheckBox.click();
    }

    public void clickProceedToPayment() {
        proceedToPayment.click();
    }

    public void enterDescribeBNI(){
        describeTextBox.sendKeys("Test data");
    }

    public void enterProductDescribeBNI(){
        describeBNITextBox.sendKeys("おはようございます");
    }

}
