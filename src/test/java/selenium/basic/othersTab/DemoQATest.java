package selenium.basic.othersTab;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.basic.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoQATest extends BaseTest {
    @Test
    void demoTest() {
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
        //subjectInput.click(); // ElementClickInterceptedException
        Actions actions = new Actions(driver);
        actions.moveToElement(subjectInput).click().perform();

        subjectInput.sendKeys("m");
        WebElement mathsOption = driver.findElement(By.id("react-select-2-option-0"));
        //mathsOption.click(); // ElementClickInterceptedException
        actions.moveToElement(mathsOption).click().perform();
        //actions.moveToElement(subjectInput).click().perform();
        //subjectInput.click();

        subjectInput.sendKeys("a");
        WebElement artsOption = driver.findElement(By.id("react-select-2-option-2"));
        actions.moveToElement(artsOption).click().perform();
        //artsOption.click();





        List<WebElement> selectedSubjects = driver.findElements(By.cssSelector(".css-1rhbuit-multiValue"));
        List<String> selectedSubjectsTexts = new ArrayList<>();
        selectedSubjects.forEach(subject -> {
            String text = subject.getText();
            selectedSubjectsTexts.add(text);
            System.out.println(text);
        });
        assertThat(selectedSubjectsTexts).contains("Math").contains("Arts");

    }
}
