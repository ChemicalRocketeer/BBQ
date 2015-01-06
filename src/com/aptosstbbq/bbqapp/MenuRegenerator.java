package com.aptosstbbq.bbqapp;

import com.aptosstbbq.bbqapp.menu.Ingredient;
import com.aptosstbbq.bbqapp.menu.Menu;
import com.aptosstbbq.bbqapp.menu.MenuItem;
import com.aptosstbbq.bbqapp.Const;
import com.aptosstbbq.bbqapp.ThreadedWriter;
import com.aptosstbbq.bbqapp.web.WebOut;

/** Regenerates the menu, for use in development only when the menu is somehow deleted or the json is invalidated. */
public class MenuRegenerator {

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.addIngredient(new Ingredient("Bread"));
		menu.addIngredient(new Ingredient("Tri-Tip"));
		menu.addIngredient(new Ingredient("Pulled Pork"));
		menu.addIngredient(new Ingredient("Cole Slaw"));
		menu.addIngredient(new Ingredient("Beans"));
		menu.addIngredient(new Ingredient("Bleu Cheese"));
		menu.addIngredient(new Ingredient("Salad"));
		menu.addMenuItem(new MenuItem("Tri-Tip Sandwich", "14.99", "Bread", "Tri-Tip"));
		menu.addMenuItem(new MenuItem("Tri-Tip Meal", "15.99", "Bread", "Tri-Tip"));
		menu.addMenuItem(new MenuItem("Tri-Tip Salad", "14.99", "Salad", "Tri-Tip"));
		menu.addMenuItem(new MenuItem("Pulled Pork Sandwich", "14.99", "Bread", "Pulled Pork"));
		new WebOut(menu).addListener((ae) -> {
			System.out.println(((WebOut) ae.getSource()).getStatus());
		}).start();
		//WebOut.out(menu);
		ThreadedWriter.write(Const.MENU_FILE_NAME, menu.toJSON());
	}
}
