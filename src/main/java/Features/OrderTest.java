package Features;

import Pages.MainPage;
import Pages.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

//cоздаем класс
@RunWith(Parameterized.class)
public class OrderTest {
    private final String name;
    private final String surname;
    private final String adresse;
    private final String number;
    private final String date;
    private final String comment;
    private final String metro;
    public WebDriver driver;
    private final String makeOrder;


    //создаем конструктор
    public OrderTest(String name, String surname, String makeOrder, String adresse, String metro, String number, String date, String comment) {
        this.name = name;
        this.surname = surname;
        this.adresse = adresse;
        this.number = number;
        this.comment = comment;
        this.metro = metro;
        this.date = date;
        this.makeOrder = makeOrder;
    }
    //указываем тестовые данные
    @Parameterized.Parameters// добавили аннотацию
    public static Object[][] data() {
        return new Object[][]{
                {"Андрей", "Андреев", ".//button[@class='Button_Button__ra12g']", "молодежная 1", "Черкизовская", "89872342211", "13.12.2022", "тест1"},
                {"Антон", "Антонов", ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']","молодежная 2", "Сокольники", "89872342212", "14.12.2022", "тест2"},
                {"Игорь", "Игорев",".//button[@class='Button_Button__ra12g']", "молодежная 3", "Комсомольская", "89872342213", "15.12.2022", "тест3"},
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
    public void test() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.clickOnCookieButton();
        mainPage.clickOnButtonMakeOrder(makeOrder);
        orderPage.setInputName(name);
        orderPage.setInputSurname(surname);
        orderPage.setInputAdresse(adresse);
        orderPage.setInputMetroStation(metro);
        orderPage.setInputNumber(number);
        orderPage.clickButtonNext();
        orderPage.setInputDate(date);
        orderPage.setInputPeriod();
        orderPage.setInputComment(comment);
        orderPage.clickButtonMakeOrderMiddle();
        orderPage.clickButtonYes();
        orderPage.checkStatusOrder();
    }

}


