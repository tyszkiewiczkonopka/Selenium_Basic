package selenium.basic.widgetsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.basic.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AutocompleteTest extends BaseTest {

    @RepeatedTest(value = 10)
    void should_show_randomly_chosen_value_in_search_input() {
        driver.get(BASE_URL + "/autocomplete.php");
        WebElement searchInput = driver.findElement(By.id("search"));

        List<WebElement> validOptions = getValidOptions();
        String randomValidOption = chooseRandomOption(validOptions);
        String inputText = getInputText(searchInput);

        assertThat(inputText).isEqualTo(randomValidOption);
    }

    private List<WebElement> getValidOptions() {
        WebElement searchInput = driver.findElement(By.id("search"));
        searchInput.click();
        searchInput.sendKeys("a");
        return printAvailableOptions();
    }

    private List<WebElement> printAvailableOptions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement listElement = driver.findElement(By.id("ui-id-1"));
        List<WebElement> liElements = listElement.findElements(By.tagName("li"));

        List<WebElement> validOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ui-id-1 li")));

        for (WebElement li : liElements) {
            if (!li.getAttribute("class").contains("ui-autocomplete-category")) {
                System.out.println(li.getText());
                validOptions.add(li);
            }
        }
        return validOptions;
    }


    private String chooseRandomOption(List<WebElement> validOptions) {
        Random random = new Random();
        int randomIndex = random.nextInt(validOptions.size());
        WebElement selectedOption = validOptions.get(randomIndex);
        String text = selectedOption.getText();

        selectedOption.click();
        return text;
    }

    private String getInputText(WebElement searchInput) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value", searchInput);
    }

}


