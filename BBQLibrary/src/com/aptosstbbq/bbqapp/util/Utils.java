package com.aptosstbbq.bbqapp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Utils {

	/** Writes the content to file, nothing more. */
	public static void writeFile(String path, String content) {
		writeFile(path, content, false);
	}

	/** Writes the content to file, nothing more. */
	public static void writeFile(String path, String content, boolean append) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(path, append)));
			out.print(content);
		} catch (Exception e) {
		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * Returns the whole contents of a file as a string, including endline characters. Returns null
	 * if the file does not exist.
	 */
	public static String readFile(String path) {
		StringBuilder steve = new StringBuilder();
		Scanner in = null;
		try {
			in = new Scanner(new File(path));
			while (in.hasNextLine()) {
				steve.append(in.nextLine());
				steve.append('\n');
			}
		} catch (FileNotFoundException e) {
			Logger.DEFAULT.log("Couldn't find file : " + path);
			return null;
		} finally {
			if (in != null)
				in.close();
		}
		return steve.toString();
	}

	/** Returns the current date and time as a string */
	public static String time() {
		return Calendar.getInstance().getTime().toString();
	}

	public static <T> boolean replaceFirstInstance(List<T> list, T oldInstance, T newInstance) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(oldInstance)) {
				list.set(i, newInstance);
				return true;
			}
		}
		return false;
	}

	public static <T> boolean arrayContains(T[] array, T element) {
		for (T e : array) {
			if (element.equals(e))
				return true;
		}
		return false;
	}
}
