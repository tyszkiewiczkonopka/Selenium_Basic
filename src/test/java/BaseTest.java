import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {
    protected WebDriver driver;
    protected final String BASE_URL = "http://www.seleniumui.moderntester.pl/";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

//    @AfterEach
//    public void quitdriver() {
//        driver.quit();
//    }
}
