package com.aptosstbbq.bbqapp;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.aptosstbbq.bbqapp.menu.BBQMenu;
import com.aptosstbbq.bbqapp.util.Logger;
import com.aptosstbbq.bbqapp.web.WebIn;
import com.aptosstbbq.bbqapp.web.WebOut;

public class Main {
	public static void main(String[] args) {
		final BBQMenu bBQMenu = BBQMenu.fromJSON(new WebIn().read());
		
		// Create a calendar marking the first reset
		Calendar resetCal = Calendar.getInstance();
		resetCal.set(Calendar.HOUR_OF_DAY, 0);
		resetCal.set(Calendar.MINUTE, 0);
		resetCal.set(Calendar.SECOND, 0);
		resetCal.set(Calendar.MILLISECOND, 0);
		resetCal.set(Calendar.DAY_OF_YEAR, resetCal.get(Calendar.DAY_OF_YEAR) + 1);
		// schedule the reset
		final long WAIT = resetCal.getTime().getTime() - Calendar.getInstance().getTime().getTime(); // the time between now and midnight tonight
		final long PERIOD = 1000 * 60 * 60 * 24; // 24 hours in milliseconds
		Timer resetTimer = new Timer(true);
		resetTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				bBQMenu.reset();
				WebOut.out(bBQMenu);
				Logger.DEFAULT.log("reset menu");
			}
		}, WAIT, PERIOD);
	}
}