package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage {

    //==============================ЛОКАТОРЫ(1 ФОРМА)==================================================================
    private final By firstNameField = By.xpath("//input[@placeholder='* Имя']");  //поле Имя
    private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");  //поле Фамилия
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");  //поле Адрес
    private final By station = By.className("select-search__input");   //поле Станция метро
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");  //поле Номер телефона
    private final By nextButton = By.xpath("//button[contains(text(),'Далее')]");  //кнопка Далее

    //==============================ЛОКАТОРЫ(2 ФОРМА)==================================================================
    private final By deliveryDatePicker = By.cssSelector("input[placeholder='* Когда привезти самокат']");  //поле Когда привезти самокат
    private final By rentalPeriodList = By.cssSelector(".Dropdown-placeholder");  //поле Срок аренды
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");  //поле Комментарий
    private final By orderButton = By.cssSelector("button[class='Button_Button__ra12g Button_Middle__1CSJM']");  //кнопка Заказать

    //==============================ЛОКАТОР(ФОРМА ДЛЯ ПОДТВЕРЖДЕНИЯ)===================================================
    private final By confirmOrderButton = By.xpath("//button[contains(text(),'Да')]");  //кнопка Да

    //==============================ЛОКАТОР(ФОРМА ДЛЯ ВЫВОДА НОМЕРА ЗАКАЗА)============================================
    private final By successModal = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");  //текст формы Заказ оформлен

    //==============================ИЗОБРАЖЕНИЕ С ТЕКСТОМ ОШИБКИ=======================================================
    private final By errorImage = By.xpath("//img[@alt='Not found']");  //изображение Not Found

    //==============================ЛОКАТОРЫ ДЛЯ ОШИБОК(1 ФОРМА)=======================================================
    //текст ошибки поля Имя
    private final By firstNameFieldError = By.xpath(".//input[@placeholder='* Имя']/following-sibling::div[contains(@class, 'Input_ErrorMessage__3HvIb')]");
    //текст ошибки поля Фамилия
    private final By lastNameFieldError = By.xpath(".//input[@placeholder='* Фамилия']/following-sibling::div[contains(@class, 'Input_ErrorMessage__3HvIb')]");
    //текст ошибки поля Адрес
    private final By addressFieldError = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']/following-sibling::div[contains(@class, 'Input_ErrorMessage__3HvIb')]");
    //текст ошибки поля Станция метро
    private final By metroFieldError = By.xpath(".//div[contains(@class, 'Order_MetroError__1BtZb')]");
    //текст ошибки поля Номер телефона
    private final By phoneFieldError = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']/following-sibling::div[contains(@class, 'Input_ErrorMessage__3HvIb')]");


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void clickNextButton() {
        click(nextButton);
    }

    public void fillFirstForm(String firstName, String lastName, String address, String stationMetro, String phone) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(addressField, address);

        if (stationMetro != null && !stationMetro.isEmpty()) {
            click(station);
            type(station, stationMetro);
            driver.findElement(station).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        }

        type(phoneField, phone);
    }

    public void fillSecondForm(String deliveryDate, String rentalPeriod, String rentalOption, String comment) {

        if (deliveryDate != null && !deliveryDate.isEmpty()) {
            type(deliveryDatePicker, deliveryDate);
            driver.findElement(deliveryDatePicker).sendKeys(Keys.ENTER);
        }

        if (rentalPeriod != null && !rentalPeriod.isEmpty()) {
            click(rentalPeriodList);
            click(By.xpath("//div[text()='" + rentalPeriod + "']"));
        }

        if (rentalOption != null && !rentalOption.isEmpty()) {
            click(By.id(rentalOption));
        }

        type(commentField, comment);
    }

    //======================================ORDER STATUS TEST===================================================
    public void clickOrder() {
        click(orderButton);
    }

    public void confirmOrder() {
         click(confirmOrderButton);
    }

    public boolean isOrderSuccess() {
        return waitForVisible(successModal).isDisplayed();
    }

    public boolean isErrorDisplayed() {
        return waitForVisible(errorImage).isDisplayed();
    }
    //=================================================================================================================

    //======================================ERROR FIELDS TEST==========================================================
    public boolean isFirstNameErrorVisible() {
        return waitForVisible(firstNameFieldError).isDisplayed();
    }

    public boolean isLastNameErrorVisible() {
        return waitForVisible(lastNameFieldError).isDisplayed();
    }

    public boolean isAddressErrorVisible() {
        return waitForVisible(addressFieldError).isDisplayed();
    }

    public boolean isMetroErrorVisible() {
        return waitForVisible(metroFieldError).isDisplayed();
    }

    public boolean isPhoneErrorVisible() {
        return waitForVisible(phoneFieldError).isDisplayed();
    }

    public boolean isConfirmModalDisplayed() {
        try {
            return waitForVisible(confirmOrderButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    //=================================================================================================================
}
