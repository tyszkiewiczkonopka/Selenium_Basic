import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class FormTest extends BaseTest {
    private final Random random = new Random();


    @Test
    //@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_successfully_sign_in() {
        driver.get(BASE_URL + "/form.php");

        driver.findElement(By.id("inputFirstName3")).sendKeys("Magdalena");
        driver.findElement(By.id("inputLastName3")).sendKeys("Tyszkiewicz");
        driver.findElement(By.id("inputEmail3")).sendKeys("mtyszkiewicz@sii.pl");

        List<WebElement> sexRadioButtons = driver.findElements(By.name("gridRadiosSex"));
        int randomSexRadioButton = random.nextInt(sexRadioButtons.size());
        sexRadioButtons.get(randomSexRadioButton).click();

        driver.findElement(By.id("inputAge3")).sendKeys("18");

        List<WebElement> experienceRadioButtons = driver.findElements(By.name("gridRadiosExperience"));
        int randomExperienceRadioButton = random.nextInt(experienceRadioButtons.size());
        experienceRadioButtons.get(randomExperienceRadioButton).click();

        driver.findElement(By.id("gridCheckAutomationTester")).click();

        WebElement selectContinentsElement = driver.findElement(By.id("selectContinents"));
        Select selectContinent = new Select(selectContinentsElement);
        List<WebElement> continents = selectContinent.getOptions();
        selectContinent.selectByIndex(random.nextInt(continents.size() -1) +1);

    }
}
