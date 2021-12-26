package controller.Order;

import entity.order.Order;

public class ShippingFee1 implements CalculateShippingFee {
	@Override
	public int calculateShippingFee(Order order) {
		int feet = order.getWeight();
		return feet;
	}

}
