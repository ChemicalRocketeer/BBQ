package com.aptosstbbq.bbqapp.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ThreadedWriter extends Thread {

	public static void write(String fileName, String contents) {
		write(fileName, contents, false);
	}

	public static void write(String fileName, String contents, boolean append) {
		new ThreadedWriter(fileName, contents, append).start();
	}

	private final String fileName, contents;
	private final boolean append;

	private ThreadedWriter(String fileName, String contents, boolean append) {
		this.fileName = fileName;
		this.contents = contents;
		this.append = append;
	}

	public void run() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, append)));
			out.println(contents);
		} catch (Exception e) {
		} finally {
			if (out != null)
				out.close();
		}
	}
}
