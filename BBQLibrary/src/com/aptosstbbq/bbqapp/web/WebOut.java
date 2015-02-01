package com.aptosstbbq.bbqapp.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import com.aptosstbbq.bbqapp.menu.BBQMenu;
import com.aptosstbbq.bbqapp.util.Logger;

public class WebOut extends Thread {

	public static interface Listener {

		public void webOutEvent(WebOut wo);
	}

	private List<Listener> listeners = new LinkedList<Listener>();

	private String server = "www.digitalrocketry.com";
	private int port = 21;
	private String username = "u857457562.larry";
	private String password = "LarryIsRad";
	private String remotePath = "menu.json";
	private int retryCount = 4; // how many retries after a connection failure?
	private long retryWaitTime = 5000; // in ms
	private Status status = Status.IDLE;

	private final InputStream source;

	public static enum Status {
		IDLE, WORKING, ERROR, COMPLETE;
	}

	public static void out(BBQMenu bBQMenu) {
		new WebOut(bBQMenu).start();
	}

	public static void out(String content) {
		new WebOut(content).start();
	}

	public static void out(InputStream content) {
		new WebOut(content).start();
	}

	public WebOut(BBQMenu menu) {
		this(menu.toJSON());
	}

	public WebOut(InputStream source) {
		this.source = source;
	}

	/**
	 * content must be UTF-8
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public WebOut(String content) {
		InputStream src = null;
		try {
			src = new ByteArrayInputStream(content.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			Logger.WEB.log("Invalid String encoding. Strings must be UTF-8! Stack Trace:\n" + e.getMessage() + '\n' + content);
			e.printStackTrace();
		}
		source = src;
	}

	public WebOut addListener(Listener l) {
		if (!listeners.contains(l)) {
			listeners.add(l);
		}
		return this;
	}

	public WebOut removeListener(Listener l) {
		listeners.remove(l);
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public String getServer() {
		return server;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public long getRetryWaitTime() {
		return retryWaitTime;
	}

	public WebOut setServer(String server) {
		this.server = server;
		return this;
	}

	public WebOut setPort(int port) {
		this.port = port;
		return this;
	}

	public WebOut setUsername(String username) {
		this.username = username;
		return this;
	}

	public WebOut setPassword(String password) {
		this.password = password;
		return this;
	}

	public WebOut setRemotePath(String remotePath) {
		this.remotePath = remotePath;
		return this;
	}

	public WebOut setRetryCount(int retryCount) {
		this.retryCount = retryCount;
		return this;
	}

	public WebOut setRetryWaitTime(long retryWaitTime) {
		this.retryWaitTime = retryWaitTime;
		return this;
	}

	public void run() {
		int tries = 0;
		status = Status.WORKING;
		boolean success = false;
		while (!success && tries < retryCount && status != Status.ERROR) {
			source.mark(Integer.MAX_VALUE);
			success = write();
			try {
				source.reset();
			} catch (IOException e) {
				status = Status.ERROR;
			}
			tries++;
			try {
				Thread.sleep(retryWaitTime);
			} catch (InterruptedException ignored) {
			}
		}
		if (success) {
			status = Status.COMPLETE;
		} else {
			status = Status.ERROR;
		}
		for (Listener l : listeners) {
			l.webOutEvent(this);
		}
	}

	private boolean write() {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			ftpClient.login(username, password);
			ftpClient.enterLocalPassiveMode();
			boolean done = ftpClient.storeFile(remotePath, source);
			if (done) {
				Logger.WEB.log("Transfer successful: " + remotePath);
			} else {
				Logger.WEB.log("Transfer failed: " + remotePath);
			}
			return done;
		} catch (IOException e) {
			Logger.WEB.log("Could not connect to server:\n" + e.getMessage());
			return false;
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException e) {
				Logger.WEB.log(e.getMessage());
			}
		}
	}
}
