package ru.papraktikum.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.papraktikum.pages.StartPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Временно добавил комментарий для обновления коммита

import static org.junit.Assert.assertEquals;

public class StartPageListTest {
    private WebDriver driver;
    private StartPage startPage;
    private WebDriverWait wait;



    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        startPage = new StartPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Скролим к нашему спсику листу
        WebElement listPosition = driver.findElement(By.xpath("//div[@id='accordion__heading-0']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", listPosition);

        startPage.clickButtonCookie();
    }


   @Test
   public void clickAllList (){
       ArrayList<String>  expectedOtveti  = new ArrayList<>(
                Arrays.asList("Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."));

       List<WebElement> listButtons = startPage.getListButtons();


        for (int i = 0; i < listButtons.size(); i++ ) {
            listButtons.get(i).click();

            String actualtext = startPage.parsingAnswerList(i);
            String expectedText = expectedOtveti.get(i);

            // Выводим
            System.out.println("Проверяем элемент № " + i);
            System.out.println("Ожидаемый текст: " + expectedText);
            System.out.println("Фактический текст: " + actualtext);

            assertEquals("Текст не совпадает для элемента № " + i, expectedText, actualtext);
        }

       }


    @After
    public void endTest (){
        driver.quit();
    }

}
