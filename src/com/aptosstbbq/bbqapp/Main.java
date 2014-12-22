package com.aptosstbbq.bbqapp;

import com.aptosstbbq.bbqapp.menu.Menu;
import com.aptosstbbq.bbqapp.menu.MenuItem;
import com.aptosstbbq.bbqapp.web.FTPOut;

public class Main {
	public static void main(String[] args) {
		String menuJSON = Utils.readFile("menu.json");
		Menu menu = Menu.fromJSON(menuJSON);
		menu.addMenuItem(new MenuItem("Pulled Pork Meal", "14", menu.getIngredient("Pulled Pork")));
		menu.toggleSoldOut("Pulled Pork");
		System.out.println("Menu written to FTP Server: " + new FTPOut().write(menu.toJSON()));
	}
}