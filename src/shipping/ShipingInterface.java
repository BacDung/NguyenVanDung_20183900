package shipping;

import entity.order.Order;

public interface ShipingInterface {
	public int calculateShippingFee(Order order,int wedth, int length, int heigth, int fee);
}
