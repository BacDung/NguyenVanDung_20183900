package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.PlaceOrderController;

class ValidatePhoneNumberTest {
	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	

	@Test
	void test() {
		boolean isValided = placeOrderController.validatePhoneNumber("0123456789");
		assertEquals(true, isValided);
	}

}
