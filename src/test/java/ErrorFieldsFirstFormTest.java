import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ErrorFieldsFirstFormTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;

    private final String fieldWithError;

    public ErrorFieldsFirstFormTest(String name, String surname, String address,
                                    String metro, String phone, String fieldWithError) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.fieldWithError = fieldWithError;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"", "Иванов", "Ленина 1", "Сокольники", "99999999999", "name"},
                {"Иван", "", "Ленина 1", "Сокольники", "99999999999", "surname"},
                {"Иван", "Иванов", "", "Сокольники", "99999999999", "address"},
                {"Иван", "Иванов", "Ленина 1", "", "99999999999", "metro"},
                {"Иван", "Иванов", "Ленина 1", "Сокольники", "123", "phone"},
        };
    }

    //=================================Ошибки для всех полей ПЕРВОЙ формы заказа=======================================
    @Test
    public void testErrors() {
        mainPage.clickOrderButton("top");

        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.clickNextButton();

        checkError(fieldWithError);
    }

    private void checkError(String field) {
        switch (field) {
            case "name":
                Assert.assertTrue(orderPage.isFirstNameErrorVisible());
                break;
            case "surname":
                Assert.assertTrue(orderPage.isLastNameErrorVisible());
                break;
            case "address":
                Assert.assertTrue(orderPage.isAddressErrorVisible());
                break;
            case "metro":
                Assert.assertTrue(orderPage.isMetroErrorVisible());
                break;
            case "phone":
                Assert.assertTrue(orderPage.isPhoneErrorVisible());
                break;
        }
    }
}