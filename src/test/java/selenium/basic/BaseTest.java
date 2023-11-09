package selenium.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "http://www.seleniumui.moderntester.pl/";
//    protected final String DEFAULT_DIRECTORY = "C:\\Users\\mtyszkiewicz\\Documents\\test_files";

    @BeforeEach
    public void setup() {
//        ChromeOptions options = new ChromeOptions();
//      options.addArguments(DEFAULT_DIRECTORY);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
