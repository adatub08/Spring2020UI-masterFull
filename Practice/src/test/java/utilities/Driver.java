package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Driver {
    //the goal is to make sure that there is a Single driver running
    //2nd method will close the driver if it is running
    //SINGLETON pattern - this means creating a class that ONLY one instance
    // of the object can be exist at a time
    private Driver() {
    }

    ;
    private static WebDriver driver;

    //will not allow anyone to create a driver object directly
    //1st method for creating a driver depending on browser specified in config.properties
    public static WebDriver getDriver() {

        if (driver == null) {
            //we will create a new driver instance here and assign it to our driver variable
            switch (ConfigsReader.getProperty("browser").toLowerCase()) {
                case "chrome":
                    driver =ChromeWebDriver.loadChromeDriver(Boolean.parseBoolean(ConfigsReader.getProperty("headless")));
                    break;
                case "firefox":
                   driver = FirefoxWebDriver.loadFireFoxDriver(Boolean.parseBoolean(ConfigsReader.getProperty("headless")));
                    break;
                case "safari":
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
                    break;
                default:
                    driver =ChromeWebDriver.loadChromeDriver(Boolean.parseBoolean(ConfigsReader.getProperty("headless")));
                    break;

            }
        }
        return driver;

    }

    public static void closeDriver() {
        if (driver == null) return;

        try {
            driver.close();
            driver.quit();
            driver = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

