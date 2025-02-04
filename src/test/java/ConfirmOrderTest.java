import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConfirmOrderTest {
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
    public void confirmOrderTest() throws InterruptedException {
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


        WebElement catsLink = driver.findElement(By.xpath("//*[@id=\"SidebarContent\"]/a[3]"));
        catsLink.click();
        Thread.sleep(2000);

        WebElement catLink = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a"));
        catLink.click();
        Thread.sleep(2000);

        WebElement addToCart = driver.findElement(By.xpath("//a[contains(@href, 'addItemToCart')]"));
        addToCart.click();
        Thread.sleep(2000);

        WebElement proceedToCheckout = driver.findElement(By.xpath("//a[contains(@href, 'newOrderForm')]"));
        proceedToCheckout.click();
        Thread.sleep(3000);

        WebElement cardType = driver.findElement(By.name("order.cardType"));
        WebElement cardNumber = driver.findElement(By.name("order.creditCard"));
        WebElement expiryDate = driver.findElement(By.name("order.expiryDate"));
        WebElement firstName = driver.findElement(By.name("order.billToFirstName"));
        WebElement lastName = driver.findElement(By.name("order.billToLastName"));
        WebElement address1 = driver.findElement(By.name("order.billAddress1"));
        WebElement address2 = driver.findElement(By.name("order.billAddress2"));
        WebElement city = driver.findElement(By.name("order.billCity"));
        WebElement state = driver.findElement(By.name("order.billState"));
        WebElement zip = driver.findElement(By.name("order.billZip"));
        WebElement country = driver.findElement(By.name("order.billCountry"));

        cardType.sendKeys("Visa");
        cardNumber.clear();
        cardNumber.sendKeys("999 9999 9999 9999");
        expiryDate.clear();
        expiryDate.sendKeys("12/03");
        firstName.clear();
        firstName.sendKeys("John");
        lastName.clear();
        lastName.sendKeys("Smith");
        address1.clear();
        address1.sendKeys("123 Main St");
        address2.clear();
        address2.sendKeys("Apt 4");
        city.clear();
        city.sendKeys("New York");
        state.clear();
        state.sendKeys("NY");
        zip.clear();
        zip.sendKeys("10001");
        country.clear();
        country.sendKeys("USA");

        WebElement continueButton = driver.findElement(By.name("newOrder"));
        continueButton.click();
        Thread.sleep(3000);

        WebElement confirmButton = driver.findElement(By.xpath("//a[contains(@href, 'confirmed=true')]"));
        confirmButton.click();
        Thread.sleep(3000);

        WebElement confirmationMessage = driver.findElement(By.xpath("//ul[@class='messages']/li"));
        String messageText = confirmationMessage.getText();
        Assert.assertEquals(messageText, "Thank you, your order has been submitted.", "Confirmation message is not displayed!");

        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("confirmed=true"), "Order confirmation URL is incorrect!");
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}