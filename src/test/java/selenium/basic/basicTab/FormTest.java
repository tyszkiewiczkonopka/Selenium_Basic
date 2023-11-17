package selenium.basic.basicTab;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.basic.BaseTest;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FormTest extends BaseTest {
    private final Random random = new Random();
    @RepeatedTest(value = 10)
    void should_successfully_sign_in() {
        driver.get(BASE_URL + "/form.php");

        fillForm();
        chooseFileToUpload();
        submitForm();

        String validationSuccessMessage = "Form send with success";
        String actualMessage = driver.findElement(By.id("validator-message")).getText();

        assertThat(actualMessage).isEqualTo(validationSuccessMessage);
    }


    private void fillForm() {
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
        selectContinent.selectByIndex(random.nextInt(continents.size() - 1) + 1);

        WebElement selectCommandsElement = driver.findElement(By.id("selectSeleniumCommands"));
        Select selectCommand = new Select(selectCommandsElement);
        selectCommand.selectByValue("switch-commands");
        selectCommand.selectByValue("wait-commands");
    }

    private void chooseFileToUpload() {
        String uploadedFileDirectory = System.getProperty("user.dir")+ "\\src\\test\\resources\\files\\search-bug.jpg";
        driver.findElement(By.id("chooseFile")).sendKeys(uploadedFileDirectory);
    }

    private void submitForm() {
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }


}

