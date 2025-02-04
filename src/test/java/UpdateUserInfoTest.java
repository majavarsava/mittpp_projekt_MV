import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateUserInfoTest {
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
    public void updateUserInfoTest() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        Thread.sleep(2000);

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));

        usernameField.clear();
        usernameField.sendKeys("mittpp1");
        passwordField.clear();
        passwordField.sendKeys("mittpp1");

        WebElement loginButton = driver.findElement(By.name("signon"));
        loginButton.click();
        Thread.sleep(3000);

        WebElement signOutLink = driver.findElement(By.linkText("Sign Out"));
        Assert.assertTrue(signOutLink.isDisplayed(), "User is not logged in!");

        driver.navigate().to(testURL);
        Thread.sleep(2000);

        WebElement myAccountLink = driver.findElement(By.linkText("My Account"));
        myAccountLink.click();
        Thread.sleep(3000);

        WebElement firstName = driver.findElement(By.name("account.firstName"));
        WebElement lastName = driver.findElement(By.name("account.lastName"));
        WebElement email = driver.findElement(By.name("account.email"));
        WebElement phone = driver.findElement(By.name("account.phone"));
        WebElement address1 = driver.findElement(By.name("account.address1"));
        WebElement city = driver.findElement(By.name("account.city"));
        WebElement state = driver.findElement(By.name("account.state"));
        WebElement zip = driver.findElement(By.name("account.zip"));
        WebElement country = driver.findElement(By.name("account.country"));

        firstName.clear();
        firstName.sendKeys("Michael");
        lastName.clear();
        lastName.sendKeys("Johnson");
        email.clear();
        email.sendKeys("michael.johnson@example.com");
        phone.clear();
        phone.sendKeys("1234567890");
        address1.clear();
        address1.sendKeys("42 Elm Street");
        city.clear();
        city.sendKeys("Los Angeles");
        state.clear();
        state.sendKeys("CA");
        zip.clear();
        zip.sendKeys("90001");
        country.clear();
        country.sendKeys("USA");
        Thread.sleep(3000);

        WebElement saveButton = driver.findElement(By.name("editAccount"));
        saveButton.click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.name("account.firstName")).getAttribute("value"), "Michael", "First name did not update!");
        Assert.assertEquals(driver.findElement(By.name("account.lastName")).getAttribute("value"), "Johnson", "Last name did not update!");
        Assert.assertEquals(driver.findElement(By.name("account.email")).getAttribute("value"), "michael.johnson@example.com", "Email did not update!");
        Assert.assertEquals(driver.findElement(By.name("account.phone")).getAttribute("value"), "1234567890", "Phone number did not update!");
        Assert.assertEquals(driver.findElement(By.name("account.address1")).getAttribute("value"), "42 Elm Street", "Address did not update!");
        Assert.assertEquals(driver.findElement(By.name("account.city")).getAttribute("value"), "Los Angeles", "City did not update!");
        Assert.assertEquals(driver.findElement(By.name("account.state")).getAttribute("value"), "CA", "State did not update!");
        Assert.assertEquals(driver.findElement(By.name("account.zip")).getAttribute("value"), "90001", "ZIP code did not update!");
        Assert.assertEquals(driver.findElement(By.name("account.country")).getAttribute("value"), "USA", "Country did not update!");

    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}