import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FormTest extends BaseTest {
    private final Random random = new Random();
    private Logger logger = LoggerFactory.getLogger("FormTest");

    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_successfully_sign_in() {
        driver.get(BASE_URL + "/form.php");

        fillform();
        chooseFileToUpload();
        submitForm();

        String validationSuccessMessage = "Form send with success";
        String actualMessage = driver.findElement(By.id("validator-message")).getText();

        assertThat(actualMessage)
                .isEqualTo(validationSuccessMessage)
                .withFailMessage("Validation message not as expected.");
    }

    @Test
        //@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_find_downloaded_file_in_default_folder() throws InterruptedException {
        driver.get(BASE_URL + "/form.php");
        fillform();

        File directory = new File(DEFAULT_DIRECTORY);
        File[] files = directory.listFiles();
        int numberOfFiles = 0;

        if (files != null) {
            numberOfFiles = files.length;
            logger.info("Number of files in the directory: " + numberOfFiles);
        } else {
            logger.error("The directory " + DEFAULT_DIRECTORY + " is empty.");
        }
        driver.findElement(By.cssSelector(".btn.btn-secondary")).click();

        Thread.sleep(2000);

        String expectedFileName = "test-file-todownload.xlsx";


//        assertThat(numberOfFiles)
//                .withFailMessage("Number of files not as expected")
//                .isEqualTo(numberOfFiles + 1);
//
//        boolean fileFound = Arrays.stream(directory.listFiles())
//                .anyMatch(file -> file.getName().equals(expectedFileName));
        File[] newFiles = directory.listFiles();


        assertThat(newFiles)
                //.withFailMessage("File not found in the directory: "+ DEFAULT_DIRECTORY)
                .extracting(File::getName) // Extract the names of the files
                .contains(expectedFileName);
    }

    private void fillform() {
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
        String uploadedFileDirectory = "/Users/magdalena/Documents/GRAFIKA/Ikony/PNG/basen.png";
        driver.findElement(By.id("chooseFile")).sendKeys(uploadedFileDirectory);
    }

    private void submitForm() {
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }


}

