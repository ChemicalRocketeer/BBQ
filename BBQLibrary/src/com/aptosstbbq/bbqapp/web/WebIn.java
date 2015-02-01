package com.aptosstbbq.bbqapp.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.aptosstbbq.bbqapp.util.Logger;

public class WebIn implements Runnable {

	public static interface Listener {

		public void webInComplete(WebIn win, Status status, String result);
	}

	public static enum Status {
		IDLE, WORKING, SUCCESS, FAILURE;
	}

	private String url = "http://digitalrocketry.com/bbq/menu.json";

	private List<Listener> listeners = new LinkedList<>();
	private volatile Status status = Status.IDLE;
	private String result = null;

	public WebIn setURL(String url) {
		synchronized (this.url) {
			if (status != Status.WORKING) this.url = url;
		}
		return this;
	}

	public WebIn addListener(Listener l) {
		synchronized (listeners) {
			if (status != Status.WORKING) listeners.add(l);
		}
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public String getResult() {
		return result;
	}

	public void run() {
		status = Status.WORKING;
		InputStream in = null;
		try {
			in = new URL(url).openStream();
			result = IOUtils.toString(in, "UTF-8");
			status = Status.SUCCESS;
		} catch (MalformedURLException e) {
			Logger.WEB.log("Malformed url: " + url);
			status = Status.FAILURE;
		} catch (IOException e) {
			Logger.WEB.log("Failed to read remote file: " + url);
			status = Status.FAILURE;
		} finally {
			if (in != null) try {
				in.close();
			} catch (IOException e) {
				Logger.DEFAULT.log("Problem closing InputStream in WebIn:\n" + e.getStackTrace());
				status = Status.FAILURE;
			}
		}
		for (Listener l : listeners) {
			l.webInComplete(this, status, result);
		}
	}
}
