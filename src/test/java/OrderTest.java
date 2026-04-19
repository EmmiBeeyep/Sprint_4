import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String orderButtonLocation; //
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String stationMetro;
    private final String phone;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String rentalOption;
    private final String comment;

    public OrderTest(String orderButtonLocation, String firstName, String lastName, String address,
                     String stationMetro, String phone, String deliveryDate,
                     String rentalPeriod, String rentalOption, String comment) {
        this.orderButtonLocation = orderButtonLocation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.stationMetro = stationMetro;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.rentalOption = rentalOption;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                // Первый набор данных (верхняя кнопка)
                {"top", "Иван", "Иванов", "ул. Ленина, 1", "Сокольники",
                        "89001234567", "25.12.2026", "сутки", "black", "тест"},

                // Второй набор данных (нижняя кнопка)
                {"bottom", "Мария", "Андреева", "пр. Мира, 10", "Черкизовская",
                        "89109876543", "26.12.2026", "двое суток", "grey", "тестТест"}
        };
    }

    //============================================Заказ самоката=======================================================
    @Test
    public void testSuccessfulOrder() {
        // Нажать кнопку заказа в зависимости от параметра
        mainPage.clickOrderButton(orderButtonLocation);

        // Заполнить первую форму
        orderPage.fillFirstForm(firstName, lastName, address, stationMetro, phone);
        orderPage.clickNextButton();

        // Заполнить вторую форму
        orderPage.fillSecondForm(deliveryDate, rentalPeriod, rentalOption, comment);
        orderPage.clickOrder();

        // Подтвердить заказ
        orderPage.confirmOrder(); // тесты упадут в хроме из-за кнопки "Да", в файрфоксе пройдут

        Assert.assertTrue(orderPage.isOrderSuccess());
    }
}
