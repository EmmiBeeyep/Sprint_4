import org.junit.Assert;
import org.junit.Test;

public class OrderStatusTest extends BaseTest {

    //Если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет
    @Test
    public void testOrderStatus() {

        mainPage.clickOrderStatusButton();

        // Вводим номер заказа "000"
        mainPage.setOrderNumber("000");

        mainPage.clickOrderStatusGoButton();

        // Проверяем отображается изображение с ошибкой
        Assert.assertTrue(orderPage.isErrorDisplayed());
    }
}
