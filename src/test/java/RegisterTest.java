import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest {
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
    public void registerTest() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Step 1: Click the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        Thread.sleep(2000);

        // Step 2: Click the "Register Now" link
        WebElement registerNowLink = driver.findElement(By.linkText("Register Now!"));
        registerNowLink.click();
        Thread.sleep(2000);

        // Step 3: Fill out the registration form
        WebElement userIdField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement confirmPasswordField = driver.findElement(By.name("repeatedPassword"));
        WebElement firstNameField = driver.findElement(By.name("account.firstName"));
        WebElement lastNameField = driver.findElement(By.name("account.lastName"));
        WebElement emailField = driver.findElement(By.name("account.email"));
        WebElement phoneField = driver.findElement(By.name("account.phone"));
        WebElement address1Field = driver.findElement(By.name("account.address1"));
        WebElement address2Field = driver.findElement(By.name("account.address2"));
        WebElement cityField = driver.findElement(By.name("account.city"));
        WebElement stateField = driver.findElement(By.name("account.state"));
        WebElement zipField = driver.findElement(By.name("account.zip"));
        WebElement countryField = driver.findElement(By.name("account.country"));

        // Fill in the form fields
        userIdField.sendKeys("mittpp3");
        passwordField.sendKeys("mittpp3");
        confirmPasswordField.sendKeys("mittpp3");
        firstNameField.sendKeys("Test");
        lastNameField.sendKeys("User");
        emailField.sendKeys("mittpp1@example.com");
        phoneField.sendKeys("1234567890");
        address1Field.sendKeys("123 Main St");
        address2Field.sendKeys("Apt 4");
        cityField.sendKeys("New York");
        stateField.sendKeys("NY");
        zipField.sendKeys("10001");
        countryField.sendKeys("USA");

        // Step 4: Click the "Save Account Information" button
        WebElement saveAccountButton = driver.findElement(By.name("newAccount"));
        saveAccountButton.click();
        Thread.sleep(3000);

        // Step 5: Verify that the user is redirected back to the original page
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, testURL, "User was not redirected to the original page after registration!");
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}