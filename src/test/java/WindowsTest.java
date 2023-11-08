import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class WindowsTest extends BaseTest {

    @Test
    void windowsTab() {
        driver.get(BASE_URL + "/windows-tabs.php");
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.id("newBrowserWindow")).click();

        for (String newBrowserWindow : driver.getWindowHandles()) {
            if (!newBrowserWindow.equals(originalWindow)) {
                driver.switchTo().window(newBrowserWindow);
            }
        }

// Execute steps from Tables exercise on the newly opened window

        driver.close();

        driver.switchTo().window(originalWindow);
        driver.findElement(By.id("newMessageWindow")).click();
        for (String newMessageWindow : driver.getWindowHandles()) {
            if (!newMessageWindow.equals(originalWindow)) {
                driver.switchTo().window(newMessageWindow);
            }
        }
        WebElement message = driver.findElement(By.cssSelector("body"));
        System.out.println(message.getText());
        driver.close();

        driver.switchTo().window(originalWindow);
        driver.findElement(By.id("newBrowserTab")).click();
        for (String newBrowserTab : driver.getWindowHandles()) {
            if (!newBrowserTab.equals(originalWindow)) {
                driver.switchTo().window(newBrowserTab);
            }
        }

//  Execute steps from Tables exercise on the new opened window
        driver.close();
    }
}
