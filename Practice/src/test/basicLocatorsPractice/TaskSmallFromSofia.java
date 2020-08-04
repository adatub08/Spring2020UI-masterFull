package basicLocatorsPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TaskSmallFromSofia {
    @Test
    public void test1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.practiceselenium.com/");
        driver.findElement(By.partialLinkText("let's Talk")).click();
        Thread.sleep(3000);
        WebElement nameInput = driver.findElement(By.name("name"));

        Assert.assertTrue(nameInput.isDisplayed());
        //driver.close();


    }

    @Test
    public void test2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.amazon.com/");
        driver.findElement(By.linkText("Sell")).click();
        Thread.sleep(4000);
        WebElement title = driver.findElement(By.tagName("h1"));
        Assert.assertTrue(title.isDisplayed());
        driver.findElement(By.linkText("Sign up")).click();
        Thread.sleep(4000);
        WebElement emailInput = driver.findElement(By.id("ap_email"));
        WebElement passwordInputField = driver.findElement(By.id("ap_password"));
        WebElement nextButton = driver.findElement(By.id("signInSubmit"));

        emailInput.sendKeys("invalidEmail");
        passwordInputField.sendKeys("pass123");
        nextButton.click();
        Thread.sleep(2000);
        WebElement errorMessage = driver.findElement(By.tagName("h4"));
        Assert.assertTrue(errorMessage.isDisplayed());
        driver.close();


    }

    @Test
    public void test3() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.practiceselenium.com/");
        Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

    }

    @Test
    public void test4() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.practiceselenium.com/");
        driver.findElement(By.linkText("Check Out")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Cancel")).click();


        driver.close();
    }
}
