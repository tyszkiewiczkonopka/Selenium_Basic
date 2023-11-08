package selenium.basic.basicTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.basic.BaseTest;


public class WindowsTest extends BaseTest {
    TableTest tableTest = new TableTest();
    private final Logger logger = LoggerFactory.getLogger("WindowsTest");


    @Test
    void windowsTab() {
        driver.get(BASE_URL + "/windows-tabs.php");
        String originalWindow = driver.getWindowHandle();
        logger.info("Opened original window");
        driver.findElement(By.id("newBrowserWindow")).click();

        for (String newBrowserWindow : driver.getWindowHandles()) {
            if (!newBrowserWindow.equals(originalWindow)) {
                driver.switchTo().window(newBrowserWindow);
            }
        }
        logger.info("Opened new window");


        logger.info("Ready to perform TableTest");
        tableTest.findInTable(driver);
        logger.info("TableTest performed");

        driver.close();

        driver.switchTo().window(originalWindow);
        logger.info("Switched back to original window");

        driver.findElement(By.id("newMessageWindow")).click();
        for (String newMessageWindow : driver.getWindowHandles()) {
            if (!newMessageWindow.equals(originalWindow)) {
                driver.switchTo().window(newMessageWindow);
            }
        }
        logger.info("Opened message window");

        WebElement message = driver.findElement(By.cssSelector("body"));
        System.out.println(message.getText());
        logger.info("Message printed");
        driver.close();

        driver.switchTo().window(originalWindow);
        logger.info("Switched back to original window");

        driver.findElement(By.id("newBrowserTab")).click();
        for (String newBrowserTab : driver.getWindowHandles()) {
            if (!newBrowserTab.equals(originalWindow)) {
                driver.switchTo().window(newBrowserTab);
            }
        }
        logger.info("Opened new tab");

        logger.info("Ready to perform TableTest");
        tableTest.findInTable(driver);
        logger.info("TableTest performed");
        driver.close();
        logger.info("Tab closed");
    }
}
