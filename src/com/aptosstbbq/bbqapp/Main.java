package com.aptosstbbq.bbqapp;

import com.aptosstbbq.bbqapp.menu.Menu;
import com.aptosstbbq.bbqapp.web.WebIn;

public class Main {
	public static void main(String[] args) {
		Menu menu = Menu.fromJSON(new WebIn().read());
		System.out.println(menu.toString());
	}
}