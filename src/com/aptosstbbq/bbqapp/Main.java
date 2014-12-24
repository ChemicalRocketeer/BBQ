package com.aptosstbbq.bbqapp;

import com.aptosstbbq.bbqapp.menu.Menu;
import com.aptosstbbq.bbqapp.web.HTTPIn;

public class Main {
	public static void main(String[] args) {
		Menu menu = Menu.fromJSON(new HTTPIn().read());
		//System.out.println(menu.toString());
		System.out.println("hello");
	}
}