package selenium.basic.interactionsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.basic.BaseTest;

import java.time.Duration;

public class ResizableTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger("ResizableTest");
    private final int HANDLE_MOVEMENT_CORRECTION = 18;

    @RepeatedTest(value = 10)
    void element_should_resize_after_moving_the_handle() {
        driver.get(BASE_URL + "/resizable.php");
        WebElement resizableElement = driver.findElement(By.id("resizable"));
        Dimension resizableElementSize = resizableElement.getSize();
        logger.info("Initial size: " + resizableElementSize);

        resizeElementToTheRight();
        logger.info("Size after moving to the right: " + resizableElement.getSize());
        resizeElementToTheBottom();
        logger.info("Size after moving to the bottom: " + resizableElement.getSize());
        resizeElementToTheRightAndBottom();
        logger.info("Size after moving to the right and to the bottom: " + resizableElement.getSize());
    }


    private void resizeElementToTheRight() {
        int offsetWidth = HANDLE_MOVEMENT_CORRECTION + 10;
        WebElement resizableHandle = driver.findElement(By.cssSelector(".ui-resizable-se"));
        Actions action = new Actions(driver);
        action.moveToElement(resizableHandle).clickAndHold().moveByOffset(offsetWidth, 0).release().build().perform();
    }

    private void resizeElementToTheBottom() {
        int offsetHeight = HANDLE_MOVEMENT_CORRECTION + 10;
        WebElement resizableHandle = driver.findElement(By.cssSelector(".ui-resizable-se"));
        Actions action = new Actions(driver);
        action.moveToElement(resizableHandle).clickAndHold().moveByOffset(0, offsetHeight).release().build().perform();
    }

    private void resizeElementToTheRightAndBottom() {
        int offsetWidth = HANDLE_MOVEMENT_CORRECTION + 10;
        int offsetHeight = HANDLE_MOVEMENT_CORRECTION + 10;
        WebElement resizableHandle = driver.findElement(By.cssSelector(".ui-resizable-se"));
        Actions action = new Actions(driver);
        action.moveToElement(resizableHandle).clickAndHold().moveByOffset(offsetWidth, offsetHeight).release().build().perform();
    }

}
