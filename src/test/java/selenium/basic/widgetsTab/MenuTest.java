package selenium.basic.widgetsTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.basic.BaseTest;

import java.time.Duration;

public class MenuTest extends BaseTest {
    @Test
    void menuTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(BASE_URL + "/menu-item.php");
        WebElement musicElement = driver.findElement(By.cssSelector("#ui-id-9"));
        WebElement jazzElement = driver.findElement(By.cssSelector("#ui-id-13"));
        WebElement modernElement = driver.findElement(By.cssSelector("#ui-id-16"));

        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOf(musicElement));
        actions.moveToElement(musicElement).build().perform();
        wait.until(ExpectedConditions.visibilityOf(jazzElement));
        actions.moveToElement(jazzElement).build().perform();
        wait.until(ExpectedConditions.visibilityOf(modernElement));
        actions.moveToElement(modernElement).click().perform();

    }
}
