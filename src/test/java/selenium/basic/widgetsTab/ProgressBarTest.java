package selenium.basic.widgetsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.basic.BaseTest;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ProgressBarTest extends BaseTest {
    @RepeatedTest(value = 10)
    void progress_bar_should_change_to_complete() {
        driver.get(BASE_URL + "/progressbar.php");
        boolean isCompeteVisible = moveProgressBarUntilCompleteIsVisible();
        assertThat(isCompeteVisible).isEqualTo(true);
    }

    @RepeatedTest(value = 10)
    void progress_bar_element_class_changes() {
        driver.get(BASE_URL + "/progressbar.php");
        boolean isClassChangedToComplete = moveProgressBarUntilClassNameChanges();
        assertThat(isClassChangedToComplete).isEqualTo(true);
    }
    private boolean moveProgressBarUntilCompleteIsVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.textToBe(By.cssSelector(".progress-label"),
                "Complete!"));

    }
    private boolean moveProgressBarUntilClassNameChanges(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.attributeContains(By.cssSelector("div #progressbar div:nth-of-type(2)"),
                        "class", "ui-progressbar-complete"));
    }
}
