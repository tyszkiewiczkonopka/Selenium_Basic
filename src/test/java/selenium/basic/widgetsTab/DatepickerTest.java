package selenium.basic.widgetsTab;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DatepickerTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger("DatepickerTest");

    @Test
    void should_show_date_in_input_as_picked_in_calendar() {
        driver.get(BASE_URL + "/datepicker.php");
        WebElement dateInput = driver.findElement(By.id("datepicker"));

        selectToday(dateInput);
        assertDate(dateInput, LocalDate.now());

        selectFirstDayNextMonth(dateInput);
        assertDate(dateInput, LocalDate.now().plusMonths(1).withDayOfMonth(1));

        selectLastDayJanuaryNextYear(dateInput);
        assertDate(dateInput, LocalDate.now().plusYears(1).withMonth(1).withDayOfMonth(31));

        selectLastDayJanuaryNextYear(dateInput);
        assertDate(dateInput, LocalDate.now().plusYears(1).withMonth(1).withDayOfMonth(31));

        String randomDay = selectRandomDayPreviousMonth(dateInput);
        assertDate(dateInput, LocalDate.now().minusMonths(1).withDayOfMonth(Integer.parseInt(randomDay)));

        LocalDate randomDate = selectRandomDatePreviousYear(dateInput);
        assertDate(dateInput, randomDate);
    }

    private void selectToday(WebElement dateInput) {
        logger.info("TODAY");
        dateInput.click();
        WebElement today = driver.findElement(By.cssSelector(".ui-state-highlight"));
        today.click();
    }

    private void selectFirstDayNextMonth(WebElement dateInput) {
        logger.info("FIRST DAY NEXT MONTH");
        dateInput.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ui-datepicker-next")));
        nextMonthButton.click();
        WebElement fistDayNextMonth = driver.findElement(By.xpath("(.//*[text()='1'])[1]"));
        fistDayNextMonth.click();
    }

    private void selectLastDayJanuaryNextYear(WebElement dateInput) {
        logger.info("LAST DAY FROM JANUARY NEXT YEAR");
        dateInput.clear();
        dateInput.click();
        goToDesiredMonthAndYear("January", LocalDate.now().getYear() + 1, "a.ui-datepicker-next");
        WebElement day = driver.findElement(By.xpath("(.//*[text()='31'])[2]"));
        day.click();
    }

    private void goToDesiredMonthAndYear(String desiredMonth, int desiredYear, String monthSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        while (true) {
            WebElement monthButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(monthSelector)));
            WebElement month = driver.findElement(By.cssSelector(".ui-datepicker-month"));
            WebElement year = driver.findElement(By.cssSelector(".ui-datepicker-year"));

            String currentMonth = month.getText();
            int currentYear = Integer.parseInt(year.getText());

            if (currentMonth.equals(desiredMonth) && currentYear == desiredYear) {
                break;
            }
            monthButton.click();
        }
    }

    private String selectRandomDayPreviousMonth(WebElement dateInput) {
        logger.info("RANDOM DAY FROM PREVIOUS MONTH");
        dateInput.clear();
        dateInput.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement previousMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ui-datepicker-prev")));
        previousMonthButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ui-datepicker-loading")));
        dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("datepicker")));

        return selectRandomDay();
    }

    private String selectRandomDay() {
        List<WebElement> clickableDates = driver.findElements(By.cssSelector(".ui-datepicker-calendar a:not(.ui-priority-secondary)"));
        int randomIndex = new Random().nextInt(clickableDates.size());
        WebElement randomSelectedDay = clickableDates.get(randomIndex);
        String randomSelectedDayText = randomSelectedDay.getText();
        logger.info("Random day: " + randomSelectedDayText);
        randomSelectedDay.click();
        return randomSelectedDayText;
    }


    private LocalDate selectRandomDatePreviousYear(WebElement dateInput) {
        logger.info("RANDOM DATE FROM LAST YEAR");

        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        int randomIndex = new Random().nextInt(months.length);
        logger.info("Random month: " + (randomIndex + 1));

        int previousYear = LocalDate.now().minusYears(1).getYear();

        dateInput.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ui-datepicker-prev")));

        goToDesiredMonthAndYear(months[randomIndex], previousYear, "a.ui-datepicker-prev");

        return LocalDate.of(previousYear, randomIndex + 1, Integer.parseInt(selectRandomDay()));
    }


    private void assertDate(WebElement dateInput, LocalDate expectedDate) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String inputValue = (String) jsExecutor.executeScript("return arguments[0].value;", dateInput);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedDate = LocalDate.parse(inputValue, formatter);

        try {
            assertThat(parsedDate).isEqualTo(expectedDate);
            logger.info("Assertion passed: The date matches the expected date (" + parsedDate + ")");
        } catch (AssertionError e) {
            logger.error("Assertion failed: The date does not match the expected date (" + parsedDate + ")", e);
            throw e;
        }
    }


}
