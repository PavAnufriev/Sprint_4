package ru.papraktikum.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.papraktikum.pages.StartPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// Временно добавил комментарий для обновления коммита

import static org.junit.Assert.assertEquals;
import static ru.papraktikum.pages.StartPage.BASE_URL;
import static ru.papraktikum.pages.StartPage.expectedAnswers;

public class StartPageListTest {
    private WebDriver driver;
    private StartPage startPage;
    private WebDriverWait wait;



    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.get(BASE_URL);

        startPage = new StartPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        startPage.clickButtonCookie();

  }


   @Test
   public void clickAllList (){
        startPage.listVoprosiScroll();
        List <WebElement> listButtons = startPage.getListButtons();


        for (int i = 0; i < listButtons.size(); i++ ) {
            listButtons.get(i).click();

            String actualtext = startPage.parsingAnswerList(i);
            String expectedText = expectedAnswers.get(i);

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
