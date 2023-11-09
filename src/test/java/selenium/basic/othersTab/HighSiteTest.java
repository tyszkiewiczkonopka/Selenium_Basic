package selenium.basic.othersTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import selenium.basic.BaseTest;

public class HighSiteTest extends BaseTest {
    @Test
    void scrollTestWithJS() {
        driver.get(BASE_URL + "/high-site.php");
        //Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("scroll-button"))));
        WebElement submitButton = driver.findElement(By.id("scroll-button"));
        js.executeScript("arguments[0].scrollInoView();", submitButton);

        while (!submitButton.isDisplayed()) {
            js.executeScript("window.scrollBy(0, 100);");
        }

    }

    @Test
    void scrollTestWithActions() {
        driver.get(BASE_URL + "/high-site.php");
        driver.findElement(By.cssSelector("input#scroll-button"));
    }

}
