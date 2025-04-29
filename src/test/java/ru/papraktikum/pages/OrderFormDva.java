package ru.papraktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Временно добавил комментарий для обновления коммита

public class OrderFormDva {
    private WebDriver driver;
    private WebDriverWait wait;

    public OrderFormDva (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }


    //Локаторы
    //Локатор для поля Календарь
    private By poleKalendar = By.xpath("//input[contains (@placeholder, '* Когда привезти самокат')]");
    //Локатор для поля Срок аренды
    private By poleSrokArendi = By.xpath("//div[text()= '* Срок аренды']");
    //Локатор для чекбокса Цвет самоката
    private By poleCvetSamokataBlack = By.xpath("//input[@id =  'black']");
    private By poleCvetSamokataGrey = By.xpath("//input[@id =  'grey']");
    //Локатор для поля Комментарий для курьера
    private By poleKommentarii = By.xpath("//input[contains (@placeholder, 'Комментарий для курьера')]");
    //Локатор для кнопки Заказать
    private By knopkaZakazat = By.xpath("//button[contains (@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    //Локатор выпадающего списка Срок аренды
    private By spisokArenda = By.xpath("//div[text()='двое суток']");
    //Локатор Всплывающее окно подтверждения
    private By knopkaOknoDa = By.xpath("//button[contains (@class, 'Button_Middle__1CSJM') and text()='Да']");
    //Локатор Всплывающее окно номер заказа
    private By oknoNomerZakaza = By.xpath("//button[contains (@class, 'Button_Middle__1CSJM') and text()='Посмотреть статус']");

    //Кликаем на поле вносим данные
    public void clickPoleKalendar (String data){
        WebElement fieldKalendar = driver.findElement(poleKalendar);
        fieldKalendar.click();
        fieldKalendar.sendKeys(data);
        fieldKalendar.sendKeys(Keys.ENTER);
        }

    public void clickPoleArenda () {
        WebElement fieldArenda = driver.findElement(poleSrokArendi);
        fieldArenda.click();
        driver.findElement(spisokArenda).click();
        //fieldArenda.sendKeys(Keys.ENTER);
    }

   public void clickCvetSamokata (String color) {
       WebElement checkBoxBlack = driver.findElement(poleCvetSamokataBlack);
       WebElement checkBoxGrey = driver.findElement(poleCvetSamokataGrey);
        if (color.equalsIgnoreCase( "grey")) {
            checkBoxGrey.click();
            } else if (color.equalsIgnoreCase("black")){ checkBoxBlack.click();}

   }

    public void clickPoleKomentarii (String komentarii) {
        WebElement fieldKomentarii = driver.findElement(poleKommentarii);
        fieldKomentarii.click();
        fieldKomentarii.sendKeys(komentarii);
        fieldKomentarii.sendKeys(Keys.ENTER);
    }

    public void clickKnopkaZakazat (){
        driver.findElement(knopkaZakazat).click();
    }

    public void clickKnopkaDa () {
        driver.findElement(knopkaOknoDa).click();
    }

    public void clickOknoNomerZakaza () {
        driver.findElement(oknoNomerZakaza).click();
    }
}


