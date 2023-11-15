package selenium.basic.basicTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.basic.BaseTest;


public class WindowsTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger("WindowsTest");
    private final TableTest tableTest = new TableTest();
    private String originalWindow;


    @RepeatedTest(value = 10)
    void should_open_new_windows_and_new_tab() {
        driver.get(BASE_URL + "/windows-tabs.php");
        originalWindow = driver.getWindowHandle();

        openBrowserWindow();
        performTableTest();
        closeWindow();
        switchToOriginalWindow();

        openMessageWindow();
        printMessageWindowText();
        closeWindow();
        switchToOriginalWindow();

        openNewTab();
        performTableTest();
        closeWindow();
    }

    private void openBrowserWindow() {
        driver.findElement(By.id("newBrowserWindow")).click();
        for (String newBrowserWindow : driver.getWindowHandles()) {
            if (!newBrowserWindow.equals(originalWindow)) {
                driver.switchTo().window(newBrowserWindow);
            }
        }
        logger.info("Opened new window");
    }

    private void openMessageWindow() {
        driver.findElement(By.id("newMessageWindow")).click();
        for (String newMessageWindow : driver.getWindowHandles()) {
            if (!newMessageWindow.equals(originalWindow)) {
                driver.switchTo().window(newMessageWindow);
            }
        }
        logger.info("Opened message window");

    }

    private void openNewTab() {
        driver.findElement(By.id("newBrowserTab")).click();
        for (String newBrowserTab : driver.getWindowHandles()) {
            if (!newBrowserTab.equals(originalWindow)) {
                driver.switchTo().window(newBrowserTab);
            }
        }
        logger.info("Opened new tab");

    }

    private void switchToOriginalWindow() {
        driver.switchTo().window(originalWindow);
        logger.info("Switched back to original window");
    }

    private void performTableTest() {
        logger.info("Ready to perform TableTest");
        tableTest.findInTable(driver);
        logger.info("TableTest performed");
    }

    private void printMessageWindowText() {
        WebElement message = driver.findElement(By.cssSelector("body"));
        System.out.println(message.getText());
        logger.info("Message printed");
    }

    private void closeWindow() {
        driver.close();

    }
}
