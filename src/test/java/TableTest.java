import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TableTest extends BaseTest {

    @Test
    void should_print_swiss_mountain_higher_than_4000() {

        driver.get(BASE_URL + "/table.php");
        WebElement mountain = driver.findElement(By.cssSelector(".table"));
        List<WebElement> rows = mountain.findElements(By.cssSelector("tbody tr"));


    }
}
