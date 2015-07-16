package com.vinay.restaurant;

import com.vinay.restaurant.dao.Menu;

/**
 * This class initialize all other classes and start the process
 * 
 * @author Vinay Soni
 * 
 */
public class MainClass {

	MainClass() {
		Menu menu = new Menu();
		Chef chef = new Chef();
		new Waiter(chef, menu);
	}

	public static void main(String[] args) {
		new MainClass();
	}
}
