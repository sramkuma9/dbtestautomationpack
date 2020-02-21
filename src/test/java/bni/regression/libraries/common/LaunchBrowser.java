package bni.regression.libraries.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class LaunchBrowser {

    public static WebDriver driver;
    private ReadWritePropertyFile readWritePropertyFile = new ReadWritePropertyFile();

    public WebDriver getDriver() {
        return driver;
    }

    public void invokeBrowser() throws Exception {
        String browserType = readWritePropertyFile.loadAndReadPropertyFile("browserType", "properties/config.properties");
        String appURL = readWritePropertyFile.loadAndReadPropertyFile("baseUrl", "properties/config.properties");

        switch(browserType)
        {
            case "IE":
                driver = initInternetExplorerDriver(appURL);
                break;
            case "FIREFOX":
                driver = initFirefoxDriver(appURL);
                break;
            case "CHROME":
                driver = initChromeDriver(appURL);
                break;
            default:
                System.out.println("browser : "+ browserType + "is invalid, launching Chrome as browser of choice..");
                driver = initChromeDriver(appURL);
        }
    }

    private WebDriver initChromeDriver(String appURL) throws Exception {
        String envName = readWritePropertyFile.loadAndReadPropertyFile("bniUrl", "properties/config.properties");
        System.out.println("Executing the tests in " + envName + " environment");
        System.out.println("Launching google chrome with new profile..");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);
        driver.get(appURL);
        TimeUnit.SECONDS.sleep(5);
        return driver;
    }

    private static WebDriver initInternetExplorerDriver(String appURL) throws Exception{
        System.out.println("Launching google chrome with new profile..");
        System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/ie.exe");
        System.out.println("Launching Internet browser..");
        WebDriver driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        TimeUnit.SECONDS.sleep(5);
        return driver;
    }

    private static WebDriver initFirefoxDriver(String appURL) throws Exception{
        System.out.println("Launching Firefox browser..");
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        TimeUnit.SECONDS.sleep(5);
        return driver;
    }
}