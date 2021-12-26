import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.Order.PlaceOrderController;

class ValidateNameTest {
	private PlaceOrderController placceController;

	//Nguyễn Văn Dũng - 201839000
		@BeforeEach
		void setUp() throws Exception {
			placceController = new PlaceOrderController();
		}
		

		@ParameterizedTest
		@CsvSource({
			", false",
			"Nguyen Van Dung, true",
			"12Gbah, false",
			"1234567890,false"
			
		})
		public void test(String name,boolean excepted) {
			boolean isValided = placceController.validateName(name);
			assertEquals(excepted, isValided);
		}
}
