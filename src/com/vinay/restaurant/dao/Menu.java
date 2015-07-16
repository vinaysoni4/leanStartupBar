package com.vinay.restaurant.dao;

import java.util.HashMap;

/**
 * This class contains menu for restaurant
 * 
 * @author Vinay Soni
 * 
 */
public class Menu {

	HashMap<Integer, Item> menuList = new HashMap<Integer, Item>(); // to store
																	// all item
																	// in menu
																	// with
																	// itemId

	// Initialize Menu
	public Menu() {
		menuList.put(1, new Item("Sandwich", 5));
		menuList.put(2, new Item("Coffee", 3));
		menuList.put(3, new Item("Cereal", 3));
		menuList.put(4, new Item("Pizaa", 7));
	}

	/**
	 * This method returns Item for give ItemId
	 * 
	 * @param itemId
	 * @return Item
	 */
	public Item getItem(int itemId) {

		if (menuList.containsKey(itemId)) {

			return menuList.get(itemId);
		} else {
			return null; //if itemId not found in menu
		}
	}

}
