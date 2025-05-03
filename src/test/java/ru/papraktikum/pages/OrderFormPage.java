package ru.papraktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

// Временно добавил комментарий для обновления коммита

public class OrderFormPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public OrderFormPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }


    //Локаторы
    //Локатор поля Имя
    private By fieldImya = By.xpath("//input[contains (@placeholder, 'Имя')]");
    //Локатор поля Фамилия
    private By fieldFamiliya = By.xpath("//input[contains (@placeholder, 'Фамилия')]");
    //Локатор поля Адрес
    private By fieldAdres = By.xpath("//input[contains (@placeholder, '* Адрес: куда привезти заказ')]");
    //Локатор выпадающего списка Станция метро
    private By fieldMetro = By.xpath("//input[contains (@placeholder, '* Станция метро')]");
    // Локатор для поля Номер телефона
    private By fieldTelefon = By.xpath("//input[contains (@placeholder, '* Телефон: на него позвонит курьер')]");
    //Локатор кнопки Далее
    private By buttonNext = By.xpath("//button[text()='Далее']");




    //Кликаем на поле и вводим данные
    public void clickSendFieldImya (String name){
        WebElement getFieldImya = driver.findElement(fieldImya);
        getFieldImya.click();
        getFieldImya.sendKeys(name);
    }

    public void clickSendFieldFamiliya (String familiya) {
        WebElement getFieldFamiliya = driver.findElement(fieldFamiliya);
        getFieldFamiliya.click();
        getFieldFamiliya.sendKeys(familiya);
    }

    public void clickSendFieldAdres (String adres) {
        WebElement getFieldAdres = driver.findElement(fieldAdres);
        getFieldAdres.click();
        getFieldAdres.sendKeys(adres);
    }

    public void clickSendFieldMetro (String metro) {
        WebElement getFieldMetro = driver.findElement(fieldMetro);
        getFieldMetro.click();
        getFieldMetro.sendKeys(metro);
        Actions actions = new Actions(driver);
        actions.moveToElement(getFieldMetro, 0, 25) //Блин я очень долго мучился с этим, не получается нормально выписать локатор из списка
                .click()
                .perform();
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(poleMetro));
    }

    public void clickSendFieldTelefon(String telefon) {
        WebElement getFieldTelefon = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldTelefon));
        getFieldTelefon.click();
        getFieldTelefon.sendKeys(telefon);
    }

    //Клик по кнопке Далее
    public void clickNext() {
        driver.findElement(buttonNext).click();
    }


    public void orderPageOne (String name, String familiya, String adres, String metro, String telefon) {
        clickSendFieldImya(name);
        clickSendFieldFamiliya(familiya);
        clickSendFieldAdres(adres);
        clickSendFieldMetro(metro);
        clickSendFieldTelefon(telefon);
        clickNext();
    }

}
