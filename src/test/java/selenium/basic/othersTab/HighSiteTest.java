package selenium.basic.othersTab;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.basic.BaseTest;

import java.io.File;
import java.util.List;

public class HighSiteTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger("HighSiteTest");

    @RepeatedTest(value = 10)
    void screenshot_should_show_submit_button_at_30_percent_after_scrolling_with_javascript() {
        driver.get(BASE_URL + "/high-site.php");
        scrollWithJSUntilElementVisible();
        takeScreenshot(driver);
    }

    @RepeatedTest(value = 10)
    void screenshot_should_show_submit_button_at_30_percent_after_scrolling_with_action_class() {
        driver.get(BASE_URL + "/high-site.php");
        scrollWithActionsUntilElementVisible();
        takeScreenshot(driver);
    }

    private void takeScreenshot(WebDriver driver) {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File destDirectory = new File("target/screenshots");

            if (!destDirectory.exists()) {
                destDirectory.mkdirs();
            }

            String destFilePath = destDirectory + "/" + "submitButton" + "_" + System.currentTimeMillis() + ".png";
            File destFile = new File(destFilePath);

            FileUtils.copyFile(srcFile, destFile);

            logger.info("Screenshot saved at: " + destFile.getAbsolutePath());

        } catch (Exception e) {
            logger.error("Error taking screenshot: " + e.getMessage());
        }
    }
    private void scrollWithJSUntilElementVisible(){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        while (true) {
            List<WebElement> submitButton = driver.findElements(By.cssSelector("#scroll-button"));
            if(!submitButton.isEmpty()) {
                break;
            }
            js.executeScript("window.scrollBy(0, 100);");
        }
    }
    private void scrollWithActionsUntilElementVisible(){
        Actions actions = new Actions(driver);

        while (true) {
            List<WebElement> submitButton = driver.findElements(By.cssSelector("#scroll-button"));
            if(!submitButton.isEmpty()) {
                break;
            }
            actions.scrollByAmount(0, 100).perform();
        }
    }
}

