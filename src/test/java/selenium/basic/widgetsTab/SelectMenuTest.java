package selenium.basic.widgetsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.basic.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SelectMenuTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger("SelectMenuTest");

    @RepeatedTest(value = 10)
    void should_select_options_from_all_menus() {
        driver.get(BASE_URL + "/selectmenu.php");
        String selectedSpeed = selectRandomSpeed();
        logger.info("SELECTED SPEED: " + selectedSpeed);

        String selectedFileText = selectFileByOptionText("Some unknown file");
        logger.info("SELECTED FILE: " + selectedFileText);

        int selectedNumber = selectNumberByIndex(2);
        logger.info("SELECTED NUMBER: " + selectedNumber);

        String selectedSalutation = selectRandomSalutation();
        logger.info("SELECTED SALUTATION: " + selectedSalutation);

    }

    private String selectRandomSpeed() {
        String speedButtonSelector = "#speed-button";
        String speedMenuOptionsSelector = "#speed-menu li";
        openAndShowMenuOptions(speedButtonSelector, speedMenuOptionsSelector);

        List<WebElement> speedOptions = driver.findElements(By.cssSelector(speedMenuOptionsSelector));

        WebElement selectedOption = selectRandom(speedOptions);
        String speedText = selectedOption.getText();

        selectedOption.click();
        return speedText;
    }

    private String selectFileByOptionText(String optionText) {
        String filesButtonSelector = "#files-button";
        String filesMenuOptionsSelector = "#files-menu";

        openAndShowMenuOptions(filesButtonSelector, filesMenuOptionsSelector);

        WebElement chosenFile = driver.findElement(By.xpath(".//div[text()='" + optionText + "']"));
        String chosenFileText = chosenFile.getText();
        chosenFile.click();

        return chosenFileText;
    }

    private int selectNumberByIndex(int indexToSelect) {
        String numberButtonSelector = "#number-button";
        String numberMenuOptionsSelector = "#number-menu";

        openAndShowMenuOptions(numberButtonSelector, numberMenuOptionsSelector);

        WebElement menu = driver.findElement(By.cssSelector(numberMenuOptionsSelector));

        List<WebElement> numbers = menu.findElements(By.tagName("li"));
        numbers.get(indexToSelect).click();

        return indexToSelect;
    }

    private String selectRandomSalutation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String salutationButtonSelector = "#salutation-button";
        String salutationMenuOptionsSelector = "#salutation-menu";

        openAndShowMenuOptions(salutationButtonSelector, salutationMenuOptionsSelector);

        List<WebElement> validSalutations =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(salutationMenuOptionsSelector + "  li:not([aria-disabled])")));

        WebElement selectedOption = selectRandom(validSalutations);
        String salutationText = selectedOption.getText();
        selectedOption.click();

        return salutationText;
    }

    private void openAndShowMenuOptions(String buttonSelector, String menuOptionSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = driver.findElement(By.cssSelector(buttonSelector));
        button.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(menuOptionSelector)));
    }

    private WebElement selectRandom(List<WebElement> options){
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());
        return options.get(randomIndex);
    }

}


