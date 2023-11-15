package selenium.basic.widgetsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.basic.BaseTest;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ModalDialogTest extends BaseTest {
    private final String name = "Magdalena";
    private final String email = "magda@gmail.com";
    private final String password = "admin";
    @RepeatedTest(value = 10)
    void should_add_new_user_to_users_table() {
        driver.get(BASE_URL + "/modal-dialog.php");
        driver.findElement(By.id("create-user")).click();

        fillModalForm();

        WebElement table = driver.findElement(By.id("users"));
        List<WebElement> userRows = table.findElements(By.cssSelector("tbody tr"));

        assertThat(userRows.size()).isEqualTo(2);
        assertThat(driver.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(1)")).getText()).isEqualTo(name);
        assertThat(driver.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(2)")).getText()).isEqualTo(email);
        assertThat(driver.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(3)")).getText()).isEqualTo(password);
    }

    private void fillModalForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("name"))));
        nameField.clear();
        nameField.sendKeys(name);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(By.cssSelector(".ui-dialog-buttonpane.ui-widget-content > div > button:nth-of-type(1)")).click();
    }
}

