package Features;

import Pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class QuestionsTest {
    public WebDriver driver;
    private final String expected;
    private final String result;
    private final String xpath;

    //создаем конструктор
    public QuestionsTest(String xpath, String expected, String result) {
        this.expected = expected;
        this.result = result;
        this.xpath = xpath;
    }

    //указываем тестовые данные
    @Parameterized.Parameters// добавили аннотацию
    public static Object[][] data() {
        return new Object[][]{
                {".//div[@id='accordion__heading-0']", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", ".//div[@class='accordion__item']//p[starts-with(text(),'Сутки')]"},
                {".//div[@id='accordion__heading-1']", "Пока что у нас так: один заказ — один самокат. Если хотите покататься" +
                        " с друзьями, можете просто сделать несколько заказов" + " — один за другим.", ".//div[@class='accordion__item']//p[starts-with(text(),'Пока что у нас')]"},
                {".//div[@id='accordion__heading-2']", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды" +
                        " начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда" +
                        " закончится 9 мая в 20:30.", ".//div[@class='accordion__item']//p[starts-with(text(),'Допустим, вы оформляете')]"},
                {".//div[@id='accordion__heading-3']", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", ".//div[@class='accordion__item']//p[starts-with(text(),'Только начиная')]"},
                {".//div[@id='accordion__heading-4']", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        ".//div[@class='accordion__item']//p[starts-with(text(),'Пока что нет!')]"},
                {".//div[@id='accordion__heading-5']", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне." +
                        " Зарядка не понадобится.", ".//div[@class='accordion__item']//p[starts-with(text(),'Самокат приезжает к вам')]"},
                {".//div[@id='accordion__heading-6']", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        ".//div[@class='accordion__item']//p[starts-with(text(),'Да, пока самокат')]"},
                {".//div[@id='accordion__heading-7']", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", ".//div[@class='accordion__item']//p[starts-with(text(),'Да, обязательно.')]"},
        };
    }


    //методы, которые будут выполняться перед тестом
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriver 2");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    //закрываем браузер после прохождения теста
    @After
    public void tearDown() {
        driver.quit();
    }

    //сам тест
    @Test
    public void test() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.clickOnCookieButton();
        mainPage.checkThatQuestionIsDisplayed(xpath, result);
        mainPage.checkText(expected, result);
    }
}
