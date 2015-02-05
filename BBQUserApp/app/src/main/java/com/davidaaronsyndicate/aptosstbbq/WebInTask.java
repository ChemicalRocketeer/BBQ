package com.davidaaronsyndicate.aptosstbbq;

import android.os.AsyncTask;

import com.aptosstbbq.bbqapp.web.WebIn;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aaron on 2/4/2015.
 */
public class WebInTask  extends AsyncTask<String, Integer ,String >  {
    WebIn boner = new WebIn();
    public static final String defaultURL = "http://digitalrocketry.com/bbq/menu.json";
    @Override
    public String doInBackground(String... u){
        boner.setURL(u[0]);
        boner.run();
        String result = boner.getResult();
        return result;
    }

    public void onPostExecute(String result) {
        for (Listener l : listeners) {
            l.WebInComplete(this);
        }
    }
    public static interface Listener {
        public void WebInComplete(WebInTask win);
    }
    List<Listener> listeners = new LinkedList<Listener>();
    public WebInTask addListener(Listener l) {
        listeners.add(l);
        return this;
    }
    public String getResult(){
        return boner.getResult();
    }
    public WebIn.Status getWebInStatus(){
        return boner.getStatus();
    }
}
