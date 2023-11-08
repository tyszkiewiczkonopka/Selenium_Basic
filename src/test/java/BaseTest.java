import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "http://www.seleniumui.moderntester.pl/";
    protected final String DEFAULT_DIRECTORY = "/Users/magdalena/IdeaProjects/files";

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(DEFAULT_DIRECTORY);

        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void quitdriver() {
        driver.quit();
    }
}
