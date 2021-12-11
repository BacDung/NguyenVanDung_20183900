import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidatePhoneNumberTest {
	private PlaceOrderController placceController;

	@BeforeEach
	void setUp() throws Exception {
		placceController = new PlaceOrderController();
	}
	

	@ParameterizedTest
	@CsvSource({
		"0123456789, true",
		"12344, false",
		"abc123, false",
		"1234567890,false"
		
	})
	public void test(String phone,boolean excepted) {
		boolean isValided = placceController.validatePhoneNumber(phone);
		assertEquals(excepted, isValided);
	}

}
