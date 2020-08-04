package Steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MeetupRegistrationSteps {
    WebDriver driver;
    String mainWindow;
    String secondWindow;
    String thirdWindow;
    String fourthWindow;


    @Given("^the user goes to a meetup page$")
    public void the_user_goes_to_a_meetup_page() throws Throwable {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.meetup.com/");

    }

    @Given("^the user clicks on \"([^\"]*)\"$")
    public void the_user_clicks_on(String JoinMeetup) throws Throwable {
        driver.findElement(By.xpath("//a[@aria-label='Join Meetup']")).click();
        //clicks on join button

    }

    @Then("^the user should be provided with options of registration \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void the_user_should_be_provided_with_options_of_registration(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        WebElement facebookAuthButton = driver.findElement(By.xpath("//a[@id='facebookAuthButton']"));
        Assert.assertTrue(facebookAuthButton.isDisplayed());
        WebElement googleAuthButton = driver.findElement(By.xpath("//a[@id='googleAuthButton']"));
        Assert.assertTrue(googleAuthButton.isDisplayed());
        WebElement appleAuthButton = driver.findElement(By.xpath("//form[@id='register-form--creds']//a[@id='appleAuthButton']"));
        Assert.assertTrue(appleAuthButton.isDisplayed());
        WebElement authWithEmail = driver.findElement(By.xpath("//p[@class='runningText']//a[@id='register-trigger--withEmail']"));
        Assert.assertTrue(authWithEmail.isDisplayed());
        mainWindow = driver.getWindowHandle();//on the facebook apple auth page
    }

    @When("^the user selects \"([^\"]*)\"$")
    public void the_user_selects(String arg1) throws Throwable {
        driver.findElement(By.xpath("//p[@class='runningText']//a[@id='register-trigger--withEmail']")).click();
        Set<String> allOpenWindows = driver.getWindowHandles();
        for (String windowHandle : allOpenWindows) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);//redirects to register with email page
                secondWindow = driver.getWindowHandle();
            }
        }

    }

    @When("^the user enters name, email, password$")
    public void the_user_enters_name_email_password() throws Throwable {
        driver.findElement(By.xpath("//input[@id='register-field--name']")).sendKeys("BahaBahaBaha");
        driver.findElement(By.xpath("//input[@id='register-field--email']")).sendKeys("someemail@gmail.com");
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='register-field--password']"))).sendKeys("Iskander2010");
        // driver.findElement(By.xpath("//input[@id='register-field--password']")).sendKeys("Iskander2010");
       // Thread.sleep(4000);

    }

    @When("^the user clicks continue$")
    public void the_user_clicks_continue() throws Throwable {
//        WebElement iFrame = driver.findElement(By.xpath("//div[@class='rc-anchor rc-anchor-normal rc-anchor-light']"));
//        driver.switchTo().frame(iFrame);
//        // Now can click on checkbox of reCaptcha now.
//
//        WebElement iFrame_checkbox =
//                driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']"));
//        iFrame_checkbox.click();
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='button button--primary button--fullWidth j-next-panel']"))).click();
        // driver.findElement(By.xpath("//button[@class='button button--primary button--fullWidth j-next-panel']")).click();
       // Thread.sleep(3000);
        Set<String> allOpenWindows = driver.getWindowHandles();
        for (String windowHandle : allOpenWindows) {
            if (!windowHandle.equals(mainWindow) && !windowHandle.equals(secondWindow)) {
                driver.switchTo().window(windowHandle);
                thirdWindow = driver.getWindowHandle();
            }
        }
    }

    @Then("^the user should see email adress$")
    public void the_user_should_see_email_adress() throws Throwable {
        WebElement someemail = driver.findElement(By.xpath("//strong[contains(text(),'someemail@gmail.com')]"));
        Assert.assertTrue(someemail.getText().contains("someemail@gmail.com"));
    }

    @Then("^the user should verify the email in the message matches the email used to register$")
    public void the_user_should_verify_the_email_in_the_message_matches_the_email_used_to_register() throws Throwable {
    }


}
