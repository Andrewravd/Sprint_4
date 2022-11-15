package Features;
import Pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class QuestionsTest {
    public WebDriver driver;

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
    public void tearDown()  {
        driver.quit();
        }

//сам тест
    @Test
    public void test() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.clickOnCookieButton();
        mainPage.checkAnswer();
    }
}
