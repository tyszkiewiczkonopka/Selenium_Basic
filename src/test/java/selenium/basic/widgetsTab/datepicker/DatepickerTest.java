package selenium.basic.widgetsTab.datepicker;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.basic.BaseTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DatepickerTest extends BaseTest {
    private static final List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");

    @RepeatedTest(10)
    void should_show_date_in_input_as_picked_in_calendar() {
        driver.get(BASE_URL + "/datepicker.php");
        log.info("Today");
        LocalDate today = LocalDate.now();
        selectDate(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        assertDate(today);

        log.info("1st day from next month");
        LocalDate firstDayNextMonth = LocalDate.now()
                .plusMonths(1)
                .withDayOfMonth(1);
        selectDate(firstDayNextMonth.getYear(), firstDayNextMonth.getMonthValue(), firstDayNextMonth.getDayOfMonth());
        assertDate(firstDayNextMonth);

        log.info("Last day from January in next year");
        LocalDate lastDayJanuaryNextYear = LocalDate.now()
                .plusYears(1)
                .withMonth(1)
                .withDayOfMonth(31);
        selectDate(lastDayJanuaryNextYear.getYear(), lastDayJanuaryNextYear.getMonthValue(), lastDayJanuaryNextYear.getDayOfMonth());
        assertDate(lastDayJanuaryNextYear);

        log.info("Random day from previous month");
        LocalDate randomDayPreviousMonth = LocalDate.now()
                .minusMonths(1)
                .withDayOfMonth(randomDay());
        selectDate(randomDayPreviousMonth.getYear(), randomDayPreviousMonth.getMonthValue(), randomDayPreviousMonth.getDayOfMonth());
        assertDate(randomDayPreviousMonth);

        log.info("Random date from last year");
        LocalDate randomDateLastYear = LocalDate.now()
                .minusYears(1)
                .withMonth(randomMonth())
                .withDayOfMonth(randomDay());
        selectDate(randomDateLastYear.getYear(), randomDateLastYear.getMonthValue(), randomDateLastYear.getDayOfMonth());
        assertDate(randomDateLastYear);

    }

    private WebElement getInputDate() {
        return driver.findElement(By.id("datepicker"));
    }

    public void selectDate(int desiredYear, int desiredMonth, int desiredDay) {
        getInputDate().click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(c -> isDisplayed(By.cssSelector(".ui-datepicker" +
                "-title")));
        var currentYear = getCurrentYear();
        var currentMonth = getCurrentMonth();

        if (desiredYear > currentYear) {
            goToMonthYear(desiredMonth, desiredYear, Direction.NEXT);
        } else if (desiredYear < currentYear) {
            goToMonthYear(desiredMonth, desiredYear, Direction.PREVIOUS);
        } else if (desiredMonth > currentMonth) {
            goToMonthYear(desiredMonth, desiredYear, Direction.NEXT);
        } else if (desiredMonth < currentMonth) {
            goToMonthYear(desiredMonth, desiredYear, Direction.PREVIOUS);
        }

        selectDay(desiredDay);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(c -> !isDisplayed(By.cssSelector(".ui-datepicker" +
                "-title")));
    }

    private void goToMonthYear(int desiredMonth, int desiredYear, Direction direction) {
        String expectedMonthYear = months.get(desiredMonth - 1) + " " + desiredYear;

        while (!getCurrentMonthYear().equals(expectedMonthYear)) {
            int monthBeforeClick = getCurrentMonth();

            if (direction == Direction.PREVIOUS) {
                driver.findElement(By.cssSelector(".ui-datepicker-prev")).click();
            } else {
                driver.findElement(By.cssSelector(".ui-datepicker-next")).click();
            }
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(c -> getCurrentMonth() != monthBeforeClick);
        }
    }

    public void selectDay(int day) {
        WebElement selectedDay = driver.findElement(By.xpath(
                "//td[@data-month='" + (getCurrentMonth() - 1) + "' and .//a[text()='" + day + "']]"));
        selectedDay.click();
    }

    private int randomDay() {
        return new Random().nextInt(LocalDate.now().getMonthValue()) + 1;
    }

    private int randomMonth() {
        return new Random().nextInt(12) + 1;
    }

    public int getCurrentYear() {
        return Integer.parseInt(driver.findElement(By.cssSelector(".ui-datepicker-year")).getText());
    }

    public int getCurrentMonth() {
        return months.indexOf(driver.findElement(By.cssSelector(".ui-datepicker-month")).getText()) + 1;
    }

    public String getCurrentMonthYear() {
        return driver.findElement(By.cssSelector(".ui-datepicker-title")).getText();
    }

    private boolean isDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    private void assertDate(LocalDate expectedDate) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String inputValue = (String) jsExecutor.executeScript("return arguments[0].value;", getInputDate());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedDate = LocalDate.parse(inputValue, formatter);

        try {
            assertThat(parsedDate).isEqualTo(expectedDate);
            log.info("Assertion passed: The date matches the expected date (" + parsedDate + ")");
        } catch (AssertionError e) {
            log.error("Assertion failed: The date does not match the expected date (" + parsedDate + ")", e);
            throw e;
        }
    }
}


