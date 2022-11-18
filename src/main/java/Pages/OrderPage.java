package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertEquals;

public class OrderPage {
    public WebDriver driver;

    public OrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //поле "Имя"
    @FindBy(xpath = ".//input[@placeholder='* Имя']")
    private WebElement inputName;
    //поле "Фамилия"
    @FindBy(xpath = ".//input[@placeholder='* Фамилия']")
    private WebElement inputSurname;
    //поле "Адрес"
    @FindBy(xpath = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement inputAdresse;
    //поле "Станция метро"
    @FindBy(xpath = ".//input[@placeholder='* Станция метро']")
    private WebElement inputMetroStation;
    //поле "Телефон"
    @FindBy(xpath = ".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement inputNumber;
    //кнопка "Далее"
    @FindBy(xpath = ".//button[text()='Далее']")
    private WebElement buttonNext;
    //поле "Когда привезти самокат"
    @FindBy(xpath = ".//input[@placeholder='* Когда привезти самокат']")
    private WebElement inputDate;
    //поле "срок аренды"
    @FindBy(xpath = ".//div[@class='Dropdown-control']")
    private WebElement inputPeriod;
    //поле "комментарий для курьера"
    @FindBy(xpath = ".//input[@placeholder='Комментарий для курьера']")
    private WebElement inputComment;
    //кнопка "Заказать"
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private WebElement buttonMakeOrderMiddle;
    //кнопка "Да"
    @FindBy(xpath = ".//button[text()='Да']")
    private WebElement buttonYes;
    //окно "Заказ оформлен"
    @FindBy(xpath = ".//div[@class='Order_ModalHeader__3FDaJ']")
    private WebElement divSuccess;

    //метод заполнения строки "Имя"
    public void setInputName(String name) {
        inputName.sendKeys(name);
    }

    //метод заполнения строки "Фамилия"
    public void setInputSurname(String surname) {
        inputSurname.sendKeys(surname);
    }

    //метод заполнения строки "Адрес"
    public void setInputAdresse(String adresse) {
        inputAdresse.sendKeys(adresse);
    }

    //метод заполнения строки "Станция метро"
    public void setInputMetroStation(String metro) {
        inputMetroStation.sendKeys(metro);
        inputMetroStation.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

    }

    //метод заполнения строки "Телефон"
    public void setInputNumber(String number) {
        inputNumber.sendKeys(number);
    }

    //метод клика по кнопке "Далее"
    public void clickButtonNext() {
        buttonNext.click();
    }

    //метод заполнения строки "Дата"
    public void setInputDate(String date) {
        inputDate.sendKeys(date);
        inputDate.sendKeys(Keys.ENTER);
    }

    //метод заполнения строки "Период"
    public void setInputPeriod() {
        inputPeriod.click();
        driver.findElement(By.xpath(".//div[text()='двое суток']")).click();

    }

    //метод заполнения строки "Комментарий"
    public void setInputComment(String comment) {
        inputComment.sendKeys(comment);
    }

    //метод клика по кнопке  "Заказать"
    public void clickButtonMakeOrderMiddle() {
        buttonMakeOrderMiddle.click();
    }

    //метод клика по кнопке "Да"
    public void clickButtonYes() {
        buttonYes.click();
    }

    //Метод проверки, что заказ оформлен
    public void checkStatusOrder() {
        String text = divSuccess.getText();
        assertEquals("Заказ оформлен", text);

    }
}
