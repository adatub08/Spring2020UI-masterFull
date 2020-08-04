package basicLocatorsPractice;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Locators {
    @Test
    public void test1() throws InterruptedException {
        //to set up the driver instead system.setProperty we do this:
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
       //gi driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.ru/");

        WebElement searchInputField = driver.findElement(By.name("q"));
        String searchCriteria = "apple";
        searchInputField.sendKeys(searchCriteria);
        driver.findElement(By.name("btnK")).click();
        Thread.sleep(5000);
        Assert.assertTrue("The title doesn't contain the search criteria. Expected: " + searchCriteria + ". Actual: " + driver.getTitle(), driver.getTitle().contains(searchCriteria));

        driver.close();
    }

    @Test
    public void test2() throws InterruptedException{
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.google.com");
        driver.findElement(By.linkText("Gmail")).click();
        Thread.sleep(3000);
        WebElement createAnAccountButton =  driver.findElement(By.partialLinkText("Create an"));

        Assert.assertTrue(createAnAccountButton.isDisplayed());
        driver.close();

    }
    @Test
    public void test3()throws InterruptedException{
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://demostore.x-cart.com/");
        driver.findElement(By.linkText("Contact us")).click();
        Thread.sleep(3000);
        WebElement headind = driver.findElement(By.tagName("h1"));

        Assert.assertTrue(headind.isDisplayed());
        driver.close();
    }
    @Test
    public void test4() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.salesforce.com/");
//        driver.findElement(By.cssSelector("#products_menu_item > button")).click();
//        driver.findElement(By.xpath("#drawer_products > div > div > div:nth-child(1) > ul > li > div > ul > li:nth-child(1) > a > span")).click();
        //#drawer_products > div > div > div:nth-child(1) > ul > li > div > ul > li:nth-child(1) > a > span
        ////*[@id="drawer_products"]/div/div/div[1]/ul/li/div/ul/li[1]/a/span
        driver.findElement(By.xpath("//*[@id='globalnavbar-header-container']/div[2]/div/div[5]/div/div/a")).click();
        driver.findElement(By.id("username")).sendKeys("Hello");
        Thread.sleep(3000);
        driver.findElement(By.id("password")).sendKeys("pa$$1234");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#Login")).click();
        WebElement errorMessageToLogin = driver.findElement(By.cssSelector("#error"));
        System.out.println(errorMessageToLogin.getText());
        Assert.assertTrue(errorMessageToLogin.isDisplayed());

        driver.close();

    }

    @Test
    public void test5()throws InterruptedException{

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("Hello");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("phone number");
        //driver.findElement(By.xpath("//input[@id='u_0_b']")).click();
        //"//*[@id='login_form']/table/tbody/tr[3]/td[2]/div/a"
       // driver.findElement(By.xpath("//*[@id='login_form']/table/tbody/tr[3]/td[2]/div/a")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='month']")).click();
        driver.close();


    }

}
