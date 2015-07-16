package com.vinay.restaurant.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contains utility methods
 * @author Vinay Soni
 */
public class Utillity {

	/**
	 * This method return current time in 12 hours format
	 * 
	 * @return current time
	 */
	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ssa");
		Date date = new Date();
		return dateFormat.format(date).toString();
	}

}
