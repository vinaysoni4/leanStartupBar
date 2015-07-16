package com.vinay.restaurant;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.vinay.restaurant.dao.Item;
import com.vinay.restaurant.dao.Menu;
import com.vinay.restaurant.dao.Order;
import com.vinay.restaurant.util.Utillity;

/**
 * This waiter class Takes order from customer and put it into Orderlist and
 * notify chef to prepare the order
 * 
 * @author Vinay Soni
 * 
 */
public class Waiter implements Runnable {
	Thread t;
	private static int orderNo;
	private Chef chef;
	private Menu menu;
	protected Queue<Order> OrderList = new LinkedList<Order>();

	public Waiter(Chef chef, Menu menu) {
		t = new Thread(this, "waiterss-1");
		t.start(); // started waitress thread
		this.chef = chef;
		this.menu = menu;
		chef.setWaitress(this);
	}

	// Print message to console
	private void printMessage(Item item) {
		System.out.println("OrderNumber:ORD" + orderNo + " for "
				+ item.getName() + " has been placed at "
				+ Utillity.getCurrentTime());

		chef.notifiychef();
	}

	protected void notifyWaiter() {
		synchronized (this) {
			if (t.getState().toString().contains("WAITING"))
				this.notify();
		}
	}

	@Override
	public void run() {
		while (true) {
			System.out.print("Enter Item Id(Press 0 for exit):");
			Scanner sc = new Scanner(System.in);
			int itemId = sc.nextInt();
			if (itemId == 0) {
				synchronized (this) {
					try {
						if (!OrderList.isEmpty()) {
							System.out
									.println("Please wait for few mintues to get proccessed all orders..");
							this.wait(); // wait for chef thread once orderList
											// get empty
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			}
			Item item = menu.getItem(itemId);
			if (item == null) {
				System.out
						.println("item id not present in menu, Please check Item id\n");
			} else {
				Order order = new Order(++orderNo, item);
				OrderList.add(order); // add new order to OrderList (Queue)
				printMessage(item);
			}

		}
		System.out
				.println("Thank you for being patient, All orders has been proccessed");
		System.exit(0);
		return;

	}
}
