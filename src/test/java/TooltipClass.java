import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TooltipClass extends BaseTest {
    @Test
    void tooltipTest() {
        driver.get(BASE_URL + "/tooltip.php");

        String tooltipMessage = driver.findElement(By.cssSelector("input#age")).getAttribute("title");
        System.out.println(tooltipMessage);

    }
}