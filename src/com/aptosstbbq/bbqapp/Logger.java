package com.aptosstbbq.bbqapp;

public class Logger {

	public static final Logger DEFAULT = new Logger("log");
	public static final Logger MENU_CHANGES = new Logger("log_menu_changes");
	public static final Logger SELL_OUT = new Logger("log_sell_out");

	private static final String FILE_EXTENSION = ".log";
	
	private final String LOG_FILE;

	public Logger() {
		this("log");
	}
	
	/**
	 * Creates a new logger with the given filename. Don't provide a file
	 * extension with the filename because it adds the file extension on its
	 * own.
	 * 
	 * @param logFile
	 */
	public Logger(String logFile) {
		LOG_FILE = logFile + FILE_EXTENSION;
	}

	public void log(String s) {
		ThreadedWriter.write(LOG_FILE, s, true);
	}
}
