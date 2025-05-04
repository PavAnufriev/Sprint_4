package ru.papraktikum.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.papraktikum.pages.OrderFormPage;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.papraktikum.pages.OrderFormDvaPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.papraktikum.pages.StartPage;

// Временно добавил комментарий для обновления коммита

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static ru.papraktikum.pages.StartPage.BASE_URL;


@RunWith(Parameterized.class)
public class SellFlowTest {


    private final String browser;
    private WebDriver driver;
    private WebDriverWait wait;
    private OrderFormPage orderForm;
    private OrderFormDvaPage orderFormDva;
    private StartPage startPage;

    private final String name;
    private final String familiya;
    private final String adres;
    private final String metro;
    private final String telefon;
    private final String data;
    private final String color;
    private final String  komentarii;

public SellFlowTest( String name, String familiya, String adres,
                    String metro, String telefon, String data, String color, String  komentarii, String browser) {
    this.name = name;
    this.familiya = familiya;
    this.adres = adres;
    this.metro = metro;
    this.telefon = telefon;
    this.data = data;
    this.color = color;
    this.komentarii = komentarii;
    this.browser = browser;
}
    @Parameterized.Parameters
            public static Object[][] testData () {
        return new Object[][]{
                {"Пашшшшшаааа", "Паааашшшшаааа", "Ул. Третьяковская д.1", "Белорусская", "12345678911", "25.05.2025", "black", "Тут будет комментарий номер один", "chrome"},
                {"Маааашшшааа", "МММааашшшшшаа", "Ул. Безвозвратная д.1", "Семеновская", "+1234567895", "10.03.2023", "grey", "Тут будет комментарий номер два", "chrome"},
                {"Пашшшшшаааа", "Паааашшшшаааа", "Ул. Третьяковская д.1", "Белорусская", "12345678911", "25.05.2025", "black", "Тут будет комментарий номер один", "firefox"},
                {"Маааашшшааа", "МММааашшшшшаа", "Ул. Безвозвратная д.1", "Семеновская", "+1234567895", "10.03.2023", "grey", "Тут будет комментарий номер два", "firefox"},
                    };
              }


    @Before
    public void setUp() {

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();

        driver.get(BASE_URL);



        orderForm = new OrderFormPage(driver);
        orderFormDva = new OrderFormDvaPage(driver);
        startPage = new StartPage(driver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }


    @Test
    public void throughFlowTestHighButton () {
        // Проверка с верхней кнопкой Заказать
        startPage.highButtonZakazat();
        orderForm.orderPageOne(name, familiya, adres, metro, telefon );
        orderFormDva.orderPageTwo(data, color, komentarii );

        String actualText = orderFormDva.getConfimationText();
        assertTrue("Нет подтверждения заказа!", actualText.contains("Заказ оформлен"));

        orderFormDva.clickWindowNomerZakaza();
}

    @Test
    public void throughFlowTestLowButton () {
    // Проверка с нижней кнопкой Заказать
        startPage.lowButtonZakazat();
        orderForm.orderPageOne(name, familiya, adres, metro, telefon );
        orderFormDva.orderPageTwo(data, color, komentarii );

        String actualText = orderFormDva.getConfimationText();
        assertTrue("Нет подтверждения заказа!", actualText.contains("Заказ оформлен"));

        orderFormDva.clickWindowNomerZakaza();
       }

    @After
    public void endTest (){
        driver.quit();
    }

    
  }
