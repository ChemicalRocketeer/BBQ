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

public class WebOut implements Runnable {

	public static interface Listener {

		public void webOutEvent(WebOut wo);
	}

	private List<Listener> listeners = new LinkedList<Listener>();

	private String server = "www.digitalrocketry.com";
	private int port = 21;
	private String username = "u857457562.larry";
	private String password = "LarryIsRad";
	private String remotePath = "menu.json";
	private int targetRetryCount = 4; // how many retries after a connection failure?
	private int tries = 0; // how many tries so far?
	private long retryWaitTime = 5000; // in ms
	private volatile Status status = Status.IDLE;

	private final InputStream source;

	public static enum Status {
		IDLE, WORKING, ERROR, SUCCESS;
	}

	public static void out(BBQMenu menu) {
		new Thread(new WebOut(menu)).start();
	}

	public static void out(String content) {
		new Thread(new WebOut(content)).start();
	}

	public static void out(InputStream content) {
		new Thread(new WebOut(content)).start();
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
		synchronized (listeners) {
			if (!listeners.contains(l)) {
				listeners.add(l);
			}
		}
		return this;
	}

	public WebOut removeListener(Listener l) {
		synchronized (listeners) {
			listeners.remove(l);
			return this;
		}
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

	public int getTargetRetryCount() {
		return targetRetryCount;
	}

	public long getRetryWaitTime() {
		return retryWaitTime;
	}

	/** Get the tries so far */
	public int getRetries() {
		return tries;
	}

	public WebOut setServer(String server) {
		if (status == Status.IDLE) {
			this.server = server;
		}
		return this;
	}

	public WebOut setPort(int port) {
		if (status == Status.IDLE) {
			this.port = port;
		}
		return this;
	}

	public WebOut setUsername(String username) {
		if (status == Status.IDLE) {
			this.username = username;
		}
		return this;
	}

	public WebOut setPassword(String password) {
		if (status == Status.IDLE) {
			this.password = password;
		}
		return this;
	}

	public WebOut setRemotePath(String remotePath) {
		if (status == Status.IDLE) {
			this.remotePath = remotePath;
		}
		return this;
	}

	public WebOut setTargetRetryCount(int retryCount) {
		if (status == Status.IDLE) {
			this.targetRetryCount = retryCount;
		}
		return this;
	}

	public WebOut setRetryWaitTime(long retryWaitTime) {
		if (status == Status.IDLE) {
			this.retryWaitTime = retryWaitTime;
		}
		return this;
	}

	public void run() {
		tries = 0;
		status = Status.WORKING;
		boolean success = false;
		while (!success && tries < targetRetryCount && status != Status.ERROR) {
			for (Listener l : listeners) {
				l.webOutEvent(this);
			}
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
			status = Status.SUCCESS;
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
