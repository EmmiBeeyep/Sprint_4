package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // ======================================DROPDOWN TEST=============================================================
    public void clickQuestion(int index) {
        WebElement element = driver.findElement(getQuestion(index));

        scrollToElement(element);
        element.click();
    }

    public String getAnswerText(int index) {
        return driver.findElement(getAnswer(index)).getText();
    }

    public boolean isAnswerDisplayed(int index) {
        return driver.findElement(getAnswer(index)).isDisplayed();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", element);
    }
    // ================================================================================================================

    // ===============================ORDER BUTTONS====================================================================
    public void clickOrderButton(String position) {
        if (position.equals("top")) {
            click(topOrderButton);
        } else {
            scrollToElement(driver.findElement(bottomOrderButton));
            click(bottomOrderButton);
        }
    }
    // ================================================================================================================

    // ===========================================LOGO NAVIGATION======================================================
    public void clickScooterLogo() {
        click(logoScooter);
    }

    public void clickYandexLogo() {
        click(logoYandex);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    // ================================================================================================================

    // ==============================================ORDER STATUS TEST=================================================
    public void clickOrderStatusButton() {
        click(orderStatusButton);
    }

    public void clickOrderStatusGoButton() {
        click(orderStatusGoButton);
    }

    public void setOrderNumber(String number) {
        type(orderNumberField, number);
    }
    // ================================================================================================================

    // ===============================ЛОКАТОРЫ=========================================================================
    //локатор вопроса о важном
    private By getQuestion(int index) {
        return By.id("accordion__heading-" + index);
    }
    //локатор ответа
    private By getAnswer(int index) {
        return By.id("accordion__panel-" + index);
    }

    //локатор верхней кнопки Заказать
    private final By topOrderButton = By.cssSelector("button[class='Button_Button__ra12g']");
    //локатор нижней кнопки Заказать
    private final By bottomOrderButton = By.xpath(".//div[@class = 'Home_ThirdPart__LSTEE']//button[text()='Заказать']");

    //локатор лого Скутер
    private final By logoScooter = By.xpath("//img[@alt='Scooter']");
    //локатор лого Яндекс
    private final By logoYandex = By.xpath("//img[@alt='Yandex']");

    //локатор кнопки Статус заказа
    private final By orderStatusButton = By.xpath("//button[contains(text(),'Статус заказа')]");
    //локатор кнопки Go
    private final By orderStatusGoButton = By.xpath("//button[normalize-space()='Go!']");
    //локатор поля для ввода номера заказа
    private final By orderNumberField = By.xpath("//input[@placeholder='Введите номер заказа']");
    //=================================================================================================================
}
