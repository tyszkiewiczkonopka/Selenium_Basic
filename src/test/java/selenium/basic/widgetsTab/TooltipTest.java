package selenium.basic.widgetsTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import selenium.basic.BaseTest;

public class TooltipTest extends BaseTest {
    @RepeatedTest(value = 10)
    void should_print_tooltip_message_to_console() {
        driver.get(BASE_URL + "/tooltip.php");
        String tooltipMessage = driver.findElement(By.cssSelector("input#age")).getAttribute("title");
        System.out.println(tooltipMessage);
    }
}