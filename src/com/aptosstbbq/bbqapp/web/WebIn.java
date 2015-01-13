package com.aptosstbbq.bbqapp.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.aptosstbbq.bbqapp.util.Logger;

public class WebIn {

	public String url = "http://digitalrocketry.com/bbq/menu.json";
	
	public String read() {
		try {
			InputStream in = new URL(url).openStream();
			return IOUtils.toString(in);
		} catch (MalformedURLException e) {
			Logger.WEB.log("Malformed url: " + url);
		} catch (IOException e) {
			Logger.WEB.log("Failed to read remote file: " + url);
		}
		return "";
	}
}
