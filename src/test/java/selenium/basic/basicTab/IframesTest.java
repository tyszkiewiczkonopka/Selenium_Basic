package selenium.basic.basicTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.basic.BaseTest;

public class IframesTest extends BaseTest {

    @RepeatedTest(value = 10)
    void should_switch_between_iframes() {
        driver.get("http://automation-practice.emilos.pl/iframes.php");

        switchToFrame("iframe1");
        fillShortForm("Magda", "Tyszkiewicz");
        backToDefaultContent();

        switchToFrame("iframe2");
        fillExtendForm("magda", "pass", "europe", "gridRadios4");
        backToDefaultContent();

        driver.findElement(By.cssSelector("li.nav-ite"));
    }

    private void switchToFrame(String iframeNameOrId) {
        driver.switchTo().frame(iframeNameOrId);
    }

    private void fillShortForm(String firstName, String surname) {
        driver.findElement(By.id("inputFirstName3")).sendKeys(firstName);
        driver.findElement(By.id("inputSurname3")).sendKeys(surname);
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }

    private void fillExtendForm(String login, String password, String continentOptionValue, String experienceOptionId) {
        driver.findElement(By.id("inputLogin")).sendKeys(login);
        driver.findElement(By.id("inputPassword")).sendKeys(password);
        WebElement selectContinentsElement = driver.findElement(By.id("inlineFormCustomSelectPref"));

        Select select = new Select(selectContinentsElement);
        select.selectByValue(continentOptionValue);
        driver.findElement(By.id(experienceOptionId)).click();
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

    }

    private void backToDefaultContent() {
        driver.switchTo().defaultContent();

    }
}
