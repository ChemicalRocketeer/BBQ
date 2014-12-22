package com.aptosstbbq.bbqapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

public class Utils {

	/** Returns the whole contents of a file as a string, including endline characters */
	public static String readFile(String path) {
		StringBuilder steve = new StringBuilder();
		try (Scanner in = new Scanner(new File(path))) {
			while (in.hasNextLine()) {
				steve.append(in.nextLine());
				//steve.append('\n');
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		return steve.toString();
	}
	
	/** Returns the current date and time as a string */
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
