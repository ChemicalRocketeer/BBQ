package com.aptosstbbq.bbqapp.util;

public class Logger {

	public static final Logger DEFAULT = new Logger("log");
	public static final Logger MENU_CHANGES = new Logger("menu_changes");
	public static final Logger SELL_OUT = new Logger("sell_out");
	public static final Logger WEB = new Logger("web");

	private static final String FILE_EXTENSION = ".log";
	
	private final String LOG_FILE;
	
	/**
	 * Creates a new logger with the given filename. Don't provide a file
	 * extension with the filename because it adds the file extension on its
	 * own.
	 * 
	 * @param logFile
	 */
	private Logger(String logFile) {
		LOG_FILE = logFile + FILE_EXTENSION;
	}

	public void log(String s) {
		ThreadedWriter.write(LOG_FILE, Utils.time() + " \t " + s, true);
	}
}
