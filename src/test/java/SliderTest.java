import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class SliderTest extends BaseTest {
    private Logger logger = LoggerFactory.getLogger("SliderTestOnSlider");

    @ParameterizedTest
    @ValueSource(ints = {50, 80, 80, 20, 0})
    void sliderTest(int targetValue) {
        driver.get(BASE_URL + "/slider.php");
        WebElement handle = driver.findElement(By.id("custom-handle"));
        moveSliderHandle(handle, targetValue);
        assertThat(targetValue)
                .isEqualTo(getSliderValue(handle));
        logger.info("Slider moved to target value of " + targetValue);
    }

    private int getSliderValue(WebElement handle) {
        return Integer.parseInt(handle.getText());
    }

    private void moveSliderHandle(WebElement handle, int targetValue) {
        for (int i = 1; i <= targetValue; i++) {
            handle.sendKeys(Keys.ARROW_RIGHT);
        }
    }

}
