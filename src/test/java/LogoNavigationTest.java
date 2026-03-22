import config.Config;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoNavigationTest extends BaseTest {

    //====================Если нажать на логотип Самоката, попадёшь на главную страницу Самоката=======================
    @Test
    public void testScooterLogoRedirectsToMainPage() {
        //переходим на страницу заказа через верхнюю кнопку Заказать
        mainPage.clickOrderButton("top");

        // Нажимаем на логотип Самоката
        mainPage.clickScooterLogo();

        Assert.assertEquals(
                Config.BASE_URL,
                mainPage.getCurrentUrl()
        );
    }

    //==============Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса(ДЗЕН)==============
    @Test
    public void testYandexLogoRedirectsToYandexPage() {
        // Запоминаем текущее окно
        String originalWindow = driver.getWindowHandle();

        // Нажимаем на логотип Яндекс
        mainPage.clickYandexLogo();

        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT))
                .until(d -> d.getWindowHandles().size() > 1);

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("dzen"));

        // Возвращаемся на страницу Самоката
        driver.switchTo().window(originalWindow);
    }
}
