package ru.papraktikum.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.papraktikum.pages.OrderForm;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.papraktikum.pages.OrderFormDva;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.papraktikum.pages.StartPage;

// Временно добавил комментарий для обновления коммита

import java.time.Duration;


@RunWith(Parameterized.class)
public class SellFlow {

    private WebDriver driver;
    private WebDriverWait wait;
    private OrderForm orderForm;
    private OrderFormDva orderFormDva;
    private StartPage startPage;

    private final String browserName;
    private final String name;
    private final String familiya;
    private final String adres;
    private final String metro;
    private final String telefon;
    private final String data;
    private final String color;
    private final String  komentarii;

public SellFlow (String browserName, String name,String familiya, String adres,
                     String metro, String telefon, String data, String color, String  komentarii) {
    this.browserName = browserName;
    this.name = name;
    this.familiya = familiya;
    this.adres = adres;
    this.metro = metro;
    this.telefon = telefon;
    this.data = data;
    this.color = color;
    this.komentarii = komentarii;
}
    @Parameterized.Parameters
            public static Object[][] testData () {
        return new Object[][]{
                {"chrome", "Пашшшшшаааа", "Паааашшшшаааа", "Ул. Третьяковская д.1", "Белорусская", "12345678911", "25.05.2025", "black", "Тут будет комментарий номер один"},
                {"firefox", "Маааашшшааа", "МММааашшшшшаа", "Ул. Безвозвратная д.1", "Семеновская", "+1234567895", "10.03.2023", "grey", "Тут будет комментарий номер два"},
                    };
              }


    @Before
    public void setUp() {
    if (browserName.equalsIgnoreCase("chrome")) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    } else  {
            WebDriverManager.firefoxdriver().setup();
            driver = new org.openqa.selenium.firefox.FirefoxDriver();
        }


        driver.get("https://qa-scooter.praktikum-services.ru");

        orderForm = new OrderForm(driver);
        orderFormDva = new OrderFormDva(driver);
        startPage = new StartPage(driver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }


    @Test
    public void throughAllFlow() {
        // Первая форма заказа
        startPage.clickButtonCookie();
        startPage.clickButtonZazatHigh();
        orderForm.clickSendPoleImya(name);
        orderForm.clickSendPoleFamiliya(familiya);
        orderForm.clickSendPoleAdres(adres);
        orderForm.clickSendPoleMetro(metro);
        orderForm.clickSendPoleTelefon(telefon);
        orderForm.clickDalee();

        //Вторая часть заказа
        orderFormDva.clickPoleKalendar(data);
        orderFormDva.clickPoleArenda();
        orderFormDva.clickCvetSamokata(color);
        orderFormDva.clickPoleKomentarii(komentarii);

        //Всплывающие окна
        orderFormDva.clickKnopkaZakazat();
        orderFormDva.clickKnopkaDa();
        orderFormDva.clickOknoNomerZakaza();


        }

    @After
    public void endTest (){
        driver.quit();
    }

    
  }
