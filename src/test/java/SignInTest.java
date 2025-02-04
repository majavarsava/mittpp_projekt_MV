import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest {
    // WebDriver instance
    public WebDriver driver;
    // Test URL
    public String testURL = "https://petstore.octoperf.com/actions/Catalog.action";

    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(testURL);
    }

    @Test
    public void signInTest() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Step 1: Click the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        Thread.sleep(2000);

        // Step 2: Fill out the sign-in form
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));

        // Enter credentials
        usernameField.clear();
        usernameField.sendKeys("mittpp1");
        passwordField.clear();
        passwordField.sendKeys("mittpp1");

        // Step 3: Click the "Login" button
        WebElement loginButton = driver.findElement(By.name("signon"));
        loginButton.click();
        Thread.sleep(3000);

        // Step 4: Verify that the user is redirected to the main page
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, testURL, "User was not redirected to the main page after signing in!");

        // Step 5: Verify that the user is logged in by checking for the "Sign Out" link
        WebElement signOutLink = driver.findElement(By.linkText("Sign Out"));
        Assert.assertTrue(signOutLink.isDisplayed(), "User is not logged in!");

        // Optional: Click "My Account" to verify further
        WebElement myAccountLink = driver.findElement(By.linkText("My Account"));
        myAccountLink.click();
        Thread.sleep(2000);

        // Verify that the "My Account" page is displayed
        WebElement myAccountHeader = driver.findElement(By.xpath("//h3[contains(text(), 'User Information')]"));
        Assert.assertTrue(myAccountHeader.isDisplayed(), "My Account page is not displayed!");
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}