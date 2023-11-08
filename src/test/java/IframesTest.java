import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class IframesTest extends BaseTest{

    @RepeatedTest(value = 10, name = RepeatedTest.SHORT_DISPLAY_NAME)
    void should_switch_between_iframes(){
        driver.get("http://automation-practice.emilos.pl/iframes.php");
        driver.switchTo().frame("iframe1");
        driver.findElement(By.id("inputFirstName3")).sendKeys("Magdalena");
        driver.findElement(By.id("inputSurname3")).sendKeys("Tyszkiewicz");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.switchTo().defaultContent();

        driver.switchTo().frame("iframe2");
        driver.findElement(By.id("inputLogin")).sendKeys("magda");
        driver.findElement(By.id("inputPassword")).sendKeys("password");
        WebElement selectContinentsElement = driver.findElement(By.id("inlineFormCustomSelectPref"));
        Select select = new Select(selectContinentsElement);
        select.selectByValue("south-america");
        driver.findElement(By.id("gridRadios3")).click();
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("li.nav-ite"));
    }
}
