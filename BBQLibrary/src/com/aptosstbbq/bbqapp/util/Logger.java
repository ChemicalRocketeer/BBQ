package com.aptosstbbq.bbqapp.util;

import java.util.LinkedList;
import java.util.List;

public class Logger {

	public static final Logger ERROR = new Logger("error");
	public static final Logger MENU_CHANGES = new Logger("menu_changes");
	public static final Logger SELL_OUT = new Logger("sell_out");
	public static final Logger WEB = new Logger("web");
	
	private static final String FILE_PREFIX = "log/", FILE_SUFFIX = ".log";

	public static interface Listener {

		public void loggerEvent(Logger log, String message);
	}

	private List<Listener> listeners = new LinkedList<>();
	private final String LOG_FILE;
	
	/**
	 * Creates a new logger with the given filename. Don't provide a file
	 * extension with the filename because it adds the file extension on its
	 * own.
	 * 
	 * @param logFile
	 */
	private Logger(String logFile) {
		LOG_FILE = FILE_PREFIX + logFile + FILE_SUFFIX;
	}

	public void log(String s) {
		ThreadedWriter.write(LOG_FILE, Utils.time() + " \t " + s, true);
	}

	public void addListener(Listener l) {
		listeners.add(l);
	}
}
