package selenium.basic.interactionsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.basic.BaseTest;

import java.util.*;
import java.util.stream.Collectors;

public class SortableTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger("SortableTest");
    private final Map<String, WebElement> ITEM_MAP = new HashMap<>();
    private final List<Integer> NUMBERS = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

    @RepeatedTest(value = 10)
    void sorted_items_should_be_the_same_as_shuffled_numbers(){
        driver.get(BASE_URL + "/sortable.php");
        shuffleItems();
        sortShuffledItems(NUMBERS);
    }

    private void shuffleItems(){
        List<WebElement> items = driver.findElements(By.cssSelector("#sortable li"));

        for (WebElement item : items) {
            ITEM_MAP.put(item.getText(), item);
        }
        Collections.shuffle(NUMBERS);
        logger.info("Shuffled numbers: " + NUMBERS);
    }
    private void sortShuffledItems(List<Integer> numbers){
        List<WebElement> sortedItems = new ArrayList<>();
        for (Integer number : numbers) {
            String itemText = "Item " + number;
            sortedItems.add(ITEM_MAP.get(itemText));
        }
        Actions actions = new Actions(driver);
        WebElement sortable = driver.findElement(By.id("sortable"));

        List<WebElement> sortedElements = new ArrayList<>();

        for (WebElement item : sortedItems) {
            actions.dragAndDrop(item, sortable).perform();
            sortedElements.add(item);
        }

        String sortedElementsString = sortedElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.joining(", "));

        logger.info("New Item Order: " + sortedElementsString);
    }
}
