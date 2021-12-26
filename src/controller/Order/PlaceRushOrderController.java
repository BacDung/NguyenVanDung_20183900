package controller.Order;

import java.io.IOException;
import java.util.HashMap;

import entity.order.Order;

public class PlaceRushOrderController extends PlaceOrderController {
	
	/**
     * This method calculates the shipping fees of rush order
     * @param order
     * @param info
     * @return shippingFee
     */
	public int calculateShippingRushFee(Order order, HashMap<String, String> info)  throws InterruptedException, IOException {
		return super.calculateShippingFee(order) + getDistance(info);
	}
	
	  /**
	   * The method get distantce the info
	   * @param info
	   * @throws InterruptedException
	   * @throws IOException
	   */
	private int getDistance(HashMap<String, String> info)  throws InterruptedException, IOException {
		return 1;
		
	}
}
