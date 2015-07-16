package com.vinay.restaurant.dao;

/**
 * This class contains detail of each item
 * 
 * @author Vinay Soni
 * 
 */
public class Item {

	private String name;
	private int preprationTime;

	Item(String name, int preprationTime) {
		this.name = name;
		this.preprationTime = preprationTime;
	}

	/**
	 * This method returns name of item
	 * 
	 * @return item name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method returns preparation time of item (in minutes)
	 * 
	 * @return preparation time(in minutes)
	 */
	public int getPreprationTime() {
		return preprationTime;
	}

}
