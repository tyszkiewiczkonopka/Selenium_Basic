package selenium.basic.widgetsTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import selenium.basic.BaseTest;

public class TooltipTest extends BaseTest {
    @Test
    void tooltipTest() {
        driver.get(BASE_URL + "/tooltip.php");

        String tooltipMessage = driver.findElement(By.cssSelector("input#age")).getAttribute("title");
        System.out.println(tooltipMessage);

    }
}