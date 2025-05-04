package ru.papraktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

// Временно добавил комментарий для обновления коммита


public class StartPage {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private WebDriver driver;
    private WebDriverWait wait;

    public static final List<String> expectedAnswers = Arrays.asList (
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области.");




    // Локаторы
    //Локатор для выпадающего списка с текстом
    private By listVoprosi = By.xpath("//div[@id='accordion__heading-0']");
    // Локатор для выпадающего списка с вопросами
    private By voprosiOvajnom = By.xpath("//div[@class = 'accordion__button']");
    // Локатор для текста из выпадающего списка
    private By voprosiOvajnomText = By.xpath("//div[@class = 'accordion__panel']/p");
    // Кукиии!!!
    private By buttonCookie = By.xpath("//button[@id = 'rcc-confirm-button']");

    // Локатор для кнопки заказать вверху справа
    private By buttonZakazatHigh = By.xpath("//button[@class = 'Button_Button__ra12g']");

    //Локатор для кнопки заказать под описанием
    private By buttonZakazatLow = By.xpath("//div[@class = 'Home_FinishButton__1_cWm']/button");



    public StartPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }


    //Скролим до листа с вопросами
    public void listVoprosiScroll () {
        WebElement element = driver.findElement(listVoprosi);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Создаем и наполняем список
    public List <WebElement> getListButtons () {
        return driver.findElements(voprosiOvajnom);
    }

    // Кликнуть на вопрос по индексу
    public void clickListButtons (int index) {
        getListButtons().get(index).click();
    }

    // Заполняем лист с ответами
    public List<WebElement> getAnswerTextList(){
        return  driver.findElements(voprosiOvajnomText);
    }




    //Кликаем на кнопку Заказать вверху справа
    public void clickButtonZazatHigh (){
        driver.findElement(buttonZakazatHigh).click();
    }

    //Кликаем на кнопку Заказать под описанием
    public void clickButtonZakazatLow (){
        driver.findElement(buttonZakazatLow).click();
    }

    // Кликаем на куки
    public void clickButtonCookie (){
        driver.findElement(buttonCookie).click();
    }



    // Спарсить текст ответа
    public String parsingAnswerList (int index) {
        List<WebElement> otveti = getAnswerTextList();
        WebElement otvet = otveti.get(index);
        wait.until(ExpectedConditions.visibilityOf(otvet));
            return otvet.getText();
        }


    //Метод через верхнюю кнопку Заказать
    public void highButtonZakazat () {
        clickButtonCookie();
        clickButtonZazatHigh();

    }


    public void lowButtonZakazat () {
        clickButtonCookie();
        clickButtonZakazatLow();
    }

}
