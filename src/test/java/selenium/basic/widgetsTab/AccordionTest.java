package selenium.basic.widgetsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.basic.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AccordionTest extends BaseTest {

    @RepeatedTest(value = 10)
    void should_print_texts_from_accordion_sections() {
        driver.get(BASE_URL + "/accordion.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        getSection1text();
        getSection2text(wait);
        getSection3text(wait);
        getSection4text(wait);
    }

    private void getSection1text() {
        WebElement section1element = driver.findElement(By.id("ui-id-2"));
        String section1text = section1element.getText();
        System.out.println("SECTION 1 TEXT: \n" + section1text + "\n");
    }

    private void getSection2text(WebDriverWait wait) {
        WebElement section2header = driver.findElement(By.id("ui-id-3"));
        section2header.click();
        WebElement section2element = driver.findElement(By.id("ui-id-4"));
        wait.until(ExpectedConditions.visibilityOf(section2element));
        String section2text = section2element.getText();
        System.out.println("SECTION 2 TEXT: \n" + section2text + "\n");
    }

    private void getSection3text(WebDriverWait wait) {
        WebElement section3header = driver.findElement(By.id("ui-id-5"));
        section3header.click();
        WebElement section3element = driver.findElement(By.id("ui-id-6"));
        wait.until(ExpectedConditions.visibilityOf(section3element));

        String section3paragraphText = section3element.findElement(By.tagName("p")).getText();
        List<WebElement> listItems = section3element.findElements(By.tagName("li"));
        List<String> listItemTexts = new ArrayList<>();
        for (WebElement listItem : listItems) {
            listItemTexts.add(listItem.getText());
        }
        String section3listText = String.join(", ", listItemTexts);
        System.out.println("SECTION 3 TEXT: \n" + section3paragraphText + "\n" + section3listText + "\n");
    }

    private void getSection4text(WebDriverWait wait) {
        WebElement section4header = driver.findElement(By.id("ui-id-7"));
        section4header.click();
        WebElement section4element = driver.findElement(By.id("ui-id-8"));
        wait.until(ExpectedConditions.visibilityOf(section4element));
        String section4text = section4element.getText();
        System.out.println("SECTION 4 TEXT: \n" + section4text + "\n");
    }
}
