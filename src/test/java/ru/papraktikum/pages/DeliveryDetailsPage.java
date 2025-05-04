package ru.papraktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Временно добавил комментарий для обновления коммита

public class DeliveryDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DeliveryDetailsPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    //Локаторы
    //Локатор для поля Календарь
    private By fieldKalendar = By.xpath("//input[contains (@placeholder, '* Когда привезти самокат')]");
    //Локатор для поля Срок аренды
    private By fieldSrokArendi = By.xpath("//div[text()= '* Срок аренды']");
    //Локатор для чекбокса Цвет самоката
    private By fieldCvetSamokataBlack = By.xpath("//input[@id =  'black']");
    private By fieldCvetSamokataGrey = By.xpath("//input[@id =  'grey']");
    //Локатор для поля Комментарий для курьера
    private By fieldKommentarii = By.xpath("//input[contains (@placeholder, 'Комментарий для курьера')]");
    //Локатор для кнопки Заказать
    private By buttonZakazat = By.xpath("//button[contains (@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    //Локатор выпадающего списка Срок аренды
    private By arrayArenda = By.xpath("//div[text()='двое суток']");
    //Локатор Всплывающее окно подтверждения
    private By buttonOknoDa = By.xpath("//button[contains (@class, 'Button_Middle__1CSJM') and text()='Да']");
    //Локатор Всплывающее окно номер заказа
    private By windowNomerZakaza = By.xpath("//button[contains(text(), 'Посмотреть статус')]");
    //Локатор в всплывающем окне Заказ оформлен
    private By confirmationOrder = By.xpath("//*[text()='Заказ оформлен']");


    //Кликаем на поле вносим данные
    public void clickFieldKalendar(String data){
        WebElement setFieldKalendar = driver.findElement(fieldKalendar);
        setFieldKalendar.click();
        setFieldKalendar.sendKeys(data);
        setFieldKalendar.sendKeys(Keys.ENTER);
        }

    public void clickFieldArenda () {
        WebElement setFieldArenda = driver.findElement(fieldSrokArendi);
        setFieldArenda.click();
        wait.until(ExpectedConditions.elementToBeClickable(arrayArenda)).click();
        //setFieldArenda.sendKeys(Keys.ENTER);
    }

   public void clickCvetSamokata (String color) {
       WebElement checkBoxBlack = driver.findElement(fieldCvetSamokataBlack);
       WebElement checkBoxGrey = driver.findElement(fieldCvetSamokataBlack);
        if (color.equalsIgnoreCase( "grey")) {
            checkBoxGrey.click();
            } else if (color.equalsIgnoreCase("black")){ checkBoxBlack.click();}

   }

    public void clickFieldKomentarii (String komentarii) {
        WebElement setFieldKomentarii = driver.findElement(fieldKommentarii);
        setFieldKomentarii.click();
        setFieldKomentarii.sendKeys(komentarii);
        setFieldKomentarii.sendKeys(Keys.ENTER);
    }

    public void clickButtonZakazat (){
        driver.findElement(buttonZakazat).click();
    }

    public void clickButtonDa () {
        WebElement buttonDa = wait.until(ExpectedConditions.elementToBeClickable(buttonOknoDa));
        buttonDa.click();
    }

    public void clickWindowNomerZakaza () {
        WebElement buttonWindow = wait.until(ExpectedConditions.elementToBeClickable(windowNomerZakaza));
        buttonWindow.click();
    }

    public String getConfimationText () {
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationOrder));
        return confirmation.getText();
    }

    public void orderPageTwo (String data, String color, String komentarii) {

        clickFieldKalendar(data);
        clickFieldArenda();
        clickCvetSamokata(color);
        clickFieldKomentarii(komentarii);
        clickButtonZakazat();
        clickButtonDa();

    }

}


