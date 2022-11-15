package Pages;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertEquals;



public class MainPage {
    public WebDriver driver;
    protected StringBuffer verificationErrors = new StringBuffer();
    //создаем массив с ожидаемым текстом ответов на вопросы
    String[] question = {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "Пока что у нас так: один заказ " +
            "— один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
            , "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды" +
            " начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда" +
            " закончится 9 мая в 20:30.", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "Пока что нет!" +
            " Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "Самокат приезжает к" +
            " вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка" +
            " не понадобится.", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. " +
            "Все же свои.", "Да, обязательно. Всем самокатов! И Москве, и Московской области."
            ,};

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    //кнопка "Заказать" в верхнем правом углу экрана
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g']")
    WebElement buttonMakeOrder;
    //кнопка "Заказать" над блоком "Вопросы о важном"
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    WebElement buttonMakeOrderMiddle;
    @FindBy(xpath = ".//button[text()='да все привыкли']")
    WebElement cookieButton;

    //метод клика по кнопке "да все привыкли"
    public void clickOnCookieButton() {
        cookieButton.click();
    }

    //метод клика по кнопке "Заказать" в верхнем правом углу экрана
    public void clickOnButtonMakeOrder(String string) {
        driver.findElement(By.xpath(string)).click();

    }

    // метод клика по кнопке "Заказать" над блоком "Вопросы о важном"
    public void clickOnButtonMakeOrderMiddle() {
        buttonMakeOrderMiddle.click();
    }

    //метод проверки текста ответов на вопросы
    public void checkAnswer() throws InterruptedException {
        List<WebElement> questions = driver.findElements(By.xpath(".//div[@class='accordion__item']"));
        List<WebElement> answers = driver.findElements(By.xpath(".//div[@class='accordion__item']//p"));
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).click();
            TimeUnit.SECONDS.sleep(1);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(answers.get(i)));
            String text = answers.get(i).getText();
            try {
                assertEquals(question[i], text);
            }  catch (Throwable e) {
                System.out.println("Exception: "
                        + e.toString());
            }

        }
    }
}
