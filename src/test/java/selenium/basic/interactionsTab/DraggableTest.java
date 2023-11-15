package selenium.basic.interactionsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.basic.BaseTest;

public class DraggableTest extends BaseTest {


    @RepeatedTest(value = 10)
    void should_move_element_to_top_right_corner() {
        driver.get(BASE_URL + "/draggable.php");
        WebElement elementToDrag = driver.findElement(By.id("draggable"));

        int elementWidth = elementToDrag.getSize().getWidth();
        int windowWidth = driver.manage().window().getSize().getWidth();

        int desiredX = windowWidth - elementWidth;
        int desiredY = 0;

        int offsetX = desiredX - elementToDrag.getLocation().getX();
        int offsetY = desiredY - elementToDrag.getLocation().getY();

        dragElementByOffset(elementToDrag, offsetX, offsetY);

    }

    @RepeatedTest(value = 10)
    void should_move_element_to_top_left_corner() {
        driver.get(BASE_URL + "/draggable.php");
        WebElement elementToDrag = driver.findElement(By.id("draggable"));

        int desiredX = 0;
        int desiredY = 0;

        int offsetX = desiredX - elementToDrag.getLocation().getX();
        int offsetY = desiredY - elementToDrag.getLocation().getY();

        dragElementByOffset(elementToDrag, offsetX, offsetY);

    }

    @RepeatedTest(value = 10)
    void should_move_element_to_bottom_left_corner() {
        driver.get(BASE_URL + "/draggable.php");
        WebElement elementToDrag = driver.findElement(By.id("draggable"));

        int elementHeight = elementToDrag.getSize().getHeight();
        int windowHeight = driver.manage().window().getSize().getHeight();

        int desiredX = 0;
        int desiredY = windowHeight - elementHeight;

        int offsetX = desiredX - elementToDrag.getLocation().getX();
        int offsetY = desiredY - elementToDrag.getLocation().getY() - elementHeight / 2;

        dragElementByOffset(elementToDrag, offsetX, offsetY);

    }

    @RepeatedTest(value = 10)
    void should_move_element_to_bottom_right_corner() {
        driver.get(BASE_URL + "/draggable.php");
        WebElement elementToDrag = driver.findElement(By.id("draggable"));

        int elementHeight = elementToDrag.getSize().getHeight();
        int windowHeight = driver.manage().window().getSize().getHeight();

        int elementWidth = elementToDrag.getSize().getWidth();
        int windowWidth = driver.manage().window().getSize().getWidth();

        int desiredX = windowWidth - elementWidth;
        int desiredY = windowHeight - elementHeight;

        int offsetX = desiredX - elementToDrag.getLocation().getX();
        int offsetY = desiredY - elementToDrag.getLocation().getY() - elementHeight / 2;

        dragElementByOffset(elementToDrag, offsetX, offsetY);

    }

    private void dragElementByOffset(WebElement elementToDrag, int offsetX, int offsetY) {
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(elementToDrag, offsetX, offsetY).perform();
    }

}



