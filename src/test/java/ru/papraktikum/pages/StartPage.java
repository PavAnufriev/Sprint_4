package ru.papraktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// Временно добавил комментарий для обновления коммита


public class StartPage {
    private WebDriver driver;
    private WebDriverWait wait;



    // Локаторы
    // Локатор для выпадающего списка с вопросами
    private By voprosiOvajnom = By.xpath("//div[@class = 'accordion__button']");
    // Локатор для текста из выпадающего списка
    private By voprosiOvajnomText = By.xpath("//div[@class = 'accordion__panel']/p");
    // Кукиии!!!
    private By ubratCookie = By.xpath("//button[@id = 'rcc-confirm-button']");

    // Локатор для кнопки заказать вверху справа
    private By buttonZakazatHigh = By.xpath("//button[@class = 'Button_Button__ra12g']");

    //Локатор для кнопки заказать под описанием
    private By buttonZakazatLow = By.xpath("//div[@class = 'Home_FinishButton__1_cWm']/button");


    public StartPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
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
        driver.findElement(ubratCookie).click();
    }

    // Создаем и наполняем список
    public List <WebElement> getListButtons () {
        return driver.findElements(voprosiOvajnom);
    }


    // Заполняем лист с ответами
    public List<WebElement> getAnswerTextList(){
        return  driver.findElements(voprosiOvajnomText);
    }

    // Кликнуть на вопрос по индексу
    public void clickListButtons (int index) {
        getListButtons().get(index).click();
    }

    // Спарсить текст ответа
    public String parsingAnswerList (int index) {
        List<WebElement> otveti = getAnswerTextList();
        WebElement otvet = otveti.get(index);
        wait.until(ExpectedConditions.visibilityOf(otvet));
            return otvet.getText();
        }

}
