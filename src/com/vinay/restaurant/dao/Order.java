package com.vinay.restaurant.dao;

/**
 * This class contains Orders
 * 
 * @author Vinay Soni
 * 
 */
public class Order {

	private int orderId;
	private Item item;

	/**
	 * This constructor Inistalize a new Order by taking OrderId and Item Object
	 * as parameters
	 * 
	 * @param orderId
	 * @param item
	 */
	public Order(int orderId, Item item) {
		this.orderId = orderId;
		this.item = item;
	}

	/**
	 * This method returns orderId
	 * 
	 * @return
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * This method returns Item
	 * 
	 * @return
	 */
	public Item getItem() {
		return item;
	}

}
