package selenium.basic.basicTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.basic.BaseTest;

import java.util.List;

public class TableTest extends BaseTest {

    @Test
    void should_print_swiss_mountain_higher_than_4000() {

        driver.get(BASE_URL + "/table.php");
        findInTable(driver);

    }

    public void findInTable(WebDriver driver) {
        WebElement mountainTable = driver.findElement(By.cssSelector(".table"));
        List<WebElement> rows = mountainTable.findElements(By.cssSelector("tbody tr"));

        for (WebElement row : rows) {
            int rowNumber = rows.indexOf(row) + 1;

            WebElement stateCell = driver.findElement(By.cssSelector("tr:nth-of-type(" + rowNumber + ") > td:nth-of-type(3)"));
            WebElement heightCell = driver.findElement(By.cssSelector("tr:nth-of-type(" + rowNumber + ") > td:nth-of-type(4)"));
            int height = Integer.parseInt(heightCell.getText());

            if (stateCell.getText().equals("Switzerland") && height > 4000) {
                System.out.println(
                        "Rank: " + driver.findElement(By.cssSelector("tr:nth-of-type(" + rowNumber + ") > th")).getText() +
                                ", Peak: " + driver.findElement(By.cssSelector("tr:nth-of-type(" + rowNumber + ") > " +
                                "td:nth-of-type(1)")).getText() +
                                ", Mountain range: " + driver.findElement(By.cssSelector("tr:nth-of-type(" + rowNumber + ") > td:nth-of-type(2)")).getText());
            }
        }
    }
}
