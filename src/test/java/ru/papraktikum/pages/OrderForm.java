package ru.papraktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Временно добавил комментарий для обновления коммита

public class OrderForm {
    private WebDriver driver;
    private WebDriverWait wait;


    public OrderForm (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }


    //Локаторы
    //Локатор поля Имя
    private By poleImya = By.xpath("//input[contains (@placeholder, 'Имя')]");
    //Локатор поля Фамилия
    private By poleFamiliya = By.xpath("//input[contains (@placeholder, 'Фамилия')]");
    //Локатор поля Адрес
    private By poleAdres = By.xpath("//input[contains (@placeholder, '* Адрес: куда привезти заказ')]");
    //Локатор выпадающего списка Станция метро
    private By poleMetro = By.xpath("//input[contains (@placeholder, '* Станция метро')]");
    // Локаторя поля Номер телефона
    private By poleTelefon = By.xpath("//input[contains (@placeholder, '* Телефон: на него позвонит курьер')]");
    //Локатор кнопки Далее
    private By knopkaDalee = By.xpath("//button[text()='Далее']");




    //Кликаем на поле и вводим данные
    public void clickSendPoleImya (String name){
        WebElement fieldPole = driver.findElement(poleImya);
        fieldPole.click();
        fieldPole.sendKeys(name);
    }

    public void clickSendPoleFamiliya (String familiya) {
        WebElement fieldFamiliya = driver.findElement(poleFamiliya);
        fieldFamiliya.click();
        fieldFamiliya.sendKeys(familiya);
    }

    public void clickSendPoleAdres (String adres) {
        WebElement fieldAdres = driver.findElement(poleAdres);
        fieldAdres.click();
        fieldAdres.sendKeys(adres);
    }

    public void clickSendPoleMetro (String metro) {
        WebElement fieldMetro = driver.findElement(poleMetro);
        fieldMetro.click();
        fieldMetro.sendKeys(metro);
        Actions actions = new Actions(driver);
        actions.moveToElement(fieldMetro, 0, 25) //Блин я очень долго мучился с этим, не получается нормально выписать локатор из списка
                .click()
                .perform();
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(poleMetro));
    }

    public void clickSendPoleTelefon (String telefon) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[contains (@placeholder, '* Телефон: на него позвонит курьер')]")
        ));
        WebElement fieldTelefon = driver.findElement(poleTelefon);
        fieldTelefon.click();
        fieldTelefon.sendKeys(telefon);
    }


    //Клик по кнопке Далее
    public void clickDalee () {
        driver.findElement(knopkaDalee).click();
    }
}
