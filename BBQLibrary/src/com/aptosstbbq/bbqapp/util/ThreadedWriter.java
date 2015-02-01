package com.aptosstbbq.bbqapp.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
			// new File(fileName).mkdirs();
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, append)));
			out.println(contents);
		} catch (IOException e) {
			System.out.println("Can't save file " + fileName + "\n" + e.getMessage());
		} finally {
			if (out != null)
				out.close();
		}
	}
}
