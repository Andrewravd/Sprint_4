package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class MainPage {
    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //локатор кнопки "Да все привыкли"
    @FindBy(xpath = ".//button[text()='да все привыкли']")
    WebElement cookieButton;

    //метод клика по кнопке "да все привыкли"
    public void clickOnCookieButton() {
        cookieButton.click();
    }

    //метод клика по кнопке "Заказать" в верхнем углу экрана
    public void clickOnButtonMakeOrder(String string) {
        driver.findElement(By.xpath(string)).click();

    }

    //метод проверки отображения ответов на странице
    public void checkThatQuestionIsDisplayed(String xpath, String result) throws InterruptedException {
        WebElement question = driver.findElement(By.xpath(xpath));
        WebElement answer = driver.findElement(By.xpath(result));
        question.click();
            TimeUnit.SECONDS.sleep(1);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(answer));

    }
    public void checkText(String expected, String result) {
        String text = driver.findElement(By.xpath(result)).getText();
        assertEquals(expected, text );
    }
}
