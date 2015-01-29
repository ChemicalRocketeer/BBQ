package com.aptosstbbq.bbqapp.web;

import com.aptosstbbq.bbqapp.menu.BBQMenu;
import com.aptosstbbq.bbqapp.util.Logger;

import org.apache.commons.net.ftp.FTPClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class WebOut extends Thread {

	private String server = "www.digitalrocketry.com";
	private int port = 21;
	private String username = "u857457562.larry";
	private String password = "LarryIsRad";
	private String remotePath = "menu.json";
	private int retryCount = 4; // how many retries after a connection failure?
	private long retryWaitTime = 5000; // in ms
	private Status status = Status.IDLE;

	private final InputStream source;

    public static interface Listener {
        public void webOutEvent(WebOut wout, Status status);
    }

	private List<Listener> listeners = new LinkedList<>();

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

	public WebOut(BBQMenu bBQMenu) {
		this(bBQMenu.toJSON());
	}

	public WebOut(InputStream source) {
		this.source = source;
	}

	public WebOut(String content) {
		this(new ByteArrayInputStream(content.getBytes()));
	}

	public WebOut addListener(Listener el) {
		if (!listeners.contains(el)) {
			listeners.add(el);
		}
		return this;
	}

	public WebOut removeListener(Listener el) {
		listeners.remove(el);
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public String getServer() {
		return server;
	}

	public WebOut setServer(String server) {
		this.server = server;
		return this;
	}

	public int getPort() {
		return port;
	}

	public WebOut setPort(int port) {
		this.port = port;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public WebOut setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public WebOut setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public WebOut setRemotePath(String remotePath) {
		this.remotePath = remotePath;
		return this;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public WebOut setRetryCount(int retryCount) {
		this.retryCount = retryCount;
		return this;
	}

	public long getRetryWaitTime() {
		return retryWaitTime;
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
			success = write(this.source);
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
		for (Listener el : listeners) {
			el.webOutEvent(this, status);
		}
	}

	private boolean write(InputStream stream) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			ftpClient.login(username, password);
			ftpClient.enterLocalPassiveMode();
			boolean done = ftpClient.storeFile(remotePath, stream);
			if (done) {
				Logger.WEB.log("Transfer successful: " + remotePath);
			} else {
				Logger.WEB.log("Transfer failed: " + remotePath);
			}
			return done;
		} catch (IOException e) {
			Logger.WEB.log(e.getMessage());
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
