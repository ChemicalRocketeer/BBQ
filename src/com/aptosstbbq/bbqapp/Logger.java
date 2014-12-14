package com.aptosstbbq.bbqapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {

	public final String LOG_FILE;
	
	public Logger() {
		this("log.txt");
	}
	
	public Logger(String logFile) {
		LOG_FILE = logFile;
	}
	
	public void log(String s) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, true)))) {
			out.print(s + "\n");
		} catch (Exception e) {}
	}
}
