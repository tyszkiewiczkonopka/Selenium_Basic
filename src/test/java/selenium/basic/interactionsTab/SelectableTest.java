package selenium.basic.interactionsTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.basic.BaseTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectableTest extends BaseTest {
    @Test
    void selectableTest() {
        driver.get(BASE_URL + "/selectable.php");
        List<WebElement> items = driver.findElements(By.cssSelector(".ui-selectee"));

        Actions actions = new Actions(driver);
        actions
                .keyDown(Keys.CONTROL)
                .click(items.get(0))
                .click(items.get(2))
                .click(items.get(3))
                .keyUp(Keys.CONTROL)
                .perform();

        List<WebElement> selectedItems = driver.findElements(By.cssSelector(".ui-selected"));
        String selectedText = driver.findElement(By.id("feedback")).getText();
        assertThat(selectedText).isEqualTo("You've selected: #1 #3 #4.");

    }
}
