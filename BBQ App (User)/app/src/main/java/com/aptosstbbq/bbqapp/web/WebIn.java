package com.aptosstbbq.bbqapp.web;

import android.os.AsyncTask;

import com.aptosstbbq.bbqapp.util.Logger;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class WebIn extends AsyncTask<String, Integer ,String > {

    public static String defaultURL = "http://digitalrocketry.com/bbq/menu.json";

    public static interface Listener {
        public void WebInComplete(WebIn win);
    }

    List<Listener> listeners = new LinkedList<Listener>();

    public WebIn addListener(Listener l) {
        listeners.add(l);
        return this;
    }

    @Override
    public String doInBackground(String... u){
        result = read(u[0]);
        return result;
    }

    public void onPostExecute(String result) {
        for (Listener l : listeners) {
            l.WebInComplete(this);
        }
    }

    public String getResult() {
        return result;
    }

    private String result = "";

	private String read(String url) {
		InputStream in = null;
		try {
			in = new URL(url).openStream();
			return IOUtils.toString(in);
		} catch (MalformedURLException e) {
			Logger.WEB.log("Malformed url: " + url);
		} catch (IOException e) {
			Logger.WEB.log("Failed to read remote file: " + url);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					Logger.DEFAULT.log("Problem closing InputStream in WebIn:\n" + e.getStackTrace());
				}
		}
		return "";
	}
}
