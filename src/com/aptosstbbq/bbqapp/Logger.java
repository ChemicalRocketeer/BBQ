package com.aptosstbbq.bbqapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {

	public static final Logger DEFAULT = new Logger("log");
	public static final Logger MENU_CHANGES = new Logger("menu_changes");
	public static final Logger SELL_OUT = new Logger("sell_out");

	public final String LOG_FILE;

	public Logger() {
		this("log");
	}
	
	/**
	 * Creates a new logger with the given filename. No need to provide a file
	 * extension with the filename because it adds the file extension on its
	 * own.
	 * 
	 * @param logFile
	 */
	public Logger(String logFile) {
		LOG_FILE = logFile + ".log";
	}

	public void log(String s) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(LOG_FILE, true)))) {
			out.println(s);
		} catch (Exception e) {
		}
	}
}
