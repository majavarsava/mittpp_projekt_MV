import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {
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
    public void searchTest() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Find the search box and enter "golden retriever"
        WebElement searchTextBox = driver.findElement(By.name("keyword"));
        searchTextBox.clear();
        searchTextBox.sendKeys("golden retriever");

        // Click the search button
        WebElement searchButton = driver.findElement(By.name("searchProducts"));
        searchButton.click();

        Thread.sleep(3000);

        Thread.sleep(3000);

        // Find and click the first search result using correct XPath
        WebElement firstResult = driver.findElement(By.xpath("//td[contains(text(), 'Golden Retriever')]/preceding-sibling::td/a"));
        String firstResultText = firstResult.getText();
        firstResult.click();

        Thread.sleep(3000);

        // Find the header of the new page
        WebElement productHeader = driver.findElement(By.xpath("//h2"));
        String productHeaderText = productHeader.getText().toLowerCase();

        // Debugging: Print the header text
        System.out.println("Product page header: " + productHeaderText);

        // Assert that the header contains "golden retriever"
        Assert.assertTrue(productHeaderText.contains("golden retriever"), "Product page did not display the correct result!");
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}

