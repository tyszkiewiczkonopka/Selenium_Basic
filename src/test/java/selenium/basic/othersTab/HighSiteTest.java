package selenium.basic.othersTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.basic.BaseTest;

import java.time.Duration;

public class HighSiteTest extends BaseTest {
    @Test
    void scrollTest() {
        driver.get(BASE_URL + "/high-site.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement submitButton =
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#scroll-button")));

        Actions actions = new Actions(driver);
        actions
                .moveToElement(submitButton)
                .perform();

    }
}
