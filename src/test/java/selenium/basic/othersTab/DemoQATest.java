package selenium.basic.othersTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.basic.BaseTest;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoQATest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger("DemoQATest");

    @RepeatedTest(value = 10)
    void should_choose_two_subjects_from_dropdown() {
        driver.get("https://demoqa.com/automation-practice-form");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement subjectInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("subjectsInput")));

        selectMaths(subjectInput);
        selectArts(subjectInput);
        assertSearchInputValues();
    }

    private void selectMaths(WebElement subjectInput) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        subjectInput.sendKeys("m");
        WebElement mathsOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-2-option-0")));
        scrollToElement(driver, mathsOption);
        mathsOption.click();
    }
    private static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void selectArts(WebElement subjectInput) {
        subjectInput.sendKeys("a");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement artsOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-2-option-2")));
        artsOption.click();
    }

    private void assertSearchInputValues() {
        List<WebElement> selectedSubjects = driver.findElements(
                By.cssSelector(".subjects-auto-complete__value-container>.subjects-auto-complete__multi-value"));
        List<String> selectedSubjectsTexts = selectedSubjects.stream()
                .map(WebElement::getText)
                .toList();

        try {
            assertThat(selectedSubjectsTexts).isEqualTo(List.of("Maths", "Arts"));
            logger.info("Assertion passed: Selected subjects match the expected values");
        } catch (AssertionError e) {
            logger.error("Assertion failed: Selected subjects do not match the expected values", e);
            throw e;
        }
    }
}
