package com.vinay.restaurant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.vinay.restaurant.dao.Item;
import com.vinay.restaurant.dao.Order;
import com.vinay.restaurant.util.Utillity;

/**
 * This chef class checks OrderList and process the existing order one by one,
 * inCase If there is no order in OrderList, it waits for Waiter class to notify
 * for if Any new order comes
 * 
 * @author Vinay Soni
 * 
 */
public class Chef implements Runnable {

	Thread t;
	Waiter waiteress;
	FileOutputStream fos;
	BufferedWriter bw;
	StringBuilder output;

	Chef() {
		t = new Thread(this, "chef-1");
		output = new StringBuilder();

		try {
			File fout = new File("OrderProcessed.txt");
			fos = new FileOutputStream(fout);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	// Initialize waitress Object and start the chef thread
	protected void setWaitress(Waiter w) {
		waiteress = w;
		t.start();
	}

	// This method use to Notify Fchef thread for new order
	protected void notifiychef() {
		synchronized (this) {
			if (t.getState().toString().contains("WAITING")) {
				this.notify();
			}
		}
	}

	// Write orderProcessed details to a text file
	private void writeToFile() throws IOException {
		bw.append(output + "\n");
		bw.flush();
		output.setLength(0);
	}

	// Appends orderPickedup details in output
	private void orderpicked(Order order) {

		output.append("\nChef:Picked up ORD" + order.getOrderId() + " at "
				+ Utillity.getCurrentTime());
	}

	// Appends orderPrepration detail in output
	private void orderPreparing(Item item) {
		output.append("\nChef:Cooking " + item.getName());
	}

	// Appends order completion detail in output
	private void orderFinished(Item item, Order order) {
		output.append("\nChef:Finished making " + item.getName() + " for ORD"
				+ order.getOrderId() + " at " + Utillity.getCurrentTime());
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (waiteress.OrderList.isEmpty()) { // check if there is any order
					waiteress.notifyWaiter(); // notify Waiter thread if it's
												// waiting for exit
					synchronized (this) {
						this.wait();
					}
				} else {
					Thread.sleep(2000); // Minimum Waiting time to process new
										// order
					Order order = waiteress.OrderList.poll();
					orderpicked(order);
					Item item = order.getItem();
					orderPreparing(item);
					Thread.sleep(item.getPreprationTime() * 1000 * 60);
					orderFinished(item, order);
					writeToFile();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					bw.close();
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
