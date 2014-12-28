package com.aptosstbbq.bbqapp;

import java.util.Timer;

import com.aptosstbbq.bbqapp.menu.Menu;
import com.aptosstbbq.bbqapp.web.WebIn;

public class Main {
	public static void main(String[] args) {
		final Menu menu = Menu.fromJSON(new WebIn().read());
		Timer resetTimer = new Timer();

		/*
		resetTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				menu.reset();
			}
		}, arg1, arg2);
		*/
	}
}