import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPage;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected OrderPage orderPage;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.findElement(By.id("rcc-confirm-button")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}