import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveFromCartTest {
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
    public void removeFromCartTest() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(2000);


        WebElement dogsLink = driver.findElement(By.xpath("//*[@id=\"SidebarContent\"]/a[2]"));
        dogsLink.click();
        Thread.sleep(2000);

        WebElement dogLink = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[1]/a"));
        dogLink.click();
        Thread.sleep(2000);

        WebElement addToCart = driver.findElement(By.xpath("//a[contains(@href, 'addItemToCart')]"));
        addToCart.click();
        Thread.sleep(2000);

        WebElement removeFromCart = driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[2]/td[8]/a"));
        removeFromCart.click();
        Thread.sleep(2000);

        WebElement emptyCartMessage = driver.findElement(By.xpath("//tr/td[b[contains(text(),'Your cart is empty.')]]")
        );
        Assert.assertEquals(emptyCartMessage.getText(), "Your cart is empty.");

        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("Cart.action"), "User is not on the shopping cart page!");
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}