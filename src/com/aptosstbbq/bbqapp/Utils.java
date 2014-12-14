package com.aptosstbbq.bbqapp;

import java.util.Calendar;

public class Utils {

	public static String time() {
		return Calendar.getInstance().getTime().toString();
	}
	
	public static <T> boolean arrayContains(T[] array, T element) {
		for (T e : array) {
			if (element.equals(e)) return true;
		}
		return false;
	}
}
