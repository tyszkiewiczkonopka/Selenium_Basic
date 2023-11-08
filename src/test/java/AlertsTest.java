import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;


public class AlertsTest extends BaseTest {
    @RepeatedTest(value = 10, name = RepeatedTest.SHORT_DISPLAY_NAME)
    void should_simple_alert_show_message_after_clicking_ok() {
        driver.get(BASE_URL + "/alerts.php");
        driver.findElement(By.id("simple-alert")).click();
        driver.switchTo().alert().accept();

        String expectedOkMessage = "OK button pressed";
        String actualOkMessage = driver.findElement(By.id("simple-alert-label")).getText();

        assertThat(actualOkMessage).isEqualTo(expectedOkMessage);
    }

    @RepeatedTest(value = 10, name = RepeatedTest.SHORT_DISPLAY_NAME)
    void should_prompt_alert_show_message_after_clicking_ok() {
        driver.get(BASE_URL + "/alerts.php");
        driver.findElement(By.id("prompt-alert")).click();
        Alert promtptAlert = driver.switchTo().alert();

        promtptAlert.sendKeys("Lord Vader");
        promtptAlert.accept();

        String expectedOkMessage = "Hello Lord Vader! How are you today?";
        String actualOkMessage = driver.findElement(By.id("prompt-label")).getText();

        assertThat(actualOkMessage).isEqualTo(expectedOkMessage);
    }

    @RepeatedTest(value = 10, name = RepeatedTest.SHORT_DISPLAY_NAME)
    void should_confirm_alert_show_message_after_clicking_ok_and_cancel() {
        driver.get(BASE_URL + "/alerts.php");
        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().accept();

        String expectedOkMessage = "You pressed OK!";
        String actualOkMessage = driver.findElement(By.id("confirm-label")).getText();

        assertThat(actualOkMessage).isEqualTo(expectedOkMessage);

        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().dismiss();

        String expectedDismissMessage = "You pressed Cancel!";
        String actualDismissMessage = driver.findElement(By.id("confirm-label")).getText();

        assertThat(expectedDismissMessage).isEqualTo(actualDismissMessage);

    }
}
