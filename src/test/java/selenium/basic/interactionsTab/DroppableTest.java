package selenium.basic.interactionsTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.basic.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;


public class DroppableTest extends BaseTest {

    @Test
    void droppableTest() {
        Actions actions = new Actions(driver);

        driver.get(BASE_URL + "/droppable.php");

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(source, target).perform();

        assertThat(driver.findElement(By.cssSelector("#droppable>p")).getText()).isEqualTo("Dropped!");
    }

}
