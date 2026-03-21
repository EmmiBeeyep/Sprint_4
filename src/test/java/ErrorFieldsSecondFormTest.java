import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ErrorFieldsSecondFormTest extends BaseTest {

    private final String date;
    private final String period;
    private final String color;
    private final String comment;

    public ErrorFieldsSecondFormTest(String date, String period, String color, String comment) {
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"", "сутки", "black", "тест"},  // нет даты
                {"03.03.2026", "", "grey", "тест"},  // нет срока
                {"", "", "black", "тест"},  // нет даты и срока
                {"", "", "", ""},  // всё пусто
        };
    }

    //=================================Ошибки для всех полей ВТОРОЙ формы заказа=======================================
    @Test
    public void testSecondFormValidation() {

        mainPage.clickOrderButton("top");

        // Заполняем первую форму валидно
        orderPage.fillFirstForm(
                "Иван", "Иванов", "Ленина 1",
                "Сокольники", "99999999999"
        );
        orderPage.clickNextButton();

        // Заполняем вторую форму
        orderPage.fillSecondForm(date, period, color, comment);

        orderPage.clickOrder();

        //текста ошибки у полей нет -> проверяем через кнопку Заказать
        Assert.assertFalse(
                "Форма прошла с невалидными данными!",
                orderPage.isConfirmModalDisplayed()
        );
    }
}